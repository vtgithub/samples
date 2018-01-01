APP.controller('timePeriodContentController', function ($scope, $rootScope, $timePeriodService, $timeDateHelperService) {
	
	init();

	$scope.filterByWeekDay = function (weekDay) {
        $scope.filteredTimePeriodList = $timePeriodService.filterByWeekDay($scope.timePeriodList, weekDay);
    }
	$scope.getWeekDay = function(index){
		return $timeDateHelperService.getWeekDayName(index);
	}

	$scope.getHourMinute = function(seconds){
		return $timeDateHelperService.getHourFromSeconds(seconds) + ":" +
		$timeDateHelperService.getMinuteFromSeconds(seconds);
	}

	$scope.submit = function(){
		timeConvertor();
		$scope.timePeriodList = $timePeriodService.submitTimePeriod($scope.timePeriodObject, function(message){
			alert(message);
			getTimePeriodList(function () {
				$scope.filterByWeekDay($scope.timePeriodObject.weekDay);
            });
		});
	}


//--------------- helper functions
	function init() {
		$scope.timePeriodObject = {};
		$scope.fromTime = {};
        $scope.fromTime.hour = 1;
        $scope.fromTime.minute = 0;
		$scope.toTime = {};
        $scope.toTime.hour = 1;
        $scope.toTime.minute = 0;
        $scope.weekDayList = $timeDateHelperService.getWeekDayList();
        $scope.timePeriodObject.weekDay = '-1';
        $scope.hourList = $timeDateHelperService.getHourList();
		$scope.minuteList = $timeDateHelperService.getMinuteList();


		$timePeriodService.getTimePeriodList(function(timePeriodList){
			$scope.timePeriodList = timePeriodList;
			$scope.filteredTimePeriodList = timePeriodList;
		});
	}

	function getTimePeriodList(callBack){
		$timePeriodService.getTimePeriodList(function(timePeriodList){
			$scope.timePeriodList = timePeriodList;
			if(callBack!=undefined)
				callBack();
		});
	}

	function timeConvertor(){
		$scope.timePeriodObject.fromTime = 
			$scope.fromTime.hour * 60 * 60 + $scope.fromTime.minute * 60; 

		$scope.timePeriodObject.toTime = 		
			$scope.toTime.hour * 60 * 60 + $scope.toTime.minute * 60; 

	}
});