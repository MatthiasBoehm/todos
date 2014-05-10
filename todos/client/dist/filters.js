angular.module('filters', []);
angular.module('filters').filter('dateFormat', [
  function() {
    return function(date, format) { return moment(date).format(format); } 
  }
]);