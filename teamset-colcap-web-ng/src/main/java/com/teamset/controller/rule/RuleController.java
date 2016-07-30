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
import org.springframework.web.bind.annotation.RestController;

import com.teamset.colcap.domain.entity.rule.Operator;
import com.teamset.colcap.domain.entity.rule.Rule;
import com.teamset.colcap.dto.Code;
import com.teamset.colcap.service.property.PropertyService;
import com.teamset.colcap.service.rule.RuleService;

@RestController
@RequestMapping("rule")
public class RuleController {

	@Inject
	private PropertyService propertyService;
	
	@Inject
	private RuleService ruleService;
	
	
	@RequestMapping("/init")
	public Map<String, Object> init() {
		Map<String, Object> map = new HashMap<>();
		Code code = new Code();
		code.setCode("age");
		code.setDesc("Age");
		Code code1 = new Code();
		code1.setCode("CR");
		code1.setDesc("Credit Rating");
		List<Code> codes = new ArrayList<>();
		codes.add(code);
		codes.add(code1);

		Set<Code> operators = new HashSet<>();

		for (Operator o : Operator.values()) {
			Code c = new Code();
			c.setCode(o.getOperator());
			c.setDesc(o.getDesc());
			operators.add(c);
		}
		
		
		map.put("criterias", propertyService.findAll());
		map.put("operators", operators);

		return map;
	}

	@RequestMapping(value = "/add-rule", method = RequestMethod.POST)
	public Map<String, Object> addRule(@RequestBody Rule rule) {
		Map<String, Object> map = new HashMap<>();
		ruleService.add(rule);
		return map;
	}
	@RequestMapping(value = "/update-rule", method = RequestMethod.POST)
	public Map<String, Object> updateRule(@RequestBody Rule rule) {
		Map<String, Object> map = new HashMap<>();
		ruleService.update(rule);
		return map;
	}
	@RequestMapping(value = "/delete-rule", method = RequestMethod.POST)
	public Map<String, Object> deleteRule(@RequestBody Rule rule) {
		Map<String, Object> map = new HashMap<>();
		ruleService.delete(rule);
		return map;
	}
}
