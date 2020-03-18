package com.service.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.recipe.model.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

     User findUserById(Integer id);

     List<User> findAll();
}
