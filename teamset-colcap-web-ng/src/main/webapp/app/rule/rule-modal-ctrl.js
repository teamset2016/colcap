(function() {
	'use strict';
	angular.module("colcap")
	.controller("RuleModalInstanceCtrl", ['$scope','$http','ruleService','$uibModal','criterias','operators','rule','collTypes','mode','sweetAlert','$uibModalInstance',ruleModalCtrl])
	
	function ruleModalCtrl($scope,$http,ruleService,$uibModal,criterias,operators,rule,collTypes,mode,sweetAlert,$uibModalInstance){
		var ctrl = this;
		ctrl.isLoading = false;
		ctrl.operators = operators;
		ctrl.criterias = criterias;
		ctrl.collTypes = collTypes;

		if("EDIT" == mode){
			ruleService.getRuleDetails(rule.masterRuleId).then(function(result){
				ctrl.rule = result.data.result;

			}).finally(function(){
				
			})
			ctrl.rule = rule;
			
		}else{
			ctrl.rule = {
					ruleCriterias : [],
					hairCutRuleSets : []
			};
		}
	
		ctrl.addCriteria = function(){
			ctrl.rule.ruleCriterias.push({operator : 'EQ'});
		}
		
		ctrl.deleteCriteria = function(index){
			ctrl.rule.ruleCriterias.splice(index,1);
		}
		
		ctrl.addHairCutRulesSet = function(){
			ctrl.rule.hairCutRuleSets.push({
				ruleCriterias: []}
			);
		}
		
		ctrl.addHairCutRulesCriteria = function(setIndex){
			console.log('setIndex==>' + setIndex);
			console.log(ctrl.rule.hairCutRuleSets[setIndex].ruleCriterias);
			ctrl.rule.hairCutRuleSets[setIndex].ruleCriterias.push({operator : 'EQ'});
		}
		
		ctrl.deleteHairCutRulesSet= function(index){
			ctrl.rule.hairCutRuleSets.splice(index,1);
		}

		ctrl.deleteHairCutRulesCriteria = function(setIndex,index){
			ctrl.rule.hairCutRuleSets[setIndex].ruleCriterias.splice(index,1);
		}
		
		ctrl.checkIfAnyBlankCriteriaHairCutSet = function(){
			angular.forEach(ctrl.rule.hairCutRuleSets,function(val,key){
				if(val.ruleCriterias.length <=0){
					return true;
				}
			});
			return false;
		}
		
				ctrl.getFieldTypeByPropertyField = function(propertyType){
			return $filter("filter")(ctrl.criterias, {
                propertyFld: propertyType
            }, function(actual, expected) {
                return angular.equals(actual, expected)
            })[0].propType;
		}
		ctrl.close = function(){
			$uibModalInstance.close('dismiss');
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
					swal({ 
						  title: "Success",
						   text: "Rule Successfully Created!",
						    type: "success" 
						  },
						  function(){
							  $uibModalInstance.close('success');
						});
				}).finally(function(){
					ctrl.isLoading = false;
				})
			}
		}
	}
	
})();
