package com.teamset.colcap.domain.entity.rule;

public interface BaseRuleCriteria {

	Operator getOperatorEnum();

	Comparable<String> getVal1();

	Comparable<String> getVal2();

}
