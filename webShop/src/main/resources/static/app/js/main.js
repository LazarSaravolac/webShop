var app = angular.module("Lazar", ["ngRoute"]);

app.controller("ctrl", function($scope){
	$scope.message = "Hello JWD 30!";
});







app.controller("osobeCtrl", function($scope, $http, $location){
	var baseArt="/api/art";
	var baseUrl = "/api/osobe";
	var baseUrlArtikli = "/api/artikli";
	$scope.otvori=false;
	$scope.otvoriNaziv=false;
    $scope.osobe=[];
    $scope.ucesnici1=[];
    $scope.artikli=[];
    $scope.novaOsoba={};
    $scope.page=0;
    $scope.totalPages=0;
    $scope.lista=[];
    $scope.trazeniUcesnik={};
    $scope.tla=[];
    $scope.prikazii=false;
    $scope.art=[];
    $scope.pokazi=false;
    $scope.kojeOsoba="";
    $scope.totalPages=1;
    $scope.page=0;
    $scope.pPretrage=[];
    $scope.art1=[];
    $scope.novArt={};
    $scope.osobes=[];
    var getOsobe = function(){
var config1 = { params: {}};
 		
 		
 		if($scope.osobes.ime != ""){
 			config1.params.ime = $scope.osobes.ime;
 		}

        $http.get(baseUrl,config1)
            .then(
            	function success(res){
            		$scope.osobe = res.data;
            	},
            	function error(res){
            		location.reload();
            		
            	}
            );
        
        
    };
    
    getOsobe();
    
   
    var getArt = function(){
    	
 		
 		
   		
   
           $http.get(baseArt)
               .then(
               	function success(res){
               		$scope.art = res.data;
               		
               	},
               	function error(res){
               		location.reload();
               		
               	}
               );
           
           
       };
    
       getArt();
       
 var getArt1 = function(){
	 var config = { params: {}};
		
		
		if($scope.pPretrage.naziv != ""){
			config.params.naziv = $scope.pPretrage.naziv;
		}
		
		
		config.params.page = $scope.page;

        $http.get(baseArt,config)
            .then(
            	function success(res){
            		$scope.art1 = res.data;
            		$scope.totalPages = res.headers("totalPages");
            		console.log($scope.totalPages);
            	},
            	function error(res){
            		location.reload();
            		alert('greska');
            	}
            );
        
        
    };
    
    getArt1();
    
    $scope.go = function(direction){
		$scope.page = $scope.page + direction;
		console.log($scope.page);
		getArt1();
	}
    
    $scope.pretraga = function(){
		$scope.page = 0;
		getArt1();
	}
    $scope.pretragaK = function(){
		getOsobe();
	}
 var getOsobeArtikli = function(){
	 var config = {params: {}};

     config.params.page = $scope.page;
     
     
     if($scope.tla.idOsobe != ""){
         config.params.idOsobe = $scope.tla.idOsobe;
     }

     if($scope.tla.naziv != ""){
         config.params.naziv = $scope.tla.naziv;
     }


        $http.get(baseUrlArtikli,config)
            .then(
            	function success(res){
            		$scope.lista = res.data;
            	},
            	function error(res){
            		location.reload();
            		
            	}
            );
        
        
    };
    
    getOsobeArtikli();
    
    
   
    var getArtikli = function(){

        

        $http.get(baseUrlArtikli)
            .then(
            	function success(res){
            		$scope.artikli= res.data;
            		
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje ucesnika!");
            	}
            );
    };
    getArtikli();
    
    
    $scope.trazi=function(){
    	if($scope.trazeniUcesnik.idTakmicenje != undefined){
    	$scope.page=0;
    	getUcesnici();
    	$scope.otvori=true;
    	$scope.otvoriNaziv=true;}else{
    		alert('Izaberite ligu')
    	}
    }
    
    
    
    $scope.dodaj = function(){
        $http.post(baseUrl, $scope.novaOsoba)
            .then(
            	function success(res){
            		console.log($scope.novaOsoba);
            		$scope.novaOsoba=null;
            		getOsobe();
            		
            		
            	},
            	function error(res){
            		alert("Neuspesno dodavanje!");
            	}
            );
    };
    
    $scope.dodajArt = function(){
        $http.post(baseArt, $scope.novArt)
            .then(
            	function success(res){
            		console.log($scope.novArt);
            		getArt();
            		getArt1();
            		
            	},
            	function error(res){
            		console.log($scope.novArt);
            		alert("Neuspesno dodavanje!");
            	}
            );
    };
    
    
    $scope.obrisi = function(id){
        $http.delete(baseUrl + "/" + id).then(
            function success(data){
            	getUcesnici();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.izmeni = function(id){
        $location.path('/ucesnici/edit/' + id);
    }
    
    $scope.nazad = function(){
        if($scope.page > 0) {
            $scope.page = $scope.page - 1;
            getUcesnici();
        }
    };

    $scope.napred = function(){
        if($scope.page < $scope.totalPages - 1){
            $scope.page = $scope.page + 1;
            getUcesnici();
        }
    };
    
    var getUcesnici1 = function(){
    	var config1 = {params: {}};


        if($scope.trazeniUcesnik.idTakmicenje != ""){
            config1.params.idTakmicenje = $scope.trazeniUcesnik.idTakmicenje;
        }


        $http.get(baseUrl,config1)
            .then(
            	function success(res){
            		$scope.ucesnici1 = res.data;
            	
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje ucesnika!");
            	}
            );
        
        
    };
    
    $scope.obrisiArt = function(id){
        $http.delete(baseArt + "/" + id).then(
            function success(data){
            	getArt1();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
   
    $scope.kupi=function(osobaId,artikalId){
    	var promise=$http.put(baseUrl + "/" + osobaId + "/" + artikalId);
		promise.then(
				function uspeh(res){
					getOsobe();
					$scope.tla.idOsobe=osobaId;
					 getOsobeArtikli();
					 $scope.prikazii=true;
					 $scope.kojeOsoba=res.headers('ime');
					
				},
				function greska(){
					alert("greska");
					console.log(baseUrl + "/" + osobaId + "/" + artikalId)
				}
		);
    }
    
    $scope.kupi1=function(osobaId,id,naziv,cena){
    	$scope.izvrsi="";
    	$scope.izvrsi=id + "Y" + naziv + "Y" + cena;
    	var promise=$http.put(baseUrl + "/" + osobaId + "/" + $scope.izvrsi);
		promise.then(
				function uspeh(res){
					getOsobe();
					$scope.tla.idOsobe=osobaId;
					 getOsobeArtikli();
					 $scope.prikazii=true;
					 $scope.kojeOsoba=res.headers('ime');
					
				},
				function greska(){
					alert('nemate dovoljno novca na racunu');
					console.log(baseUrl + "/" + osobaId + "/" + $scope.izvrsi)
				}
		);
    }
    
    $scope.obrni=function(){
    	$scope.prenos.o3=$scope.prenos.o1;
    	$scope.prenos.o1=$scope.prenos.o2;
    	$scope.prenos.o2=$scope.prenos.o3;
    	
    }
    
    $scope.goToEdit = function(id){
		$location.path("/artikli/edit/" + id);
	}
    
    $scope.predji=function(){
    	$scope.pokazi=!$scope.pokazi;
    	document.getElementById('ej').scrollIntoView();
//    	 $location.path('/ucesnici/odigraj/');
    	 console.log($scope.trazeniUcesnik.idTakmicenje);
    	 getUcesnici();
    	 console.log($scope.ucesnici);
    }
    
   
    $scope.prenos=function(o1,o2,iznos){
    	var promise=$http.put(baseUrl + "/" + o1 + "/" + o2 + "/" +  iznos);
		promise.then(
				function uspeh(res){
					getOsobe();
					
				},
				function greska(){
					alert("greska");
				
				}
		);
    }


    
    
    $scope.odigraj=function(u1,u2,ishod){
    	var promise=$http.put(baseUrl + "/" + u1 + "/" + u2 + "/" +  ishod);
		promise.then(
				function uspeh(res){
					$scope.ucesnici=res.data;
					getUcesnici();
					$location.path('/ucesnici');
					
				},
				function greska(){
					alert("greska");
				
				}
		);
    }
});











app.controller("editUcesnikCtrl", function($scope, $http , $routeParams,$location){
	 $scope.novUcesnikE={};
	 var baseUrl = "/api/ucesnici";

	  

	    var getStariU= function(){

	        $http.get(baseUrl + "/" + $routeParams.id)
	            .then(
	            	function success(res){
	            		$scope.novUcesnikE = res.data;
	            	},
	            	function error(data){
	            		alert("Neušpesno dobavljanje ucesnika.");
	            	}
	            );

	    }
	    getStariU();
	
	    $scope.izmeni = function(){
	        $http.put(baseUrl + "/" + $scope.novUcesnikE.id, $scope.novUcesnikE)
	            .then(
	        		function success(data){
	        			alert("Uspešno izmenjen ucesnik!");
	        			$location.path("/");
	        		},
	        		function error(data){
	        			alert("Neuspešna izmena ucesnik.");
	        		}
	            );
	    }
});

app.directive('ngConfirmClick', [
    function(){
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.bind('click',function (event) {
                    if ( window.confirm(msg) ) {
                        scope.$eval(clickAction)
                    }
                });
            }
        };
}])

app.controller("editACtrl", function($scope, $routeParams, $http, $location){
	var artikliId = $routeParams.aid;
	var baseUrl = "/api/art";
	

	
	$scope.novArtikl = {};
	
	var getArtikli = function(){
		
		$http.get(baseUrl + "/" + artikliId).then(
			function success(res){
				$scope.novArtikl = res.data;
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
	getArtikli();
	
	$scope.edit = function(){
		$http.put(baseUrl + "/" + artikliId, $scope.novArtikl).then(
			function success(res){
				//alert("Uspeh");
				getArtikli();
				console.log($scope.novArtikl);
				$location.path("/artikli");
			},
			function error(res){
				alert("Something went wrong");
			}
		);

}
	
});


app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		
		.when('/', {
			templateUrl : '/app/html/partial/ucesnik.html'
		})
		
		.when('/ucesnici/edit/:id', {
			templateUrl : '/app/html/partial/edit-ucesnici.html'
		})
		.when('/korisnici', {
			templateUrl : '/app/html/partial/korisnik.html'
		})
		.when('/artikli', {
			templateUrl : '/app/html/partial/artikli.html'
		})
		.when('/artikli/edit/:aid', {
			templateUrl : '/app/html/partial/edit-artikli.html'
		})
		.when('/ucesnici/odigraj/', {
			templateUrl : '/app/html/partial/odigrajMec.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
