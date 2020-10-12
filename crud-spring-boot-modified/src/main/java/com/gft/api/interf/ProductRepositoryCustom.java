package com.gft.api.interf;

import java.util.List;

import com.gft.api.domain.Product;


public interface ProductRepositoryCustom {

	List<Product> getProductsBySearch(String name, Double valmin, Double valmax);
}
