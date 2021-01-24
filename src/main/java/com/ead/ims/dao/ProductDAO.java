
package com.ead.ims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ead.ims.model.Product;
import com.ead.ims.util.ConnectDatabase;
import com.ead.ims.util.Dbquery;

@Component
public class ProductDAO {

	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private Dbquery query;

	public ProductDAO() {
		query = new Dbquery();
	}
	//Method to fetch all products from database
	public ArrayList<Product> getAllProducts() throws ClassNotFoundException, SQLException {
		ArrayList<Product> products = new ArrayList<>();
		try {
			ConnectDatabase.getInstance();		//Get the database instance
			connection = ConnectDatabase.getConnection();		//Get the database connection
			statement = connection.prepareStatement(query.getAllproducts());		//Create the statement containing the SQL query to be executed.
			resultSet = statement.executeQuery();		//Execute the SQL query.
			System.out.println("result" + resultSet.toString());
			while (resultSet.next()) {		//Iterate over each product from the product result set
				Product product = new Product();		//Create product instance to assign product read from result set
				product.setProduct_id(resultSet.getString("product_id"));	//Set product_Id from result set to Product instance.
				product.setProduct_name(resultSet.getString("product"));	//Set product from result set to Product instance.
				product.setModel(resultSet.getString("model"));	//Set model from result set to Product instance.
				product.setManufacture(resultSet.getString("manufacture"));	//Set manufacture from result set to Product instance.
				product.setType_code(resultSet.getString("type_code"));	//Set type_code from result set to Product instance.
				product.setLocation_code(resultSet.getInt("location_code"));	//Set location_code from result set to Product instance.
				product.setMsrp(resultSet.getInt("msrp"));	//Set msrp from result set to Product instance.
				product.setDiscount_rate(resultSet.getFloat("discount_rate"));	//Set discount_rate from result set to Product instance.
				product.setStock_qty(resultSet.getInt("stock_qty"));	//Set stock_qty from result set to Product instance.
				products.add(product);		//Add the product instance to list of products
			}
		} catch (Exception e) {
			System.out.println("exception" + e);
		}
		return products;		//Return the product list to calling method.
		}
	//Method to fetch all products in descending order
	public ArrayList<String[]> getAllProductsDesc() throws ClassNotFoundException, SQLException {
		ArrayList<String[]> products = new ArrayList<String[]>();
		try {
			ConnectDatabase.getInstance();		//Get the database instance
			connection = ConnectDatabase.getConnection();		//Get the database connection
			statement = connection.prepareStatement(query.getAllproductsdsc());		//Create the statement containing the SQL query to be executed.
			resultSet = statement.executeQuery();		//Execute the SQL query.
			System.out.println("result" + resultSet.toString());
			while (resultSet.next()) {		//Iterate over each product from the product result set
				String[] product = new String[10];
				product[0] = resultSet.getString("product_id");		//Set product_Id from result set to Product instance.
				product[1] = resultSet.getString("product");		//Set product from result set to Product instance.
				product[2] = resultSet.getString("model");		//Set model from result set to Product instance.
				product[3] = resultSet.getString("manufacture");	//Set manufacture from result set to Product instance.
				product[4] = resultSet.getString("type_code");		//Set type_code from result set to Product instance.
				product[5] = resultSet.getString("location_code");	//Set location_code from result set to Product instance.
				product[6] = resultSet.getString("msrp");	//Set msrp from result set to Product instance.
				product[7] = resultSet.getString("unit_cost");		//Set unit_cost from result set to Product instance.
				product[8] = resultSet.getString("discount_rate");	//Set discount_rate from result set to Product instance.
				product[9] = resultSet.getString("stock_qty");		//Set stock_qty from result set to Product instance.
				products.add(product);		//Add the product instance to list of products
			}
		} catch (Exception e) {
			System.out.println("exception" + e);
		}
		return products;	//Return the product list to calling method.
	}
	//Method to insert product into the database
	public boolean insertProducts(Product product) throws ClassNotFoundException, SQLException {
		boolean status = false;
		try {
				ConnectDatabase.getInstance();		//Get the database instance
				connection = ConnectDatabase.getConnection();		//Get the database connection
				statement = connection.prepareStatement(query.getInsertproduct());		//Create the statement containing the SQL query to be executed.
				statement.setString(1, product.getProduct_id());		//Set product_id parameter in the query
				statement.setString(2, product.getProduct());		//Set product parameter in the query
				statement.setString(3, product.getModel());		//Set model parameter in the query
				statement.setString(4, product.getManufacture());		//Set manufacture parameter in the query
				statement.setString(5, product.getType_code());		//Set type_code parameter in the query
				statement.setInt(6, product.getLocation_code());		//Set location_code parameter in the query
				statement.setFloat(7, product.getMsrp());		//Set msrp parameter in the query
				statement.setFloat(8, product.getUnit_cost());		//Set unit_cost parameter in the query
				statement.setFloat(9, product.getDiscount_rate());		//Set discount_rate parameter in the query
				statement.setInt(10, product.getStock_qty());		//Set stock_qty parameter in the query
				status = statement.execute();		//Execute the query
			} catch (Exception e) {
				System.out.println("exception" + e);
			}
			return status;		//Return status to the calling method.
	}
		//Method to search a product in the inventory by parameter
	public List<Product> search(String field, String value) {
		List<Product> products = new ArrayList<Product>();
		int value1 = 1;
		if (field.equalsIgnoreCase("locationcode"))
			value1 = Integer.parseInt(value);
		try {
			ConnectDatabase.getInstance();		//Get the database instance
			connection = ConnectDatabase.getConnection();		//Get the database connection
			switch (field) {
			case "id":
				statement = connection.prepareStatement(query.getSearchproductbyid());		//Search query by product_ID
				statement.setString(1, value);
				break;
			case "product":
				statement = connection.prepareStatement(query.getSearchproductbyname());		//Search query by name
				statement.setString(1, value);
				break;
			case "manufacture":
				statement = connection.prepareStatement(query.getSearchproductbymanufacture());		//Search query by manufacture
				statement.setString(1, value);
				break;
			case "model":
				statement = connection.prepareStatement(query.getSearchproductbymodel());		//Search query by model
				statement.setString(1, value);
				break;
			case "typecode":
				statement = connection.prepareStatement(query.getSearchproductbytypecode());		//Search query by typecode
				statement.setString(1, value);
				break;
			case "locationcode":
				statement = connection.prepareStatement(query.getSearchproductbylocationcode());		//Search query by location_code
				statement.setInt(1, value1);
				break;
			}
			resultSet = statement.executeQuery();		//Execute the query
			while (resultSet.next()) {		//Iterate over each product from the product result set
				Product product = new Product();
				product.setProduct_id(resultSet.getString("product_id"));		//Set product_Id from result set to Product instance.
				product.setProduct_name(resultSet.getString("product"));		//Set product from result set to Product instance.
				product.setModel(resultSet.getString("model"));		//Set model from result set to Product instance.
				product.setManufacture(resultSet.getString("manufacture"));		//Set manufacture from result set to Product instance.
				product.setType_code(resultSet.getString("type_code"));		//Set type_code from result set to Product instance.
				product.setLocation_code(resultSet.getInt("location_code"));		//Set location_code from result set to Product instance.
				product.setMsrp(resultSet.getInt("msrp"));		//Set msrp from result set to Product instance.
				product.setDiscount_rate(resultSet.getFloat("discount_rate"));		//Set discount_rate from result set to Product instance.
				product.setStock_qty(resultSet.getInt("stock_qty"));		//Set stock_qty from result set to Product instance.
				products.add(product);		//Add product instance to product list.
			}
		} catch (Exception e) {
			System.out.println("exception" + e);
		}
		return products;		//Return product list to calling method
	}
	//Method to update product in the inventory by parameter
	public int update(String field, String value, String id) {
		int status = 0;
		int value1 = 0;
		double value2 = 0.0;
		String q = "";

		try {
			ConnectDatabase.getInstance();		//Get the database instance
			connection = ConnectDatabase.getConnection();		//Get the database connection
			if (field.equalsIgnoreCase("location_code") | field.equalsIgnoreCase("stock_qty")) {	//Enter if parameter is location_code or stock_qty
				value1 = Integer.parseInt(value);
				q = query.getUpdateproduct() + " " + field + " = " + value1 + " where product_id = '" + id + "'";		//Framing query
				statement = connection.prepareStatement(q);		//Create the statement containing the SQL query to be executed.

			}

			else if (field.equalsIgnoreCase("msrp") | field.equalsIgnoreCase("unit_cost")	//Enter if parameter is msrp or unit_Cost or discount
					| field.equalsIgnoreCase("discount")) {
				value2 = Double.parseDouble(value);
				q = query.getUpdateproduct() + " " + field + " = " + value2 + " where product_id = '" + id + "'";		//Framing query
				statement = connection.prepareStatement(q);		//Create the statement containing the SQL query to be executed.
			} else {
				System.out.println(field + "" + value1 + "" + id);
				q = query.getUpdateproduct() + " " + field + " = '" + value + "' where product_id = '" + id + "'";	//Framing query
				statement = connection.prepareStatement(q);		//Create the statement containing the SQL query to be executed.
			}
			status = statement.executeUpdate();		//Execute the query
		} catch (Exception e) {
			System.out.println("exception" + e);
		}
		return status;		//Return status to the calling method
	}

	//MEthod to delete product in the inventory by passing Product_id
		public int delete(String product_id) {
			int status=0;
			try {
				ConnectDatabase.getInstance();		//Get the database instance
				connection = ConnectDatabase.getConnection();		//Get the database connection
				statement = connection.prepareStatement(query.getDeleteproduct());		//Create the statement containing the SQL query to be executed.
				statement.setString(1, product_id);	
				status = statement.executeUpdate();		//Execute the query
			} catch (Exception e) {
				System.out.println("exception" + e);
			}
			return status;		//Return status to the calling method.
	}
//auntication user with user provided values
	public int authUser(String username,String password) {
		int status=0;
		try {
			ConnectDatabase.getInstance();
			connection = ConnectDatabase.getConnection();
			statement = connection.prepareStatement(query.getAuthuser());
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			resultSet.next();
			if(resultSet.getString("username").equalsIgnoreCase(username)&&resultSet.getString("password").equalsIgnoreCase(password))
				status=1;
		} catch (Exception e) {
			System.out.println("exception" + e);
		}
		return status;
	}
}
