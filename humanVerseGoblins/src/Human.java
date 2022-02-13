import java.util.*;

public class Human {
    int health;
    int strength;
    int row;
    int col;
    String name;
    ArrayList<String> inventory = new ArrayList<>();

    Human(int health, int strength, int row, int col, String name) {
        this.health = health;
        this.strength = strength;
        this.row = row;
        this.col = col;
        this.name = name;
    }

    public String checkTreasureForPotion(String str, Treasure treasure, Human human, Land land, Scanner scan) {
        String message;
        boolean potion;
        if(str.equals(treasure.drink1)) {
            System.out.println("You have found a rare item. Press any button to see what it is!");
            scan.next();
            human.health = human.health + 10;
            message = "Congratulations " + human.name + "! The Magic Potion has raised your health by 10 points!";
            land.grid.get(human.row)[human.col] = " H ";
            potion = true;
        } else if(str.equals(treasure.drink2)) {
            System.out.println("You have found a rare item. Press any button to see what it is!");
            scan.next();
            human.health = human.health - 5;
            message = "Sorry " + human.name + " the chest contained a bomb and it blew taking away 5 health points.";
            land.grid.get(human.row)[human.col] = " H ";
            potion = true;
        } else {
            human.inventory.add(str);
            message = "Treasure chest contained: " + str + ". It has been added to your arsenal.";
            land.grid.get(human.row)[human.col] = " H ";
            potion = false;
        }
        return message;
    }

    public String useItem(String str, Human human, Goblin goblin) {
        String message = "";
        for(String i : inventory) {
            if(i.toLowerCase().equals(str)) {
                switch (str) {
                    case "dagger" -> {
                        human.strength = human.strength + 1;
                        message = "Your strength during this battle will be raised by 1.";
                        inventory.remove("Dagger");
                    }
                    case "sword" -> {
                        human.strength = human.strength + 3;
                        message = "Your strength during this battle will be raised by 3.";
                        inventory.remove("Sword");
                    }
                    case "brass armor" -> {
                        goblin.strength = goblin.strength - 1;
                        message = "The brass armor reduced the strength of your opponent by 1 point during this battle.";
                        inventory.remove("Brass Armor");
                    }
                    case "gold armor" -> {
                        goblin.strength = 1;
                        message = "The gold armor reduced the strength of your opponent to 1 1 point during this battle.";
                        inventory.remove("Gold Armor");
                    }
                    default -> { message = "Not a valid response."; }
                }

            }
            message = "Sorry, you do not have " + str + " in your arsenal.";
        }
        return message;
    }

    public String printInventory() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<inventory.size(); i++) {
            sb.append(": ");
            sb.append(inventory.get(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    public String attack(Human human, Goblin goblin) {
        double x = 1 + Math.random() * human.strength;
        int hitStrength = (int) x;
        goblin.health = goblin.health - hitStrength;
        return "!*!ThUd!*! " + human.name + " attacks " + goblin.name + " for a damage of: " + hitStrength;
    }

    public String move(HashMap<Integer, String[]> grid, int[] arr, String s) {
        switch (s.toLowerCase()) {
            case "i":
                if (this.row - 1 >= 1) {
                    grid.get(this.row)[this.col] = " - ";
                    this.row = this.row - 1;
                    arr[0] = this.row;
                    if (grid.get(this.row)[this.col].equals(" G ")) {
                        grid.get(this.row)[this.col] = "G:H";
                    } else if (grid.get(this.row)[this.col].equals(" - ")) {
                        grid.get(this.row)[this.col] = " H ";
                    } else {
                        grid.get(this.row)[this.col] = "[H]";
                    }
                }
                break;
            case "k":
                if (this.row + 1 < 16) {
                    grid.get(this.row)[this.col] = " - ";
                    this.row = this.row + 1;
                    arr[0] = this.row;
                    if (grid.get(this.row)[this.col].equals(" G ")) {
                        grid.get(this.row)[this.col] = "G:H";
                    } else if (grid.get(this.row)[this.col].equals(" - ")) {
                        grid.get(this.row)[this.col] = " H ";
                    } else {
                        grid.get(this.row)[this.col] = "[H]";
                    }
                }
                break;
            case "j":
                if (this.col - 1 >= 1) {
                    grid.get(this.row)[this.col] = " - ";
                    this.col = this.col - 1;
                    arr[1] = this.col;
                    if (grid.get(this.row)[this.col].equals(" G ")) {
                        grid.get(this.row)[this.col] = "G:H";
                    } else if (grid.get(this.row)[this.col].equals(" - ")) {
                        grid.get(this.row)[this.col] = " H ";
                    } else {
                        grid.get(this.row)[this.col] = "[H]";

                    }
                }
                break;
            case "l":
                if (this.col + 1 < 16) {
                    grid.get(this.row)[this.col] = " - ";
                    this.col = this.col + 1;
                    arr[1] = this.col;
                    if (grid.get(this.row)[this.col].equals(" G ")) {
                        grid.get(this.row)[this.col] = "G:H";
                    } else if (grid.get(this.row)[this.col].equals(" - ")) {
                        grid.get(this.row)[this.col] = " H ";
                    } else {
                        grid.get(this.row)[this.col] = "[H]";

                    }
                }
                break;
            default:
                return toString(grid);
        }
        return toString(grid);
    }

    public String toString(HashMap<Integer, String[]> hash) {
        String str2 = "";
        for(int i=0; i<hash.size(); i++) {
            for(String s : hash.get(i)) {
                str2 += s;
            }
        }
        return str2;
    }

}


/*
                     if(grid.get(arr[0] - 1)[arr[1]].equals(" G ")) {
                    grid.get(arr[0])[arr[1]] = " - ";
                    arr[0] = arr[0] - 1;
                    grid.get(arr[0])[arr[1]] = "G:H";


                     public String attack(Human human, Goblin goblin) {
        double humanAttack = 1 + (Math.random() * human.strength);
        return "Human attacks Goblin for " + (int) humanAttack + " health points!";
    }

 */