package com.gft.api;

import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.gft.api.business.ProductBusinessObject;
import com.gft.api.controller.JsonReaderController;
import com.gft.api.controller.ProductController;
import com.gft.api.domain.Product;
import com.gft.api.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest 
@AutoConfigureMockMvc
public class ApplicationTest {
	
	//classe para testes unitários usando @Autowired 
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);
	
	@Autowired
	private JsonReaderController jsonReaderController;
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private  ProductBusinessObject productBusinessObject;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MockMvc mvc;
	
	@Mock
	private EntityManager manager;
	
	@Before
    public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		manager = Mockito.mock(EntityManager.class);
	    productRepository.deleteAll();
    }
	
	@After
	public void getTesteHttp() throws Exception {
		log.info("getTesteHttp");
	            mvc.perform(MockMvcRequestBuilders.get("/api/teste").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Teste")));
		
	}
	
	/*@After 
	public void getImportHttp() throws Exception {
		log.info("getImportHttp");

		log.info("getTransaction");
	
		mvc.perform(MockMvcRequestBuilders.get("/api/import/validation").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Operação executada!")));
		log.info("perform");

		log.info("commit");
	}*/
	
	/*@After 
	public void getImportHttpRequest() throws Exception {
		HttpUriRequest request = new HttpGet( "http://localhost:9099/api/clean/import");
		 
	    // When
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	}*/
	
	@Test
	public void gravaProduto() {
		productBusinessObject.addProductPopulate();
	}
	
	@Test
	public void getTeste() throws Exception {
		ProductBusinessObject mock = org.mockito.Mockito.mock(ProductBusinessObject.class);
		Mockito.when(mock.getTeste()).thenReturn("Teste");
	}
	
	@Test
	public void addProductTeste() throws Exception {
		ProductBusinessObject mock = org.mockito.Mockito.mock(ProductBusinessObject.class);
		Product product = new Product();
		product.setId(3);
		product.setOrigin("Marcelo");
		product.setPrice(2.9);
		product.setQuantity(20);
		product.setType("145");
		Mockito.when(mock.addProduct(product)).thenReturn(new Product());
	}
	
	@Test
	public void getMockTeste() throws Exception {
		
		ProductController mock = org.mockito.Mockito.mock(ProductController.class);
		Mockito.when(mock.getTeste()).thenReturn("Teste");
	
	}
	
	@Test
	public void  getProdutos() {
		
		List<Product> list = productBusinessObject.getProducts();
		for (Iterator<Product> iterator = list.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			log.info(product.getProduct());
		}
	}

	
	
}
