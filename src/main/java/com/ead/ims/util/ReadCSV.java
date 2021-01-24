package com.ead.ims.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.ead.ims.model.Product;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class ReadCSV {
	private static  String line[];
	private static List<Product> product;
	
	public static List<Product> readfile(String filename){
	try { 
		product=new ArrayList<Product>();
        FileReader filereader = new FileReader(filename); 
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build(); 
        CSVReader csvReader = new CSVReaderBuilder(filereader)
        						  .withSkipLines(1)
                                  .withCSVParser(parser) 
                                  .build(); 

        while ((line = csvReader.readNext()) != null) {
            Product temp_prod = new Product();
            temp_prod.setProduct_id(line[0]);
            temp_prod.setProduct(line[1]);
            temp_prod.setModel(line[2]);
            temp_prod.setManufacture(line[3]);
            temp_prod.setType_code(line[4]);
            temp_prod.setLocation_code(Integer.parseInt(line[5]));
            temp_prod.setMsrp(Float.parseFloat(line[6]));
            temp_prod.setUnit_cost(Float.parseFloat(line[7]));
            temp_prod.setDiscount_rate(Float.parseFloat(line[8]));
            temp_prod.setStock_qty(Integer.parseInt(line[9]));
            product.add(temp_prod);

        }

    } 
    catch (Exception e) { 
        e.printStackTrace(); 
    }
	return product;
	}

}
