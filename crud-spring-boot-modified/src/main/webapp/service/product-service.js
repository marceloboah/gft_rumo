angular.module('gftApplication').service('productService',['$http',function($http){
	
	return {
		getProducts: function() {
			return $http.get("api/products");
		},
		findProductsBySearch: function(name, valmim, valmax) {
			return $http.get("api/products/" + name + "/valmim/" + valmim+ "/valmax/" + valmax);
		}
	}
	
}]);