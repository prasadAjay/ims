package com.ead.ims.model;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class Product {
	@Id 
	private String product_id;
	@Field
	private String product;
	@Field
	private String model;
	@Field
	private String manufacture;
	@Field
	private String type_code;
	@Field
	private int location_code;
	@Field
	private float msrp;
	@Field
	private float unit_cost;
	@Field
	private float discount_rate;
	@Field
	private int stock_qty;
	
	public Product(){}
	
	
	public Product(String product_id, String product, String model, String manufacture, String type_code,
			int location_code, float msrp, float unit_cost, float discount_rate, int stock_qty) {
		super();
		this.product_id = product_id;
		this.product = product;
		this.model = model;
		this.manufacture = manufacture;
		this.type_code = type_code;
		this.location_code = location_code;
		this.msrp = msrp;
		this.unit_cost = unit_cost;
		this.discount_rate = discount_rate;
		this.stock_qty = stock_qty;
	}

	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public int getLocation_code() {
		return location_code;
	}
	public void setLocation_code(int location_code) {
		this.location_code = location_code;
	}
	public float getMsrp() {
		return msrp;
	}
	public void setMsrp(float msrp) {
		this.msrp = msrp;
	}
	public float getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(float unit_cost) {
		this.unit_cost = unit_cost;
	}
	public float getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(float discount_rate) {
		this.discount_rate = discount_rate;
	}
	public int getStock_qty() {
		return stock_qty;
	}
	public void setStock_qty(int stock_qty) {
		this.stock_qty = stock_qty;
	}
	
	public String getProduct_id()
	{
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product;
	}
	public void setProduct_name(String product_name) {
		this.product = product_name;
	}
	
}

