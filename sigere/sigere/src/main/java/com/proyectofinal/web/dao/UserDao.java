package com.proyectofinal.web.dao;

import com.proyectofinal.web.model.Login;
import com.proyectofinal.web.model.User;

public interface UserDao {

  void register(User user);

  User validateUser(Login login);
  
  boolean existsUser(final User user);
  
  int getIdByUser(final User user);
}
