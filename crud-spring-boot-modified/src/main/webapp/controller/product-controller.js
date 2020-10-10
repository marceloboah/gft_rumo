var app = angular.module("gftApplication", []); 
app.controller("gftController", ["$scope", "$http", "$timeout", "productService", function($scope, $http, $timeout, productService) {
	$scope.error = false;
	$scope.success = false;
	$scope.found = false;
	$scope.option = -1;
	$scope.product = {};
	$scope.listOfProducts = [];
	
	$scope.cleanVariables = function() {
		$scope.found = false;
		$scope.product = {};
		$scope.name = null;
		$scope.valmin = null;
		$scope.valmax = null;
		$scope.color = null;
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
		if (isFormValid) {
			console.log("findProductsBySearch");
			var name = $scope.name.toUpperCase();
			var valmin = $scope.valmin;
			var valmax = $scope.valmax;
			productService.findProductsBySearch(name, valmin, valmax).then(function successCallback(response) {
				$scope.listOfProducts = response.data;
				console.log($scope.listOfProducts);
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