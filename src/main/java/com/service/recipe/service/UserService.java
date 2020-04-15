package com.service.recipe.service;

import com.service.recipe.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User recipe);

    User findByUserId(Integer id);

    List<User> userList();

    void updateUser(User recipe);

    void deleteUser(Integer id);

    User findByUserName(String username);

    User findByUserEmail(String email);

    User login(String username, String password);
}
