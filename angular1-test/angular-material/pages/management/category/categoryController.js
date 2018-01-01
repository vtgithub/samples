APP.controller('categoryContentController', function ($scope, $rootScope, $categoryService, $window) {

    init();

	$scope.updateCatList = function(cat){
		if (cat == null || cat == undefined)
			return;
		$scope.parentCounter++;
		$scope.allCatList.push(cat.children);
        $scope.parentIdList.push(cat._id);
	}

	$scope.previousCat = function () {
		if($scope.parentCounter != 0){
            $scope.parentCounter--;
            $scope.allCatList.pop();
            $scope.parentIdList.pop();
		}

    }

    $scope.add = function(){
        insertChildForParent($scope.originalCat, $scope.category, $scope.parentIdList[$scope.parentCounter] );
        $scope.category = {};
    }

    $scope.submit = function () {
        $categoryService.saveCategoryObject($scope.originalCat, function (responseMessage) {
            alert(responseMessage);
            init();
        });
    }

    $scope.refresh = function () {
        init();
        // $window.location.reload();
    }

	// -------------- helpers

	function init() {
        reset();
        getAllCats();
    }


    function reset() {
        $scope.parentCounter = 0;
        $scope.category = {};
        $scope.allCatList = new Array();
        $scope.parentIdList = new Array();
    }

    function getAllCats() {
        $categoryService.getCategoryObject(function (catObject) {
            $scope.originalCat = catObject;
            $scope.parentIdList.push(catObject._id);
            $scope.allCatList.push(catObject.children);
        });
    }

    function insertChildForParent(tree, child , parentId){
		if(tree == null || tree == undefined)
		    return;
	    else if(parentId == tree._id){
	        if (tree.children == null || tree.children == undefined)
                tree.children = new Array();
            tree.children.push(getNewCatObject(child));
            $scope.allCatList.pop();
            $scope.allCatList.push(tree.children);
		}else if(tree.children != null && tree.children!= undefined){
            for(var i = 0 ; i< tree.children.length; i++) {
                insertChildForParent(tree.children[i], child, parentId);
            }

        }
	}

	function getNewCatObject(catObj) {
        var newCatObj = {};
        newCatObj.name = catObj.name;
        newCatObj._id = catObj._id;
        return newCatObj;
    }

});