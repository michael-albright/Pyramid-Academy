import java.util.*;

public class Land {
    HashMap<Integer, String[]> grid = new HashMap<>();
    ArrayList<Goblin> goblins = new ArrayList<>();

    public void createGameBoard() {
        grid.put(0, new String[] {"\n            Humans Verse Goblins\n"});
        for(int i=1; i<16; i++) {
            grid.put(i, new String[] {" - ", " - ", " - ", " - ", " - ", " - ", " - ",
                    " - ", " - ", " - ", " - ", " - ", " - ", " - ", " - ", "\n" });
        }
        //return toString(grid);
    }

    public boolean testForBattle(HashMap<Integer, int[]> gobCoords, int[] humanCoords) {
        for(int i=0; i<gobCoords.size(); i++) {
            if(Arrays.equals(gobCoords.get(i), humanCoords)) {
                return true;
            }
        }
        return false;
    }

    public void removeGoblin(HashMap<Integer, int[]> gobCoords, int[] humanCoords) {
        for (int i=0; i<gobCoords.size(); i++) {
            if (gobCoords.get(i) == humanCoords) {
                gobCoords.remove(i);
            }
        }
    }

    // COULD HAVE A PROBLEM WITH ADDING THE COORDS TO THE HASHMAP, IN MAP.ENTRY
    public HashMap<Integer, int[]> addGoblin(int d) {
        HashMap<Integer, int[]> coordinates = new HashMap<>();
        int i = 0;
        while(d > 0) {
            boolean same = true;
            while(same) {
                int x = randomNum(1, 15);
                int y = randomNum(0, 15);
                if(grid.get(x)[y].equals(" - ")) {
                    grid.get(x)[y] = " G ";
                    int[] arr = {x, y};
                    coordinates.put(i, arr);
                    i++;
                    same = false;
                }
            }
            d--;
        }
        return coordinates;
    }

    public int[] addHuman() {
        int[] coordinates = new int[2];
        boolean same = true;
        while(same) {
            int x = randomNum(1, 15);
            int y = randomNum(0, 15);
            if(grid.get(x)[y].equals(" - ")) {
                grid.get(x)[y] = " H ";
                coordinates[0] = x;
                coordinates[1] = y;
            }
           same = false;
        }
        return coordinates;
    }

    public int[] addTreasure() {
        int[] coordinates = new int[2];
        boolean same = true;
        while(same) {
            int x = randomNum(1, 15);
            int y = randomNum(0, 15);
            if(grid.get(x)[y].equals(" - ")) {
                grid.get(x)[y] = "[*]";
                coordinates[0] = x;
                coordinates[1] = y;
            }
            same = false;
        }
        return coordinates;
    }

    public int randomNum(int i, int j) {
        double x = i + Math.random() * (j - i);
        return (int) x;
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
        String[] names = {"The Greedy Goblin", "Gil the Goblin", "Flesh Eater", "Sally Swordsman", "Googley Eyed Goblin",
                "Grumpy Goblin", "Goblisauras Rex", "Adam the Goblin", "Jacob the Goblin", "The Gargantuan Goblin",
                "Creepy Goblin", "Goblin the Grey", "Grizzly Goblin"};
        double x = Math.random() * 12;
        int y = (int) x;
        return names[y];

    }

    public Goblin getGoblinToFight(int[] humCoords, ArrayList<Goblin> goblins) {
        Goblin fighter = null;
        for(Goblin a : goblins) {
            if(a.row == humCoords[0] && a.col == humCoords[1]) {
                fighter = a;
            }
        }
        return fighter;
    }

    /*
    int[] temp = new int[2];
        for(int i=0; i<gobCoords.size(); i++) {
            if(humCoords == gobCoords.get(i)) {
                temp = gobCoords.get(i);
            }
        }

        HashMap<Integer, String[]> grid,  HashMap<Integer, int[]> gobCoords,

     */
}













        /*
        //CHECK FOR REOCCURING KEYS IN ADDGOBLIN
        for (int i = 0; i < coordinates.size(); i++) {
                        int z = 0;
                        for (Map.Entry<Integer, int[]> entries : coordinates.entrySet()) {
                            z = entries.getKey();
                        }
                        if (i != z || z == 0) {
                            int[] arr = {x, y};
                            coordinates.put(i, arr);
                        }
                    }




       String[]
        HashMap<Integer, ArrayList<String>> gameBoard = new HashMap<>();
        String str = "---------------\n";
        ArrayList<String> column0 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column1 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column2 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column3 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column4 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column5 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column6 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column7 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column8 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column9 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column10 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column11 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column12 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column13 = new ArrayList<>(Arrays.asList(str.split("")));
        ArrayList<String> column14 = new ArrayList<>(Arrays.asList(str.split("")));

        gameBoard.put(0, column0);
        gameBoard.put(1, column1);
        gameBoard.put(2, column2);
        gameBoard.put(3, column3);
        gameBoard.put(4, column4);
        gameBoard.put(5, column5);
        gameBoard.put(6, column6);
        gameBoard.put(7, column7);
        gameBoard.put(8, column8);
        gameBoard.put(9, column9);
        gameBoard.put(10, column10);
        gameBoard.put(11, column11);
        gameBoard.put(12, column12);
        gameBoard.put(13, column13);
        gameBoard.put(14, column14);

        return gameBoard;
    }
    public String toString(HashMap<Integer, ArrayList<String>> hash) {
        String str2 = " ";
        for(int i=0; i<hash.size(); i++) {
            for(String s : hash.get(i)) {
                str2 += " " + s + " ";
            }
        }
        return str2;
    }

    public int randomNum() {
        double x = Math.random() * 15;
        return (int) x;
    }


    public void addBeing(String s, HashMap<Integer, ArrayList<String>> hash) {
        //int x = randomNum();
        //int y = randomNum();
        hash.get(randomNum()).set(randomNum(), s);

    }

    public HashMap<Integer, Integer> findCoordinates(String s, HashMap<Integer, ArrayList<String>> grid, HashMap<Integer, Integer> coords) {
        for(int i=0; i<grid.size(); i++) {
            if(grid.get(i).contains(s)) {
                int j = grid.get(i).indexOf(s);
                    coords.put(i, j);

            }
        }
        return coords;
    }

         */


