package com.gft.api.exception;

import com.gft.api.domain.Product;

public class ProductAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 2871523074897812823L;

	public ProductAlreadyExistsException(Product id) {
		super("Product id = " + id + " already exists in database.");
	}

}
