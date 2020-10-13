package com.gft.api.interf;

import java.util.List;

import com.gft.api.domain.Product;


public interface ProductCustomMethods {

	Product getProductsBySearch(String name, Double valmin, Double valmax, Integer paginaatual);
	Product getAllProductsByPage(Integer paginaatual);
	List<Product> getProductsByList(String name, Double valmin, Double valmax, Integer paginaatual);

}
