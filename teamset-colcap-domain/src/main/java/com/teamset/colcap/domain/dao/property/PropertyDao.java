package com.teamset.colcap.domain.dao.property;

import java.util.List;
import java.util.Set;

import com.teamset.colcap.domain.dao.GenericDao;
import com.teamset.colcap.domain.entity.collateral.property.Property;

public interface PropertyDao extends GenericDao<Property, String> {

	List<Property> getPropertyList(Set<String> propCodeSet);

}
