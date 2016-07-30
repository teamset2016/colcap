package com.teamset.colcap.domain.dao.rule;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.entity.rule.MasterRule;

@Repository
public class MasterRuleDaoImpl extends GenericDaoImpl<MasterRule, Long> implements MasterRuleDao {

	private final static String ACCT_ID = "acctId";

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterRule> findMasterRule(Long acctId) {
		Criteria criteria = getSession().createCriteria(MasterRule.class);
		criteria.add(Restrictions.eq(ACCT_ID, acctId));

		return criteria.list();
	}

}
