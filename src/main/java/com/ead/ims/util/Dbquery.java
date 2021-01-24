package com.ead.ims.util;

public class Dbquery {
	private static final String SEARCHPRODUCTBYID = "select * from Products where product_id = ?";
	private static final String SEARCHPRODUCTBYNAME = "select * from Products where product = ?";
	private static final String SEARCHPRODUCTBYMANUFACTURE = "select * from Products where manufacture = ?";
	private static final String SEARCHPRODUCTBYTYPECODE = "select * from Products where type_code = ?";
	private static final String SEARCHPRODUCTBYLOCATIONCODE = "select * from Products where location_code = ?";
	private static final String SEARCHPRODUCTBYMODEL = "select * from Products where model = ?";
	private static final String ALLPRODUCTS = "Select * from Products";
	private static final String ALLPRODUCTSDSC = "Select * from Products order by product_id desc";
	private static final String INSERTPRODUCT = "INSERT INTO Products(product_id, product, model, manufacture, type_code, location_code,msrp,unit_cost,discount_rate,stock_qty) VALUES(?,?,?,?,?,?,?,?,?,?);";
	private static final String UPDATEPRODUCT = "UPDATE PRODUCTS SET";
	private static final String DELETEPRODUCT = "DELETE FROM PRODUCTS WHERE product_id = ? ";
	private static final String authuser = "Select * from customer where username=? and password=?";
	public static String getAuthuser() {
		return authuser;
	}
	public static String getSearchproductbymodel() {
		return SEARCHPRODUCTBYMODEL;
	}
	public static String getSearchproductbyid() {
		return SEARCHPRODUCTBYID;
	}
	public static String getSearchproductbyname() {
		return SEARCHPRODUCTBYNAME;
	}
	public static String getSearchproductbymanufacture() {
		return SEARCHPRODUCTBYMANUFACTURE;
	}
	public static String getSearchproductbytypecode() {
		return SEARCHPRODUCTBYTYPECODE;
	}
	public static String getSearchproductbylocationcode() {
		return SEARCHPRODUCTBYLOCATIONCODE;
	}
	public static String getAllproducts() {
		return ALLPRODUCTS;
	}
	public static String getAllproductsdsc() {
		return ALLPRODUCTSDSC;
	}
	public static String getInsertproduct() {
		return INSERTPRODUCT;
	}
	public static String getUpdateproduct() {
		return UPDATEPRODUCT;
	}
	public static String getDeleteproduct() {
		return DELETEPRODUCT;
	}

}
