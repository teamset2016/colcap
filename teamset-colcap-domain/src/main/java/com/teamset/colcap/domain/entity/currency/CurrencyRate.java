package com.teamset.colcap.domain.entity.currency;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATE")
public class CurrencyRate {

	@EmbeddedId
	private CurrencyRatePk currencyRatePk;

	@Column(name = "RATE_DT")
	private Date rateDt;

	@Column(name = "RATE")
	private BigDecimal rate;

	@Column(name = "CURR_UNIT")
	private Integer currUnit;

	public CurrencyRatePk getCurrencyRatePk() {
		return currencyRatePk;
	}

	public void setCurrencyRatePk(CurrencyRatePk currencyRatePk) {
		this.currencyRatePk = currencyRatePk;
	}

	public Date getRateDt() {
		return rateDt;
	}

	public void setRateDt(Date rateDt) {
		this.rateDt = rateDt;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getCurrUnit() {
		return currUnit;
	}

	public void setCurrUnit(Integer currUnit) {
		this.currUnit = currUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyRatePk == null) ? 0 : currencyRatePk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyRate other = (CurrencyRate) obj;
		if (currencyRatePk == null) {
			if (other.currencyRatePk != null)
				return false;
		} else if (!currencyRatePk.equals(other.currencyRatePk))
			return false;
		return true;
	}

}
