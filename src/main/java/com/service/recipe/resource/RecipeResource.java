package com.service.recipe.resource;

import com.service.recipe.model.Recipe;
import com.service.recipe.model.User;
import com.service.recipe.service.RecipeService;
import com.service.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://receptoland.cz"})
@RequestMapping("/recipes")
public class RecipeResource {
    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<Recipe> getRecipeList() {
        return recipeService.recipeList();
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<?> getRecipeListFromUser(@PathVariable("username") String username) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        try {
            List<Recipe> recipes = recipeService.findByCreatedBy(username);
            LOGGER.log(Level.INFO, "recipes fetched from DB");
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("And error occured", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Recipe getSelectedRecipeFromUser(@PathVariable("id") Integer id) {
        return recipeService.findByRecipeId(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRecipe(@RequestBody Recipe request) {
        String username = request.getCreatedBy();
        User user = userService.findByUserName(username);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        try {
            Recipe newRecipe = recipeService.saveRecipe(request);
            return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during saving recipe", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}/{username}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        Recipe recipe = recipeService.findByRecipeId(id);
        if (recipe == null) {
            return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
        }
        try {
            recipeService.deleteRecipe(id, username);

            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured in backend and recipe is not deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
