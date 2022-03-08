package com.example.humansversegoblinsgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.util.Arrays;


public class Controller {
    GameSetUp game = new GameSetUp();
    Human human;
    Goblin goblin;
    Treasure treasure;

    @FXML
    ImageView humImg;
    @FXML
    ImageView gobImg;
    @FXML
    ImageView treasImg;
    @FXML
    AnchorPane gameBoard;
    @FXML
    TextArea statusText;
    @FXML
    TextArea battleText;
    @FXML
    Button daggerButton;
    @FXML
    Button swordButton;
    @FXML
    Button armorButton;


    @FXML
    public void initialize() {
        human = new Human(10, 4);
        goblin = new Goblin(game.randomNum(1, 8), game.randomNum(1, 6));
        treasure = new Treasure();
        treasure.fillTreasuresArray();
        setHumanImage();
        setGoblinImage();
        setTreasureImage();
        outputHumanStatus();
        outputGoblinStatus();
    }

                                 ///// Add Entities to GUI //////
    @FXML
    public void setHumanImage() {
        int[] arr = game.placeElement();
        humImg.setImage(new Image("human.png"));
        humImg.setTranslateX(arr[0]);
        humImg.setTranslateY(arr[1]);
        human.setHumanCoords(arr);
    }
    @FXML
    public void setGoblinImage() {
        gobImg.setImage(new Image("goblin.png"));
        while (true) {
            int[] arr = game.placeElement();
            if (!Arrays.equals(arr, human.humanCoords) && !Arrays.equals(arr, treasure.treasureCoords)) {
                gobImg.setTranslateX(arr[0]);
                gobImg.setTranslateY(arr[1]);
                goblin.setGoblinCoords(arr);
                break;
            }
            System.out.println("goblin same");
        }
    }
    @FXML
    public void setTreasureImage() {
        if(!treasImg.hasProperties()) {
            treasImg.setImage(new Image("treasure.png"));
        }
        while (true) {
            int[] arr = game.placeElement();
            if (!Arrays.equals(arr, human.humanCoords) && !Arrays.equals(arr, goblin.goblinCoords)) {
                treasImg.setTranslateX(arr[0]);
                treasImg.setTranslateY(arr[1]);
                treasure.setTreasureCoords(arr);
                break;
            }
            System.out.println("treasure same");
        }
    }

                            ////////    BATTLE    ///////////
    @FXML
    public void humanVsGoblin() {
        while (true) {
            battleText.appendText(human.attack(human, goblin) + "\n");
            outputHumanStatus();
            outputGoblinStatus();
            if (goblin.health <= 0) {
                battleText.appendText("\nYou killed the Goblin!\n\n");
                goblin = new Goblin(game.randomNum(1, 10), game.randomNum(1, 6));
                setGoblinImage();
                outputHumanStatus();
                outputGoblinStatus();
                if(treasure.treasureCoords[0] == 370) {
                    setTreasureImage();
                    treasImg.setVisible(true);
                    treasImg.setManaged(true);
                }
                break;
            } else {
                battleText.appendText(goblin.attack(goblin, human) + "\n\n");
                if(checkForUsedArmor()) {
                    outputHumanStatus();
                    outputGoblinStatus();
                }
                if (human.health <= 0) {
                    battleText.appendText("The Goblin killed you.\n\nGame Over.");
                    human.health = 0;
                    outputHumanStatus();
                    gameBoard.getChildren().remove(humImg);
                    break;
                }
            }
        }
        if(checkForUsedWeapon()) {
            outputHumanStatus();
            outputGoblinStatus();
        }
    }

                     ///////////// OUTPUT HEALTH/STRENGTH STATUS ////////////
    @FXML
    public void outputHumanStatus() {
        if(human.health < 0) human.health = 0;
        statusText.setText("          Status:\nYour Health: " + human.health + "\n" + "Your Strength: " + human.strength +
                "\n-----------------------------\n");
    }

    @FXML
    public void outputGoblinStatus() {
        if(goblin.health < 0) goblin.health = 0;
        statusText.appendText("Goblin Health: " + goblin.health + "\n" + "Goblin Strength: " + goblin.strength);
    }

                                ////////// TREASURE STUFF ////////////
    @FXML
    public void openTreasure() {
        treasImg.setVisible(false);
        treasImg.setManaged(false);
        treasure.treasureCoords[0] = 370;
        treasure.treasureCoords[1] = 111;
        String booty = treasure.treasures[game.randomNum(0, 22)];
        if (booty.equals(treasure.potion)) {
            battleText.appendText("*** Rare Item! ***\n\nThe Magic Potion rasied your health by 10!\n\n");
            human.health = human.health + 10;
            outputHumanStatus();
            outputGoblinStatus();
        } else if (booty.equals(treasure.bomb)) {
            battleText.appendText("?%&$!BaAaAaNG!$&%?\n\nThe treasure chest blew!\nYou lost 5 health points.\n\n");
            human.health = human.health - 5;
            outputHumanStatus();
            outputGoblinStatus();
            if (human.health <= 0) battleText.appendText("Sorry, the bOmB killed you.\n\nGame Over.");
        } else {
            if(human.arsenal.contains(booty)) {
                battleText.appendText( booty + " is already in your arsenal.\n\n");
            } else {
                human.arsenal.add(booty);
                if (booty.equals(treasure.dagger)) {
                    battleText.appendText("The treasure chest contained a Dagger.\nIt has been added to your arsenal.\n\n");
                    daggerButton.setDisable(false);
                } else if (booty.equals(treasure.sword)) {
                    battleText.appendText("The treasure chest contained a Sword.\nIt has been added to your arsenal.\n\n");
                    swordButton.setDisable(false);
                } else {
                    battleText.appendText("The treasure chest contained Armor.\nIt has been added to your arsenal.\n\n");
                    armorButton.setDisable(false);
                }
            }
        }
    }

    @FXML
    public void useDagger() {
        human.strength = 5;
        battleText.appendText("Your strength is up 1 for your next battle.\n\n");
        outputHumanStatus();
        outputGoblinStatus();
        daggerButton.setDisable(true);
        human.arsenal.remove("Dagger");
    }

    @FXML
    public void useSword() {
        human.strength = 7;
        battleText.appendText("Your strength is up 3 for your next battle.\n\n");
        outputHumanStatus();
        outputGoblinStatus();
        swordButton.setDisable(true);
        human.arsenal.remove("Sword");
    }

    @FXML
    public void useArmor() {
        human.arsenal.remove("Armor");
        human.arsenal.add("usedArmor");
        goblin.strength = 2;
        battleText.appendText("All Goblin's strength will be a measley 2 until you are struck again.\n\n");
        outputHumanStatus();
        outputGoblinStatus();
        armorButton.setDisable(true);
    }

    @FXML
    public boolean checkForUsedWeapon() {
        if (human.strength == 5 || human.strength == 8 || human.strength == 7) {
            human.strength = 4;
            //human.strength = human.strength - 1;
            return true;
        }
        return false;
    }

    @FXML
    public boolean checkForUsedArmor() {
        if (human.arsenal.contains("usedArmor")) {
            human.arsenal.remove("Armor");
            goblin.strength = game.randomNum(1, 6);
            return true;
        }
        return false;
    }

                            //////////// MOVE HUMAN //////////  FINAL SECTION /////////
    @FXML
    public void setHumanCoords() {
        int[] arr = {(int) humImg.getTranslateX(), (int) humImg.getTranslateY()};
        human.setHumanCoords(arr);
    }
    @FXML
    public void moveRight() {
        String str = game.checkForOpenSpace("right", human, goblin, treasure);
        switch (str) {
            case "move" -> {
                humImg.setTranslateX(humImg.getTranslateX() + 37);
                setHumanCoords();
            }
            case "goblin" -> {
                battleText.appendText("\n** Human Verse Goblin **\n\n");
                humanVsGoblin();
            }
            case "treasure" -> {
                humImg.setTranslateX(humImg.getTranslateX() + 37);
                setHumanCoords();
                openTreasure();
            }
        }
    }

    @FXML
    public void moveLeft() {
        String temp = game.checkForOpenSpace("left", human, goblin, treasure);
        switch (temp) {
            case "move" -> {
                humImg.setTranslateX(humImg.getTranslateX() - 37);
                setHumanCoords();
            }
            case "goblin" -> {
                battleText.appendText("\n** Human Verse Goblin **\n\n");
                humanVsGoblin();
            }
            case "treasure" -> {
                humImg.setTranslateX(humImg.getTranslateX() - 37);
                setHumanCoords();
                openTreasure();
            }
        }
    }

    @FXML
    public void moveUp() {
            String temp = game.checkForOpenSpace("up", human, goblin, treasure);
            switch (temp) {
                case "move" -> {
                    humImg.setTranslateY(humImg.getTranslateY() - 37);
                    setHumanCoords();
                }
                case "goblin" -> {
                    battleText.appendText("\n** Human Verse Goblin **\n\n");
                    humanVsGoblin();
                }
                case "treasure" -> {
                    humImg.setTranslateY(humImg.getTranslateY() - 37);
                    setHumanCoords();
                    openTreasure();
                }
            }
    }

    @FXML
    public void moveDown() {
        String temp = game.checkForOpenSpace("down", human, goblin, treasure);
        switch (temp) {
            case "move" -> {
                humImg.setTranslateY(humImg.getTranslateY() + 37);
                setHumanCoords();
            }
            case "goblin" -> {
                battleText.appendText("\n** Human Verse Goblin **\n\n");
                humanVsGoblin();
            }
            case "treasure" -> {
                humImg.setTranslateY(humImg.getTranslateY() + 37);
                setHumanCoords();
                openTreasure();
            }
        }
    }
}
