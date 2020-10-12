var app = angular.module("gftApplication", []); 
app.controller("gftController", ["$scope", "$http", "$timeout", "productService", function($scope, $http, $timeout, productService) {
	$scope.error = false;
	$scope.success = false;
	$scope.found = false;
	$scope.option = -1;
	$scope.product = {};
	$scope.search = {};
	$scope.listOfProducts = [];
	$scope.showtable = false;
	
	$scope.cleanVariables = function() {
		$scope.found = false;
		$scope.product = {};
		$scope.name = null;
		$scope.valmin = null;
		$scope.valmax = null;
		$scope.color = null;
		$scope.search = {};
		$scope.listOfProducts = [];
	}
	
	$scope.cleanAlerts = function() {
		$scope.success=false;
		$scope.error=false;
	}

	$scope.goTo = function(option) {
		console.log("goTo");
		$scope.option = option;
		$scope.cleanVariables();
	}
	
	$scope.listProducts = function() {
		console.log("listProducts");
		productService.getProducts().then(function(response) {
			$scope.listOfProducts = response.data;
			console.log($scope.listOfProducts);
		});
	}
	
	$scope.findProductsBySearch = function(isFormValid) {
		console.log($scope.search);
		console.log($scope.min);
		console.log($scope.max);
		if($scope.search.name == null && $scope.search.floor == null && $scope.search.max == null ){
			$scope.alertMessage = "Please fill at least one field!"
			$scope.error = true;
			return;
		}else{
			if($scope.search.floor == null && $scope.search.max != null || $scope.search.floor != null && $scope.search.max == null){
				$scope.alertMessage = "When filling in Price, it is mandatory to fill in the min and max fields!"
				$scope.error = true;
				return;
			}
			if($scope.search.name == undefined){
				$scope.search.name=null;
			}
				
					console.log("promise");
					console.log($scope.search); 
					 
					  productService.findProductsBySearch($scope.search.name, $scope.search.floor, $scope.search.max).then(function successCallback(response) {
							$scope.listOfProducts = response.data;
							console.log($scope.listOfProducts);
							$scope.showtable = true;
							if(Object.keys($scope.listOfProducts).length > 0){
								$scope.showtable = true;
							}
							$scope.found = true;
						}, function errorCallback(response) {
							$scope.error=true;
							$scope.alertMessage = "Product not found in database.";
						});
						$timeout(function() {
							if(!$scope.found) {
								$scope.cleanAlerts();
							}
							$scope.cleanAlerts();
							$scope.error = false;
						},5000);
						
					
			
			
			
			
			
			
		}
		
		
	}
	
	
	
}]);