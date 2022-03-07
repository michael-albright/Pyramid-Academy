package com.example.humansversegoblinsgui;

public class Goblin {
    int health;
    int strength;
    int[] goblinCoords = new int[2];

    Goblin(int health, int strength) {
        this.health = health;
        this.strength = strength;

    }

    public void setGoblinCoords(int[] arr) {
        goblinCoords[0] = arr[0];
        goblinCoords[1] =  arr[1];
    }

    public String attack(Goblin goblin, Human human) {
        double x = 1 + Math.random() * goblin.strength;
        int hitStrength = (int) x;
        human.health = human.health - hitStrength;
        return "\n!*!bAnG!*! The Goblin attacks for a damage of: " + hitStrength;
    }
}
