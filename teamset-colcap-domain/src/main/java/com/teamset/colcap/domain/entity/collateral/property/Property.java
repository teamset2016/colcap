package com.teamset.colcap.domain.entity.collateral.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
public class Property {

	@Id
	@Column(name = "PROP_CODE")
	private String propCode;

	@Column(name = "PROP_BEAN")
	private String propBean;

	@Column(name = "PROP_FIELD")
	private String propField;

	public String getPropCode() {
		return propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}

	public String getPropBean() {
		return propBean;
	}

	public void setPropBean(String propBean) {
		this.propBean = propBean;
	}

	public String getPropField() {
		return propField;
	}

	public void setPropField(String propField) {
		this.propField = propField;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propBean == null) ? 0 : propBean.hashCode());
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
		Property other = (Property) obj;
		if (propBean == null) {
			if (other.propBean != null)
				return false;
		} else if (!propBean.equals(other.propBean))
			return false;
		return true;
	}

}
