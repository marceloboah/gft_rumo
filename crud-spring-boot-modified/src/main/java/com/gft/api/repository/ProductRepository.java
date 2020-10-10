package com.gft.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gft.api.domain.Product;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {

}
