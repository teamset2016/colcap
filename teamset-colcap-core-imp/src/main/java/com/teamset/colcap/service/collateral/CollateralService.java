package com.teamset.colcap.service.collateral;

import java.util.List;

import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.rule.Rule;

public interface CollateralService {

	Collateral get(Long collId);

	List<Collateral> findEligibleColleteral(List<Rule> ruleList, Long collId);

}
