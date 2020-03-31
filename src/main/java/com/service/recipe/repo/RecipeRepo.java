package com.service.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.recipe.model.Recipe;

import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Integer> {

    Recipe findRecipeById(Integer id);

    List<Recipe> findRecipeByCreatedBy(String username);

    List<Recipe> findAll();
}
