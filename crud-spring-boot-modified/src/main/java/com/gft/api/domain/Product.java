package com.gft.api.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@DiscriminatorValue(value = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue
	private long id;
	
	
	@Column(name = "PRODUCT")
	private String product;
	
	@Column(name = "QUANTITY")
	private long quantity;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "ORIGIN")
	private String origin;
	
	@Column(name = "INDUSTRY")
	private String industry;


	
	public Product() {
		super();
		
	}
	
	public Product(long id, String product, long quantity, Double price, String type, String origin, String industry) {
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}



}
