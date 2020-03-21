package com.service.recipe.service;

import com.service.recipe.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RecipeService {

     Recipe saveRecipe(Recipe recipe);

     Recipe findByRecipeId(Integer id);

     List<Recipe> recipeList();

     void updateRecipe(Recipe recipe);

     Integer deleteRecipe(Integer id);

     String saveImage(HttpServletRequest request, String fileName);

}
