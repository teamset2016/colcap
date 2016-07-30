package com.teamset.controller.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamset.colcap.domain.dao.constant.collateral.CollateralConstant;
import com.teamset.colcap.domain.entity.rule.Operator;
import com.teamset.colcap.domain.entity.rule.RuleDisplay;
import com.teamset.colcap.dto.Code;
import com.teamset.colcap.service.property.PropertyService;
import com.teamset.colcap.service.rule.MasterRuleService;
import com.teamset.colcap.service.rule.RuleService;

@RestController
@RequestMapping("rule")
public class RuleController {

	@Inject
	private PropertyService propertyService;
	
	@Inject
	private RuleService ruleService;
	
	@Inject
	private MasterRuleService masterRuleService;
	
	@RequestMapping("/init")
	public Map<String, Object> init() {
		Map<String, Object> map = new HashMap<>();
		Set<Code> operators = new HashSet<>();

		for (Operator o : Operator.values()) {
			Code c = new Code();
			c.setCode(o.getOperator());
			c.setDesc(o.getDesc());
			operators.add(c);
		}
		Set<String> collTypes = new HashSet<>();
		collTypes.add(CollateralConstant.COLL_TYPE_CASH);
		
		collTypes.add(CollateralConstant.COLL_TYPE_DEBT);
		collTypes.add(CollateralConstant.COLL_TYPE_EQUITY);
		collTypes.add(CollateralConstant.COLL_TYPE_METALS);
		collTypes.add(CollateralConstant.COLL_TYPE_PROPERTY);

		map.put("masterRules", masterRuleService.findMasterRule(1L));
		map.put("criterias", propertyService.findAll());
		map.put("operators", operators);
		map.put("collTypes", collTypes);

		return map;
	}

	@RequestMapping(value = "/add-rule", method = RequestMethod.POST)
	public Map<String, Object> addRule(@RequestBody RuleDisplay rule) {
		Map<String, Object> map = new HashMap<>();
		ruleService.add(rule);
		return map;
	}
	
	@RequestMapping(value = "/update-rule", method = RequestMethod.POST)
	public Map<String, Object> updateRule(@RequestBody RuleDisplay rule) {
		Map<String, Object> map = new HashMap<>();
		ruleService.update(rule);
		return map;
	}
	@RequestMapping(value = "/delete-rule", method = RequestMethod.POST)
	public Map<String, Object> deleteRule(@RequestParam Long ruleId) {
		Map<String, Object> map = new HashMap<>();
		ruleService.delete(ruleId);
		return map;
	}
	
	@RequestMapping(value = "/get-rule-details", method = RequestMethod.POST)
	public Map<String, Object> getRuleDisplay(@RequestParam Long masterRuleId) {
		Map<String, Object> map = new HashMap<>();
		map.put("rule", ruleService.getRuleDisplay(masterRuleId));
		return map;
	}
}
