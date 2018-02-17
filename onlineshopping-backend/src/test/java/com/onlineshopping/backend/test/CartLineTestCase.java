package com.onlineshopping.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.onlineshopping.backend.dao.*;
import com.onlineshopping.backend.dto.*;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;

	private CartLine cartLine = null;
	private Product product = null;
	private User user = null;
	private Cart cart = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.onlineshopping.backend");
		context.refresh();
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		productDAO = (ProductDAO) context.getBean("productDao");
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*@Test
	public void testAddNewCartLine() {
		user = userDAO.getByEmail("sa@gmail.com");

		cart = user.getCart();

		product = productDAO.get(1);

		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);

		assertEquals("Failed to add the CartLine!", true, cartLineDAO.add(cartLine));

		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		assertEquals("Failed to update the cart!", true, cartLineDAO.updateCart(cart));
	}*/

	// @Test
	// public void testUpdateCartLine() {
	//
	// // fetch the user and then cart of that user
	// User user = userDAO.getByEmail("absr@gmail.com");
	// Cart cart = user.getCart();
	//
	// cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
	//
	// cartLine.setProductCount(cartLine.getProductCount() + 1);
	//
	// double oldTotal = cartLine.getTotal();
	//
	// cartLine.setTotal(cartLine.getProduct().getUnitPrice() *
	// cartLine.getProductCount());
	//
	// cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
	//
	// assertEquals("Failed to update the CartLine!", true,
	// cartLineDAO.update(cartLine));
	//
	// }

}
