package com.teamset.colcap.service.currency;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.currency.CurrencyRateDao;
import com.teamset.colcap.domain.entity.currency.CurrencyRate;
import com.teamset.colcap.domain.entity.currency.CurrencyRatePk;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

	@Inject
	private CurrencyRateDao currencyRateDao;

	@Override
	@Transactional(readOnly = true)
	public CurrencyRate get(String fromCurrCode, String toCurrCode) {
		return currencyRateDao.get(new CurrencyRatePk(fromCurrCode, toCurrCode));
	}

	@Override
	public BigDecimal getFxVal(BigDecimal val, String fromCurrCode, String toCurrCode) {
		if (!fromCurrCode.equals(toCurrCode)) {
			CurrencyRate currencyRate = get(fromCurrCode, toCurrCode);

			if (currencyRate != null) {
				val = val.multiply(currencyRate.getRate().multiply(new BigDecimal(currencyRate.getCurrUnit())));
			}
		}

		return val;
	}
}
