"use strict";
(function() {
    var serviceApp = angular.module("colcap.ruleService", []);
    serviceApp.factory("ruleService", ["$http",ruleServices])
    function ruleServices($http) {
    	 var ret = {};
    	 ret.init = function() {
             return $http({
                 method: "POST",
                 url:  "rule/init"
             })
         };
         ret.addRule = function(rule) {
             return $http({
                 method: "POST",
                 url:  "rule/add-rule",
                  data : rule
             })
         };
         ret.updateRule = function(rule) {
             return $http({
                 method: "POST",
                 url:  "rule/update-rule",
                 data : rule

             })
         };
         ret.deleteRule = function(ruleId) {
             return $http({
                 method: "POST",
                 url:  "rule/delete-rule",
                params : {
                	ruleId :ruleId
                }
             })
         };
         ret.findRules = function(rule) {
             return $http({
                 method: "POST",
                 url:  "rule/find-rules",
                  data : rule
             })
         };
         
         ret.getRuleDetails = function(masterRuleId) {
             return $http({
                 method: "POST",
                 url:  "rule/get-rule-details",
                 params : {
                	 masterRuleId : masterRuleId
                 }
             })
         };
         return ret;
    }
      
})();