package com.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	/*Show Single Product*/
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDao.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//Update view count
		product.setViews(product.getViews() + 1);
		productDao.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		return mv;
	}

}
