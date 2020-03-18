package com.service.recipe.service;

import com.service.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {

     void saveRecipe(Recipe recipe);

     Recipe findByRecipeId(Integer id);

     List<Recipe> recipeList();

     void updateRecipe(Recipe recipe);

     void deleteRecipe(Integer id);

}
