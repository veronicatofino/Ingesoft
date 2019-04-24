package com.proyectofinal.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyectofinal.web.dao.UserDao;
import com.proyectofinal.web.model.Login;
import com.proyectofinal.web.model.User;

public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public void register(User user) {
    userDao.register(user);
  }

  public User validateUser(Login login) {
    return userDao.validateUser(login);
  }
  
  @Override
  public boolean existsUser(User user) {
	  return userDao.existsUser(user);
  }
  
  @Override
	public int getIdByUser(User user) {
		return userDao.getIdByUser(user);
	}
  

}