package com.teamset.colcap.domain.dao.collateral;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.property.Property;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.domain.entity.rule.RuleCriteria;

public interface CollateralDao extends GenericDao<Collateral, Long> {

	List<Collateral> findColleteral(Long acctId, Set<Long> collIdSet);

	List<Collateral> findEligibleColleteral(Rule eligibilityRule, String collType, Map<String, Property> propertyMap);

	List<Collateral> findColleteralForHaircut(Rule haircutRule, String collType, Set<Long> collIdSet, Map<String, Property> propertyMap);

	List<Collateral> findColleteral(List<RuleCriteria> ruleCriteriaList, String collType, Set<Long> collIdSet, Map<String, Property> propertyMap);

}
