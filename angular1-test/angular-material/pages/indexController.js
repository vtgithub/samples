/**
 * Created by vahid on 3/10/17.
 */

var APP = angular.module('mainApp',['ngCookies']);

var BASE_URL = "http://localhost:63342/angular-material/pages/";
// var BASE_URL = "file:///home/vahid/repo/projects/application-server/client/angular-material/pages/";
var SERVICE_BASE_URL = "http://172.22.32.16:8180/management-war/";
// var APP = angular.module("KATS", ['ngCookies']).config(function ($httpProvider) {
//     $httpProvider.defaults.withCredentials = true;
//     delete $httpProvider.defaults.headers.common["X-Requested-With"];
// });

// APP.controller('mainController', function ($scope, $rootScope, $cookies, $window, $http, $factoryService) {
APP.controller('indexController', function ($scope, $rootScope) {

    $scope.mainContent = "management/management.html";
    // $scope.mainContent = "management/timePeriod/timePeriodContent.html";

    $scope.setMainContent = function(viewPageAddress){
        $scope.mainContent = viewPageAddress;
    }
});
