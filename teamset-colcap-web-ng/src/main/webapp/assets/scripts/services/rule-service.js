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
         ret.deleteRule = function(rule) {
             return $http({
                 method: "POST",
                 url:  "rule/delete-rule",
                data : rule
             })
         };
         return ret;
    }
      
})();