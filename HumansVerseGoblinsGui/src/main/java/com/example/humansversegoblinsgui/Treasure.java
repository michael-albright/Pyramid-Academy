package com.example.humansversegoblinsgui;

public class Treasure {
    String dagger = "Dagger"; // 10
    String sword = "Sword"; // 4
    String armor = "Armor"; // 6
    String potion = "Magic Potion"; // 1
    String bomb = "Bomb"; // 1

    String[] treasures = new String[22];
    int[] treasureCoords = new int[2];

    Treasure() {

    }

    Treasure(String[] treasures, int row, int col) {
        this.treasures = treasures;
    }

    public void setTreasureCoords(int[] arr) {
        treasureCoords[0] = arr[0];
        treasureCoords[1] = arr[1];
    }

    public String[] fillTreasuresArray() {
        for(int i=0; i<10; i++) {
            treasures[i] = dagger;
        }
        for(int i=10; i<17; i++) {
            treasures[i] = armor;
        }
        for(int i=17; i<20; i++) {
            treasures[i] = sword;
        }
        for(int i=20; i<21; i++) {
            treasures[i] = potion;
        }
        for(int i=21; i<22; i++) {
            treasures[i] = bomb;
        }
        return treasures;
    }

}
