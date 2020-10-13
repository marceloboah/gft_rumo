package com.gft.api.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gft.api.domain.Product;
import com.gft.api.interf.ProductRepositoryCustom;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> , ProductRepositoryCustom {
	
	@Query("SELECT p FROM Product p WHERE p.origin = ?1 and p.product = ?2 and p.quantity = ?3 and p.price = ?4 and p.type = ?5 ")
	Product findProductByParam(String origin, String product, long quantity, Double price, String type);
	
	@Query(value = "SELECT * FROM Product ORDER BY id \n-- #pageable\n", 
		   countQuery = "SELECT count(*) FROM Product",
		   nativeQuery = true)
	Product findAllByPage(Pageable pageable);

	

}
