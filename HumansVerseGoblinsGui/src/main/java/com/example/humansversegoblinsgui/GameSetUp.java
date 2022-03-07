package com.example.humansversegoblinsgui;


import java.util.Arrays;

public class GameSetUp {

    public String checkForOpenSpace(String s, Human human, Goblin goblin, Treasure treasure) {
        int x = 0;
        switch (s) {
            case "up" -> {
                x += human.humanCoords[1] - 37;
                if (Arrays.equals(new int[]{human.humanCoords[0], x}, goblin.goblinCoords)) return "goblin";
                if (Arrays.equals(new int[]{human.humanCoords[0], x}, treasure.treasureCoords)) return "treasure";
                if(x < 0) return "no";
            }
            case "down" -> {
                x += human.humanCoords[1] + 37;
                if (Arrays.equals(new int[]{human.humanCoords[0], x}, goblin.goblinCoords)) return "goblin";
                if (Arrays.equals(new int[]{human.humanCoords[0], x}, treasure.treasureCoords)) return "treasure";
                if(x > 222) return "no";
            }
            case "left" -> {
                x += human.humanCoords[0] - 37;
                if (Arrays.equals(new int[]{x, human.humanCoords[1]}, goblin.goblinCoords)) return "goblin";
                if (Arrays.equals(new int[]{x, human.humanCoords[1]}, treasure.treasureCoords)) return "treasure";
                if(x < 0) return "no";
            }
            case "right" -> {
                x += human.humanCoords[0] + 37;
                if (Arrays.equals(new int[]{x, human.humanCoords[1]}, goblin.goblinCoords)) return "goblin";
                if (Arrays.equals(new int[]{x, human.humanCoords[1]}, treasure.treasureCoords)) return "treasure";
                if(x > 333) return "no";
            }
        }
        return "move";
    }

    public int[] placeElement() {
        int x = randomNum(0, 8) * 37;
        int y = randomNum(0, 5) * 37;
        return new int[]{x, y};
    }

    public int randomNum(int i, int j) {
        double x = i + Math.random() * (j - i);
        return (int) x;
    }
}

