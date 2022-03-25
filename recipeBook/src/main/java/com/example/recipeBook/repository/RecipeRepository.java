package com.example.recipeBook.repository;

import com.example.recipeBook.enitity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe getByName(String name);
}
