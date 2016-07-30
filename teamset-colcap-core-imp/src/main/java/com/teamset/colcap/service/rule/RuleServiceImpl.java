package com.teamset.colcap.service.rule;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.rule.MasterRuleDao;
import com.teamset.colcap.domain.dao.rule.RuleDao;
import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;

@Service
public class RuleServiceImpl implements RuleService {

	@Inject
	private RuleDao ruleDao;

	@Inject
	private MasterRuleDao masterRuleDao;

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
	@Transactional(readOnly = true)
	public List<MasterRule> findMasterRule(Long acctId) {
		return masterRuleDao.findMasterRule(acctId);
	}

}
