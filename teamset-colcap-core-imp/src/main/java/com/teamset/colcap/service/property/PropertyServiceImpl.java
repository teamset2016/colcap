package com.teamset.colcap.service.property;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.property.PropertyDao;
import com.teamset.colcap.domain.entity.collateral.property.Property;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Inject
	private PropertyDao propertyDao;

	@Override
	@Transactional(readOnly = true)
	public Property get(String propCode) {
		return propertyDao.get(propCode);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Property> getPropertyList(Set<String> propCodeSet) {
		return propertyDao.getPropertyList(propCodeSet);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Property> findAll() {
		return propertyDao.findAll();
	}
}
