package com.teamset.colcap.domain.dao.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.entity.collateral.property.Property;

@Repository
public class PropertyDaoImpl extends GenericDaoImpl<Property, String> implements PropertyDao {

	private static final String PROP_CODE = "propCode";

	@SuppressWarnings("unchecked")
	@Override
	public List<Property> getPropertyList(Set<String> propCodeSet) {
		Criteria criteria = getSession().createCriteria(Property.class);

		if (propCodeSet == null || CollectionUtils.isNotEmpty(propCodeSet)) {
			criteria.add(Restrictions.in(PROP_CODE, propCodeSet));

			return criteria.list();
		} else {
			return new ArrayList<>();
		}

	}

}
