angular.module('gftApplication').service('productService',['$http',function($http){
	
	return {
		getProducts: function(page) {
			return $http.get("api/products?page="+page);
		},
		findProductsBySearch: function(name, floor, valmax, page) {
			return $http.get("api/products/find?name=" + name + "&floor=" + floor+ "&valmax=" + valmax+ "&paginaatual=" + page);
		}
	}
	
}]);