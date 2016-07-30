package com.teamset.colcap.domain.entity.rule;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RULE")
public class Rule implements Serializable {

	private static final long serialVersionUID = -1037770663595666107L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RULE_ID")
	private Long ruleId;

	@Column(name = "MASTER_RULE_ID")
	private Long masterRuleId;

	@Column(name = "RULE_TYPE")
	private Character ruleType;

	@Column(name = "HAIRCUT_RATE")
	private BigDecimal haircutRate;

	@Column(name = "FX_HAIRCUT_RATE")
	private BigDecimal fxHaircutRate;

	@Column(name = "RUN_SEQ")
	private Integer runSeq;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getMasterRuleId() {
		return masterRuleId;
	}

	public void setMasterRuleId(Long masterRuleId) {
		this.masterRuleId = masterRuleId;
	}

	public Character getRuleType() {
		return ruleType;
	}

	public void setRuleType(Character ruleType) {
		this.ruleType = ruleType;
	}

	public BigDecimal getHaircutRate() {
		return haircutRate;
	}

	public void setHaircutRate(BigDecimal haircutRate) {
		this.haircutRate = haircutRate;
	}

	public BigDecimal getFxHaircutRate() {
		return fxHaircutRate;
	}

	public void setFxHaircutRate(BigDecimal fxHaircutRate) {
		this.fxHaircutRate = fxHaircutRate;
	}

	public Integer getRunSeq() {
		return runSeq;
	}

	public void setRunSeq(Integer runSeq) {
		this.runSeq = runSeq;
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
