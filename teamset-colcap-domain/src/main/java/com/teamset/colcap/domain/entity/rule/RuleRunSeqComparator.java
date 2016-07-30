package com.teamset.colcap.domain.entity.rule;

import java.util.Comparator;

public class RuleRunSeqComparator implements Comparator<Rule> {

	@Override
	public int compare(Rule rule1, Rule rule2) {
		if ((rule1 == null || rule1.getRunSeq() == null) && (rule2 == null || rule2.getRunSeq() == null)) {
			return 0;
		} else if (rule1 == null || rule1.getRunSeq() == null) {
			return 1;
		} else if (rule2 == null || rule2.getRunSeq() == null) {
			return -1;
		} else {
			return rule1.getRunSeq().compareTo(rule2.getRunSeq());
		}
	}

}
