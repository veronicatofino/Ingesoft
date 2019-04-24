package com.proyectofinal.web.service;

import com.proyectofinal.web.model.Login;
import com.proyectofinal.web.model.User;

public interface UserService {

  void register(User user);

  User validateUser(Login login);
  
  boolean existsUser(final User user);
  
  int getIdByUser(final User user);
}
