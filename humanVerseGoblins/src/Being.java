import java.util.*;

public class Being {
    String name;
    int health;
    int strength;
    int row;
    int col;
    String[] inventory;



    public void move() {

    }

    public String attack(Being attacker, Being receiver) {
        double x = 1 + Math.random() * this.strength;
        int hitStrength = (int) x;
        this.health = this.health - hitStrength;
        return "!*!SmAsH!*! " + this.name + " attacks " + this.name + " for " + hitStrength + " health points!";
    }

    public Goblin getGoblinToFight(HashMap<Integer, String[]> grid,  HashMap<Integer, int[]> gobCoords, int[] humCoords, ArrayList<Goblin> goblins) {
        Goblin fighter = new Goblin();
        int[] temp = new int[2];
        for(int i=0; i<gobCoords.size(); i++) {
            if(humCoords == gobCoords.get(i)) {
                temp = gobCoords.get(i);
            }
        }
        for(Goblin a : goblins) {
            if(a.row == temp[0] && a.col == temp[1]) {
                fighter = a;
            }
        }
        return fighter;
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

    public String randomGoblinName() {
        String[] names = {"The Greedy Goblin", "Gil the Goblin", "Flesh Eater", "Sally Swordsman", " Googley Eyed Goblin",
                "Grumpy Goblin", "Goblisauras Rex", "Adam the Goblin", "Jacob the Goblin", "The Gargantuan Goblin",
                "Creepy Goblin", "Goblin the Grey", "Grizzly Goblin"};
        double x = Math.random() * 12;
        int y = (int) x;
        return names[y];

    }



}
