package com.teamset.colcap.domain.entity.rule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RuleCriteriaPk implements Serializable {

	private static final long serialVersionUID = 2017272987971612305L;

	@Column(name = "RULE_ID")
	private Long ruleId;

	@Column(name = "PROP_FIELD")
	private String propField;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
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
		result = prime * result + ((propField == null) ? 0 : propField.hashCode());
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
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
		RuleCriteriaPk other = (RuleCriteriaPk) obj;
		if (propField == null) {
			if (other.propField != null)
				return false;
		} else if (!propField.equals(other.propField))
			return false;
		if (ruleId == null) {
			if (other.ruleId != null)
				return false;
		} else if (!ruleId.equals(other.ruleId))
			return false;
		return true;
	}

}
