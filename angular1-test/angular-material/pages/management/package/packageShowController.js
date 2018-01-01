APP.controller('packageShowController', function ($scope, $rootScope, $packageService, $categoryService) {

    init();

    $scope.updateCatList = function(cat){
        if (cat.children == null || cat.children == undefined){
            getPackageListBycatId(cat._id);
            return;
        }
        $scope.parentCounter++;
        $scope.allCatList.push(cat.children);
        $scope.parentIdList.push(cat._id);
    }

    $scope.previousCat = function () {
        $scope.packageList = [];
        if($scope.parentCounter != 0){
            $scope.parentCounter--;
            $scope.allCatList.pop();
            $scope.parentIdList.pop();
        }

    }

    $scope.getCatName = function (catId) {
        return $categoryService.getCatNameById($scope.originalCat, catId);
    }


    //--------------------  helpers
    function init() {
        reset();
        getAllCats();
    }

    function reset() {
        $scope.package = {};
        $scope.parentCounter = 0;
        $scope.category = {};
        $scope.allCatList = new Array();
        $scope.parentIdList = new Array();
    }

    function getAllCats() {
        if($scope.originalCat != null && $scope.originalCat != undefined){
            $scope.parentIdList.push($scope.originalCat._id);
            $scope.allCatList.push($scope.originalCat.children);
        }else{
            $categoryService.getCategoryObject(function (catObject) {
                $scope.originalCat = catObject;
                $scope.parentIdList.push(catObject._id);
                $scope.allCatList.push(catObject.children);

            });
        }
    }

    function getPackageListBycatId(catId) {
        $packageService.getPackageListByCatId(catId, function (packageList) {
            console.log(packageList);
            $scope.packageList = packageList;
        });
    }


});