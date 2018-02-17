package com.onlineshopping.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFound() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Page is not Contructed");
		mv.addObject("errorDescription", "The page you are looking for is not available.");
		mv.addObject("title", "404-Error");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product is not Available");
		mv.addObject("errorDescription", "The product you are looking for is not available.");
		mv.addObject("title", "Product-Unavilable");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Please Contact Administrator.");
		mv.addObject("errorDescription", ex.toString());
		mv.addObject("title", "Errore");
		ex.printStackTrace();
		return mv;
	}
	
}
