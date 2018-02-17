package com.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshopping.backend.dao.CategoryDAO;
import com.onlineshopping.backend.dao.ProductDAO;
import com.onlineshopping.backend.dto.Category;
import com.onlineshopping.backend.dto.Product;
import com.onlineshopping.exception.ProductNotFoundException;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDao;
	@Autowired
	private ProductDAO productDao;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);

		logger.info("Inside PageController Index Method-INFO");
		logger.debug("Inside PageController Index Method-DEBUG");

		// passing list of categoires.
		mv.addObject("categories", categoryDao.list());

		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/products")
	public ModelAndView productList() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Products");
		mv.addObject("userClickProduct", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}

	// show all products based on category
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Products");
		mv.addObject("userClickAllProduct", true);

		// passing list of categoires.
		mv.addObject("categories", categoryDao.list());

		return mv;
	}

	// show all products based on category id
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		// categorydao to fetch a single category.
		Category category = null;
		category = categoryDao.get(id);

		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProduct", true);

		// passing single of category.
		mv.addObject("category", category);
		// passing list of categoires.
		mv.addObject("categories", categoryDao.list());

		return mv;
	}

	/* Show Single Product */
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");

		Product product = productDao.get(id);

		if (product == null)
			throw new ProductNotFoundException();

		// Update view count
		product.setViews(product.getViews() + 1);
		productDao.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		return mv;
	}

	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Register");
		return mv;
	}

	// Login
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");

		if (error != null) {
			mv.addObject("message", "Invalid Username and Password");
		}

		if (logout != null) {
			mv.addObject("message", "User Has Successfully Logout");
		}
		mv.addObject("title", "Login");
		return mv;
	}

	// Access Denied
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403-Access Denied!!");
		mv.addObject("errorTitle", "Aha! Caught You");
		mv.addObject("errorDescription", "You are not authorized to view this page.");
		return mv;
	}

	// LOGOUT
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

}
