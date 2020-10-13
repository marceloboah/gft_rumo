package com.gft.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public Product getProductsBySearch(String name, Double valmin, Double valmax, Integer paginaatual) {
		Product retorno = new Product();
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
            criteriaQuery.orderBy(cb.asc(ptable.get("product")));

            TypedQuery<Product> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPagetotallines(totalRows);
            retorno.setPagenumber(paginaatual);
            query.setFirstResult(paginaatual * 50);
            query.setMaxResults(50);
            
            List<Product> list = query.getResultList();
            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            retorno.getListProducts().addAll(list);
            return retorno;
        }else {
        	
        	if (valmin > -1 && valmax > -1) {
                
            	criteriaQuery.select(ptable).where(cb.between(ptable.get("price"), valmin, valmax));
            	criteriaQuery.orderBy(cb.asc(ptable.get("product")));
            }
            
        	TypedQuery<Product> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPagetotallines(totalRows);
            retorno.setPagenumber(paginaatual);
            query.setFirstResult(paginaatual * 50);
            query.setMaxResults(50);
            
            List<Product> list = query.getResultList();
            retorno.getListProducts().addAll(list);
            return retorno;
        	
        }
        
    }


	@Override
	public Product getAllProductsByPage(Integer paginaatual) {
		Product retorno = new Product();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
        Root<Product> ptable = criteriaQuery.from(Product.class);
        
        criteriaQuery.select(ptable);
        
        criteriaQuery.select(ptable);
    	criteriaQuery.orderBy(cb.asc(ptable.get("product")));
    	TypedQuery<Product> query = em.createQuery(criteriaQuery);

        int totalRows = query.getResultList().size();
        retorno.setPagetotallines(totalRows);
        retorno.setPagenumber(paginaatual);
        query.setFirstResult(paginaatual * 50);
        query.setMaxResults(50);
        
        List<Product> list = query.getResultList();
        retorno.getListProducts().addAll(list);
        return retorno;
	}


	@Override
	public List<Product> getProductsByList(String name, Double valmin, Double valmax, Integer paginaatual) {
		Product retorno = new Product();
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
            criteriaQuery.orderBy(cb.asc(ptable.get("product")));


            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            TypedQuery<Product> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPagetotallines(totalRows);
            retorno.setPagenumber(paginaatual);
            query.setFirstResult(paginaatual * 50);
            query.setMaxResults(50);
            
            List<Product> list = query.getResultList();
            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            
            return list;
        }else {
        	
        	if (valmin > -1 && valmax > -1) {
                
            	criteriaQuery.select(ptable).where(cb.between(ptable.get("price"), valmin, valmax));
            	criteriaQuery.orderBy(cb.asc(ptable.get("product")));
            }
         
        	//List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
        	TypedQuery<Product> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPagetotallines(totalRows);
            retorno.setPagenumber(paginaatual);
            query.setFirstResult(paginaatual * 50);
            query.setMaxResults(50);
            
            List<Product> list = query.getResultList();
            
            return list;
        	
        }
	}

}