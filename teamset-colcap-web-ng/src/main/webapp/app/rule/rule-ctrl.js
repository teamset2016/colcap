(function() {
	'use strict';
	angular.module("colcap")
	.controller("RuleCtrl", ['$scope','$http','ruleService','$uibModal',ruleCtrl])
	
	function ruleCtrl($scope,$http,ruleService,$uibModal){
		var ctrl = this;
		ctrl.isLoading = false;

		ctrl.init = function(){	
			ctrl.isLoading = true;
			ruleService.init().then(function(result) {
				ctrl.criterias = result.data.criterias;
				console.log(ctrl.criterias)
				ctrl.operators = result.data.operators;
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
	                    },mode: function(){
	                    	return "EDIT";
	                    }
	                }
	            })
		}
		ctrl.openChartDialog = function() {
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
                    	return undefined
                    },mode: function(){
                    	return "ADD";
                    }
                }
            })
        }
		ctrl.deleteRule = function(rule) {
			ruleService.deleteRule(rule).then(function(result) {
			}).finally(function(){
			});
        }
	}
	
})();
