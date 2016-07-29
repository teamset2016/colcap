package com.teamset.colcap.domain.entity.currency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATE")
public class CurrencyRate implements Serializable {

	private static final long serialVersionUID = 783206070356792537L;

	@EmbeddedId
	private CurrencyRatePk pk;

	@Column(name = "RATE_DT")
	private Date rateDt;

	@Column(name = "RATE")
	private BigDecimal rate;

	@Column(name = "CURR_UNIT")
	private Integer currUnit;

	public CurrencyRatePk getPk() {
		return pk;
	}

	public void setPk(CurrencyRatePk pk) {
		this.pk = pk;
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
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

}
