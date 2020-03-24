package com.service.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.recipe.model.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    /* lekce 24 pokud by default select nefungoval je traba zmenit na
    @Query("SELECT u FROM User u order by u.created DESC")*/
    User findUserById(Integer id);

    List<User> findAll();

    User findUserByName(String name);

    User findUserByEmail(String email);
}
