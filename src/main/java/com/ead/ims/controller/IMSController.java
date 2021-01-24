package com.ead.ims.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ead.ims.Service.*;
import com.ead.ims.model.Product;
//import com.ead.ims.util.Database;
import com.ead.ims.util.*;

@Controller
//Controller that has all request mappings of the functionalities such as Upload, Search, Modify, Delete.
public class IMSController {
	ProductINF serviceImpl;
	JavaMailSender emailSender;

	@Autowired
	public IMSController(ProductIMPL serviceImpl, JavaMailSender emailSender) {
		this.serviceImpl = serviceImpl;
		this.emailSender = emailSender;
	}
//this method is for user authentication
	@RequestMapping(value = "/auth/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public int auth(@PathVariable("username") String username,@PathVariable("password") String password) {
			int status=0;
			return status=((ProductIMPL) serviceImpl).auth(username,password);
	}
	//Method to upload data from the CSV file into the database
	@RequestMapping(value = "/uploadcsv")
	@ResponseBody
	public void singleFileUpload(@RequestParam("file") MultipartFile file) {
		List<Product> temp = new ArrayList<Product>();	//Create a list of Products object to assign the CSV data
		temp = FileUpload.upload(file);	//Call upload method to read CSV data and assign list of products to temp.
		for (Product p : temp)	//Iterate over each product in temp
			serviceImpl.insertProduct(p);	//Save each product into the database by calling insertProduct method.
	}
	//Method to display all products in the inventory
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Product> getAll() {
		List<Product> productList = new ArrayList<>();	//Create a list of Products object to assign the products fetched.
		productList = serviceImpl.getAllProducts();		//Fetch all products in the inventory by calling getAllProducts method.
		return productList;		//Return all products fetched to be displayed.
	}
	//Method to display all products in the inventory in descending order
	@RequestMapping(value = "/getAllDESC", method = RequestMethod.GET)
	public @ResponseBody List<String[]> getAllDesc() {
		List<String[]> productList = new ArrayList<String[]>();		//Create a list of Products object to assign the products fetched.
		productList = serviceImpl.getAllProductsDesc();		//Fetch all products in the inventory and save it in the productList variable in descending order 
		return productList;		//Return all products fetched to be displayed.
	}
	//Method to search for product by its parameter
	@RequestMapping(value = "/search/{field}/{value}")
	@ResponseBody
	public List<Product> selectbyid(@PathVariable("field") String field, @PathVariable("value") String value) {
		List<Product> temp = new ArrayList<>();		//Create a list of Products object to assign the products fetched.
		temp = serviceImpl.search(field, value);	//Fetch products from the inventory by passing parameter name and its value and store it in temp.

		return temp;		//Return all products fetched to be displayed.

	}
	//Method to update product parameter by passing its unique product_ID
	@RequestMapping(value = "/update/{field}/{value}/{id}")
	@ResponseBody
	public int update(@PathVariable("field") String field, @PathVariable("value") String value,
			@PathVariable("id") String id) {
		int status = serviceImpl.updateProduct(field, value, id);		//Call updateProduct method to update the product in inventory by passing its unique Product_ID and parameter which has to be updated along with its respective value.

		return status;		//Return status of whether the update was successful or not.

	}
	//Method to delete product in the inventory by passing its unique Product_ID
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public int update(@PathVariable("id") String id) {
		int status = serviceImpl.deleteProduct(id);			//Call deleteProduct method to delete product in the inventory by passing its unique product_ID.
		return status;		//Return status of whether the deletion was successful or not.

	}
// This method is used for sending feedback over email
	@RequestMapping(value = "/email/{name}/{emailId}/{query}/{message}")
	@ResponseBody
	public int sendEmail(@PathVariable("name") String name, @PathVariable("emailId") String email,
			@PathVariable("query") String query, @PathVariable("message") String msg) {
		SimpleMailMessage message = new SimpleMailMessage();//smtp java simple email api
		message.setSubject("Feedback");//set email parametes
		message.setText(msg);
		message.setTo("spsuryaprakash123@gmail.com");
		message.setFrom(email);
		System.out.println("mes" + message.toString());
		emailSender.send(message);
		return 1;
	}
}
