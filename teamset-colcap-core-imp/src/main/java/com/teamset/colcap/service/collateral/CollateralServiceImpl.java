package com.teamset.colcap.service.collateral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.collateral.CollateralDao;
import com.teamset.colcap.domain.dao.constant.rule.RuleConstant;
import com.teamset.colcap.domain.dao.property.PropertyDao;
import com.teamset.colcap.domain.dao.rule.RuleDao;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.CollateralHaircut;
import com.teamset.colcap.domain.entity.collateral.property.Property;
import com.teamset.colcap.domain.entity.rule.MasterRule;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleRunSeqComparator;

@Service
public class CollateralServiceImpl implements CollateralService {

	@Inject
	private CollateralDao collateralDao;

	@Inject
	private RuleDao ruleDao;

	@Inject
	private PropertyDao propertyDao;

	@Override
	@Transactional(readOnly = true)
	public Collateral get(Long collId) {
		return collateralDao.get(collId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Collateral> findColleteral(Long acctId, Set<Long> collIdSet) {
		return findColleteral(acctId, collIdSet);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<CollateralHaircut> findColleteralHaircut(List<MasterRule> masterRuleList, Long collId) {
		Set<CollateralHaircut> collHaircutSet = new HashSet<>();
		Map<String, Property> propertyMap = getPropertyMap();
		RuleRunSeqComparator ruleRunSeqComparator = new RuleRunSeqComparator();

		for (MasterRule masterRule : masterRuleList) {
			List<Rule> ruleList = ruleDao.findRule(masterRule.getMasterRuleId());

			if (CollectionUtils.isNotEmpty(ruleList)) {
				Rule eligibilityRule = null;
				List<Rule> haircutRuleList = new ArrayList<>();

				for (Rule rule : ruleList) {
					if (eligibilityRule == null && RuleConstant.RULE_TYPE_ELIGIBILITY_RULE == rule.getRuleType()) {
						eligibilityRule = rule;
					} else if (RuleConstant.RULE_TYPE_HAIRCUT_RULE == rule.getRuleType()) {
						haircutRuleList.add(rule);
					}
				}

				if (eligibilityRule != null) {
					List<Collateral> eligibleCollList = collateralDao.findEligibleColleteral(eligibilityRule, propertyMap);

					if (CollectionUtils.isNotEmpty(eligibleCollList)) {
						Map<Long, Collateral> eligibleCollMap = new HashMap<>();

						for (Collateral coll : eligibleCollList) {
							eligibleCollMap.put(coll.getCollId(), coll);
						}

						if (CollectionUtils.isNotEmpty(haircutRuleList)) {
							Collections.sort(haircutRuleList, ruleRunSeqComparator);
							outer: for (Rule haircutRule : haircutRuleList) {
								List<Collateral> collList = collateralDao.findColleteralForHaircut(haircutRule, eligibleCollMap.keySet(), propertyMap);

								for (Collateral coll : collList) {
									collHaircutSet.add(new CollateralHaircut(coll, haircutRule));
									eligibleCollMap.remove(coll.getCollId());

									if (CollectionUtils.isEmpty(eligibleCollMap.keySet())) {
										break outer;
									}
								}
							}
						}

						for (Long eligibleCollId : eligibleCollMap.keySet()) {
							collHaircutSet.add(new CollateralHaircut(eligibleCollMap.get(eligibleCollId), eligibilityRule));
						}
					}
				}
			}
		}

		return collHaircutSet;
	}

	private Map<String, Property> getPropertyMap() {
		List<Property> propertyList = propertyDao.findAll();
		Map<String, Property> propertyMap = new HashMap<>();

		for (Property property : propertyList) {
			propertyMap.put(property.getPropField(), property);
		}

		return propertyMap;
	}

}
