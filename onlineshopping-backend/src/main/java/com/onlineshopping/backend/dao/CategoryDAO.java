package com.onlineshopping.backend.dao;

import java.util.List;
import com.onlineshopping.backend.dto.Category;

public interface CategoryDAO {

	List<Category> list();
	Category get(int id);
}
