package com.proyectofinal.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.web.model.Project;
import com.proyectofinal.web.model.User;
import com.proyectofinal.web.service.ProjectService;
import com.proyectofinal.web.service.UserService;

@Controller
public class WelcomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("redirect:/home");
		}
		final User user = (User) request.getSession().getAttribute("user");
		final int id = userService.getIdByUser(user);
		final List<Project> projects = projectService.getProjectsByUserId(id);
		
		final StringBuilder builder = new StringBuilder();
		for (Project p : projects) {
			builder.append("<a href = \"project?id=" + p.getId() + "\">").append(p.getName()).append("</a></br>");
		}
	    return new ModelAndView("welcome").addObject("projects", builder.toString());
	  }
	
}
