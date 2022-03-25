package com.example.recipeBook.service;

import com.example.recipeBook.enitity.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecipeService {
    ResponseEntity<Recipe> getRecipe(String name, Long id);
    ResponseEntity<List<String>> getAllRecipes();
    ResponseEntity<Recipe> addRecipe(Recipe recipe);
    ResponseEntity<Recipe> updateRecipe(Long id, Recipe recipe);
    ResponseEntity<String> deleteRecipe(Long id);
    ResponseEntity<String> deleteAllRecipes();
}
