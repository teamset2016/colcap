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
