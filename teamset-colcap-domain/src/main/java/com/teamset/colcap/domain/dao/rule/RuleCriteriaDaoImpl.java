package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;
import com.teamset.colcap.domain.entity.rule.RuleCriteriaPk;

@Repository
public class RuleCriteriaDaoImpl extends GenericDaoImpl<RuleCriteria, RuleCriteriaPk> implements RuleCriteriaDao {

	private final static String RULE_ID = "pk.ruleId";

	@SuppressWarnings("unchecked")
	@Override
	public List<RuleCriteria> findRuleCriteria(Long ruleId) {
		Criteria criteria = getSession().createCriteria(RuleCriteria.class);
		criteria.add(Restrictions.eq(RULE_ID, ruleId));

		return criteria.list();
	}

}
