package com.teamset.colcap.domain.entity.rule;

public class RuleCriteriaDisplay {

	private String propertyFld;

	private String operator;

	private String value1;

	private String value2;

	public String getOperator() {
		return operator;
	}

	public String getPropertyFld() {
		return propertyFld;
	}

	public void setPropertyFld(String propertyFld) {
		this.propertyFld = propertyFld;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
