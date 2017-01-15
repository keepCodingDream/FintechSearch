/* *
 * app.route module
 *
 * @author Yu Yuelin <yuyuelin@cashbus.com>
 *
 * */

'use strict';
///////////////////////////
// Module definition
///////////////////////////
function mojo() {
	angular.module('tracy').config(route);

	route.$inject = ['$routeProvider', '$locationProvider', '$httpProvider', '$compileProvider'];
	function route($routeProvider, $locationProvider, $httpProvider, $compileProvider){

		$locationProvider.html5Mode({enabled: true, requireBase: true});
		// boost angularjs performance with debug disabled
		$compileProvider.debugInfoEnabled(false);

		var cfgWithoutLogin = {isFree: true};
	    var cfgNeedLogin = {isFree: false};
	    // Disable POST ajax request caching
	    var headers = $httpProvider.defaults.headers;
	    // headers.common['X-Requested-With'] = 'XMLHttpRequest';
	    headers.post['Cache-Control'] = 'no-cache';
	    headers.post['Pragma'] = 'no-cache';
	    // Disable PUT ajax request caching
	    headers.put['Cache-Control'] = 'no-cache';
	    headers.put['Pragma'] = 'no-cache';

	    $routeProvider
	        .when('/', {redirectTo: '/home'})
			.when('/home', {templateUrl: __uri('./home/home.html'), controller: 'homeController'})
	        .otherwise({redirectTo: '/home'});
	}
}

module.exports = { init: mojo };
