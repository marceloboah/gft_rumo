package com.gft.api.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;



import com.gft.api.domain.Product;
import com.gft.api.dto.PaginationDTO;
import com.gft.api.dto.ProductDTO;
import com.gft.api.exception.ProductNotFoundException;
import com.gft.api.repository.ProductRepository;



@Service
public class ProductBusinessObject {
	
	private static final Logger log = LoggerFactory.getLogger(ProductBusinessObject.class);
	
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
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPageNumber(),retorno.getPageTotalLines());
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
    
    public Product getProductsBySearch(String name, String floor,String valmax, Integer page) {
    	Double min = null;
    	Double max = null;
		if(!floor.equals(null)  && !floor.equals("null")) {
			min = Double.valueOf(floor);		
		}
		if(!valmax.equals(null)  && !valmax.equals("null")) {
			max = Double.valueOf(valmax);		
		}
		Product retorno = (Product) productRepository.getProductsBySearch(name, min, max, page);
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPageNumber(),retorno.getPageTotalLines());
		retorno.setPagination(pagination);
        return retorno;
    }
    
    
    
    public List<ProductDTO> getProductsByList(String name, String valmin,String valmax, Integer page) {
    	Double min = null;
    	Double max = null;
		if(valmin != null && valmin != "null") {
			min = Double.valueOf(valmin);		
		}
		if(valmax != null && valmax != "null") {
			max = Double.valueOf(valmax);		
		}
		List<ProductDTO> lista = new ArrayList<ProductDTO>();
		List<Product> retorno = (List<Product>) productRepository.getProductsByList(name, min, max, page);
		if(retorno.size()>0) {//Coloca no DTO para melhorar a visualização devido aos Transients montados em Product
			for (Product product : retorno) {
				ProductDTO p = new ProductDTO();
				p.setProduct(product.getProduct());
				p.setQuantity(product.getQuantity());
				p.setPrice(product.getPrice());
				p.setType(product.getType());
				p.setOrigin(product.getOrigin());
				p.setIndustry(product.getIndustry());
				lista.add(p);
			}
			
		}
		
        return lista;
    }
    
    
    public PaginationDTO calculaPaginacao(Integer pageNumber, Integer totalLines) {
    	PaginationDTO paginationDTO = new PaginationDTO();
    	
    	paginationDTO.setMaxPerPage(50); 
    	paginationDTO.setPageNumber(pageNumber);
    	paginationDTO.setTotalLines(totalLines);
    	paginationDTO.setTotalPages(totalLines/50);
    	
    	if((pageNumber -10) > 0) { //-10
    		paginationDTO.setShowBackTen(true);
    	}else{
    		paginationDTO.setShowBackTen(false);
    	}
    	
    	if((pageNumber -1) >= 1) {
    		paginationDTO.setShowBackOne(true);
    	}else{
    		paginationDTO.setShowBackOne(false);
    	}
    	
    	if((pageNumber +1) <= (totalLines/50)) {
    		paginationDTO.setShowNextOne(true);
    	}else{
    		paginationDTO.setShowNextOne(false);
    	}
    	
    	if((pageNumber +10) < (totalLines/50)) {//+10
    		paginationDTO.setShowNextTen(true);
    	}else{
    		paginationDTO.setShowNextTen(false);
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
		    log.info("Iniciar leitura");
			Set<String> fileLists = new HashSet<>();
			fileLists = Stream.of(new File("C:/data/").listFiles())
		      .filter(file -> !file.isDirectory())
		      .map(File::getName)
		      .collect(Collectors.toSet());
			
			for (String filename : fileLists) {
				log.info(filename);
				new Thread("" + filename){
				        public void run(){
				        	log.info("Thread:  running");
				          addLinesToBase(filename);
				        }
				      }.start();
			}
			return "Operação executada!";
	 }
	 
	 
	 public String readFiles(Integer kindImport)  {
		    log.info("Iniciar leitura");
			Set<String> fileLists = new HashSet<>();
			fileLists = Stream.of(new File("C:/data/").listFiles())
		      .filter(file -> !file.isDirectory())
		      .map(File::getName)
		      .collect(Collectors.toSet());
			
			for (String filename : fileLists) {
				log.info(filename);
				new Thread("" + filename){
				        public void run(){
				          log.info("Thread:  running");
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
					//log.info(file);
					//log.info(String.valueOf(lineObject));
					String product = (String) lineObject.get("product");
				    //log.info(product);
				    long quantity = (long) lineObject.get("quantity");
				    //log.info(quantity+"");
				    String pricestr = (String) lineObject.get("price");
				    pricestr = pricestr.substring(1, pricestr.length());  
				    Double price = new Double(pricestr);
			        //log.info(price);
				    String origin = (String) lineObject.get("origin");
				    //log.info(origin);
				    String industry = (String) lineObject.get("industry");
				    //log.info(industry);
				    String type = (String) lineObject.get("type");
				    //log.info(type);
				    
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
