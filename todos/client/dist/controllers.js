'use strict';

var todosControllers = angular.module('todosControllers', ["resources"]);

todosControllers.controller('TodosCtrl', ['$scope', 'Todos', 'TodosSearch', 
  function($scope, Todos, TodosSearch) {
    $scope.todos = Todos.query(); 

    $scope.newTask = { start: new Date(),  due: new Date() };
    
    $scope.save = function() {
      
      if($scope.newTask.start == null || $scope.newTask.due == null) {
        alert("Invalid dates");
        return;
      }

      Todos.save($scope.newTask).$promise
      .then(function () { $scope.todos = Todos.query(); });

    }

    $scope.delete = function(theId) {
    	    	
      Todos.delete({id: theId}).$promise.then
        (function () { $scope.todos = Todos.query(); });
    }

    $scope.search = function() {
      var todos = TodosSearch.query({id: $scope.searchName});
      
      $scope.foundTodos = todos;
    }

    $scope.reverse = false;
    $scope.sortOn = "";

    var signs = [ "task", "start", "due" ];

    $scope.changeSortOn = function(what) {
      var oldWhat = $scope.sortOn;
      $scope.sortOn=what; 

      if(oldWhat == what) {
        $scope.reverse = !$scope.reverse;
      } else {
        $scope.reverse = false;
      }
      
      for (var i = 0; i < signs.length; i++) {

        // console.log(s);

        var s = signs[i];

        var sign = "sign" + s;

        var updown = $scope.reverse ? "/assets/up.bmp" : "/assets/down.bmp";

        $scope[sign] = (s == what) ? updown : "/assets/empty.bmp";
      }

      
    }

    $scope.noSort = function() {
      $scope.sortOn = '';

      for(var i = 0; i < signs.length; i++) {
        var sign = "sign" + signs[i];

        $scope[sign] = "/assets/empty.bmp";
      }
    }

    $scope.changeSortOn("task");

  }]);


todosControllers.controller('DetailCtrl', ['$scope', '$route', 'Todos',
  function($scope, $route, Todos) {
    $scope.todo = Todos.get({id: $route.current.params.id});
  }]);


todosControllers.controller('EditCtrl', ['$scope', '$routeParams', 'Todos', 
  function($scope, $routeParams, Todos) {

    var todo = Todos.get({id: $routeParams.id}, 
      function() {
        var m = todo.start;
        todo.start = new Date();
        todo.start.setTime(m);

        m = todo.due;
        todo.due = new Date();
        todo.due.setTime(m);
      });

    $scope.todo = todo;

    $scope.save = function() {
      Todos.edit($scope.todo);
    }
  }]);

