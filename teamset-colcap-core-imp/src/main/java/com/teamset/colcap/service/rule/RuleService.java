package com.teamset.colcap.service.rule;

import java.util.List;

import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;
import com.teamset.colcap.domain.entity.rule.RuleDisplay;

public interface RuleService {

	Rule get(Long ruleId);

	void delete(Long ruleId);

	void update(RuleDisplay rule);

	void add(RuleDisplay rule);

	List<Rule> findRule(Long masterRuleId);

	List<MasterRule> findMasterRule(Long acctId);

	List<RuleCriteria> findRuleCriteria(Long ruleId);
	
	RuleDisplay getRuleDisplay(Long masterRuleId);

}
