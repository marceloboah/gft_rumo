package com.gft.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.gft.api.domain.Product;
import com.gft.api.interf.ProductCustomMethods;

@Repository
public class ProductRepositoryImpl implements ProductCustomMethods{

    @PersistenceContext
    private EntityManager em;

	
	@Override
    public List<Product> getProductsBySearch(String name, Double valmin, Double valmax) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
        Root<Product> ptable = criteriaQuery.from(Product.class);
        
        criteriaQuery.select(ptable);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (name !=  null && !name.equals("null")) {
            predicates.add(cb.like(ptable.get("product"), name));
            if (valmin > -1 && valmax > -1) {
                
                predicates.add(cb.between(ptable.get("price"), valmin, valmax));
            }
            
            criteriaQuery.select(ptable).where(predicates.toArray(new Predicate[predicates.size()]));
           
            List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            return list;
        }else {
        	
        	if (valmin > -1 && valmax > -1) {
                
            	criteriaQuery.select(ptable).where(cb.between(ptable.get("price"), valmin, valmax));
            }
            
            List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            return list;
        	
        }
        
    }

}