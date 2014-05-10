angular.module('resources', []);
angular.module('resources').factory('Todos', ['$resource', 
  function($resource) {
    
    return $resource("/todos/:id", {}, 
      {edit: {method: "PUT"}}); //TODO
  }
]);
angular.module('resources').factory('TodosSearch', ['$resource',
	function($resource) {
		return $resource("/todos/search/:id");
	}]);