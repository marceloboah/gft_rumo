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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.business.ProductBusinessObject;
import com.gft.api.domain.Product;
import com.gft.api.repository.ProductRepository;


@RestController
@RequestMapping("api")
@CrossOrigin({"*"})
public class ProductController {
	@Autowired
	private ProductBusinessObject productBusinessObject;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productBusinessObject.getProductById(id);
    }
	
	@GetMapping("products/find")
    public Product getProductsBySearch(
    		@RequestParam(value="name",required=false)  String name,
    	   	@RequestParam(value="floor",required=false)  String floor,
    	   	@RequestParam(value="valmax",required=false) String valmax,
    	   	@RequestParam(value="paginaatual",required=false) Integer paginaatual) {
		Product retorno = productBusinessObject.getProductsBySearch(name, floor, valmax, paginaatual);
		return retorno;
    }
	
	
	@GetMapping("products")
    public Product getProducts(@RequestParam(value="page",required=false)  Integer page) {
        return productBusinessObject.getAllProductsByPage(page);
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
