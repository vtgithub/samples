APP.service('$categoryService', function($http){
	this.getCategoryObject = function(callBack){
		$http({
			method:"GET",
			url:SERVICE_BASE_URL + "category/non-button",
			cache:false
			// headers:{"sessionid":"c613304d-d35b-45bb-aed7-7720afcda409"}
		}).success(function(response){
			callBack(response.data);
		// console.log(response.data);
		});
	};

	this.saveCategoryObject = function (dataObj, callBack) {
		$http({
			method:"POST",
			url:SERVICE_BASE_URL + "management/category/add",
			cache:false,
			data:dataObj
		}).success(function (response) {
			callBack(response.message);
        });
    }

    var catName;
    this.getCatNameById = function(catObject, catId){
    	catName = undefined;
		findCatName(catObject, catId);
		return catName;
	}


	function findCatName(catObject, catId) {
		if(catObject == null || catObject == undefined)
			return;
		else if (catId == catObject._id){
			catName = catObject.name;
		}else if(catObject.children != null && catObject.children != undefined){
            for(var i = 0 ; i< catObject.children.length; i++) {
				findCatName(catObject.children[i], catId);
            }
        }
    }
});
