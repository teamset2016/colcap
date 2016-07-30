(function() {
	'use strict';
	angular.module("colcap")
	.controller("RuleCtrl", ['$scope','$http','ruleService','$uibModal','sweetAlert',ruleCtrl])
	
	function ruleCtrl($scope,$http,ruleService,$uibModal,sweetAlert){
		var ctrl = this;
		ctrl.isLoading = false;

		ctrl.init = function(){	
			ctrl.isLoading = true;
			ruleService.init().then(function(result) {
				ctrl.criterias = result.data.criterias;
				ctrl.operators = result.data.operators;
				ctrl.masterRules = result.data.masterRules;
				ctrl.collTypes = result.data.collTypes;
			}).finally(function(){
				ctrl.isLoading = false;
			})
		}
		ctrl.init();
		ctrl.openEditChartDialog = function(rule){
			 $uibModal.open({
	                templateUrl: "app/rule/rule-modal.html",
	                size: "lg",
	                controller: "RuleModalInstanceCtrl",
	                controllerAs: "ruleModalCtrl",
	                resolve: {
	                	criterias: function() {
	                        return ctrl.criterias
	                    },
	                    operators: function() {
	                        return ctrl.operators
	                    },rule: function(){
	                    	return rule;
	                    },collTypes : function(){
	                    	return ctrl.collTypes
	                    },mode: function(){
	                    	return "EDIT";
	                    }
	                }
	            })
		}
		 ctrl.openChartDialog = function() {
			 var modalInstance = $uibModal.open({
                templateUrl: "app/rule/rule-modal.html",
                size: "lg",
                controller: "RuleModalInstanceCtrl",
                controllerAs: "ruleModalCtrl",
                resolve: {
                	criterias: function() {
                        return ctrl.criterias
                    },
                    operators: function() {
                        return ctrl.operators
                    },rule: function(){
                    	return undefined
                    },collTypes : function(){
                    	return ctrl.collTypes
                    },mode: function(){
                    	return "ADD";
                    }
                }
            })
            modalInstance.result.then(function(data) {
        		ctrl.init();
            })
        }
		ctrl.deleteRule = function(ruleId) {
			ruleService.deleteRule(ruleId).then(function(result) {	
        		ctrl.init();
        		sweetAlert.success("Rule Sucessfully Deleted!","Deleted");
			}).finally(function(){
		});
        }
	}
	
})();
