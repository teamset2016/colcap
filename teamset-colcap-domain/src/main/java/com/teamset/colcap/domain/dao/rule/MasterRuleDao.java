package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.rule.MasterRule;

public interface MasterRuleDao extends GenericDao<MasterRule, Long> {
	List<MasterRule> findMasterRule(Long acctId);
	
}
