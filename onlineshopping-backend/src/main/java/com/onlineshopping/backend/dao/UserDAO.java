package com.onlineshopping.backend.dao;

import java.util.List;

import com.onlineshopping.backend.dto.Address;
import com.onlineshopping.backend.dto.Cart;
import com.onlineshopping.backend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	User getByEmail(String email);

	boolean addAddress(Address address);
	//Alernative way to run multiple query.
//	Address getBillingAddress(int userId);
//	List<Address> listShippingAddresses(int userId);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);

	boolean updateCart(Cart cart);
}
