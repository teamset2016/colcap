package com.teamset.colcap.service.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.teamset.colcap.domain.dao.constant.rule.RuleConstant;
import com.teamset.colcap.domain.dao.rule.MasterRuleDao;
import com.teamset.colcap.domain.dao.rule.RuleCriteriaDao;
import com.teamset.colcap.domain.dao.rule.RuleDao;
import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;
import com.teamset.colcap.domain.entity.rule.RuleCriteriaDisplay;
import com.teamset.colcap.domain.entity.rule.RuleCriteriaPk;
import com.teamset.colcap.domain.entity.rule.RuleDisplay;

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
	@Transactional
	public void delete(Long ruleId) {
		List<Rule> rules = findRule(ruleId);

		for(Rule rule : rules){
			ruleDao.delete(rule);
			List<RuleCriteria> ruleCriterias = findRuleCriteria(ruleId);
			for(RuleCriteria ruleCriteria : ruleCriterias){
				ruleCriteriaDao.delete(ruleCriteria);
			}

		}
		
		masterRuleDao.delete(ruleId);
	}

	@Override
	@Transactional
	public void update(RuleDisplay rule) {
//		 ruleDao.update(rule);

	}

	@Override
	@Transactional
	public void add(RuleDisplay rule) {

		MasterRule masterRule = new MasterRule();
		masterRule.setRuleName(rule.getRuleName());
		masterRule.setCollType(rule.getCollType());
		masterRule.setAcctId(1L);
		masterRule.setTier(rule.getTier());
		masterRuleDao.add(masterRule);
		for (RuleCriteriaDisplay rDisplay : rule.getRuleCriterias()) {
			RuleCriteria rc = new RuleCriteria();
			rc.setOperator(rDisplay.getOperator());
			rc.setVal1(rDisplay.getValue1());
			rc.setVal2(rDisplay.getValue2());
			RuleCriteriaPk pk = new RuleCriteriaPk();
			pk.setPropField(rDisplay.getPropertyFld());
			pk.setRuleId(masterRule.getMasterRuleId());
			rc.setPk(pk);
			ruleCriteriaDao.add(rc);
		}

		Rule masterHairCutRate = new Rule();
		masterHairCutRate.setHaircutRate(rule.getHaircutRate());
		masterHairCutRate.setMasterRuleId(masterRule.getMasterRuleId());
		masterHairCutRate.setRuleType(RuleConstant.RULE_TYPE_ELIGIBILITY_RULE);
		ruleDao.add(masterHairCutRate);
		for (RuleDisplay r : rule.getHairCutRuleSets()) {
			Rule rl = new Rule();
			rl.setHaircutRate(r.getHaircutRate());
			rl.setMasterRuleId(masterRule.getMasterRuleId());
			rl.setRuleType(RuleConstant.RULE_TYPE_HAIRCUT_RULE);
			ruleDao.add(rl);
			for (RuleCriteriaDisplay rDisplay : r.getRuleCriterias()) {
				RuleCriteria rc = new RuleCriteria();
				rc.setOperator(rDisplay.getOperator());
				rc.setVal1(rDisplay.getValue1());
				rc.setVal2(rDisplay.getValue2());
				RuleCriteriaPk pk = new RuleCriteriaPk();
				pk.setPropField(rDisplay.getPropertyFld());
				pk.setRuleId(masterRule.getMasterRuleId());
				rc.setPk(pk);
				ruleCriteriaDao.add(rc);
			}
		}
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

	@Override
	@Transactional(readOnly = true)
	public RuleDisplay getRuleDisplay(Long ruleId) {

		MasterRule masterRule = masterRuleDao.get(ruleId);

		RuleDisplay ruleDisplay = new RuleDisplay();
		List<Rule> rules = findRule(ruleId);
		if (masterRule != null) {
			ruleDisplay.setCollType(masterRule.getCollType());
			ruleDisplay.setRuleName(masterRule.getRuleName());
			ruleDisplay.setCollType(masterRule.getCollType());
			ruleDisplay.setTier(masterRule.getTier());
		}
		
		if (rules != null) {
			for (Rule rule : rules) {
				if (RuleConstant.RULE_TYPE_ELIGIBILITY_RULE == rule
						.getRuleType()) {
					ruleDisplay.setHaircutRate(rule.getHaircutRate());
					List<RuleCriteria> ruleCriterias = findRuleCriteria(rule.getRuleId());
					
					Set<RuleCriteriaDisplay> lcd = new HashSet<>();
					for(RuleCriteria criteria : ruleCriterias){
						RuleCriteriaDisplay ruleCriteriaDisplay = new RuleCriteriaDisplay();
						ruleCriteriaDisplay.setOperator(criteria.getOperator());
						ruleCriteriaDisplay.setValue1(String.valueOf(criteria.getVal1()));
						ruleCriteriaDisplay.setValue2(String.valueOf(criteria.getVal2()));
						ruleCriteriaDisplay.setPropertyFld(criteria.getPk().getPropField());
						lcd.add(ruleCriteriaDisplay);
					}
					ruleDisplay.setRuleCriterias(lcd);

				} else {
					if (CollectionUtils.isEmpty(ruleDisplay
							.getHairCutRuleSets())) {
						ruleDisplay
								.setHairCutRuleSets(new HashSet<RuleDisplay>());
					}
					RuleDisplay rD = new RuleDisplay();
					Set<RuleCriteriaDisplay> lcd = new HashSet<>();
					List<RuleCriteria> ruleCriterias = findRuleCriteria(rule.getRuleId());

					rD.setHaircutRate(rule.getHaircutRate());
					for(RuleCriteria criteria : ruleCriterias){
						RuleCriteriaDisplay rcd = new RuleCriteriaDisplay();
						rcd.setOperator(criteria.getOperator());
						rcd.setValue1(String.valueOf(criteria.getVal1()));
						rcd.setValue2(String.valueOf(criteria.getVal2()));
						rcd.setPropertyFld(criteria.getPk().getPropField());
						lcd.add(rcd);
					}
					rD.setRuleCriterias(lcd);
					ruleDisplay.getHairCutRuleSets().add(rD);
				}
			}
		}
		return ruleDisplay;
	}
		
	public void add(Rule rule) {
		ruleDao.add(rule);

	}


}
