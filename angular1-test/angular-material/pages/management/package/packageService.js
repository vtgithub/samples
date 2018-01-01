APP.service('$packageService', function($http){
	this.getPackageList = function(callBack){
		$http({
			method:"GET",
			url:SERVICE_BASE_URL + "management/package",
			cache:false
		}).success(function(response){
			callBack(response.data.list);
		});
	};

	this.saveCategoryObject = function (dataObj, callBack) {
		$http({
			method:"POST",
			url:SERVICE_BASE_URL + "management/package/add",
			cache:false,
			data:dataObj
		}).success(function (response) {
			callBack(response.message);
        });
    }
    
    this.getPackageListByCatId = function (catId, callBack) {
        $http({
            method:"GET",
            url:SERVICE_BASE_URL + "package/" + catId,
            cache:false
        }).success(function(response){
            callBack(response.data.list);
        });
    }


});
