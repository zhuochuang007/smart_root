require.config({
　　　　baseUrl: "scripts",
　　　　paths: {
　　　　　　jquery: "vendor/jquery-1.12.4.min",
　　　　　　bootstrap: "vendor/bootstrap.min",
        angular:"vendor/angular.min"
　　　　},
	 shim: {
	    'angular': {
	        exports: 'angular'
	    }
	 }
　　});
require(['jquery', 'bootstrap','angular'],function($, canvas, sub) {});
