var homeModule = angular.module('home', []);

homeModule.controller('homeController', ['$scope', function($scope) {
    var a = {
        title: 'title',
        content: 'content'
    };
    $scope.result = [a,a,a,a,a,a,a]
}]);

module.exports = homeModule;
