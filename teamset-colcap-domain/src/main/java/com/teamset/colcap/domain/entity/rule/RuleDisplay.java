package com.teamset.colcap.domain.entity.rule;

import java.math.BigDecimal;
import java.util.Set;

public class RuleDisplay {

	private String ruleId;

	private String collType;

	private String ruleName;

	private BigDecimal haircutRate;

	private String tier;

	private Set<RuleCriteriaDisplay> ruleCriterias;

	private Set<RuleDisplay> hairCutRuleSets;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getCollType() {
		return collType;
	}

	public void setCollType(String collType) {
		this.collType = collType;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public BigDecimal getHaircutRate() {
		return haircutRate;
	}

	public void setHaircutRate(BigDecimal haircutRate) {
		this.haircutRate = haircutRate;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public Set<RuleCriteriaDisplay> getRuleCriterias() {
		return ruleCriterias;
	}

	public void setRuleCriterias(Set<RuleCriteriaDisplay> ruleCriterias) {
		this.ruleCriterias = ruleCriterias;
	}

	public Set<RuleDisplay> getHairCutRuleSets() {
		return hairCutRuleSets;
	}

	public void setHairCutRuleSets(Set<RuleDisplay> hairCutRuleSets) {
		this.hairCutRuleSets = hairCutRuleSets;
	}

}
