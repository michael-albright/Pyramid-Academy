import java.util.*;

public class Land {
    HashMap<Integer, String[]> grid = new HashMap<>();
    ArrayList<Goblin> goblins = new ArrayList<>();
    ArrayList<Treasure> treasureChests = new ArrayList<>();

    public void findMovingGoblins() {

    }

    public boolean setGoblinMovement() {
        double x = 1 + Math.random() * 2;
        return ((int) x==1);
    }

    public void createGameBoard() {
        grid.put(0, new String[]{"\n            Humans Verse Goblins\n"});
        for (int i = 1; i < 16; i++) {
            grid.put(i, new String[]{" - ", " - ", " - ", " - ", " - ", " - ", " - ",
                    " - ", " - ", " - ", " - ", " - ", " - ", " - ", " - ", "\n"});
        }
    }
/*
    public boolean testForElement(HashMap<Integer, int[]> hash1_coords, HashMap<Integer, int[]> hash2_coords) {
        int[] temp = { 111 };
        for (int i=0; i < hash1_coords.size(); i++) {
            if (Arrays.equals(hash1_coords.get(i), hash2_coords.get(0))) {
                hash1_coords.put(i, temp);
                return true;
            }
        }
        return false;
    }

 */

    public boolean testForElement(HashMap<Integer, int[]> hashToEdit, HashMap<Integer, int[]> hashToCompare) {
        int[] temp = { 111 };
        for (int i=0; i < hashToEdit.size(); i++) {
            for(int j=0; j<hashToCompare.size(); j++) {
                if (Arrays.equals(hashToEdit.get(i), hashToCompare.get(j))) {
                    hashToEdit.put(i, temp);
                    return true;
                }
            }
        }
        return false;
    }
/*
    public void removeElement(HashMap<Integer, int[]> gobCoords, HashMap<Integer, int[]> humanCoords) {
        for (int i=0; i<gobCoords.size(); i++) {
            if (Arrays.equals(gobCoords.get(i), humanCoords.get(0))) {
                gobCoords.remove(i);
                break;
            }
        }
    }

 */

    public void removeElement(HashMap<Integer, int[]> hashToRemove, HashMap<Integer, int[]> hashToCompare) {
        for (int i=0; i<hashToRemove.size(); i++) {
            for(int j=0; j<hashToCompare.size(); j++) {
                if (Arrays.equals(hashToRemove.get(i), hashToCompare.get(j))) {
                    hashToRemove.remove(i);
                    break;
                }
            }
        }
    }

    public void addElement(int d, HashMap<Integer, int[]> coordinates, String s) {
        int i = 0;
        while (d > 0) {
            boolean same = true;
            while (same) {
                int x = randomNum(1, 15);
                int y = randomNum(0, 15);
                if (grid.get(x)[y].equals(" - ")) {
                    grid.get(x)[y] = s;
                    int[] arr = {x, y};
                    coordinates.put(coordinates.size(), arr);
                    i++;
                    same = false;
                }
            }
            d--;
        }
    }

    public String randomGoblinName() {
        String[] names = {"The Greedy Goblin", "Gil the Goblin", "Flesh Eater", "Sally Swordsman", "Googley Eyed Goblin",
                "Grumpy Goblin", "Goblisauras Rex", "Adam the Goblin", "Jacob the Goblin", "The Gargantuan Goblin",
                "Creepy Goblin", "Goblin the Grey", "Grizzly Goblin"};
        double x = Math.random() * 12;
        int y = (int) x;
        return names[y];
    }

    public Goblin getGoblinToFight(HashMap<Integer, int[]> humCoords, ArrayList<Goblin> arrList) {
        Goblin fighter = null;
        for (Goblin a : arrList) {
            if (a.row == humCoords.get(0)[0] && a.col == humCoords.get(0)[1]) {
                fighter = a;
            }
        }
        return fighter;
    }

    public Treasure getTreasure(HashMap<Integer, int[]> humCoords, ArrayList<Treasure> arrList) {
        Treasure chest = null;
        for (Treasure i : arrList) {
            if (i.row == humCoords.get(0)[0] && i.col == humCoords.get(0)[1]) {
                chest = i;
            }
        }
        return chest;
    }

    public String toString(HashMap<Integer, String[]> hash) {
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < hash.size(); i++) {
            for (String s : hash.get(i)) {
                str2.append(s);
            }
        }
        return str2.toString();
    }

    public int randomNum(int i, int j) {
        double x = i + Math.random() * (j - i);
        return (int) x;
    }

    public boolean checkForWin(HashMap<Integer, int[]> coordinates) {
        int[] temp = { 111 };
        for(int i=0; i<coordinates.size(); i++) {
            if(!Arrays.equals(coordinates.get(i), temp)) {
                return false;
            }
        }
        return true;
    }

}


