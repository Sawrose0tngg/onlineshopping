package com.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshopping.backend.dao.CategoryDAO;
import com.onlineshopping.backend.dao.ProductDAO;
import com.onlineshopping.backend.dto.Category;
import com.onlineshopping.backend.dto.Product;
import com.onlineshopping.util.FileUploadUtility;
import com.onlineshopping.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDao;
	@Autowired
	private ProductDAO productDao;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Sumitted Successfully");
			} else if (operation.equals("category")) {
				mv.addObject("message", "Category Submitted Successfully");
			}
		}

		mv.addObject("product", nProduct);

		return mv;
	}

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = productDao.get(id);

		mv.addObject("product", nProduct);

		return mv;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		// check if there is any error
		if (results.hasErrors()) {
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed for Product Submission");
			model.addAttribute("errorStatus", true);
			return "page";
		}

		logger.info(mProduct.toString());
		if (mProduct.getId() == 0) {// get the id in hidden field
			productDao.add(mProduct);
		} else {
			productDao.update(mProduct);
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handlerProductActivation(@PathVariable int id) {
		System.out.println("Hello");
		Product product = productDao.get(id);

		boolean isActive = product.isActive();

		product.setActive(!isActive);

		productDao.update(product);

		return (isActive) ? "You have successfully deactivate product with id: " + product.getId()
				: "You have successfully activate product with id: " + product.getId();
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDao.add(category);
		return "redirect:/manage/products?operation=category";
	}

	// Retruning Categories List
	@ModelAttribute("categories")
	public List<Category> getCatagories() {

		return categoryDao.list();
	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

}
