package com.example.recipeBook.controller;

import com.example.recipeBook.enitity.Recipe;
import com.example.recipeBook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipeBook/v1")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipeBook/v1/")
    public String home() {
        return "<HTML><HEAD><H1> Recipe Book </H1></HEAD></HTML>";
    }

    @GetMapping("/recipes/recipe")
    public ResponseEntity<Recipe> getRecipe(@RequestParam(required = false) String name, @RequestParam(required = false) Long id) {
        return recipeService.getRecipe(name, id);
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<String>> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping("/recipes")
    ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/recipes/{id}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/recipes/{id}")
    ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id) {
        return recipeService.deleteRecipe(id);
    }

    @DeleteMapping("/recipes")
    ResponseEntity<String> deleteAllRecipes(){
        return recipeService.deleteAllRecipes();
    }

}
