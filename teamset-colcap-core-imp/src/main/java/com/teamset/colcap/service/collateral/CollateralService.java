package com.teamset.colcap.service.collateral;

import java.util.List;
import java.util.Set;

import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.collateral.CollateralHaircut;
import com.teamset.colcap.domain.entity.rule.MasterRule;

public interface CollateralService {

	Collateral get(Long collId);

	List<Collateral> findColleteral(Long acctId, Set<Long> collIdSet);

	Set<CollateralHaircut> findColleteralHaircut(List<MasterRule> masterRuleList, Long collId);

}
