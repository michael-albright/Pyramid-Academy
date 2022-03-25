package com.example.recipeBook.service;

import com.example.recipeBook.enitity.Recipe;
import com.example.recipeBook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    public RecipeRepository recipeRepository;

    @Override
    public ResponseEntity<Recipe> getRecipe(@RequestParam(required = false) String name, @RequestParam(required = false) Long id) {
        try {
            List<Recipe> recipes = new ArrayList<>(recipeRepository.findAll());
            if(recipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            if (id == null) {
                for (Recipe recipe : recipes) {
                    if(recipe.getName().equalsIgnoreCase(name)) {
                        return new ResponseEntity<>(recipe, HttpStatus.OK);
                    }
                }
            } else {
                for(Recipe recipe : recipes) {
                    if(recipe.getId() == id) {
                        return new ResponseEntity<>(recipe, HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<String>> getAllRecipes() {
        try {
            List<Recipe> recipes = new ArrayList<>(recipeRepository.findAll());
            if(recipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recipes.stream()
                    .map(Recipe::getName).collect(Collectors.toList()), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        try {
            Recipe newRecipe = recipeRepository.save(new Recipe(recipe.getName(), recipe.getCreator(), recipe.getIngredients(), recipe.getDirections()));
            return new ResponseEntity<>(newRecipe, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe) {
        Optional<Recipe> recipeData = recipeRepository.findById(id);
        if(recipeData.isPresent()) {
            Recipe updatedRecipe = recipeData.get();
            updatedRecipe.setName(recipe.getName());
            updatedRecipe.setCreator(recipe.getCreator());
            updatedRecipe.setIngredients(recipe.getIngredients());
            updatedRecipe.setDirections(recipe.getDirections());
            return new ResponseEntity<>(recipeRepository.save(updatedRecipe), HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id) {
        try {
            recipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Recipe #" + id + " deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteAllRecipes() {
        try {
            recipeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("All recipes deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
