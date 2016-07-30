package com.teamset.colcap.domain.entity.collateral;

import com.teamset.colcap.domain.entity.rule.Rule;

public class CollateralHaircut {

	private Collateral collateral;

	private Rule haircutRule;

	public CollateralHaircut(Collateral collateral, Rule haircutRule) {
		this.collateral = collateral;
		this.haircutRule = haircutRule;
	}

	public Collateral getCollateral() {
		return collateral;
	}

	public void setCollateral(Collateral collateral) {
		this.collateral = collateral;
	}

	public Rule getHaircutRule() {
		return haircutRule;
	}

	public void setHaircutRule(Rule haircutRule) {
		this.haircutRule = haircutRule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collateral == null) ? 0 : collateral.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollateralHaircut other = (CollateralHaircut) obj;
		if (collateral == null) {
			if (other.collateral != null)
				return false;
		} else if (!collateral.equals(other.collateral))
			return false;
		return true;
	}

}
