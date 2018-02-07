package com.onlineshopping.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.onlineshopping.backend.dao.ProductDAO;
import com.onlineshopping.backend.dto.Product;

public class ProductTest {

	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDao;
	
	private Product product;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.onlineshopping.backend");
		context.refresh();
		productDao = (ProductDAO) context.getBean("productDao");
	}
	
	/*@Test 
	public void testCRUDProduct() {
		
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description on Oppo mobile");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong while inserting new product", true, productDao.add(product));

		product = new Product();
		product = productDao.get(10);
		product.setName("Samsung Galaxy young");
		assertEquals("Something went wrong while updating product", true, productDao.update(product));
		
		assertEquals("Something went wrong while deleting product", true, productDao.delete(product));
		
		assertEquals("Something went wrong while viewing products", 8, productDao.list().size());
		
	}
	
	@Test
	public void testListActiveProduct() {
		
		assertEquals("Something went wrong while viewing active products", 7, productDao.listActiveProducts().size());
	}
	@Test
	public void testListActiveByProductID() {
		
		assertEquals("Something went wrong while viewing active products by Id", 6, productDao.listActiveProductsByCategory(3).size());
	}*/
	@Test
	public void testLatestAvtiveProduct() {
		
		assertEquals("Something went wrong while viewing latest active products", 3, productDao.getLatestActiveProducts(3).size());
	}
}
