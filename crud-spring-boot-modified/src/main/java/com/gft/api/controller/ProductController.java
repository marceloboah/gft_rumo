package com.gft.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.business.ProductBusinessObject;
import com.gft.api.domain.Product;


@RestController
@RequestMapping("api")
@CrossOrigin({"*"})
public class ProductController {
	@Autowired
	private ProductBusinessObject productBusinessObject;
	
	@GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productBusinessObject.getProductById(id);
    }
	
	@GetMapping("products/{name}/valmin/{valmin}/valmax/{valmax}")
    public List<Product> getProductsBySearch(@PathVariable("name") String name, @PathVariable("valmin") String valmin,@PathVariable("valmax") String valmax) {
        return productBusinessObject.getProductsBySearch(name, valmin,valmax);
    }
	
	@GetMapping("products")
    public List<Product> getProducts() {
        return productBusinessObject.getProducts();
    }
	
	@PutMapping("product")
    public Product editProduct(@RequestBody Product product) {
		return productBusinessObject.editProduct(product);
    }
	
	@PostMapping("product")
    public Product addProduct(@RequestBody Product product) {
		return productBusinessObject.addProduct(product);
    }
    
    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable("id") Long number) {
    	productBusinessObject.deleteProduct(number);
    }
    
    @GetMapping("product/populate")
    public List<Product> getPopulateProducts() {
    	productBusinessObject.addProductPopulate();
        return productBusinessObject.getProducts();
    }
    
    @GetMapping("teste")
    public String getTeste() {
        return productBusinessObject.getTeste();
    }
	


}
