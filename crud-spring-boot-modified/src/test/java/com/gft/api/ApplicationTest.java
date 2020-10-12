package com.gft.api;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gft.api.business.ProductBusinessObject;
import com.gft.api.controller.ProductController;
import com.gft.api.domain.Product;
import com.gft.api.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest 
@AutoConfigureMockMvc
public class ApplicationTest {
	
	//classe para testes unitários usando @Autowired 
	
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private  ProductBusinessObject productBusinessObject;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();
        productRepository.deleteAll();
    }

	
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
			System.out.println(product.getProduct());
		}
	}

	
	
}
