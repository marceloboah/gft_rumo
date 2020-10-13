package com.gft.api.interf;

import com.gft.api.domain.Product;


public interface ProductRepositoryCustom {

	Product getProductsBySearch(String name, Double valmin, Double valmax, Integer paginaatual);
	Product getAllProductsByPage(Integer paginaatual);
}
