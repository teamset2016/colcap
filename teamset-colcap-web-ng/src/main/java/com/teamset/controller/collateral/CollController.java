package com.teamset.controller.collateral;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.CollateralHaircut;
import com.teamset.colcap.model.CollCapitalReqCalculator;
import com.teamset.colcap.service.account.AccountService;
import com.teamset.colcap.service.collateral.CollateralService;
import com.teamset.colcap.service.currency.CurrencyRateService;
import com.teamset.colcap.service.rule.RuleService;

@RestController
@RequestMapping("coll")
public class CollController {

	@Inject
	private CollateralService collateralService;

	@Inject
	private RuleService ruleService;

	@Inject
	private AccountService accountService;

	@Inject
	private CurrencyRateService currencyRateService;

	@RequestMapping(value = "/get-coll-list")
	public List<Collateral> getCollList() {
		return collateralService.findColleteral(1L);
	}

	@RequestMapping(value = "/calc-coll-cap-req")
	public Map<String, Object> calcCollCapReq() {
		Set<CollateralHaircut> collHaircutSet = collateralService.findColleteralHaircut(ruleService.findMasterRule(1L));

		return CollCapitalReqCalculator.calculate(accountService.get(1L), collHaircutSet, currencyRateService, collateralService);
	}

}
