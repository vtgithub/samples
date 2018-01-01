function getCatListByFactoryId(factoryId){
    for(var i=0 ; i<FACTORY_LIST.length ; i++){
        if(FACTORY_LIST[i].fId == factoryId)
            return FACTORY_LIST[i].catList;
    }
    return undefined;
}
function getCarListByCatId(catId) {
    for(var i=0 ; i<FACTORY_LIST.length ; i++){
        for (var j=0 ; j<FACTORY_LIST[i].catList.length ; j++){
            if (FACTORY_LIST[i].catList[j].catId == catId)
                return FACTORY_LIST[i].catList[j].carList;
        }
    }
    return undefined;
}