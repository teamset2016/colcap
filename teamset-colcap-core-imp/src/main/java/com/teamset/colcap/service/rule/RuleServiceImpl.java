package com.teamset.colcap.service.rule;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.rule.MasterRuleDao;
import com.teamset.colcap.domain.dao.rule.RuleCriteriaDao;
import com.teamset.colcap.domain.dao.rule.RuleDao;
import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;

@Service
public class RuleServiceImpl implements RuleService {

	@Inject
	private RuleDao ruleDao;

	@Inject
	private MasterRuleDao masterRuleDao;

	@Inject
	private RuleCriteriaDao ruleCriteriaDao;

	@Override
	@Transactional(readOnly = true)
	public Rule get(Long ruleId) {
		return ruleDao.get(ruleId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rule> findRule(Long masterRuleId) {
		return ruleDao.findRule(masterRuleId);
	}

	@Override
	public void delete(Rule rule) {
		ruleDao.delete(rule);

	}

	@Override
	public void update(Rule rule) {
		ruleDao.update(rule);

	}

	@Override
	public void add(Rule rule) {
		ruleDao.add(rule);
		
	}
	@Transactional(readOnly = true)
	public List<MasterRule> findMasterRule(Long acctId) {
		return masterRuleDao.findMasterRule(acctId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RuleCriteria> findRuleCriteria(Long ruleId) {
		return ruleCriteriaDao.findRuleCriteria(ruleId);
	}

}
