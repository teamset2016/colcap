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
