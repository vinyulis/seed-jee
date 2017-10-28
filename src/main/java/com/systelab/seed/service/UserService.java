package com.systelab.seed.service;

import com.systelab.seed.model.user.User;
import com.systelab.seed.util.exceptions.UserNotFoundException;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UserService
{

  User getUser(Long id);

  List<User> getAllUsers();

  void create(User user);

  void delete(Long id) throws UserNotFoundException;

  String getToken(String uri, String login, String password) throws SecurityException;

}
