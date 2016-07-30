

(function() {
	'use strict';
	angular.module(	"colcap")
	.controller("MainCtrl", ['$scope','$http','homeService','DTOptionsBuilder',mainCtrl])
	
function mainCtrl($scope,$http,homeService,DTOptionsBuilder){
	var ctrl = this;
	ctrl.triggered = false;
	ctrl.dtOptions = DTOptionsBuilder.newOptions().withDisplayLength(50);
	
	homeService.getCollList().then(function(response){
		ctrl.collList = response.data;
	});
	
	ctrl.trigger = function() {
		ctrl.triggered = true;
		
		homeService.calcCollCapReq().then(function(response){
			ctrl.result = response.data;
		});
	}

}
	
})();
