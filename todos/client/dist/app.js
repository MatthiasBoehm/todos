'use strict';

/* App Module */

var todosApp = angular.module('todosApp', [
  'ngRoute',
  'ngResource',
  'todosControllers', 
  'filters'
]);


todosApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/todos', {
        templateUrl: '/assets/partials/todo-list.html',
        controller: 'TodosCtrl'
      }).
      when('/todos/:id', {
        templateUrl: '/assets/partials/todo-detail.html',
        controller: 'DetailCtrl'
      }).
      when('/todos/edit/:id', {
        templateUrl: '/assets/partials/todo-edit.html', 
        controller: 'EditCtrl'
      })./*
      when('/todos/delete/:id', {
        templateUrl: '/assets/partials/todo-delete.html',
        controller: 
      }).*/
      otherwise({
        redirectTo: '/todos'
      });
  }]);
