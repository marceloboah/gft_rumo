var app = angular.module("gftApplication", []); 
app.controller("gftController", ["$scope", "$http", "$timeout", "productService", function($scope, $http, $timeout, productService) {
	$scope.error = false;
	$scope.success = false;
	$scope.found = false;
	$scope.option = -1;
	$scope.product = {};
	$scope.search = {};
	$scope.search.floor = null;
	$scope.search.max = null;
	$scope.paginate = {};
	$scope.paginate.pageNumber = 1;
	$scope.paginate.pageSize = 50;
	$scope.paginate.pages = 0;
	$scope.paginate.showNextTen = false;
	$scope.paginate.showBackTen = false;
	$scope.paginate.showAtual = false;
	$scope.paginate.showNextOne = false;
	$scope.paginate.showBackOne = false;
	$scope.paginate.navigation = false;
	
	$scope.listOfProducts = [];
	$scope.showTable = false;
	
	$scope.cleanVariables = function() {
		$scope.found = false;
		$scope.success=false;
		$scope.product = {};
		$scope.name = null;
		$scope.floor = null;
		$scope.valmax = null;
		$scope.color = null;
		$scope.showTable = false;
		$scope.paginate = {}
		$scope.search = {};
		$scope.listOfProducts = [];
		$scope.paginate.pageNumber = 1;
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
		productService.getProducts($scope.paginate.pageNumber).then(function(response) {
			$scope.listOfProducts = response.data.listProducts;
			$scope.showtable = true;
			if(Object.keys($scope.listOfProducts).length > 0){
				$scope.paginate.total = response.data.pageTotaLlines;
				$scope.paginate = response.data.pagination;
				$scope.paginate.navigation = true;
				$scope.showTable = true;
				$scope.paginate.showAtual = true;
			}
			$scope.found = true;
			
		});
	}
	
	$scope.next = function() {
		console.log($scope.search);
		$scope.paginate.pageNumber = $scope.paginate.pageNumber+1;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	$scope.prev = function() {
		$scope.paginate.pageNumber = $scope.paginate.pageNumber-1;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	$scope.moreTen = function() {
		$scope.paginate.pageNumber = $scope.paginate.pageNumber+10;
		if($scope.option==3){//list
			$scope.listProducts();
		}else{//search
			$scope.findProductsBySearch();
		}
	}
	$scope.minusTen = function() {
		$scope.paginate.pageNumber = $scope.paginate.pageNumber-10;
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
			if($scope.search.floor == undefined){
				$scope.search.floor=null;
			}
			if($scope.search.max == undefined){
				$scope.search.max=null;
			}
				
					 
					  productService.findProductsBySearch($scope.search.name, $scope.search.floor, $scope.search.max,$scope.paginate.pageNumber).then(function successCallback(response) {
							$scope.listOfProducts = response.data.listProducts;
							if(Object.keys($scope.listOfProducts).length > 0){
								$scope.found = true;
								$scope.showTable = true;
								$scope.paginate.total = response.data.pageTotalLines;
								$scope.paginate = response.data.pagination;
								$scope.paginate.navigation = true;
								$scope.showtable = true;
								$scope.paginate.showAtual = true;
							}else{
								$scope.success=true;
								$scope.alertMessage = "A busca não retornou resultados!";
								
							}
							
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