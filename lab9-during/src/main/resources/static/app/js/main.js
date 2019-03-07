var wafepaApp = angular.module("wafepaApp", ["ngRoute"]);

wafepaApp.controller("homeCtrl", function($scope){
	
	$scope.message = "Hello JWD35!";
	
});

wafepaApp.controller("activitiesCtrl", function($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function(){
		var promise = $http.get(url);
		promise.then(
			function success(res){
				//console.log(res);
				$scope.activities = res.data;
			},
			function error(res){
				alert("Couldn't fetch activities.");
			}
		);
		//console.log("Test");
	}
	
	getActivities();
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}
	
	$scope.goToAdd = function(){
		$location.path("/activities/add");
	}
	
	$scope.delete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(res){
				getActivities();
			},
			function error(){
				alert("Couldn't delete activity");
			}
		)
	}
	
});


wafepaApp.controller("editActivityCtrl", function($scope, $routeParams, $http, $location){
	
	//console.log($routeParams);
	var url = "/api/activities/" + $routeParams.id;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(){
				alert("Couldn't fetch activity.");
			}
		);
	}
	
	getActivity();
	
	
	$scope.edit = function(){
		$http.put(url, $scope.activity).then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Couldn't edit activity");
			}
		);
	}
	
});


wafepaApp.controller("addActivityCtrl", function($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	$scope.add = function(){
		var promise = $http.post(url, $scope.activity);
		promise.then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Couldn't save activity.");
			}
		);
	}
	
});


wafepaApp.controller("recordsCtrl", function($scope, $http, $location){
	
	var recordsUrl = "/api/records";
	var usersUrl = "/api/users";
	var activitiesUrl = "/api/activities";
	
	
	$scope.records = [];
	$scope.users = [];
	$scope.activities = [];
	
	$scope.searchParams = {};
	$scope.searchParams.activityName = "";
	$scope.searchParams.minDuration = "";
	$scope.searchParams.intensity = "";
	
	
	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.duration = "";
	$scope.newRecord.intensity = "";
	
	$scope.newRecord.userId = "";
	$scope.newRecord.activityId = "";
	
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	
	$scope.search = function(){
		//console.log($scope.searchParams);
		$scope.pageNum = 0;
		getRecords();
	}
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getRecords();
	}
	
	
	var getRecords = function(){
		
		var config = { params: {}};
		
		if($scope.searchParams.activityName != ""){
			config.params.activityName = $scope.searchParams.activityName;
		}
		if($scope.searchParams.minDuration != ""){
			config.params.minDuration = $scope.searchParams.minDuration;
		}
		if($scope.searchParams.intensity != ""){
			config.params.intensity = $scope.searchParams.intensity;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(recordsUrl, config).then(
			function success(res){
				$scope.records = res.data;
				$scope.totalPages = res.headers("totalPages");				
			},
			function error(){
				alert("Couldn't fetch records");
			}
		);
	}
	getRecords();
	
	var getUsers = function(){
		$http.get(usersUrl).then(
			function success(res){
				$scope.users = res.data;
			},
			function error(){
				alert("Couldn't fetch users");
			}
		);
	}
	getUsers();
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
			},
			function error(){
				alert("Couldn't fetch activities");
			}
		);
	}
	getActivities();
	
	
	$scope.add = function(){
		//console.log($scope.newRecord);
		$http.post(recordsUrl, $scope.newRecord).then(
			function success(res){
				getRecords();
			},
			function error(){
				alert("Couldn't save record");
			}
		);
	}

	$scope.goToEdit = function(id){
		$location.path("/records/edit/" + id);
	}
	
});

wafepaApp.controller("editRecordCtrl", function($scope, $http, $routeParams, $location){
	
	var recordUrl = "/api/records/" + $routeParams.id;
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users";
	
	$scope.record = {};
	$scope.record.time = "";
	$scope.record.duration = "";
	$scope.record.intensity = "";
	$scope.record.userId = "";
	$scope.record.activityId = "";	
	
	
	$scope.activities = [];
	$scope.users = [];
	
	
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
				getUsers();
			},
			function error(){
				alert("Couldn't fetch activities");
			}
		);
	}
	
	var getUsers = function(){
		return $http.get(usersUrl).then(
			function success(res){
				$scope.users = res.data;
				getRecord();
			},
			function error(){
				alert("Couldn't fetch users.");
			}
		);
	}
	
	
	var getRecord = function(){
		$http.get(recordUrl).then(
			function success(res){
				$scope.record = res.data;
			},
			function error(){
				alert("Couldn't fetch record.");
			}
		);
	}
	
	getActivities();

	
	// Pogledati promise chaining kako bi se ovo odradilo na kraci nacin
	// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then#Chaining
	// https://javascript.info/promise-chaining
	
	$scope.doEdit = function(){
		$http.put(recordUrl, $scope.record).then(
			function success(){
				$location.path("/records");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
});


wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: "homeCtrl"
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/activity-add.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/activity-edit.html'
		})
		.when('/records', {
			templateUrl : '/app/html/records.html'
		})
		.when('/records/edit/:id', {
			templateUrl : '/app/html/record-edit.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
