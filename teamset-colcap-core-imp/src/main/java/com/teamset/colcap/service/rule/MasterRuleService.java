package com.teamset.colcap.service.rule;

import java.util.List;

import com.teamset.colcap.domain.entity.rule.MasterRule;

public interface MasterRuleService {

	List<MasterRule> findMasterRule(Long acctId);
	
}
