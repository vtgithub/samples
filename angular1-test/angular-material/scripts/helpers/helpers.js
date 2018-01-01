//Object.prototype.can = function(methodName){
//    return (typeof(this[methodName]) == "function");
//}

var helpers = {
  randomString: function (length) {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (var i = 0; i < length; i++) {
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    }
    return text;
  },

  getGregorian: function (year, month, day) {
    var gregorianDate = toGregorian(
        year,
        month,
        day
    );
    var gregorianDateResult = gregorianDate.gy + "/" + gregorianDate.gm + "/" + gregorianDate.gd;
    return gregorianDateResult;
  },

  getUTCFromJalali: function ( year, month, day){
    var gregorianDate = toGregorian(
        year,
        month,
        day

    );
    //var date = new Date(gregorianDate.gy, gregorianDate.gm-1, gregorianDate.gd);

    //return date.getTime();
      return Date.UTC(gregorianDate.gy, gregorianDate.gm-1, gregorianDate.gd);
  },

  getJalaliFromUTC: function (seconds) {
      var d = new Date(seconds);
      //console.log(d);
      return toJalaali(d.getUTCFullYear(), d.getUTCMonth()+1, d.getDate());
  },

  getGhamariFromUTC: function (seconds) {

    var ghamariDate = getIslamicDate(seconds);

      if(ghamariDate.gm == 0){
          ghamariDate.gm = 12;
          ghamariDate.gy = ghamariDate.gy - 1;
          if( ghamariDate.gm % 2 == 0  ){
                if(ghamariDate.gd == 1){
                    ghamariDate.gm -= 1;
                    ghamariDate.gd = 29;
                }else{
                    ghamariDate.gd -= 1;
                }

          }else{
              if(ghamariDate.gd == 1){

              }else{
                  ghamariDate.gm -= 1;
                  ghamariDate.gd = 30;
              }
              ghamariDate.gd -= 1;
          }
      }

      if(ghamariDate.gd < 3){
          ghamariDate.gm = (ghamariDate.gm - 1 == 0) ? 12 : (ghamariDate.gm - 1);
          if( ghamariDate.gd % 2 == 0  )
            ghamariDate.gd = 29 - ghamariDate.gd;
          else
              ghamariDate.gd = 30 - ghamariDate.gd;
      }else{
          ghamariDate.gd = ghamariDate.gd - 2;
      }

      return ghamariDate.gy + "/" + ghamariDate.gm + "/" + (ghamariDate.gd);
  },
    dateInitializer:  function () {

    var dateComboInitializer = {};
    var days = [];
    var months = [];
    var years = [];

    for (var i = 1; i <= 31; i++) {
        days[i-1] = i;
    }
    for (i = 1; i <= 12; i++) {
        months[i-1] = i;
    }
    var j = 0;
    for (i = 1300; i <= 1400; i++) {
        years[j++] = i;
    }

    dateComboInitializer.years = years;
    dateComboInitializer.months = months;
    dateComboInitializer.days = days;
    return dateComboInitializer;
    },

    getCurrentUTCTime : function (){
        var d = new Date();
        var n = d.getTime();
        var jalaliDate = this.getJalaliFromUTC(n);
        return this.getUTCFromJalali(jalaliDate.jy, jalaliDate.jm, jalaliDate.jd);
    },

    getJalaliStringFormat : function (jalaliDate){
        return jalaliDate.jy + "/" + jalaliDate.jm  + "/" + jalaliDate.jd ;
    }

};




