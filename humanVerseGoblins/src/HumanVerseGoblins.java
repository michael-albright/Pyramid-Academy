import java.util.*;

public class HumanVerseGoblins {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Land land = new Land();
        land.createGameBoard();
        Treasure temp = new Treasure();
        int[] humanCoords = land.addHuman();
        Human human = new Human(10, 4, humanCoords[0], humanCoords[1], "Mike");
        int[] treasureCoords = land.addTreasure();
        Treasure treasure = new Treasure(temp.fillTreasuresArray(), treasureCoords[0], treasureCoords[1]);
        HashMap<Integer, int[]> goblinCoords = land.addGoblin(10);
        for(int i=0; i<goblinCoords.size(); i++) {
            land.goblins.add(new Goblin(land.randomNum(2,8), land.randomNum(1,6), goblinCoords.get(i)[0], goblinCoords.get(i)[1], land.randomGoblinName()));
        }
        System.out.println(land.toString(land.grid));
        boolean playGame = true;

        // Moving loop
        while(playGame) {
            boolean alive = true;
            System.out.println(human.name + "'s health: " + human.health + "  " + human.name + "'s strength: " + human.strength);
            if(!human.inventory.isEmpty()) {
                System.out.println("Your arsenal" + human.printInventory());
            }
            System.out.println("Make a move");
            String input = scan.next();
            System.out.println(human.move(land.grid, humanCoords, input));

                // Treasure
            if(human.row == treasure.row && human.col == treasure.col) {
                String str = treasure.treasures[land.randomNum(0, 31)];
                System.out.println(human.checkTreasureForPotion(str, treasure, human, land, scan));
            }

                    // Battle
            if(land.testForBattle(goblinCoords, humanCoords)) {
                Goblin goblin = land.getGoblinToFight(humanCoords, land.goblins);
                System.out.println(human.name + "'s health: " + human.health + "  " + goblin.name + "'s health: " + goblin.health);
                if(!human.inventory.isEmpty()) {
                    System.out.println("Your arsenal" + human.printInventory());
                    System.out.println("Would you like to use any items from your arsenal during this battle? (y or n)");
                    String in = scan.next();
                    if (in.equalsIgnoreCase("y")) {
                        System.out.println("Which item would you like to use?");
                        System.out.println("Your arsenal" + human.printInventory());
                        String item = scan.next();
                        human.useItem(item, human, goblin);
                        System.out.println("Press any button to continue");
                        scan.next();
                    }
                }
                    // Battle loop
                while(alive) {
                    System.out.println(human.attack(human, goblin));
                    if(goblin.health > 0) {
                        System.out.println(human.name + "'s health: " + human.health + "  " + goblin.name + "'s health: " + goblin.health);
                        System.out.println("Press any button to continue");
                        scan.next();
                    } else {
                        System.out.println("You have killed " + goblin.name + "!!");
                        System.out.println(human.name + "'s health: " + human.health + "  " + human.name + "'s strength: " + human.strength);
                        land.removeGoblin(goblinCoords, humanCoords);
                        land.grid.get(human.row)[human.col] = " H ";

                        //CREATE NEW TREASURE

                        land.addTreasure();
                        System.out.println("Press any button to continue");
                        scan.next();
                        System.out.println(land.toString(land.grid));
                        break;
                    }
                    System.out.println(goblin.attack(goblin, human));
                    if(human.health > 0) {
                        System.out.println(human.name + "'s health: " + human.health + "  " + goblin.name + "'s health: " + goblin.health);
                        System.out.println("Press any button to continue");
                        scan.next();
                    } else {
                        System.out.println("Game Over\n Seriously, you lose, GAME OVER!\n Sorry :(");
                        alive = false;
                        playGame = false;
                    }

                }
            }
            //playGame = false;
        }




    }
}









/*
    HashMap<Integer, ArrayList<String>> grid = land.createGameBoard();
    HashMap<Integer, Integer> goblinCoordinates = new HashMap<>();
    HashMap<Integer, Integer> humanCoordinates = new HashMap<>();
        land.addBeing("H", grid);
                land.addBeing("G", grid);

                System.out.println(land.toString(grid));
                System.out.println(land.findCoordinates("G", grid, goblinCoordinates));
                System.out.println(land.findCoordinates("H", grid, humanCoordinates));

 */