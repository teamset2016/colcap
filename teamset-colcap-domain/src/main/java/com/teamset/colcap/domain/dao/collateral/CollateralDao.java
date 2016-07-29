package com.teamset.colcap.domain.dao.collateral;

import java.util.List;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.rule.Rule;

public interface CollateralDao extends GenericDao<Collateral, Long> {

	List<Collateral> findEligibleColleteral(List<Rule> ruleList, Long collId);

}
