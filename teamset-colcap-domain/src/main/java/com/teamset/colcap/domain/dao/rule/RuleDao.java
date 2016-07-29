package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.rule.Rule;

public interface RuleDao extends GenericDao<Rule, Long> {

	List<Rule> findRule(Long acctId);

}
