package com.gft.api.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.Pageable;

import com.gft.api.dto.PaginationDTO;


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
	
	@Transient
	private Set<Product> listProducts = new HashSet<Product>();

	@Transient
	private Pageable pageable;
	
	@Transient
	private Integer pagenumber;
	
	@Transient
	private Integer pagetotallines;
	
	@Transient
	private PaginationDTO pagination;

	
	public Product() {
		super();
		
	}

	public Product(long id, String product, long quantity, Double price, String type, String origin, String industry,
			Set<Product> listProducts, Pageable pageable, Integer pagenumber, Integer pagetotallines,
			PaginationDTO pagination) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.type = type;
		this.origin = origin;
		this.industry = industry;
		this.listProducts = listProducts;
		this.pageable = pageable;
		this.pagenumber = pagenumber;
		this.pagetotallines = pagetotallines;
		this.pagination = pagination;
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
	public Set<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(Set<Product> listProducts) {
		this.listProducts = listProducts;
	}
	public Pageable getPageable() {
		return pageable;
	}
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}
	public Integer getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}
	public Integer getPagetotallines() {
		return pagetotallines;
	}
	public void setPagetotallines(Integer pagetotallines) {
		this.pagetotallines = pagetotallines;
	}



	public PaginationDTO getPagination() {
		return pagination;
	}



	public void setPagination(PaginationDTO pagination) {
		this.pagination = pagination;
	}

}
