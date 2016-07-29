package com.teamset.colcap.domain.entity.currency;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CurrencyRatePk implements Serializable {

	private static final long serialVersionUID = -3938935275619078449L;

	@Column(name = "FROM_CURR_CODE")
	private String fromCurrCode;

	@Column(name = "TO_CURR_CODE")
	private String toCurrCode;

	public CurrencyRatePk(String fromCurrCode, String toCurrCode) {
		this.fromCurrCode = fromCurrCode;
		this.toCurrCode = toCurrCode;
	}

	public String getFromCurrCode() {
		return fromCurrCode;
	}

	public void setFromCurrCode(String fromCurrCode) {
		this.fromCurrCode = fromCurrCode;
	}

	public String getToCurrCode() {
		return toCurrCode;
	}

	public void setToCurrCode(String toCurrCode) {
		this.toCurrCode = toCurrCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromCurrCode == null) ? 0 : fromCurrCode.hashCode());
		result = prime * result + ((toCurrCode == null) ? 0 : toCurrCode.hashCode());
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
		CurrencyRatePk other = (CurrencyRatePk) obj;
		if (fromCurrCode == null) {
			if (other.fromCurrCode != null)
				return false;
		} else if (!fromCurrCode.equals(other.fromCurrCode))
			return false;
		if (toCurrCode == null) {
			if (other.toCurrCode != null)
				return false;
		} else if (!toCurrCode.equals(other.toCurrCode))
			return false;
		return true;
	}

}
