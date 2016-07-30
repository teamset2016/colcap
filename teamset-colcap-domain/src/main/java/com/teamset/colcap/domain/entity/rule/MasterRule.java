package com.teamset.colcap.domain.entity.rule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MASTER_RULE")
public class MasterRule implements Serializable {

	private static final long serialVersionUID = -1037770663595666107L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MASTER_RULE_ID")
	private Long masterRuleId;

	@Column(name = "ACCT_ID")
	private Long acctId;

	@Column(name = "RULE_NAME")
	private String ruleName;

	@Column(name = "COLL_TYPE")
	private String collType;

	@Column(name = "TIER")
	private String tier;

	public Long getMasterRuleId() {
		return masterRuleId;
	}

	public void setMasterRuleId(Long masterRuleId) {
		this.masterRuleId = masterRuleId;
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

	public String getCollType() {
		return collType;
	}

	public void setCollType(String collType) {
		this.collType = collType;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((masterRuleId == null) ? 0 : masterRuleId.hashCode());
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
		MasterRule other = (MasterRule) obj;
		if (masterRuleId == null) {
			if (other.masterRuleId != null)
				return false;
		} else if (!masterRuleId.equals(other.masterRuleId))
			return false;
		return true;
	}

}
