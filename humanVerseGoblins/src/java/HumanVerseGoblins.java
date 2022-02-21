import java.util.*;

public class HumanVerseGoblins {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            Land land = new Land();
            land.createGameBoard();

            // Add a human
            HashMap<Integer, int[]> humanCoords = new HashMap<>();
            land.addElement(1, humanCoords, " H ");
            Human human = new Human(5, 4, humanCoords.get(0)[0], humanCoords.get(0)[1], "Mike");
            String[] tempWeapon = {"no weapon"};

            // Add treasures
            Treasure temp = new Treasure();
            HashMap<Integer, int[]> treasureCoords = new HashMap<>();
            land.addElement(1, treasureCoords, "[*]");
            for (int i = 0; i < treasureCoords.size(); i++) {
                land.treasureChests.add(new Treasure(temp.fillTreasuresArray(), treasureCoords.get(i)[0], treasureCoords.get(i)[1]));
            }
            // Add goblins
            HashMap<Integer, int[]> goblinCoords = new HashMap<>();
            land.addElement(10, goblinCoords, " G ");
            for (int i = 0; i < goblinCoords.size(); i++) {
                land.goblins.add(new Goblin(land.randomNum(2, 8), land.randomNum(2, 6), goblinCoords.get(i)[0], goblinCoords.get(i)[1], land.randomGoblinName(), land.setGoblinMovement()));
            }
            System.out.println("\nGameplay: \nThe object of the game is to kill all the Goblins before your \n" +
                    "health runs out. \n\nTreasures will appear after each Goblin you take down as reward! \n\n" +
                    "'i' -> move up, 'k' -> move down, 'j' -> move left, 'l' -> move right.. \n\nGood Luck!!\n");
            System.out.println("Press any key to continue");
            scan.next();
            System.out.println(land.toString(land.grid));
            boolean playGame = true;

            // Moving loop
            while (playGame) {
                boolean alive = true;
                System.out.println(human.name + "'s health: " + human.health + "  " + human.name + "'s strength: " + human.strength);
                if (!human.inventory.isEmpty()) {
                    System.out.println("Your arsenal" + human.printInventory(human.inventory));
                }

                System.out.println("Make a move");
                String input = scan.next();
                System.out.println(human.move(land.grid, humanCoords, input));

                // Check for treasure chest
                if (land.testForElement(treasureCoords, humanCoords)) {
                    Treasure treasure = land.getTreasure(humanCoords, land.treasureChests);
                    String str = treasure.treasures[land.randomNum(0, 22)];
                     if(!human.checkTreasureForPotion(str, treasure, human, land, scan)) {
                         playGame = false;
                     }
                    land.removeElement(treasureCoords, humanCoords);
                }

                // Check for Goblin
                if (land.testForElement(goblinCoords, humanCoords)) {
                    Goblin goblin = land.getGoblinToFight(humanCoords, land.goblins);
                    System.out.println(human.name + "'s health: " + human.health + "  " + goblin.name + "'s health: " + goblin.health);
                    System.out.println();

                    //Check arsenal for weapons and use weapons
                    if (!human.inventory.isEmpty()) {
                        System.out.println("Your arsenal" + human.printInventory(human.inventory));
                        System.out.println("Would you like to use any items from your arsenal during this battle? (y or n)");
                        String in = scan.next();

                        // Use weapons
                        if (in.equalsIgnoreCase("y")) {
                            while (tempWeapon[0].equals("no weapon")) {
                                System.out.println("Which item would you like to use?");
                                System.out.println("Your arsenal" + human.printInventory(human.inventory));
                                String item = scan.next();
                                System.out.println(human.useItem(item, human, goblin, tempWeapon));
                            }
                            System.out.println("Press any key to continue");
                            scan.next();
                        }
                    }
                    // Battle loop
                    while (alive) {
                        System.out.println(human.attack(human, goblin));
                        if (goblin.health > 0) {
                            System.out.println(human.name + "'s health: " + human.health + "  " + goblin.name + "'s health: " + goblin.health);
                            System.out.println("Press any key to continue");
                            scan.next();
                        } else {
                            System.out.println("You have killed " + goblin.name + "!!");
                            if (land.checkForWin(goblinCoords)) {
                                System.out.println("Congratulations!! You have killed all the Goblins.");
                                playGame = false;
                                break;
                            }
                            if (!tempWeapon[0].equals("no weapon")) {
                                human.removeWeapon(human, tempWeapon);
                            }
                            System.out.println(human.name + "'s health: " + human.health + "  " + human.name + "'s strength: " + human.strength);
                            land.removeElement(goblinCoords, humanCoords);
                            land.grid.get(human.row)[human.col] = " H ";


                            //CREATE NEW TREASURE
                            int z = treasureCoords.size();
                            land.addElement(1, treasureCoords, "[*]");
                            land.treasureChests.add(new Treasure(temp.fillTreasuresArray(), treasureCoords.get(z)[0], treasureCoords.get(z)[1]));
                            System.out.println("Press any key to continue");
                            scan.next();
                            System.out.println(land.toString(land.grid));
                            break;
                        }
                        System.out.println(goblin.attack(goblin, human));
                        if (human.health > 0) {
                            System.out.println(human.name + "'s health: " + human.health + "  " + goblin.name + "'s health: " + goblin.health);
                            System.out.println("Press any key to continue");
                            scan.next();
                        } else {
                            System.out.println("Game Over\nSeriously, you lose, GAME OVER!\nSorry :(");
                            alive = false;
                            playGame = false;
                        }

                    }
                }
            }
            System.out.println("Would you like to play again?");
            String in = scan.next();
            if(!in.equals("y")) {
                System.out.println("Thanks for playing!");
                playAgain = false;
            }
        }
    }
}

/*
                for(Goblin i : land.goblins) {
                    if (i.move) {
                        /////// UP ///////
                        while(true) {
                            if (human.row < i.row && i.row - 1 >= 1) {
                                if (!land.grid.get(i.row - 1)[i.col].equals(" G ")) {
                                    if (land.grid.get(i.row - 1)[i.col].equals(" - ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row - 1)[i.col] = " G ";
                                        i.row = i.row - 1;
                                        break;
                                    } else if (land.grid.get(i.row - 1)[i.col].equals(" H ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row - 1)[i.col] = "G:H";
                                        i.row = i.row - 1;
                                        break;
                                    } else {
                                        System.out.println("A goblin has destroyed the treasure.");
                                        land.testForElement(treasureCoords, goblinCoords);
                                        land.removeElement(treasureCoords, goblinCoords);
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row - 1)[i.col] = " G ";
                                        i.row = i.row - 1;
                                        break;
                                    }
                                } else {
                                    int x = 2;
                                    while (true) {
                                        if (land.grid.get(i.row - x)[i.col].equals(" - ")) {
                                            land.grid.get(i.row)[i.col] = " - ";
                                            land.grid.get(i.row - x)[i.col] = " G ";
                                            i.row = i.row - x;
                                            break;
                                        }
                                        x++;
                                    }
                                    break;
                                }
                            }
                            /////// DOWN ///////
                            if (human.row > i.row && i.row + 1 < 16) {
                                if (!land.grid.get(i.row + 1)[i.col].equals(" G ")) {
                                    if (land.grid.get(i.row + 1)[i.col].equals(" - ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row + 1)[i.col] = " G ";
                                        i.row = i.row + 1;
                                        break;
                                    } else if (land.grid.get(i.row + 1)[i.col].equals(" H ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row + 1)[i.col] = "G:H";
                                        i.row = i.row + 1;
                                        break;
                                    } else {
                                        System.out.println("A goblin has destroyed the treasure.");
                                        land.testForElement(treasureCoords, goblinCoords);
                                        land.removeElement(treasureCoords, goblinCoords);
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row + 1)[i.col] = " G ";
                                        i.row = i.row + 1;
                                        break;
                                    }
                                } else {
                                    int x = 2;
                                    while (true) {
                                        if (land.grid.get(i.row + x)[i.col].equals(" - ")) {
                                            land.grid.get(i.row)[i.col] = " - ";
                                            land.grid.get(i.row + x)[i.col] = " G ";
                                            i.row = i.row + x;
                                            break;
                                        }
                                        x++;
                                    }
                                    break;
                                }

                            }
                            /////// LEFT ///////
                            if (human.col < i.col && i.col - 1 >= 0) {
                                if (!land.grid.get(i.row)[i.col - 1].equals(" G ")) {
                                    if (land.grid.get(i.row)[i.col - 1].equals(" - ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row)[i.col - 1] = " G ";
                                        i.col = i.col - 1;
                                        break;
                                    } else if (land.grid.get(i.row)[i.col - 1].equals(" H ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row)[i.col - 1] = "G:H";
                                        i.col = i.col - 1;
                                        break;
                                    } else {
                                        System.out.println("A goblin has destroyed the treasure.");
                                        land.testForElement(treasureCoords, goblinCoords);
                                        land.removeElement(treasureCoords, goblinCoords);
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row)[i.col - 1] = " G ";
                                        i.col = i.col - 1;
                                        break;
                                    }
                                } else {
                                    int x = 2;
                                    while (true) {
                                        if (land.grid.get(i.row)[i.col - x].equals(" - ")) {
                                            land.grid.get(i.row)[i.col] = " - ";
                                            land.grid.get(i.row)[i.col - x] = " G ";
                                            i.col = i.col - x;
                                            break;
                                        }
                                        x++;
                                    }
                                    break;
                                }
                            }
                            /////// RIGHT ///////
                            if (human.col > i.col && i.col + 1 < 15) {
                                if (!land.grid.get(i.row)[i.col + 1].equals(" G ")) {
                                    if (land.grid.get(i.row)[i.col + 1].equals(" - ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row)[i.col + 1] = " G ";
                                        i.col = i.col + 1;
                                        break;
                                    } else if (land.grid.get(i.row)[i.col + 1].equals(" H ")) {
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row)[i.col + 1] = "G:H";
                                        i.col = i.col + 1;
                                        break;
                                    } else {
                                        System.out.println("A goblin has destroyed the treasure.");
                                        land.testForElement(treasureCoords, goblinCoords);
                                        land.removeElement(treasureCoords, goblinCoords);
                                        land.grid.get(i.row)[i.col] = " - ";
                                        land.grid.get(i.row)[i.col + 1] = " G ";
                                        i.col = i.col + 1;
                                        break;
                                    }
                                } else {
                                    int x = 2;
                                    while (true) {
                                        if (land.grid.get(i.row)[i.col + x].equals(" - ")) {
                                            land.grid.get(i.row)[i.col] = " - ";
                                            land.grid.get(i.row)[i.col + x] = " G ";
                                            i.col = i.col + x;
                                            break;
                                        }
                                        x++;
                                    }
                                    break;
                                }
                            }
                        }

                    }
                }
                for(Goblin i : land.goblins) {
                    for(int j=0; j< goblinCoords.size(); j++) {
                        if(goblinCoords.get(j)[0] != i.row || goblinCoords.get(j)[1] != i.col) {
                            int[] tempArr = {i.row, i.col};
                            goblinCoords.replace(j, tempArr);
                        }
                    }
                }




 */







