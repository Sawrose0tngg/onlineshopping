package com.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.backend.dao.CartLineDAO;
import com.onlineshopping.backend.dao.ProductDAO;
import com.onlineshopping.backend.dto.Cart;
import com.onlineshopping.backend.dto.CartLine;
import com.onlineshopping.backend.dto.Product;
import com.onlineshopping.model.UserModel;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession session;

	// returns the cart of the user who has logged in
	private Cart getCart() {
		return ((UserModel) session.getAttribute("user")).getCart();
	}

	public List<CartLine> getCartLines() {
		return cartLineDAO.list(getCart().getId());
	}

	public String updateCartLine(int cartLineId, int count) {

		// fetch cartline
		CartLine cartLine = cartLineDAO.get(cartLineId);

		if (cartLine == null) {
			return "result=error";
		} else {
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();

			if (product.getQuantity() <= count) {
				count = product.getQuantity();
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartLine);

			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			return "result=updated";
		}

	}

	public String deleteCartLine(int cartLineId) {

		// fetch the cartline
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		} else {
			// update cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);

			// remove cart line
			cartLineDAO.remove(cartLine);
			return "result=deleted";
		}
	}

	public String addCartLine(int productId) {

		String response = null;

		Cart cart = this.getCart();// get from session
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if (cartLine == null) {
			// add new cartline
			cartLine = new CartLine();
			// fetch product.
			Product product = productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);

			cartLineDAO.add(cartLine);

			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			response = "result=added";
		}

		return response;
	}

}
