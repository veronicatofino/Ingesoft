package com.proyectofinal.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.web.model.User;
import com.proyectofinal.web.service.UserService;

@Controller
public class RegistrationController {
  @Autowired
  public UserService userService;
  
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("user", new User());
    return mav;
  }
  
  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
	  if (userService.existsUser(user)) {
		  return new ModelAndView("register").addObject("message", "User already exists.");
	  }
	  if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getFirstname().isEmpty() || user.getEmail().isEmpty()) {
		  return new ModelAndView("register").addObject("message", "Missing some attribute values");
	  }
	  userService.register(user);
	  return new ModelAndView("welcome", "firstname", user.getFirstname());
  }
}