angular.module('gftApplication').service('productService',['$http',function($http){
	
	return {
		getProducts: function() {
			return $http.get("api/products");
		},
		findProductsBySearch: function(name, floor, valmax) {
			return $http.get("api/products/find?name=" + name + "&floor=" + floor+ "&valmax=" + valmax);
		}
	}
	
}]);