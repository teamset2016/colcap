package com.teamset.colcap.service.collateral;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.collateral.CollateralDao;
import com.teamset.colcap.domain.entity.collateral.Collateral;
import com.teamset.colcap.domain.entity.rule.Rule;

@Service
public class CollateralServiceImpl implements CollateralService {

	@Inject
	private CollateralDao collateralDao;

	@Override
	@Transactional(readOnly = true)
	public Collateral get(Long collId) {
		return collateralDao.get(collId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Collateral> findEligibleColleteral(List<Rule> ruleList, Long collId) {
		if (CollectionUtils.isNotEmpty(ruleList)) {
			return collateralDao.findEligibleColleteral(ruleList, collId);
		} else {
			return new ArrayList<>();
		}
	}
}
