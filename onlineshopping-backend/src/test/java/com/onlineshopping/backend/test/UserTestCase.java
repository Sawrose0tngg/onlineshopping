package com.onlineshopping.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.onlineshopping.backend.dao.UserDAO;
import com.onlineshopping.backend.dto.Address;
import com.onlineshopping.backend.dto.Cart;
import com.onlineshopping.backend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;

	private static UserDAO userDao;
	private User user;
	private Cart cart;
	private Address address;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.onlineshopping.backend");
		context.refresh();

		userDao = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test public void addUser() { user = new User();
	 * user.setFirstName("sawrose"); user.setLastName("tamang");
	 * user.setEmail("sawrose@gmail.com"); user.setContactNumber("9813059225");
	 * user.setRole("USER"); user.setPassword("sawrose");
	 * assertEquals("Failed to add new user", true, userDao.addUser(user));
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
	 * address.setAddressLineTwo("Near Kaabil Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); address.setBilling(true);
	 * address.setUserId(user.getId()); assertEquals("Failed to Add Address", true,
	 * userDao.addAddress(address));
	 * 
	 * if (user.getRole().equals("USER")) { cart = new Cart(); //
	 * cart.setId(user.getId()); cart.setUser(user);
	 * 
	 * assertEquals("Failed to add cart", true, userDao.addCart(cart)); }
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
	 * address.setAddressLineTwo("Near Kudrat Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); address.setShipping(true);
	 * address.setUserId(user.getId());
	 * assertEquals("Failed to add the shipping address!", true,
	 * userDao.addAddress(address));
	 * 
	 * }
	 */
	/*
	 * @Test public void addUser() { user = new User();
	 * user.setFirstName("sawrose"); user.setLastName("tamang");
	 * user.setEmail("sawrose@gmail.com"); user.setContactNumber("9813059225");
	 * user.setRole("USER"); user.setPassword("sawrose");
	 * 
	 * if (user.getRole().equals("USER")) { cart = new Cart(); //
	 * cart.setId(user.getId()); cart.setUser(user);
	 * 
	 * user.setCart(cart); } assertEquals("Failed to add new user", true,
	 * userDao.addUser(user)); }
	 */
	/*
	 * @Test public void addUser() { user = userDao.getByEmail("sawrose@gmail.com");
	 * cart = user.getCart(); cart.setGrandTotal(1000); cart.setCartLines(5);
	 * assertEquals("Failed to update cart", true, userDao.updateCart(cart)); }
	 */
	/*@Test
	public void testAddresses() {
		user = new User();
		user.setFirstName("sawrose");
		user.setLastName("tamang");
		user.setEmail("sawrose@gmail.com");
		user.setContactNumber("9813059225");
		user.setRole("USER");
		user.setPassword("sawrose");
		assertEquals("Failed to add new user", true, userDao.addUser(user));

		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//attached user to address.
		address.setUser(user);
		assertEquals("Failed to Add Address", true, userDao.addAddress(address));

		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		address.setUser(user);
		assertEquals("Failed to add the shipping address!", true, userDao.addAddress(address));

	}*/
	/*@Test
	public void testAddAddress() {
		
		user = userDao.getByEmail("sawrose@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("400005");
		address.setShipping(true);
		
		//attached user to address.
		address.setUser(user);
		assertEquals("Failed to Add Address", true, userDao.addAddress(address));
		
	}*/
	
	@Test
	public void testGetAddresses() {
		user = userDao.getByEmail("sawrose@gmail.com");
		assertEquals("Failed to fetch the list of address", 2, userDao.listShippingAddresses(user).size());
		assertEquals("Failed to fetch the billing address", "Mumbai", userDao.getBillingAddress(user).getCity());
		
//		Alternate from id in which it will prevent from running multiple query.
//		assertEquals("Failed to fetch the list of address", 2, userDao.listShippingAddresses(user.getId()).size());
//		assertEquals("Failed to fetch the billing address", "Mumbai", userDao.getBillingAddress(user.getId()).getCity());
	}
	
	
}
