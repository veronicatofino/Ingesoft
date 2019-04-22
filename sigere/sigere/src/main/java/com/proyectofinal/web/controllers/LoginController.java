package com.proyectofinal.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.web.model.Login;
import com.proyectofinal.web.model.User;
import com.proyectofinal.web.service.UserService;
@Controller
public class LoginController {

  @Autowired
  UserService userService;

  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("login") Login login) {
    ModelAndView mav = null;
    User user = userService.validateUser(login);
    if (user != null) {
      mav = new ModelAndView("redirect:/welcome");
      mav.addObject("firstname", user.getFirstname());
      request.getSession().setAttribute("user", user);
    } else {
      mav = new ModelAndView("Home");
      mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }

}