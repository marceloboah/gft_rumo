package com.gft.api.exception;

public class ProductNotFoundException  extends RuntimeException{
	
	private static final long serialVersionUID = 5754160221785019934L;

	public ProductNotFoundException(Long id) {
		super("Product with id = " + id + " not found in database.");
	}

}
