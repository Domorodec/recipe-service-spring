package com.service.recipe.service.impl;

import com.service.recipe.model.Recipe;
import com.service.recipe.repo.RecipeRepo;
import com.service.recipe.service.RecipeService;
import com.service.recipe.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;

    @Override
    public Recipe saveRecipe(Recipe recipe) {
    /* lection 23 more stuff for get content https://www.udemy.com/course/building-a-restful-api-application
    -using-spring-and-angular/learn/lecture/14039437#overview*/
        recipeRepo.save(recipe);
        return recipe;
    }

    @Override
    public Recipe findByRecipeId(Integer id) {
        return recipeRepo.findRecipeById(id);
    }

    @Override
    public List<Recipe> findByCreatedBy(String username) {
        return recipeRepo.findRecipeByCreatedBy(username);
    }

    @Override
    public List<Recipe> recipeList() {
        return recipeRepo.findAll();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    @Override
    public void deleteRecipe(Integer id) {
        recipeRepo.deleteById(id);
    }
}
