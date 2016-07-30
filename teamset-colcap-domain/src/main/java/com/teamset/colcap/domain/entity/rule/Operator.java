package com.teamset.colcap.domain.entity.rule;

public enum Operator {

	EQ("EQ", "Equal"),

	NE("NE", "Not Equal"),

	GT("GT", "Greater Than"),

	GE("GE", "Greater Equal"),

	LT("LT", "Less Than"),
	
	BT("BT", "Between"),

	LE("LE", "Less Than Equal");

	private String operator;

	private String desc;

	private Operator(String operator, String desc) {
		this.operator = operator;
		this.desc = desc;
	}

	public String getOperator() {
		return operator;
	}
	
	public String getDesc() {
		return desc;
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> boolean evaluate(Comparable<T> value1,
			Comparable<T> value2) {
		if (this == GT) {
			return value1.compareTo((T) value2) == 0;
		} else if (this == GE) {
			return value1.compareTo((T) value2) >= 0;
		} else if (this == LT) {
			return value1.compareTo((T) value2) < 0;
		} else if (this == LE) {
			return value1.compareTo((T) value2) <= 0;
		} else if (this == EQ) {
			return value1.compareTo((T) value2) == 0;
		} else if (this == NE) {
			return value1.compareTo((T) value2) != 0;
		}
		return false;
	}

}
