package com.teamset.colcap.service.rule;

import java.util.List;

import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;

public interface RuleService {

	Rule get(Long ruleId);

	List<Rule> findRule(Long masterRuleId);

	List<MasterRule> findMasterRule(Long acctId);

	List<RuleCriteria> findRuleCriteria(Long ruleId);

}
