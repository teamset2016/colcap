/*
 * Route.js
 */

(function() {
	'use strict';
	angular
    .module('colcap.router',["ui.router", 
	            			"ncy-angular-breadcrumb"])
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });

function config($stateProvider, $urlRouterProvider,$breadcrumbProvider) {
	$urlRouterProvider.otherwise("/home/main");
	$breadcrumbProvider.setOptions({
		prefixStateName : "index.main",
		template : "bootstrap3"
	});
	$stateProvider
    .state('index', {
        abstract: true,
        url: "/home",
		templateUrl : "/app/common/template/common-tpl.html",
    })  .state("index.main", {
            url: "/main",	
            templateUrl: "/app/common/template/home-main.html",
            data: {
                pageTitle: "Home"
            },
            ncyBreadcrumb: {
                label: "Home"
            }
        }).state('rule', {
            abstract: true,
            url: "/rule",
    		templateUrl : "/app/common/template/common-tpl.html",
        }).state('rule.list', {
            url: "/rule/list",
    		templateUrl : "/app/rule/rule.html",
    		 data: {
                 pageTitle: "Rule Maintenance"
             } ,ncyBreadcrumb: {
                 label: "Rule Maintenance"
             }
        });
};

})();