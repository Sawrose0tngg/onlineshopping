package com.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome To Spring Web MVC");
		return mv;
	}

	/*@RequestMapping(value = "/test")
	public ModelAndView test(@RequestParam(value = "greet", required = false) String greet) {
		ModelAndView mv = new ModelAndView("page");
		if (greet == null)
			greet = "Hello";
		mv.addObject("greeting", "Your Parameter say: " + greet);
		return mv;
	}*/
	
	@RequestMapping(value = "/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting") String greet) {
		ModelAndView mv = new ModelAndView("page");
		if (greet == null)
			greet = "Hello";
		mv.addObject("greeting", "Your Parameter say: " + greet);
		return mv;
	}
	
	
}
