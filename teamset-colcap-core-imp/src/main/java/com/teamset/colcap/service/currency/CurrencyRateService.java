package com.teamset.colcap.service.currency;

import com.teamset.colcap.domain.entity.currency.CurrencyRate;

public interface CurrencyRateService {

	CurrencyRate get(String currencyFrom, String currencyTo);

}
