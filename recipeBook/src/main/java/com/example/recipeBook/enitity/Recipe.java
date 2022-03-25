package com.example.recipeBook.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {
        //be sure that all columns are all lowercase, thus in 'new_column' format
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String creator;
    private String ingredients;
    private String directions;

    public Recipe(String name, String creator, String ingredients, String directions) {
        this.name = name;
        this.creator = creator;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}

