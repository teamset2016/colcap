
(function() {
	'use strict';
/**
 * @ngdoc overview
 * @name centralSg
 * @description # centralSg
 * 
 * Main module of the application.
 */
angular.module('colcap',  [ "ui.select",
	            			"datatables",
	            			,'colcap.router','colcap.icheck','colcap.pageTitle','colcap.sweetAlert','colcap.ruleService','colcap.homeService']);
})();
