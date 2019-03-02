var app = angular.module('first_contact');

//Child controller za first controller
app.controller("secondController", function($scope){	
	
		$scope.name = "Marko";
});
