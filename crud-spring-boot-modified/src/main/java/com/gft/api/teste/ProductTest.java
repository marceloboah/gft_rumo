package com.gft.api.teste;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.context.WebApplicationContext;

import com.gft.api.business.ProductBusinessObject;
import com.gft.api.controller.ProductController;
import com.gft.api.domain.Product;
import com.gft.api.repository.ProductRepository;




@RunWith(MockitoJUnitRunner.class)
public class ProductTest {
	
	//Classe para Testes unitários Usando @InjectMocks não executados na subida do servidor
	
	@InjectMocks
	private ProductController productController;
	
	@InjectMocks
	private  ProductBusinessObject productBusinessObject;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private HttpSession httpSession;
	
	@Autowired
    private WebApplicationContext wac;
	
	
	@Before
	public void init()  {
		MockitoAnnotations.initMocks(this);
		
	}
	
	
	@Test
	public void gravaProdutoTest() {
		productBusinessObject.addProductPopulate();
	}
	
	@Test
	public void  getProdutosTest() {
		
		List<Product> list = productBusinessObject.getProducts();
		for (Iterator<Product> iterator = list.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println(product.getProduct());
		}
	}
	
	
	@Test
	public void gravaProduto() {
		productBusinessObject.addProductPopulate();
	}
	
	@Test
	public void getTeste() throws Exception {
		ProductController mock = org.mockito.Mockito.mock(ProductController.class);
		Mockito.when(mock.getTeste()).thenReturn("Teste");
	}
	
	@Test
	public void addProductTeste() throws Exception {
		ProductController mock = org.mockito.Mockito.mock(ProductController.class);
		Product product = new Product();
		//product.setId(3);
		product.setOrigin("Marcelo");
		product.setPrice("2");
		product.setQuantity(20);
		product.setType("145");
		Mockito.when(mock.addProduct(product)).thenReturn(product);
	}
	
	
	@Test
	public void  getProdutos() {
		
		List<Product> list = productBusinessObject.getProducts();
		for (Iterator<Product> iterator = list.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			p(product.getProduct());
		}
	}
	
	private static void p(String text) {
        System.out.println(text);
    }
	
}
