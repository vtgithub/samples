	APP.service('$timePeriodService', function($http){
	this.getTimePeriodList = function(getDataCallBack){
		$http({
			method:"GET",
			url:SERVICE_BASE_URL + "management/timePeriod",
			cache:false
			// headers:{"sessionid":"c613304d-d35b-45bb-aed7-7720afcda409"}
		}).success(function(response){
			getDataCallBack(response.data.list);
		});
	};

	this.submitTimePeriod = function(timePeriodObject, callBack){
		$http({
			method:"POST",
			url:SERVICE_BASE_URL + "management/timePeriod/add",
			data:timePeriodObject,
			cache:false
		}).success(function(response){
			callBack(response.message);
		}).error(function(response){
			console("timePeriod insertion failed");
		});
	}

	this.filterByWeekDay = function(timePeriodObjectList, weekDayId){
		// console.log(timePeriodObjectList.length)
		var newTimePeriodObjectList	= new Array();
		if(timePeriodObjectList == undefined || weekDayId == undefined)
			return newTimePeriodObjectList;
		for(var i = 0 ; i < timePeriodObjectList.length; i++){
            if(timePeriodObjectList[i].weekDay == weekDayId){
				console.log(timePeriodObjectList[i].weekDay );
                newTimePeriodObjectList.push(timePeriodObjectList[i]);
			}
        }

		return newTimePeriodObjectList;
	}

});
