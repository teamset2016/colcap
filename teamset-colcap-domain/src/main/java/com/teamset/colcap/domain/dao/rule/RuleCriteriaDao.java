package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;

public interface RuleCriteriaDao extends GenericDao<RuleCriteria, Long> {

	List<RuleCriteria> findRuleCriteria(Long ruleId);

}
