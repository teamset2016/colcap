/*
 * icheck - Directive for custom checkbox icheck
 */
(function() {
	'use strict';
	angular
    .module('colcap.icheck',['ui.bootstrap'])
    .directive('icheck', icheck);
	
function icheck($timeout) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, element, $attrs, ngModel) {
            return $timeout(function() {
                var value;
                value = $attrs['value'];

                $scope.$watch($attrs['ngModel'], function(newValue){
                    $(element).iCheck('update');
                })

                return $(element).iCheck({
                      checkboxClass: 'icheckbox_flat-blue',
                         radioClass: 'iradio_flat-blue'

                }).on('ifChanged', function(event) {
                        if ($(element).attr('type') === 'checkbox' && $attrs['ngModel']) {
                            $scope.$apply(function() {
                                return ngModel.$setViewValue(event.target.checked);
                            });
                        }
                        if ($(element).attr('type') === 'radio' && $attrs['ngModel']) {
                            return $scope.$apply(function() {
                                return ngModel.$setViewValue(value);
                            });
                        }
                    });
            });
        }
    };
};

})();

/*
 * Page Title Directive
 * 
 */
(function() {
	'use strict';
	/*
	 *
	 * Pass all functions into module
	 */
	angular
	    .module('colcap.pageTitle',[])
	    .directive('pageTitle', pageTitle);
	
	function pageTitle($rootScope, $timeout) {
	    return {
	        link: function(scope, element) {
	            var listener = function(event, toState, toParams, fromState, fromParams) {
	                // Default title 
	                var title = 'Colcap';
	                // Create your own title pattern
	                if (toState.data && toState.data.pageTitle) title = 'Colcap | ' + toState.data.pageTitle;
	                $timeout(function() {
	                    element.text(title);
	                });
	            };
	            $rootScope.$on('$stateChangeStart', listener);
	        }
	    }
	};
})();

/*
 * sparkline - Directive for Sparkline chart
 */
(function() {
	'use strict';
	/*
	 * Pass all functions into module
	 */
	angular
	    .module('colcap.sparkline',['ui.bootstrap'])
	    .directive('sparkline', sparkline);

function sparkline() {
    return {
        restrict: 'A',
        scope: {
            sparkData: '=',
            sparkOptions: '=',
        },
        link: function (scope, element, attrs) {
            scope.$watch(scope.sparkData, function () {
                render();
            });
            scope.$watch(scope.sparkOptions, function(){
                render();
            });
            var render = function () {
                $(element).sparkline(scope.sparkData, scope.sparkOptions);
            };
        }
    };
};
})();


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
         return ret;
    }
      
})();

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
	            			,'colcap.router','colcap.icheck','colcap.pageTitle','colcap.sweetAlert','colcap.ruleService']);
})();

(function() {
'use strict';
	angular.module("colcap")
	.constant('DEBUG_MODE'/*DEBUG MODE*/,true)
	.constant('VERSION_TAG',  /*VERSION_TAG*/new Date().getTime())
	.constant('LOCALES', /*LOCACES*/{
	    'locales': {
	      'zh_cn': 'chinese',
	      'en_us': 'English'
	    },
	    'preferredLocale': 'en_us'
	})
	.constant('APP_PATH', /*APPLICATION PATH*/'/app');
})();



(function() {
	'use strict';
	angular.module(	"colcap")
	.controller("MainCtrl", ['$scope','$http',mainCtrl])
	
function mainCtrl($scope,$http){
}
	
})();

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
             }
        });
};

})();
(function() {
	'use strict';
	angular.module("colcap")
	.controller("RuleCtrl", ['$scope','$http','ruleService','$uibModal',ruleCtrl])
	
	function ruleCtrl($scope,$http,ruleService,$uibModal){
		var ctrl = this;
		ruleService.init().then(function(result) {
			ctrl.criterias = result.data.criterias;
			ctrl.operators = result.data.operators;

		});
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
                    }
                }
            })
        }
	}
	
})();

(function() {
	'use strict';
	angular.module("colcap")
	.controller("RuleModalInstanceCtrl", ['$scope','$http','ruleService','$uibModal','criterias','operators',ruleModalCtrl])
	
	function ruleModalCtrl($scope,$http,ruleService,$uibModal,criterias,operators){
		var ctrl = this;
		ctrl.operators = operators;
		ctrl.rule = {
				ruleCriterias : [],
				hairCutRuleCriterias : []
		};
		ctrl.addCriteria = function(){
			ctrl.rule.ruleCriterias.push({operator : 'EQ'});
		}
		
		ctrl.deleteCriteria = function(index){
			ctrl.rule.ruleCriterias.splice(index,1);
		}
		
		ctrl.addHairCutCriteria = function(){
			ctrl.rule.hairCutRuleCriterias.push({operator : 'EQ'});
		}
		
		ctrl.removeHairCutCriteria = function(index){
			ctrl.rule.hairCutRuleCriterias.splice(index,1);
		}
	}
	
})();

