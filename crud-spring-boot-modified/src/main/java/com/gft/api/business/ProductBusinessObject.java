package com.gft.api.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.api.domain.Product;
import com.gft.api.exception.ProductAlreadyExistsException;
import com.gft.api.exception.ProductNotFoundException;
import com.gft.api.repository.ProductRepository;



@Service
public class ProductBusinessObject {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product getProductById(Long id) {
		 return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
   }
	
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }
    
    public List<Product> getProductsBySearch(String name, String valmin,String valmax) {
        return (List<Product>) productRepository.findAll();
    }
    
    
    
    public void cleanImport() {
    	productRepository.deleteAll();
    }
	
    public Product editProduct(Product product) {
    	return productRepository.save(product);
    }
    
    public Product addProduct(Product product) {
    	boolean personaExists = productRepository.findById(product.getId()).isPresent();
    	if (personaExists) {
    		throw new ProductAlreadyExistsException(product);
    	}
    	return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
    	productRepository.deleteById(id);
    }

	public Product addProductPopulate() {
		Product product = new Product();
		product.setId(2);
		product.setOrigin("Marcelo");
		product.setPrice("2");
		product.setQuantity(20);
		product.setType("145");
		product = productRepository.save(product);
		return product;
		
	}
	
	public void leituraJson() {
		Product product = new Product();
		product.setId(1);
		product.setOrigin("Marcelo");
		product.setPrice("2");
		product.setQuantity(20);
		product.setType("145");
		productRepository.save(product);
		
	}
	
	 public String getTeste() {
		    return "Teste";
	 }
	 
	 public String readFiles()  {
			System.out.println("Iniciar leitura");
			Set<String> fileLists = new HashSet<>();
			fileLists = Stream.of(new File("C:/data/").listFiles())
		      .filter(file -> !file.isDirectory())
		      .map(File::getName)
		      .collect(Collectors.toSet());
			
			for (String filename : fileLists) {
				System.out.println(filename);
				new Thread("" + filename){
				        public void run(){
				          System.out.println("Thread:  running");
				          addLinesToBase(filename);
				        }
				      }.start();
			}
			
			 
			
			return "Operação executada!";

	 }
	 
	 public void addLinesToBase(String file)  {
		 JSONParser parser = new JSONParser();
		 Object obj;
		 try {
				obj = parser.parse(new FileReader("C:/data/"+file));
		        JSONObject jsonObject = (JSONObject)obj;
		        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
		        @SuppressWarnings("unchecked")
				Iterator<JSONObject> iterator = jsonArray.iterator();
		        
				while (iterator.hasNext()) {
					if(iterator.next().equals(null)) {
						return;
					}
					JSONObject lineObject = iterator.next();
					System.out.println(file);
					System.out.println(iterator.next());
					System.out.println(iterator.next());
					String product = (String) lineObject.get("product");
				    //System.out.println(product);
				    long quantity = (long) lineObject.get("quantity");
				    //System.out.println(quantity+"");
				    String price = (String) lineObject.get("price");
				    //System.out.println(price);
				    String origin = (String) lineObject.get("origin");
				    //System.out.println(origin);
				    String industry = (String) lineObject.get("industry");
				    //System.out.println(industry);
				    String type = (String) lineObject.get("type");
				    //System.out.println(type);
				    
				    Product productToInsert = new Product();
				   // productToInsert.setId(2);
				    productToInsert.setProduct(product);
				    productToInsert.setOrigin(origin);
				    productToInsert.setPrice(price);
				    productToInsert.setQuantity(quantity);
				    productToInsert.setType(type);
				    productRepository.save(productToInsert);
				    
				}
				
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	 }
}
