package com.example.humansversegoblinsgui;

import javafx.scene.ImageCursor;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Human {
    int health;
    int strength;
    int[] humanCoords = new int[2];
    ArrayList<String> arsenal = new ArrayList<>();

    Human(int health, int strength) {
        this.health = health;
        this.strength = strength;

    }

    public String attack(Human human, Goblin goblin) {
        double x = 1 + Math.random() * human.strength;
        int hitStrength = (int) x;
        goblin.health = goblin.health - hitStrength;
        return "!*!ThUd!*! You attacked for a damage of: " + hitStrength;
    }

    public void setHumanCoords(int[] arr) {
        humanCoords[0] = arr[0];
        humanCoords[1] =  arr[1];
    }

}
