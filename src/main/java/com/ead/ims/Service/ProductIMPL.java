package com.ead.ims.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ead.ims.dao.ProductDAO;

import com.ead.ims.model.Product;

@Component
public class ProductIMPL implements ProductINF {
	ProductDAO dao;
	List<String[]> temp;

	@Autowired
	public ProductIMPL(ProductDAO dao) {
		this.dao = dao;
		temp = new ArrayList<>();
	}

	private static Logger log = Logger.getLogger(ProductIMPL.class);

	public int auth(String username, String pwd) {
		int status = 0;
		status = dao.authUser(username, pwd);
		return status;
	}
	//Method to fetch all products in the inventory
	@Override
	public List<Product> getAllProducts() {

		ArrayList<Product> showProducts = new ArrayList<>();
		try {
			showProducts = dao.getAllProducts();//Call Dao layer method to fetch all products from inventory
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return showProducts;

	}
	//Method to fetch all products in the inventory in descending order
	@Override
	public List<String[]> getAllProductsDesc() {
		try {
			temp = dao.getAllProductsDesc();
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return temp;
	}
	//Method to delete product in inventory
	@Override
	public int deleteProduct(String product_id) {
		int status = 0;
		try {
			status = dao.delete(product_id);
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return status;
	}
	//Method to insert product into the database
	@Override
	public boolean insertProduct(Product product) {
		boolean status = false;
		try {
			status = dao.insertProducts(product);
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return status;
	}
	//Method to search product in the inventory using parameter
	@Override
	public List<Product> search(String field, String value) {
		List<Product> showProducts = new ArrayList<Product>();
		try {
			showProducts = dao.search(field, value);
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return showProducts;
	}
	//Method to update product in the inventory using parameter
	@Override
	public int updateProduct(String field, String value, String id) {
		int status = 0;
		try {
			status = dao.update(field, value, id);
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
		}
		return status;
	}

}
