var app = angular.module("gftApplication", []); 
app.controller("gftController", ["$scope", "$http", "$timeout", "productService", function($scope, $http, $timeout, productService) {
	$scope.error = false;
	$scope.success = false;
	$scope.found = false;
	$scope.option = -1;
	$scope.product = {};
	$scope.search = {};
	$scope.paginate = {};
	$scope.paginate.pagenumber = 1;
	$scope.paginate.pagesize = 50;
	$scope.paginate.pages = 0;
	$scope.paginate.shownextten = false;
	$scope.paginate.showbackten = false;
	$scope.paginate.showatual = false;
	$scope.paginate.shownextone = false;
	$scope.paginate.showbackone = false;
	$scope.paginate.navigation = false;
	
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
		$scope.paginate.pagenumber = 1;
	}
	
	$scope.cleanAlerts = function() {
		$scope.success=false;
		$scope.error=false;
	}

	$scope.goTo = function(option) {
		$scope.option = option;
		$scope.cleanVariables();
	}
	
	$scope.listProducts = function() {
		productService.getProducts($scope.paginate.pagenumber).then(function(response) {
			$scope.listOfProducts = response.data.listProducts;
			$scope.showtable = true;
			if(Object.keys($scope.listOfProducts).length > 0){
				$scope.paginate.total = response.data.pagetotallines;
				$scope.paginate = response.data.pagination;
				$scope.paginate.navigation = true;
				$scope.showtable = true;
				$scope.paginate.showatual = true;
			}
			$scope.found = true;
			
		});
	}
	
	$scope.next = function() {
		console.log($scope.search);
		$scope.paginate.pagenumber = $scope.paginate.pagenumber+1;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	$scope.prev = function() {
		$scope.paginate.pagenumber = $scope.paginate.pagenumber-1;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	$scope.moreten = function() {
		$scope.paginate.pagenumber = $scope.paginate.pagenumber+10;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	$scope.minusten = function() {
		$scope.paginate.pagenumber = $scope.paginate.pagenumber-10;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	
	$scope.findProductsBySearch = function() {
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
				
					 
					  productService.findProductsBySearch($scope.search.name, $scope.search.floor, $scope.search.max,$scope.paginate.pagenumber).then(function successCallback(response) {
							$scope.listOfProducts = response.data.listProducts;
							$scope.showtable = true;
							if(Object.keys($scope.listOfProducts).length > 0){
								$scope.paginate.total = response.data.pagetotallines;
								$scope.paginate = response.data.pagination;
								$scope.paginate.navigation = true;
								$scope.showtable = true;
								$scope.paginate.showatual = true;
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