"use strict";
(function() {
    var serviceApp = angular.module("colcap.homeService", []);
    serviceApp.factory("homeService", ["$http",homeService])
    function homeService($http) {
    	 var ret = {};
    	 ret.getCollList = function() {
             return $http({
                 method: "POST",
                 url:  "coll/get-coll-list"
             })
         };
         
         ret.calcCollCapReq = function() {
             return $http({
                 method: "POST",
                 url:  "coll/calc-coll-cap-req"
             })
         };
         
         return ret;
    }
      
})();