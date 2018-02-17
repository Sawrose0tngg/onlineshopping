package com.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlineshopping.backend.dao.ProductDAO;
import com.onlineshopping.backend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDao;

	@RequestMapping("/all/products")
	@ResponseBody // json data converter
	public List<Product> getAllProducts() {

		return productDao.listActiveProducts();
	}
	@RequestMapping("/admin/all/products")
	@ResponseBody // json data converter
	public List<Product> getAllProductsForAdmin() {

		return productDao.list();
	}

	@RequestMapping("/category/{id}/products")
	@ResponseBody // json data converter
	public List<Product> getAllProductsByCategory(@PathVariable int id) {

		return productDao.listActiveProductsByCategory(id);
	}

}
