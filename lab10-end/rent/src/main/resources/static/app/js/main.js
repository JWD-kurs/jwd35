var rentApp = angular.module("rentApp", ['ngRoute']);

rentApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/automobili.html'
    }).when('/automobili/edit/:id',{
        templateUrl: '/app/html/edit-automobil.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

rentApp.controller("automobiliCtrl", function($scope, $http, $location){

	var baseUrlKompanije = "/api/kompanije";
    var baseUrlAutomobili = "/api/automobili";
    

    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.kompanije = [];
    $scope.automobili = [];

    $scope.novAutomobil = {};
    $scope.novAutomobil.model = "";
    $scope.novAutomobil.registracija = "";
    $scope.novAutomobil.godiste = "";
    $scope.novAutomobil.potrosnja = "";
    $scope.novAutomobil.kompanijaId = "";
    


    $scope.trazeniAutomobil = {};
    $scope.trazeniAutomobil.model = "";
    $scope.trazeniAutomobil.godiste = "";
    $scope.trazeniAutomobil.potrosnja = "";

    var getAutomobili = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazeniAutomobil.model != ""){
            config.params.model = $scope.trazeniAutomobil.model;
        }

        if($scope.trazeniAutomobil.godiste != ""){
            config.params.godiste = $scope.trazeniAutomobil.godiste;
        }

        if($scope.trazeniAutomobil.potrosnja != ""){
            config.params.potrosnja = $scope.trazeniAutomobil.potrosnja;
        }


        $http.get(baseUrlAutomobili, config)
            .then(
            	function success(res){
            		$scope.automobili = res.data;
            		$scope.totalPages = res.headers('totalPages');
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje automobila!");
            	}
            );
    };

    var getKompanije = function(){

        $http.get(baseUrlKompanije)
            .then(
            	function success(res){
            		$scope.kompanije = res.data;
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje kompanija!");
            	}
            );

    };

    getKompanije();
    getAutomobili();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getAutomobili();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getAutomobili();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlAutomobili, $scope.novAutomobil)
            .then(
            	function success(res){
            		getAutomobili();
	
            		$scope.novAutomobil.model = "";
            	    $scope.novAutomobil.registracija = "";
            	    $scope.novAutomobil.godiste = "";
            	    $scope.novAutomobil.potrosnja = "";
            	    $scope.novAutomobil.kompanijaId = "";
            	},
            	function error(res){
            		alert("Neuspesno dodavanje!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getAutomobili();
    }

    $scope.izmeni = function(id){
        $location.path('/automobili/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlAutomobili + "/" + id).then(
            function success(data){
            	getAutomobili();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.iznajmi = function(id){
    	$http.post(baseUrlAutomobili + "/" + id + "/iznajmljivanje").then(
    		function success(data){
    			alert("Automobil je uspesno iznajmljen.");
    			getAutomobili();
    		},
    		function error(data){
    			alert("Nije uspelo iznajmljivanje")
    		}
    	)
    }
});

rentApp.controller("editAutomobilCtrl", function($scope, $http, $routeParams, $location){

	var baseUrlKompanije = "/api/kompanije";
	var baseUrlAutomobili = "/api/automobili";

    $scope.stariAutomobil = null;

    var getStariAutomobil = function(){

        $http.get(baseUrlAutomobili + "/" + $routeParams.id)
            .then(
            	function success(res){
            		$scope.stariAutomobil = res.data;
            	},
            	function error(data){
            		alert("Neušpesno dobavljanje automobila.");
            	}
            );

    }
    
    getStariAutomobil();
    
    var getKompanije = function(){

        $http.get(baseUrlKompanije)
            .then(
            	function success(res){
            		$scope.kompanije = res.data;
            		
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje kompanija!");
            	}
            );

    };

    getKompanije();
    
    $scope.izmeni = function(){
        $http.put(baseUrlAutomobili + "/" + $scope.stariAutomobil.id, $scope.stariAutomobil)
            .then(
        		function success(data){
        			alert("Uspešno izmenjen automobil!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena automobila.");
        		}
            );
    }
});

