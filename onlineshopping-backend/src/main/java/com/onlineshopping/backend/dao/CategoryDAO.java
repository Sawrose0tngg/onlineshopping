package com.onlineshopping.backend.dao;

import java.util.List;
import com.onlineshopping.backend.dto.Category;

public interface CategoryDAO {

	Category get(int id);

	List<Category> list();

	boolean add(Category category);

	boolean delete(Category category);

	boolean update(Category category);
}
