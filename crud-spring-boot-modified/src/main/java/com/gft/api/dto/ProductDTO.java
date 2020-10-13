package com.gft.api.dto;

import javax.persistence.Column;

public class ProductDTO {
	
private long id;
	
	
	private String product;
	private long quantity;
	private Double price;
	private String type;
	private String origin;
	private String industry;
	
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(long id, String product, long quantity, Double price, String type, String origin,
			String industry) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.type = type;
		this.origin = origin;
		this.industry = industry;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	
}
