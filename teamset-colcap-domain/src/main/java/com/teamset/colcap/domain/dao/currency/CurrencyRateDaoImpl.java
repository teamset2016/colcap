package com.teamset.colcap.domain.dao.currency;

import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.entity.currency.CurrencyRate;
import com.teamset.colcap.domain.entity.currency.CurrencyRatePk;

@Repository
public class CurrencyRateDaoImpl extends GenericDaoImpl<CurrencyRate, CurrencyRatePk> implements CurrencyRateDao {

}
