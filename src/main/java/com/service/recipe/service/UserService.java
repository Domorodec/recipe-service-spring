package com.service.recipe.service;

import com.service.recipe.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User recipe);

    User findByUserId(Integer id);

    List<User> userList();

    void updateUser(User recipe);

    void deleteUser(Integer id);
}
