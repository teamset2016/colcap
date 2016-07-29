package com.teamset.colcap.domain.entity.collateral.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
public class Property {

	@Id
	@Column(name = "PROP_FIELD")
	private String propField;

	@Column(name = "PROP_TYPE")
	private String propType;

	@Column(name = "PROP_DESC")
	private String propDesc;

	public String getPropField() {
		return propField;
	}

	public void setPropField(String propField) {
		this.propField = propField;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropDesc() {
		return propDesc;
	}

	public void setPropDesc(String propDesc) {
		this.propDesc = propDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propField == null) ? 0 : propField.hashCode());
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
		if (propField == null) {
			if (other.propField != null)
				return false;
		} else if (!propField.equals(other.propField))
			return false;
		return true;
	}

}
