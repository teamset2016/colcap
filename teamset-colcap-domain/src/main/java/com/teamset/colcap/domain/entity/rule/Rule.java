package com.teamset.colcap.domain.entity.rule;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RULE")
public class Rule implements Serializable {

	private static final long serialVersionUID = -1037770663595666107L;

	@Id
	@Column(name = "RULE_ID")
	private Long ruleId;

	@Column(name = "ACCT_ID")
	private Long acctId;

	@Column(name = "RULE_NAME")
	private String ruleName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.rule")
	private Set<RuleCriteria> ruleCriteriaSet;

	public Set<RuleCriteria> getRuleCriteriaSet() {
		return ruleCriteriaSet;
	}

	public void setRuleCriteriaSet(Set<RuleCriteria> ruleCriteriaSet) {
		this.ruleCriteriaSet = ruleCriteriaSet;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Rule other = (Rule) obj;
		if (ruleId == null) {
			if (other.ruleId != null)
				return false;
		} else if (!ruleId.equals(other.ruleId))
			return false;
		return true;
	}

}
