package com.teamset.colcap.domain.dao.collateral;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.dao.constant.property.PropertyConstant;
import com.teamset.colcap.domain.dao.constant.rule.RuleConstant;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.property.Property;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;

@Repository
public class CollateralDaoImpl extends GenericDaoImpl<Collateral, Long> implements CollateralDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Collateral> findEligibleColleteral(List<Rule> ruleList, Long collId) {
		boolean isFirstRule = true;
		int criteriaIndex = 0;
		Map<String, Object> paramMap = new HashMap<>();
		StringBuilder querySb = new StringBuilder("from colleteral where");

		if (collId != null) {
			querySb.append(" collId = :collId");
			paramMap.put("collId", collId);
		}

		for (Rule rule : ruleList) {
			Set<RuleCriteria> ruleCriteriaSet = rule.getRuleCriteriaSet();

			if (CollectionUtils.isNotEmpty(ruleCriteriaSet)) {
				querySb.append("\n");

				if (!isFirstRule) {
					querySb.append("or ");
				} else {
					isFirstRule = false;
				}

				querySb.append("(\n");
				boolean isFirstCriteria = true;

				for (RuleCriteria ruleCriteria : ruleCriteriaSet) {
					if (!isFirstCriteria) {
						querySb.append("and ");
					} else {
						isFirstCriteria = false;
					}

					String operator = ruleCriteria.getOperator();
					Property property = ruleCriteria.getPk().getProperty();
					String propField = property.getPropField();
					String propType = property.getPropType();
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
				querySb.append("\n)");
			}
		}

		Query query = getSession().createQuery(querySb.toString());

		for (String paramKey : paramMap.keySet()) {
			query.setParameter(paramKey, paramMap.get(paramKey));
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

	// private static List<Rule> getTestRuleList() {
	// List<Rule> ruleList = new ArrayList<>();
	// Rule rule1 = new Rule();
	// ruleList.add(rule1);
	// Set<RuleCriteria> rule1CriteriaSet = new HashSet<>();
	// rule1.setRuleCriteriaSet(rule1CriteriaSet);
	//
	// RuleCriteria rule1Criteria1 = new RuleCriteria();
	// rule1CriteriaSet.add(rule1Criteria1);
	// RuleCriteriaPk rule1Criteria1Pk = new RuleCriteriaPk();
	// rule1Criteria1.setPk(rule1Criteria1Pk);
	// Property rule1Criteria1PkProp = new Property();
	// rule1Criteria1Pk.setProperty(rule1Criteria1PkProp);
	// rule1Criteria1PkProp.setPropField("age");
	// rule1Criteria1PkProp.setPropType(PropertyConstant.TYPE_STR);
	// rule1Criteria1.setOperator(RuleConstant.OPR_EQ);
	// rule1Criteria1.setVal1("10");
	// rule1Criteria1.setVal2("20");
	//
	// RuleCriteria rule1Criteria2 = new RuleCriteria();
	// rule1CriteriaSet.add(rule1Criteria2);
	// RuleCriteriaPk rule1Criteria2Pk = new RuleCriteriaPk();
	// rule1Criteria2.setPk(rule1Criteria2Pk);
	// Property rule1Criteria2PkProp = new Property();
	// rule1Criteria2Pk.setProperty(rule1Criteria2PkProp);
	// rule1Criteria2PkProp.setPropField("age");
	// rule1Criteria2PkProp.setPropType(PropertyConstant.TYPE_NUM);
	// rule1Criteria2.setOperator(RuleConstant.OPR_EQ);
	// rule1Criteria2.setVal1("10");
	// rule1Criteria2.setVal2("20");
	//
	// Rule rule2 = new Rule();
	// ruleList.add(rule2);
	// Set<RuleCriteria> rule2CriteriaSet = new HashSet<>();
	// rule2.setRuleCriteriaSet(rule2CriteriaSet);
	//
	// RuleCriteria rule2Criteria1 = new RuleCriteria();
	// rule2CriteriaSet.add(rule2Criteria1);
	// RuleCriteriaPk rule2Criteria1Pk = new RuleCriteriaPk();
	// rule2Criteria1.setPk(rule2Criteria1Pk);
	// Property rule2Criteria1PkProp = new Property();
	// rule2Criteria1Pk.setProperty(rule2Criteria1PkProp);
	// rule2Criteria1PkProp.setPropField("type");
	// rule2Criteria1PkProp.setPropType(PropertyConstant.TYPE_NUM);
	// rule2Criteria1.setOperator(RuleConstant.OPR_BT);
	// rule2Criteria1.setVal1("10");
	// rule2Criteria1.setVal2("20");
	//
	// RuleCriteria rule2Criteria2 = new RuleCriteria();
	// rule2CriteriaSet.add(rule2Criteria2);
	// RuleCriteriaPk rule2Criteria2Pk = new RuleCriteriaPk();
	// rule2Criteria2.setPk(rule2Criteria2Pk);
	// Property rule2Criteria2PkProp = new Property();
	// rule2Criteria2Pk.setProperty(rule2Criteria2PkProp);
	// rule2Criteria2PkProp.setPropField("age");
	// rule2Criteria2PkProp.setPropType(PropertyConstant.TYPE_NUM);
	// rule2Criteria2.setOperator(RuleConstant.OPR_LE);
	// rule2Criteria2.setVal1("10");
	// rule2Criteria2.setVal2("20");
	//
	// return ruleList;
	// }
	//
	// public static void main(String[] args) {
	// List<Rule> ruleList = getTestRuleList();
	//
	// boolean isFirstRule = true;
	// int criteriaIndex = 0;
	// Map<String, Object> paramMap = new HashMap<>();
	// StringBuilder querySb = new StringBuilder("from colleteral where");
	//
	// for (Rule rule : ruleList) {
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
	// querySb.append("(\n");
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
	// Property property = ruleCriteria.getPk().getProperty();
	// String propField = property.getPropField();
	// String propType = property.getPropType();
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
	// querySb.append("\n)");
	// }
	// }
	//
	// System.out.println(querySb.toString());
	//
	// for (String paramKey : paramMap.keySet()) {
	// System.out.println(paramKey + " " + paramMap.get(paramKey));
	// }
	// }
}
