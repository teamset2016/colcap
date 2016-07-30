package com.teamset.colcap.service.currency;

import java.math.BigDecimal;

import com.teamset.colcap.domain.entity.currency.CurrencyRate;

public interface CurrencyRateService {

	CurrencyRate get(String fromCurrCode, String toCurrCode);

	BigDecimal getFxVal(BigDecimal val, String fromCurrCode, String toCurrCode);

}
