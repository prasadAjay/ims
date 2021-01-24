package com.ead.ims.Service;

import java.util.List;

import com.ead.ims.model.Product;

public interface ProductINF {

	public List<Product> getAllProducts();

	public List<String[]> getAllProductsDesc();

	public List<Product> search(String field, String value);

	public int updateProduct(String field, String value, String id);

	public boolean insertProduct(Product product);

	public int deleteProduct(String id);


}