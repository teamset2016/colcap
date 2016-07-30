(function() {
	'use strict';
	angular.module("colcap")
	.controller("RuleModalInstanceCtrl", ['$scope','$http','ruleService','$uibModal','criterias','operators','rule','mode',ruleModalCtrl])
	
	function ruleModalCtrl($scope,$http,ruleService,$uibModal,criterias,operators,rule,mode){
		var ctrl = this;
		ctrl.isLoading = false;
		ctrl.operators = operators;
		ctrl.criterias = criterias;
		ctrl.rule = operators;

		if("EDIT" == mode){
			ctrl.rule = rule;
			
		}else{
			ctrl.rule = {
					ruleCriterias : [],
					hairCutRuleCriterias : []
			};
		}
	
		ctrl.addCriteria = function(){
			ctrl.rule.ruleCriterias.push({operator : 'EQ'});
		}
		
		ctrl.deleteCriteria = function(index){
			ctrl.rule.ruleCriterias.splice(index,1);
		}
		
		ctrl.addHairCutCriteria = function(){
			ctrl.rule.hairCutRuleCriterias.push({operator : 'EQ'});
		}
		
		ctrl.removeHairCutCriteria = function(index){
			ctrl.rule.hairCutRuleCriterias.splice(index,1);
		}
		ctrl.getOperator = function(fld){
			if(fld != 'NUM'){
				var operators = [];
				angular.forEach(ctrl.operators,function(val,fld){
					if(val.code != 'BT'){
						operators.push(val);	
					}
				});
				return operators;
			}
			return ctrl.operators;
		}
		ctrl.getFieldTypeByPropertyField = function(propertyType){
			return $filter("filter")(ctrl.criterias, {
                propertyFld: propertyType
            }, function(actual, expected) {
                return angular.equals(actual, expected)
            })[0].propType;
		}
		
		ctrl.submit = function(){
			ctrl.isLoading = true;

			if(mode == "EDIT"){
				ruleService.updateRule(ctrl.rule).then(function(){
					
				}).finally(function(){
					ctrl.isLoading = false;
				})
				
			}else{
				ruleService.addRule(ctrl.rule).then(function(){
					
				}).finally(function(){
					ctrl.isLoading = false;
				})
				
				
			}
		
		}
	}
	
})();
