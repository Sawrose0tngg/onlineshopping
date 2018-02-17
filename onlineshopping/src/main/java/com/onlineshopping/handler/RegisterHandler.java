package com.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.onlineshopping.backend.dao.UserDAO;
import com.onlineshopping.backend.dto.Address;
import com.onlineshopping.backend.dto.Cart;
import com.onlineshopping.backend.dto.User;
import com.onlineshopping.model.RegisterModel;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegisterModel init() {

		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";

		if (!user.getPassword().equals(user.getConfirmPassword())) {

			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password Doesn't Match!!!").build());

			transitionValue = "failure";
		}

		if (userDao.getByEmail(user.getEmail()) != null) {

			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email Already Used").build());
			transitionValue = "failure";// must be equal to sinup-flow transition value
		}

		return transitionValue;
	}

	public String saveAll(RegisterModel model) {
		String transitionValue = "success";
		// Fetch User
		User user = model.getUser();
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Save User
		userDao.addUser(user);

		// Get Address
		Address billing = model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);

		userDao.addAddress(billing);

		return transitionValue;
	}
}
