package com.gft.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gft.api.domain.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.origin = ?1 and p.product = ?2 and p.quantity = ?3 and p.price = ?4 and p.type = ?5 ")
	Product findProductByParam(String origin, String product, long quantity, String price, String type);

}
