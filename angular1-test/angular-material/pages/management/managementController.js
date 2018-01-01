APP.controller('managementController', function ($scope, $rootScope) {

	$scope.welcomMessage = "welcom to management console";

    $scope.setManagementContent = function(viewPageAddress){
        console.log(viewPageAddress);
        $scope.managementContent = viewPageAddress;
    }
});