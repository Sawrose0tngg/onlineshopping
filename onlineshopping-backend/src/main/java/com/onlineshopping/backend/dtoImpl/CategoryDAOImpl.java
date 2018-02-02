package com.onlineshopping.backend.dtoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.onlineshopping.backend.dao.CategoryDAO;
import com.onlineshopping.backend.dto.Category;

@Repository("categoryDao") // This name must be same to autowired class
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	static {
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is about television.");
		category.setImageUrl("CAT_1.png");
		categories.add(category);

		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is about mobile.");
		category.setImageUrl("CAT_2.png");
		categories.add(category);

		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is about laptop.");
		category.setImageUrl("CAT_3.png");
		categories.add(category);

	}

	@Override
	public List<Category> list() {

		return categories;
	}

	@Override
	public Category get(int id) {

		// enhanced for loop
		for (Category category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}

		return null;
	}

}
