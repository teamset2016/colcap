package com.teamset.colcap.service.rule;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.rule.MasterRuleDao;
import com.teamset.colcap.domain.entity.rule.MasterRule;

@Service
public class MasterRuleServiceImpl implements MasterRuleService {

	@Inject
	private MasterRuleDao masterRuleDao;

	@Override
	@Transactional(readOnly=true)
	public List<MasterRule> findMasterRule(Long acctId) {
		return masterRuleDao.findMasterRule(acctId);
	}

	

}
