package com.onlineshopping.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.onlineshopping.backend.dao.CategoryDAO;
import com.onlineshopping.backend.dto.Category;

public class CategoryTest {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDao;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.onlineshopping.backend");
		context.refresh();

		categoryDao = (CategoryDAO) context.getBean("categoryDao");
	}

	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Television");
		category.setDescription("This is about television.");
		category.setImageUrl("CAT_1.png");

		assertEquals("Failed to add category", true, categoryDao.add(category));
	}
	@Test
	public void testGetCategory() {
		category = categoryDao.get(3);
		assertEquals("Fail to get the cateogry", "Television", category.getName());
	}
	@Test
	public void updateCategory() {
		category = categoryDao.get(2);
		category.setName("Mobile");
		category.setDescription("This is about mobile.");
		category.setImageUrl("CAM_1.png");

		assertEquals("Failed to add category", true, categoryDao.update(category));
	}
	@Test
	public void deleteCategory() {
		category = categoryDao.get(3);
		
		assertEquals("Failed to delete category", true, categoryDao.delete(category));
	}
	@Test
	public void getAllCategory() {
		
		assertEquals("Failed to get all categories", 2, categoryDao.list().size());
	}*/
	
	@Test
	public void testCRUDCategory() {
		
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is about laptop.");
		category.setImageUrl("CAL_1.png");

		assertEquals("Failed to add category", true, categoryDao.add(category));
		
		category = categoryDao.get(2);
		category.setName("MyLaptop");
		assertEquals("Failed to add category", true, categoryDao.update(category));
		
		category.setActive(false);
		assertEquals("Failed to add category", true, categoryDao.delete(category));
	
		assertEquals("Failed to get all categories", 2, categoryDao.list().size());
	}
}
