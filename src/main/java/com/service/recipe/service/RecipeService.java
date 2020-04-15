package com.service.recipe.service;

import com.service.recipe.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RecipeService {

     Recipe saveRecipe(Recipe recipe);

     Recipe findByRecipeId(Integer id);

     List<Recipe> findByCreatedBy(String username);

     List<Recipe> recipeList();

     void updateRecipe(Recipe recipe);

     void deleteRecipe(Integer id);
}
