package com.gft.api.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.gft.api.domain.Product;
import com.gft.api.dto.PaginationDTO;
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
	
	public Product getAllProductsByPage(Integer paginaatual) {
		Product retorno = (Product) productRepository.getAllProductsByPage(paginaatual);
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPagenumber(),retorno.getPagetotallines());
		retorno.setPagination(pagination);
        return retorno;
		
	}
	
	//configurar Pageable
	@SuppressWarnings({ "unchecked", "null" })
	public List<Product> getProductsByPage(Integer page) {
		Pageable pageable = null;
		pageable.setMaxPageSize(50);
    	pageable.setDefaultPageSize(50);
    	pageable.setPageParameter(String.valueOf(page));
        return (List<Product>) productRepository.findAllByPage(pageable);
    }
    
    public Product getProductsBySearch(String name, String valmin,String valmax, Integer paginaatual) {
    	Double min = null;
    	Double max = null;
		if(valmin != null) {
			min = Double.valueOf(valmin);		
		}
		if(valmax != null) {
			max = Double.valueOf(valmax);		
		}
		Product retorno = (Product) productRepository.getProductsBySearch(name, min, max, paginaatual);
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPagenumber(),retorno.getPagetotallines());
		retorno.setPagination(pagination);
        return retorno;
    }
    
    
    public PaginationDTO calculaPaginacao(Integer pagenumber, Integer totallines) {
    	PaginationDTO paginationDTO = new PaginationDTO();
    	
    	paginationDTO.setMaxperpage(50); 
    	paginationDTO.setPagenumber(pagenumber);
    	paginationDTO.setTotallines(totallines);
    	paginationDTO.setTotalpages(totallines/50);
    	
    	if((pagenumber -10) > 0) { //-10
    		paginationDTO.setShowbackten(true);
    	}else{
    		paginationDTO.setShowbackten(false);
    	}
    	
    	if((pagenumber -1) >= 1) {
    		paginationDTO.setShowbackone(true);
    	}else{
    		paginationDTO.setShowbackone(false);
    	}
    	
    	if((pagenumber +1) <= (totallines/50)) {
    		paginationDTO.setShownextone(true);
    	}else{
    		paginationDTO.setShownextone(false);
    	}
    	
    	if((pagenumber +10) < (totallines/50)) {//+10
    		paginationDTO.setShownextten(true);
    	}else{
    		paginationDTO.setShownextten(false);
    	}
		return paginationDTO;
    }
    
    
    public void cleanImport() {
    	productRepository.deleteAll();
    }
	
    public Product editProduct(Product product) {
    	return productRepository.save(product);
    }
    
    public Product addProduct(Product product) {
    	boolean productExists = productRepository.findById(product.getId()).isPresent();
    	if (productExists) {
    	//	throw new ProductAlreadyExistsException(product);
    	}
    	return productRepository.save(product);
    }
    
    public Product addProductWithValidation(Product product) {
    	Product productVerify = new Product();
    	productVerify = productRepository.findProductByParam(product.getOrigin(),product.getProduct(),product.getQuantity(),product.getPrice(),product.getType());
    	if(productVerify != null && productVerify.getProduct().equals("TKC")) {
    		System.out.println(productVerify.getId());
    	}
    	if (productVerify != null && productVerify.getId()>1) {
    		product.setId(productVerify.getId());
    		return productRepository.save(product);//Atualiza produto existente
    	}
    	return productRepository.save(product);//adiciona novo produto
    }
    
    public void deleteProduct(Long id) {
    	productRepository.deleteById(id);
    }

	public Product addProductPopulate() {
		Product product = new Product();
		product.setId(2);
		product.setOrigin("Marcelo");
		//product.setPrice(2);
		product.setQuantity(20);
		product.setType("145");
		product = productRepository.save(product);
		return product;
		
	}
	
	public void leituraJson() {
		Product product = new Product();
		product.setId(1);
		product.setOrigin("Marcelo");
		//product.setPrice(2);
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
	 
	 
	 public String readFiles(Integer kindImport)  {
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
				          addLinesToBase(filename, kindImport);
				        }
				      }.start();
			}
			return "Operação executada!";
	 }
	 
	 
	 public void addLinesToBase(String file)  {//chamada anterior envia 1
		 Integer kindImport = 1;
		 addLinesToBase(file, kindImport);
	 }
	 
	 public void addLinesToBase(String file,Integer kindImport)  {//parametro para chamada nova com validação
		 JSONParser parser = new JSONParser();
		 Object obj;
		 try {
				obj = parser.parse(new FileReader("C:/data/"+file));
		        JSONObject jsonObject = (JSONObject)obj;
		        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
		        
		        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
					JSONObject lineObject = (JSONObject) iterator.next();
					System.out.println(file);
					System.out.println(lineObject);
					String product = (String) lineObject.get("product");
				    //System.out.println(product);
				    long quantity = (long) lineObject.get("quantity");
				    //System.out.println(quantity+"");
				    String pricestr = (String) lineObject.get("price");
				    pricestr = pricestr.substring(1, pricestr.length());  
				    Double price = new Double(pricestr);
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
				    if(kindImport >= 2) {
				    	productToInsert.setIndustry(industry);
				    	this.addProductWithValidation(productToInsert);
				    }else {
				    	productRepository.save(productToInsert);
				    }
				    
				    
				    
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
