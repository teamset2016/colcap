package com.teamset.colcap.service.collateral;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.collateral.CollateralDao;
import com.teamset.colcap.domain.entity.collateral.Collateral;

@Service
public class CollateralServiceImpl implements CollateralService {

	@Inject
	private CollateralDao collateralDao;

	@Override
	@Transactional(readOnly = true)
	public Collateral get(Long collId) {
		return collateralDao.get(collId);
	}
}
