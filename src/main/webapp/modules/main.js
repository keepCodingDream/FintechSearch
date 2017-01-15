/* *
 * main controller
 *
 * @author Yu Yuelin <yuyuelin@cashbus.com>
 *
 * */

'use strict';
var deps = ['ngRoute', 'ngTouch', 'ngResource', 'ngSanitize',
    require('./home/index').name
];
var app = angular.module('tracy', deps);
var confs = [require('app.route')];
function loadUp(route){
    // 初始化 route
    route.init();
}
loadUp.apply(app, confs);

angular.bootstrap(document, ['tracy'], {strictDi: true});
module.exports = app;
