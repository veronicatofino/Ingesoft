package com.proyectofinal.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.web.model.Project;
import com.proyectofinal.web.service.ProjectService;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam(name = "id") final int id) {
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("redirect:/home");
		}
		final Project project = projectService.getProjectById(id);
	    return new ModelAndView("project").addObject("name", project.getName()).addObject("desc", project.getDescription());
	  }
}
