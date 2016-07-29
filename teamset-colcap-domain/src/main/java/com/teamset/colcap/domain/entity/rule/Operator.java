package com.teamset.colcap.domain.entity.rule;

public enum Operator {

	EQ("EQ"),

	NE("NE"),

	GT("GT"),

	GE("GE"),

	LT("LT"),

	LE("LE"),

	BT("BT");

	private String operator;

	private Operator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public <T extends Comparable<T>> boolean evaluate(T val, T targetVal) {
		if (this == GT) {
			return val.compareTo(targetVal) > 0;
		} else if (this == GE) {
			return val.compareTo(targetVal) >= 0;
		} else if (this == LT) {
			return val.compareTo(targetVal) < 0;
		} else if (this == LE) {
			return val.compareTo(targetVal) <= 0;
		} else if (this == EQ) {
			return val.compareTo(targetVal) == 0;
		} else if (this == NE) {
			return val.compareTo(targetVal) != 0;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> boolean evaluate(T val, T fromVal, T toVal) {
		if (this == BT) {
			return val.compareTo(fromVal) >= 0 && val.compareTo(toVal) <= 0;
		}

		return false;
	}

}
