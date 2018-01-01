/**
 * Created by vahid on 5/14/17.
 */
APP.directive('parent-temp', function () {
   return{
       restrict: 'E',
       template: "<div><a ng-click = 'changeMainContent()'>test directive</a></div>"
   }
});
