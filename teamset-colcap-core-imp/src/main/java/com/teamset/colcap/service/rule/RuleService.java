package com.teamset.colcap.service.rule;

import java.util.List;

import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;

public interface RuleService {

	Rule get(Long ruleId);

	void delete(Rule rule);

	void update(Rule rule);

	void add(Rule rule);

	List<Rule> findRule(Long acctId);

	List<MasterRule> findMasterRule(Long acctId);

}
