package com.teamset.colcap.domain.entity.rule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RULE_CRITERIA")
public class RuleCriteria implements Serializable, BaseRuleCriteria {

	private static final long serialVersionUID = -3283572426068271548L;

	@EmbeddedId
	private RuleCriteriaPk pk;

	@Column(name = "OPERATOR")
	private String operator;

	@Column(name = "VAL_TYPE")
	private String valueType;

	@Column(name = "VAL1")
	private String val1;

	@Column(name = "VAL2")
	private String val2;

	public RuleCriteriaPk getPk() {
		return pk;
	}

	public void setPk(RuleCriteriaPk pk) {
		this.pk = pk;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

	public void setVal2(String val2) {
		this.val2 = val2;
	}

	@Override
	public Operator getOperatorEnum() {
		// TODO Auto-generated method stub
		return Operator.valueOf(this.operator.toUpperCase());
	}

	@Override
	public Comparable<String> getVal1() {
		return val1;
	}

	@Override
	public Comparable<String> getVal2() {
		return val2;
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
		RuleCriteria other = (RuleCriteria) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

}
