
(function() {
	'use strict';
	/*
	 * 
	 * Pass all functions into module
	 */
	angular
	    .module('colcap.sweetAlert',['ui.bootstrap'])
	    .factory('sweetAlert', sweetAlert);
	
	function sweetAlert($timeout, $window) {
		var swal = $window.swal;
		return {
			swal : function(arg1, arg2, arg3) {
				$timeout(function() {
					"function" == typeof arg2 ? swal(arg1, function(isConfirm) {
						$timeout(function() {
							arg2(isConfirm)
						})
					}, arg3) : swal(arg1, arg2, arg3)
				}, 200)
			},
			success : function(title, message) {
				$timeout(function() {
					swal(title, message, "success")
				}, 200)
			},
			error : function(title, message) {
				$timeout(function() {
					swal(title, message, "error")
				}, 200)
			},
			warning : function(title, message) {
				$timeout(function() {
					swal(title, message, "warning")
				}, 200)
			},
			info : function(title, message) {
				$timeout(function() {
					swal(title, message, "info")
				}, 200)
			}
		}
	};
	
})();
