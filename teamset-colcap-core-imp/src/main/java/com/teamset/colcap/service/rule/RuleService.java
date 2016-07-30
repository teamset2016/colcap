package com.teamset.colcap.service.rule;

import java.util.List;

import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;

public interface RuleService {

	Rule get(Long ruleId);

	List<Rule> findRule(Long masterRuleId);

	List<MasterRule> findMasterRule(Long acctId);

}
