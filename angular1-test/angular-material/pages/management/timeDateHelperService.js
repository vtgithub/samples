APP.service('$timeDateHelperService', function($http){

    var WEEK_DAY_LIST= [];
    var HOUR_LIST = [];
	var MINUTE_LIST = [];
	init();


    this.getWeekDayList =function(){
		return WEEK_DAY_LIST;
	}

	this.getHourList = function () {
		return HOUR_LIST;
    }

    this.getMinuteList = function () {
		return MINUTE_LIST;
    }

	this.getWeekDayName = function(weekDayIndex){
		if (weekDayIndex != undefined)
			return WEEK_DAY_LIST[weekDayIndex].name;
		return undefined;
	};

	this.getHourFromSeconds = function(seconds){
		if (seconds != undefined)
			return Math.floor( seconds / (60*60) );
	}

	this.getMinuteFromSeconds = function(seconds){
		if (seconds != undefined)
			return Math.floor((seconds / 60)) % 60;
	}



	function init() {
        WEEK_DAY_LIST = [
            {id:"-1", name:"انتخاب کنید"},
            {id:"0", name:"شنبه"},
            {id:"1", name:"یکشنبه"},
            {id:"2", name:"دوشنبه"},
            {id:"3", name:"سه شنبه"},
            {id:"4", name:"چهارشنبه"},
            {id:"5", name:"پنجشنبه"},
            {id:"6", name:"جمعه"}
        ];
        for (var i = 0; i < 24; i++) {
            HOUR_LIST[i] = i;
        }
        for (var i = 0; i < 60; i++) {
            MINUTE_LIST[i] = i;
        }
    }

});