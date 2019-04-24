package com.proyectofinal.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.web.model.Login;
import com.proyectofinal.web.model.User;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("welcome", "firstname", ((User) request.getSession().getAttribute("user")).getFirstname());
		}
	    ModelAndView mav = new ModelAndView("Home");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	  public ModelAndView logoutUser(HttpServletRequest request, HttpServletResponse response) {
	    request.getSession().setAttribute("user", null);
		return new ModelAndView("redirect:/home");
	  }
}
