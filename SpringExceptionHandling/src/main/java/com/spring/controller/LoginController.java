package com.spring.controller;

import java.io.IOException;
import java.sql.SQLException;,

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.exceptions.UserNotFoundException;
import com.spring.model.Login;

@Controller
public class LoginController {
	
	@RequestMapping("/welcome")
	public String hello(){
		
		return "welcome"; ///////////////
	}

	public ModelAndView loginUser(@ModelAttribute("login") Login login){
		
		String userId = login.getUserId();
		String userPassword = login.getPassword();
		return null;
		////////////////
		
	}
	
	@RequestMapping(value = "/exception/{id}")
	public ModelAndView testExceptions(@PathVariable Integer id) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(id==1){
			throw new UserNotFoundException("user not found:)");
		}else if( id ==2){
			throw new IOException();
		}else if(id==3){
			throw new SQLException();
		}else if(id==4){
			throw new RuntimeException();
		}else{
			mv.setViewName("welcome"); 
		}
		return mv;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public void handleIOException(HttpServletRequest request, Exception exception) {

		System.out.println("Exception occured at : " + request.getRequestURL());
		System.out.println("Exception is " + exception);
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", exception);
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error");

	}

}
