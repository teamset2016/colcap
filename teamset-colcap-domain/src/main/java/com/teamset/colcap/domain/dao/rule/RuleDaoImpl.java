package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.entity.rule.Rule;

@Repository
public class RuleDaoImpl extends GenericDaoImpl<Rule, Long> implements RuleDao {

	private final static String MASTER_RULE_ID = "masterRuleId";

	@SuppressWarnings("unchecked")
	@Override
	public List<Rule> findRule(Long masterRuleId) {
		Criteria criteria = getSession().createCriteria(Rule.class);
		criteria.add(Restrictions.eq(MASTER_RULE_ID, masterRuleId));

		return criteria.list();
	}

}
