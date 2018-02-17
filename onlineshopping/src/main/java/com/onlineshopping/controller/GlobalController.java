package com.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.onlineshopping.backend.dao.UserDAO;
import com.onlineshopping.backend.dto.User;
import com.onlineshopping.model.UserModel;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDao;

	private UserModel userModel = null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {

		if (session.getAttribute("userModel") == null) {
			// add user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			User user = userDao.getByEmail(authentication.getName());
			if (user != null) {
				userModel = new UserModel();
				
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());

				if (userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}
				//set session
				session.setAttribute("user", userModel);
				return userModel;
			}

		}
		return (UserModel) session.getAttribute("userModel");
	}
}
