package com.teamset.colcap.domain.dao.collateral;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.dao.constant.property.PropertyConstant;
import com.teamset.colcap.domain.dao.constant.rule.RuleConstant;
import com.teamset.colcap.domain.dao.rule.RuleCriteriaDao;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.property.Property;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;

@Repository
public class CollateralDaoImpl extends GenericDaoImpl<Collateral, Long> implements CollateralDao {

	@Inject
	private RuleCriteriaDao ruleCriteriaDao;

	@Override
	public List<Collateral> findEligibleColleteral(Rule eligibilityRule, Map<String, Property> propertyMap) {
		return findColleteral(ruleCriteriaDao.findRuleCriteria(eligibilityRule.getRuleId()), null, propertyMap);
	}

	public List<Collateral> findColleteralForHaircut(Rule haircutRule, Set<Long> collIdSet, Map<String, Property> propertyMap) {
		return findColleteral(ruleCriteriaDao.findRuleCriteria(haircutRule.getRuleId()), collIdSet, propertyMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Collateral> findColleteral(List<RuleCriteria> ruleCriteriaList, Set<Long> collIdSet, Map<String, Property> propertyMap) {
		boolean isFirstRule = true;
		int criteriaIndex = 0;
		Map<String, Object> paramMap = new HashMap<>();
		StringBuilder querySb = new StringBuilder("from colleteral where");

		if (CollectionUtils.isNotEmpty(ruleCriteriaList)) {
			querySb.append("\n");

			if (!isFirstRule) {
				querySb.append("or ");
			} else {
				isFirstRule = false;
			}

			querySb.append("(");
			querySb.append("\n");
			boolean isFirstCriteria = true;

			for (RuleCriteria ruleCriteria : ruleCriteriaList) {
				if (!isFirstCriteria) {
					querySb.append("and ");
				} else {
					isFirstCriteria = false;
				}

				String operator = ruleCriteria.getOperator();
				String propField = ruleCriteria.getPk().getPropField();
				String propType = propertyMap.get(propField).getPropType();
				String paramKey = String.format("cri%dVal1", criteriaIndex);
				paramMap.put(paramKey, ruleCriteria.getVal1());

				if (RuleConstant.OPR_EQ.equals(operator) || RuleConstant.OPR_NE.equals(operator) || RuleConstant.OPR_GT.equals(operator) || RuleConstant.OPR_GE.equals(operator)
						|| RuleConstant.OPR_LT.equals(operator) || RuleConstant.OPR_LE.equals(operator)) {
					querySb.append(getCriteriaStr(operator, propField, propType, paramKey));
				} else {
					querySb.append(getCriteriaStr(RuleConstant.OPR_GE, propField, propType, paramKey));

					String paramKey2 = String.format("cri%dVal2", criteriaIndex);
					paramMap.put(paramKey2, ruleCriteria.getVal2());

					querySb.append("and ");
					querySb.append(getCriteriaStr(RuleConstant.OPR_LE, propField, propType, paramKey));
				}

				criteriaIndex++;
			}
			querySb.append("\n");
			querySb.append(")");
		}

		if (collIdSet != null) {
			querySb.append("\n");
			querySb.append("and collId in (:collIdSet)");
		}

		Query query = getSession().createQuery(querySb.toString());

		for (String paramKey : paramMap.keySet()) {
			query.setParameter(paramKey, paramMap.get(paramKey));
		}

		if (collIdSet != null) {
			query.setParameterList("collIdSet", collIdSet);
		}

		return query.list();
	}

	private String getCriteriaStr(String operator, String propField, String propType, String paramKey) {
		if (RuleConstant.OPR_EQ.equals(operator) || RuleConstant.OPR_NE.equals(operator) || RuleConstant.OPR_GT.equals(operator) || RuleConstant.OPR_GE.equals(operator)
				|| RuleConstant.OPR_LT.equals(operator) || RuleConstant.OPR_LE.equals(operator)) {
			String criteriaFormat;

			if ((RuleConstant.OPR_EQ.equals(operator) || RuleConstant.OPR_NE.equals(operator)) && PropertyConstant.TYPE_STR.equals(propType)) {
				criteriaFormat = " %s %s :%s ";
			} else {
				criteriaFormat = "(cast %s as int) %s :%s ";
			}

			return String.format(criteriaFormat, propField, getOperatorSymbol(operator), paramKey);
		}

		return null;
	}

	private static final String SYM_EQ = "=";

	private static final String SYM_NE = "!=";

	private static final String SYM_GT = ">";

	private static final String SYM_GE = ">=";

	private static final String SYM_LT = "<";

	private static final String SYM_LE = "<=";

	private String getOperatorSymbol(String operator) {
		switch (operator) {
		case RuleConstant.OPR_EQ:
			return SYM_EQ;
		case RuleConstant.OPR_NE:
			return SYM_NE;
		case RuleConstant.OPR_GT:
			return SYM_GT;
		case RuleConstant.OPR_GE:
			return SYM_GE;
		case RuleConstant.OPR_LT:
			return SYM_LT;
		case RuleConstant.OPR_LE:
			return SYM_LE;
		}

		return null;
	}

	// private static Set<Rule> getTestRuleList() {
	// Set<Rule> ruleSet = new HashSet<>();
	// Rule rule1 = new Rule();
	// rule1.setRuleId(1L);
	// ruleSet.add(rule1);
	// Set<RuleCriteria> rule1CriteriaSet = new HashSet<>();
	// rule1.setRuleCriteriaSet(rule1CriteriaSet);
	//
	// RuleCriteria rule1Criteria1 = new RuleCriteria();
	// rule1Criteria1.setRuleCriteriaId(1L);
	// rule1CriteriaSet.add(rule1Criteria1);
	// rule1Criteria1.setPropField("type");
	// rule1Criteria1.setOperator(RuleConstant.OPR_EQ);
	// rule1Criteria1.setVal1("10");
	// rule1Criteria1.setVal2("20");
	//
	// RuleCriteria rule1Criteria2 = new RuleCriteria();
	// rule1Criteria2.setRuleCriteriaId(2L);
	// rule1CriteriaSet.add(rule1Criteria2);
	// rule1Criteria2.setPropField("age");
	// rule1Criteria2.setOperator(RuleConstant.OPR_EQ);
	// rule1Criteria2.setVal1("10");
	// rule1Criteria2.setVal2("20");
	//
	// Rule rule2 = new Rule();
	// rule2.setRuleId(2L);
	// ruleSet.add(rule2);
	// Set<RuleCriteria> rule2CriteriaSet = new HashSet<>();
	// rule2.setRuleCriteriaSet(rule2CriteriaSet);
	//
	// RuleCriteria rule2Criteria1 = new RuleCriteria();
	// rule2Criteria1.setRuleCriteriaId(3L);
	// rule2CriteriaSet.add(rule2Criteria1);
	// rule1Criteria2.setPropField("age");
	// rule2Criteria1.setOperator(RuleConstant.OPR_BT);
	// rule2Criteria1.setVal1("10");
	// rule2Criteria1.setVal2("20");
	//
	// RuleCriteria rule2Criteria2 = new RuleCriteria();
	// rule2Criteria2.setRuleCriteriaId(4L);
	// rule2CriteriaSet.add(rule2Criteria2);
	// rule1Criteria2.setPropField("age2");
	// rule2Criteria2.setOperator(RuleConstant.OPR_LE);
	// rule2Criteria2.setVal1("10");
	// rule2Criteria2.setVal2("20");
	//
	// return ruleSet;
	// }
	//
	// public static void main(String[] args) {
	// Map<String, Property> propertyMap = new HashMap<>();
	// Property prop1 = new Property();
	// prop1.setPropField("age");
	// prop1.setPropType(PropertyConstant.TYPE_NUM);
	// propertyMap.put(prop1.getPropField(), prop1);
	// Property prop2 = new Property();
	// prop2.setPropField("type");
	// prop2.setPropType(PropertyConstant.TYPE_STR);
	// propertyMap.put(prop2.getPropField(), prop2);
	// Property prop3 = new Property();
	// prop3.setPropField("age2");
	// prop3.setPropType(PropertyConstant.TYPE_NUM);
	// propertyMap.put(prop3.getPropField(), prop3);
	//
	// Set<Rule> ruleSet = getTestRuleList();
	//
	// Set<Long> collIdSet = new HashSet<>();
	// collIdSet.add(1L);
	//
	// boolean isFirstRule = true;
	// int criteriaIndex = 0;
	// Map<String, Object> paramMap = new HashMap<>();
	// StringBuilder querySb = new StringBuilder("from colleteral where");
	//
	// for (Rule rule : ruleSet) {
	// Set<RuleCriteria> ruleCriteriaSet = rule.getRuleCriteriaSet();
	//
	// if (CollectionUtils.isNotEmpty(ruleCriteriaSet)) {
	// querySb.append("\n");
	//
	// if (!isFirstRule) {
	// querySb.append("or ");
	// } else {
	// isFirstRule = false;
	// }
	//
	// querySb.append("(");
	// querySb.append("\n");
	// boolean isFirstCriteria = true;
	//
	// for (RuleCriteria ruleCriteria : ruleCriteriaSet) {
	// if (!isFirstCriteria) {
	// querySb.append("and ");
	// } else {
	// isFirstCriteria = false;
	// }
	//
	// String operator = ruleCriteria.getOperator();
	// String propField = ruleCriteria.getPropField();
	// String propType = propertyMap.get(propField).getPropType();
	// String paramKey = String.format("cri%dVal1", criteriaIndex);
	// paramMap.put(paramKey, ruleCriteria.getVal1());
	//
	// if (RuleConstant.OPR_EQ.equals(operator) ||
	// RuleConstant.OPR_NE.equals(operator) ||
	// RuleConstant.OPR_GT.equals(operator) ||
	// RuleConstant.OPR_GE.equals(operator)
	// || RuleConstant.OPR_LT.equals(operator) ||
	// RuleConstant.OPR_LE.equals(operator)) {
	// querySb.append(getCriteriaStr(operator, propField, propType, paramKey));
	// } else {
	// querySb.append(getCriteriaStr(RuleConstant.OPR_GE, propField, propType,
	// paramKey));
	//
	// String paramKey2 = String.format("cri%dVal2", criteriaIndex);
	// paramMap.put(paramKey2, ruleCriteria.getVal2());
	//
	// querySb.append("and ");
	// querySb.append(getCriteriaStr(RuleConstant.OPR_LE, propField, propType,
	// paramKey));
	// }
	//
	// criteriaIndex++;
	// }
	// querySb.append("\n");
	// querySb.append(")");
	// }
	// }
	//
	// if (collIdSet != null) {
	// querySb.append("\n");
	// querySb.append("and collId in (:collIdSet)");
	// }
	//
	// System.out.println(querySb.toString());
	//
	// for (String paramKey : paramMap.keySet()) {
	// System.out.println(paramKey);
	// }
	//
	// if (collIdSet != null) {
	// System.out.println("collIdSet");
	// }
	// }
}
