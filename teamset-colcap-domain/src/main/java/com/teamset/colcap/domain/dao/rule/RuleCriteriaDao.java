package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;
import com.teamset.colcap.domain.entity.rule.RuleCriteriaPk;

public interface RuleCriteriaDao extends GenericDao<RuleCriteria, RuleCriteriaPk> {

	List<RuleCriteria> findRuleCriteria(Long ruleId);

}
