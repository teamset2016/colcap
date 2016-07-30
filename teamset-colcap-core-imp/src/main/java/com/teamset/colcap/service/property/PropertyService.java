package com.teamset.colcap.service.property;

import java.util.List;
import java.util.Set;

import com.teamset.colcap.domain.entity.collateral.property.Property;

public interface PropertyService {

	Property get(String propCode);

	List<Property> getPropertyList(Set<String> propCodeSet);
	
	List<Property> findAll();


}
