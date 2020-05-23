package com.service.recipe.service.impl;

import com.service.recipe.kafka.KafkaProducer;
import com.service.recipe.model.Recipe;
import com.service.recipe.repo.RecipeRepo;
import com.service.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public Recipe saveRecipe(Recipe recipe) {
    /* lection 23 more stuff for get content https://www.udemy.com/course/building-a-restful-api-application
    -using-spring-and-angular/learn/lecture/14039437#overview*/
        this.kafkaProducer.sendMessage("User " + recipe.getCreatedBy() + " requested to save recipe with details:" +
                "name: " + recipe.getName() + "" +
                "category: " + recipe.getCategory() + "" +
                "img: " + recipe.getImg() + "" +
                "content: " + recipe.getContent() + "" +
                "created by: " + recipe.getCreatedBy() + " at [" + new Date() + "]");
        recipeRepo.save(recipe);
        return recipe;
    }

    @Override
    public Recipe findByRecipeId(Integer id) {
        this.kafkaProducer.sendMessage("User requested recipe with id " + id + " at [" + new Date() + "]");
        return recipeRepo.findRecipeById(id);
    }

    @Override
    public List<Recipe> findByCreatedBy(String username) {
        this.kafkaProducer.sendMessage("User " + username + " requested list of his recipes at [" + new Date() + "]");
        return recipeRepo.findRecipeByCreatedBy(username);
    }

    @Override
    public List<Recipe> recipeList() {
        this.kafkaProducer.sendMessage("User requested all recipes at [" + new Date() + "]");
        return recipeRepo.findAll();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        this.kafkaProducer.sendMessage("User " + recipe.getCreatedBy() + " updated recipe with details:" +
                "name: " + recipe.getName() + "" +
                "category: " + recipe.getCategory() + "" +
                "img: " + recipe.getImg() + "" +
                "content: " + recipe.getContent() + "" +
                "created by: " + recipe.getCreatedBy() + " at [" + new Date() + "]");
        recipeRepo.save(recipe);
    }

    @Override
    public void deleteRecipe(Integer id) {
        this.kafkaProducer.sendMessage("User requested deleting recipe with id " + id + " at [" + new Date() + "]");
        recipeRepo.deleteById(id);
    }
}
