package com.teamset.colcap.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teamset.colcap.domain.dao.constant.collateral.CollateralConstant;
import com.teamset.colcap.domain.entity.account.Account;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.CollateralHaircut;
import com.teamset.colcap.domain.entity.currency.CurrencyRate;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.service.collateral.CollateralService;
import com.teamset.colcap.service.currency.CurrencyRateService;

public class CollCapitalReqCalculator {

	public static Map<String, Object> calculate(Account account, Set<CollateralHaircut> collHaircutSet, CurrencyRateService currencyRateService, CollateralService collateralService) {
		BigDecimal totalCapitalReq = BigDecimal.ZERO;
		BigDecimal totalExp = BigDecimal.ZERO;
		BigDecimal totalEligibleColl = BigDecimal.ZERO;
		BigDecimal totalEquity = BigDecimal.ZERO;
		BigDecimal totalCash = BigDecimal.ZERO;
		BigDecimal totalMetals = BigDecimal.ZERO;
		BigDecimal totalDebt = BigDecimal.ZERO;
		BigDecimal totalProperty = BigDecimal.ZERO;
		Map<String, CurrencyRate> toBaseCurrRateCacheMap = new HashMap<>();
		Set<Long> eligibleCollIdSet = new HashSet<>();
		String baseCurrCode = account.getBaseCurrCode();

		for (CollateralHaircut collHaircut : collHaircutSet) {
			Rule haircutRule = collHaircut.getHaircutRule();
			Collateral coll = collHaircut.getCollateral();
			BigDecimal currentExpVal = coll.getExpSecured().multiply(getToBaseRate(coll.getExpCurrCode(), baseCurrCode, toBaseCurrRateCacheMap, currencyRateService));
			BigDecimal currentCollVal = coll.getCollVal().multiply(getToBaseRate(coll.getCollValCurrCode(), baseCurrCode, toBaseCurrRateCacheMap, currencyRateService));
			BigDecimal fxHaircutPct = coll.getCollValCurrCode().equals(coll.getExpCurrCode()) ? BigDecimal.ZERO : haircutRule.getFxHaircutRate().divide(new BigDecimal(100));
			totalCapitalReq = totalCapitalReq.add(calculate(currentExpVal, currentCollVal, haircutRule.getHaircutRate().divide(new BigDecimal(100)), fxHaircutPct));

			switch (coll.getCollType()) {
			case CollateralConstant.COLL_TYPE_EQUITY:
				totalEquity = totalEquity.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_CASH:
				totalCash = totalCash.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_METALS:
				totalMetals = totalMetals.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_DEBT:
				totalDebt = totalDebt.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_PROPERTY:
				totalProperty = totalProperty.add(currentCollVal);
				break;
			}

			totalEligibleColl = totalEligibleColl.add(currentCollVal);
			totalExp = totalExp.add(currentExpVal);
			eligibleCollIdSet.add(coll.getCollId());
		}

		List<Collateral> nonEligibleCollList = collateralService.findColleteral(account.getAcctId(), eligibleCollIdSet);
		BigDecimal totalNonEligibleColl = BigDecimal.ZERO;

		for (Collateral coll : nonEligibleCollList) {
			BigDecimal currentExpVal = coll.getExpSecured().multiply(getToBaseRate(coll.getExpCurrCode(), baseCurrCode, toBaseCurrRateCacheMap, currencyRateService));
			BigDecimal currentCollVal = coll.getCollVal().multiply(getToBaseRate(coll.getCollValCurrCode(), baseCurrCode, toBaseCurrRateCacheMap, currencyRateService));

			switch (coll.getCollType()) {
			case CollateralConstant.COLL_TYPE_EQUITY:
				totalEquity = totalEquity.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_CASH:
				totalCash = totalCash.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_METALS:
				totalMetals = totalMetals.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_DEBT:
				totalDebt = totalDebt.add(currentCollVal);
				break;
			case CollateralConstant.COLL_TYPE_PROPERTY:
				totalProperty = totalProperty.add(currentCollVal);
				break;
			}

			totalNonEligibleColl = totalNonEligibleColl.add(currentCollVal);
			totalExp = totalExp.add(currentExpVal);
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("currencyCode", baseCurrCode);
		resultMap.put("totalExp", totalExp);
		resultMap.put("totalEquity", totalEquity);
		resultMap.put("totalCash", totalCash);
		resultMap.put("totalMetals", totalMetals);
		resultMap.put("totalDebt", totalDebt);
		resultMap.put("totalProperty", totalProperty);
		resultMap.put("totalEligibleColl", totalEligibleColl);
		resultMap.put("totalEligibleCount", collHaircutSet.size());
		resultMap.put("totalNonEligibleColl", totalNonEligibleColl);
		resultMap.put("totalNonEligibleCount", nonEligibleCollList.size());
		resultMap.put("totalCapitalReq", totalCapitalReq);

		return resultMap;
	}

	private static BigDecimal getToBaseRate(String fromCurrCode, String baseCurrCode, Map<String, CurrencyRate> toBaseCurrRateCacheMap, CurrencyRateService currencyRateService) {
		if (!baseCurrCode.equals(fromCurrCode)) {
			CurrencyRate currencyRate = toBaseCurrRateCacheMap.get(fromCurrCode);

			if (currencyRate == null) {
				currencyRate = currencyRateService.get(fromCurrCode, baseCurrCode);

				if (currencyRate != null) {
					toBaseCurrRateCacheMap.put(fromCurrCode, currencyRate);

					return currencyRate.getRate().multiply(new BigDecimal(currencyRate.getCurrUnit()));
				}
			}
		}

		return BigDecimal.ONE;
	}

	private static BigDecimal calculate(BigDecimal currentExpVal, BigDecimal currentCollVal, BigDecimal haircutPct, BigDecimal fxHaircutPct) {
		BigDecimal capReq = currentExpVal.multiply(BigDecimal.ONE.add(haircutPct)).subtract(currentCollVal.multiply(BigDecimal.ONE.subtract(haircutPct).subtract(fxHaircutPct)));

		return BigDecimal.ZERO.compareTo(capReq) >= 0 ? BigDecimal.ZERO : capReq;
	}

}