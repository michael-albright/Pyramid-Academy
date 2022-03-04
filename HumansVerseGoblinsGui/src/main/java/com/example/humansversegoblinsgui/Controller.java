package com.example.humansversegoblinsgui;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;


public class Controller {
    //Data Fields
    GameSetUp game = new GameSetUp();
    //Land land = new Land();
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
    Rectangle map;

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

                ////// setEntity AND setEntityCoords should be a universal method in Entity and ran here
                                 ///// Add Entities to GUI //////
    @FXML
    public void setHumanImage() {
        int[] arr = game.placeElement();
        //System.out.println("Human new" + Arrays.toString(arr));
        humImg.setImage(new Image("human.png"));
        humImg.setTranslateX(arr[0]);
        humImg.setTranslateY(arr[1]);
        human.setHumanCoords(arr);
        //System.out.println("Human coords" + Arrays.toString(human.humanCoords));
    }
    @FXML
    public void setGoblinImage() {
        gobImg.setImage(new Image("goblin.png"));
        while (true) {
            int[] arr = game.placeElement();
            //System.out.println("Gobl new" + Arrays.toString(arr));
            if (!Arrays.equals(arr, human.humanCoords)) {
                gobImg.setTranslateX(arr[0]);
                gobImg.setTranslateY(arr[1]);
                goblin.setGoblinCoords(arr);
                //System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
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
            //System.out.println("Treas new" + Arrays.toString(arr));
            if (!Arrays.equals(arr, human.humanCoords) && !Arrays.equals(arr, goblin.goblinCoords)) {
                treasImg.setTranslateX(arr[0]);
                treasImg.setTranslateY(arr[1]);
                treasure.setTreasureCoords(arr);
                //System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
                break;
            }
            System.out.println("treasure same");
        }
    }
    // gobImg.getTranslateX() != arr[0] || gobImg.getTranslateY() != arr[1] && humImg.getTranslateX() != arr[0] || humImg.getTranslateY() != arr[1]


                            ////////    BATTLE    ///////////
    @FXML
    public void humanVsGoblin() {
        while (true) {
            battleText.appendText(human.attack(human, goblin) + "\n");
            outputHumanStatus();
            outputGoblinStatus();
            if (goblin.health <= 0) {
                battleText.appendText("You killed the Goblin!\n");
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
                battleText.appendText(goblin.attack(goblin, human) + "\n");
                if(checkForUsedArmor()) {
                    outputHumanStatus();
                    outputGoblinStatus();
                }
                if (human.health <= 0) {
                    battleText.appendText("The Goblin killed you.\nGame Over.");
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
        statusText.setText("Your Health: " + human.health + "\n" + "Your Strength: " + human.strength);
    }

    @FXML
    public void outputGoblinStatus() {
        if(goblin.health < 0) goblin.health = 0;
        statusText.appendText("\nGoblin Health: " + goblin.health + "\n" + "Goblin Strength: " + goblin.strength);
    }

                                ////////// TREASURE STUFF ////////////
    @FXML
    public void openTreasure() {
        // .resizerelocate()  or
        treasImg.setVisible(false);
        treasImg.setManaged(false);
        treasure.treasureCoords[0] = 370;
        treasure.treasureCoords[1] = 111;
        //gameBoard.getChildren().remove(treasImg);
        String booty = treasure.treasures[game.randomNum(0, 22)];
        if (booty.equals(treasure.potion)) {
            battleText.appendText("*****You have found a rare item!*****\nThe Magic Potion rasied your health by 10!\n");
            human.health = human.health + 10;
        } else if (booty.equals(treasure.bomb)) {
            battleText.appendText("?%&$!BaAaAaNG!$&%?\nThe treasure chest blew!\nYou lost 5 health points.\n");
            human.health = human.health - 5;
            if (human.health <= 0) battleText.appendText("Sorry, the bOmB killed you.\nGame Over.");
        } else {
            if(human.arsenal.contains(booty)) {
                battleText.appendText( booty + " is already in your arsenal.\n");
            } else {
                human.arsenal.add(booty);
                if (booty.equals(treasure.dagger)) {
                    battleText.appendText("The treasure chest contained a Dagger.\nIt has been added to your arsenal.\n");
                    daggerButton.setDisable(false);
                } else if (booty.equals(treasure.sword)) {
                    battleText.appendText("The treasure chest contained a Sword.\nIt has been added to your arsenal.\n");
                    swordButton.setDisable(false);
                } else {
                    battleText.appendText("The treasure chest contained Armor.\nIt has been added to your arsenal.\n");
                    armorButton.setDisable(false);
                }
            }
        }
    }

    @FXML
    public void useDagger() {
        human.strength = 5;
        battleText.appendText("Your strength is up 1 for your next battle.\n");
        outputHumanStatus();
        outputGoblinStatus();
        daggerButton.setDisable(true);
        human.arsenal.remove("Dagger");
    }

    @FXML
    public void useSword() {
        human.strength = 7;
        battleText.appendText("Your strength is up 3 for your next battle.\n");
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
        battleText.appendText("All Goblin's strength will be a measley 2 until you are struck again.\n");
        outputHumanStatus();
        outputGoblinStatus();
        armorButton.setDisable(true);
    }

    @FXML
    public boolean checkForUsedWeapon() {
        if (human.strength == 5) {
            human.strength = 4;
            return true;
        }
        if (human.strength == 7) {
            human.strength = 4;
            return true;
        }
        if (human.strength == 8) {
            human.strength = 4;
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
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        String str = game.checkForOpenSpace("right", human, goblin, treasure);
        switch (str) {
            case "move" -> {
                humImg.setTranslateX(humImg.getTranslateX() + 37);
                setHumanCoords();
                System.out.println(Arrays.toString(human.humanCoords));
            }
            case "goblin" -> {
                //humImg.setTranslateX(humImg.getTranslateX() + 37);
                humanVsGoblin();
                System.out.println(Arrays.toString(human.humanCoords));
            }
            case "treasure" -> {
                humImg.setTranslateX(humImg.getTranslateX() + 37);
                setHumanCoords();
                openTreasure();
                System.out.println(Arrays.toString(human.humanCoords));
            }
        }
    }

    @FXML
    public void moveLeft() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        String temp = game.checkForOpenSpace("left", human, goblin, treasure);
        switch (temp) {
            case "move" -> {
                humImg.setTranslateX(humImg.getTranslateX() - 37);
                setHumanCoords();
                System.out.println(Arrays.toString(human.humanCoords));
            }
            case "goblin" -> {
                //humImg.setTranslateX(humImg.getTranslateX() - 37);
                humanVsGoblin();
                System.out.println(Arrays.toString(human.humanCoords));
            }
            case "treasure" -> {
                humImg.setTranslateX(humImg.getTranslateX() - 37);
                setHumanCoords();
                openTreasure();
                System.out.println(Arrays.toString(human.humanCoords));
            }
        }
    }

    @FXML
    public void moveUp() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
            String temp = game.checkForOpenSpace("up", human, goblin, treasure);
            switch (temp) {
                case "move" -> {
                    humImg.setTranslateY(humImg.getTranslateY() - 37);
                    setHumanCoords();
                    System.out.println(Arrays.toString(human.humanCoords));
                }
                case "goblin" -> {
                    //humImg.setTranslateY(humImg.getTranslateY() - 37);
                    humanVsGoblin();
                    System.out.println(Arrays.toString(human.humanCoords));
                }
                case "treasure" -> {
                    humImg.setTranslateY(humImg.getTranslateY() - 37);
                    setHumanCoords();
                    openTreasure();
                    System.out.println(Arrays.toString(human.humanCoords));
                }
            }
    }

    @FXML
    public void moveDown() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        String temp = game.checkForOpenSpace("down", human, goblin, treasure);
        switch (temp) {
            case "move" -> {
                humImg.setTranslateY(humImg.getTranslateY() + 37);
                setHumanCoords();
                System.out.println(Arrays.toString(human.humanCoords));
            }
            case "goblin" -> {
                //humImg.setTranslateY(humImg.getTranslateY() + 37);
                humanVsGoblin();
                System.out.println(Arrays.toString(human.humanCoords));
            }
            case "treasure" -> {
                humImg.setTranslateY(humImg.getTranslateY() + 37);
                setHumanCoords();
                openTreasure();
                System.out.println(Arrays.toString(human.humanCoords));
            }
        }
    }


}



/*
    @FXML
    public void setArsenalButtons() {
        if(human.arsenal.contains("dagger")) {
            daggerButton.setDisable(false);
        } else if(human.arsenal.contains("sword")) {
            swordButton.setDisable(false);
        } else armorButton.setDisable(false);
    }




      @FXML
    public void moveRight() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        if(game.checkForOpenSpace("right", human, goblin, treasure)) {
            humImg.setTranslateX(humImg.getTranslateX() + 37);
            setHumanCoords();
            System.out.println(Arrays.toString(human.humanCoords));
        } else if(Arrays.equals(human.humanCoords[0] + 37, , goblin.goblinCoords)) {
                humanVsGoblin();
        } else if (Arrays.equals(human.humanCoords, treasure.treasureCoords)) {
            humImg.setTranslateX(humImg.getTranslateX() + 37);
            setHumanCoords();
            openTreasure();
        }
    }


      @FXML
    public void moveLeft() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        if (game.checkForOpenSpace("left", human, goblin, treasure)) {
            humImg.setTranslateX(humImg.getTranslateX() - 37);
            int[] arr = {(int) humImg.getTranslateX(), (int) humImg.getTranslateY()};
            human.setHumanCoords(arr);
            System.out.println(Arrays.toString(human.humanCoords));
        } else if(humImg.getTranslateX() - 37 == gobImg.getTranslateX() && humImg.getTranslateY() == gobImg.getTranslateY()) {
                humanVsGoblin();
        } else if (humImg.getTranslateX() - 37 == treasImg.getTranslateX() && humImg.getTranslateY() == treasImg.getTranslateY()) {
            humImg.setTranslateX(humImg.getTranslateX() - 37);
            setHumanCoords();
            openTreasure();
        }
    }

    @FXML
    public void moveUp() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        if(game.checkForOpenSpace("up", human, goblin, treasure)) {
            humImg.setTranslateY(humImg.getTranslateY() - 37);
            int[] arr = {(int) humImg.getTranslateX(), (int) humImg.getTranslateY()};
            human.setHumanCoords(arr);
            System.out.println(Arrays.toString(human.humanCoords));
        } else if (humImg.getTranslateY() - 37 == gobImg.getTranslateY() && humImg.getTranslateX() == gobImg.getTranslateX()) {
                humanVsGoblin();
        } else if (humImg.getTranslateY() - 37 == treasImg.getTranslateY() && humImg.getTranslateX() == treasImg.getTranslateX()) {
            humImg.setTranslateY(humImg.getTranslateY() - 37);
            setHumanCoords();
            openTreasure();
        }
    }

    @FXML
    public void moveDown() {
        System.out.println("Goblin coords " + Arrays.toString(goblin.goblinCoords));
        System.out.println("Treas coords " + Arrays.toString(treasure.treasureCoords));
        if(game.checkForOpenSpace("down", human, goblin, treasure)) {
            humImg.setTranslateY(humImg.getTranslateY() + 37);
            int[] arr = {(int) humImg.getTranslateX(), (int) humImg.getTranslateY()};
            human.setHumanCoords(arr);
            System.out.println(Arrays.toString(human.humanCoords));
        } else if (humImg.getTranslateY() + 37 == gobImg.getTranslateY() && humImg.getTranslateX() == gobImg.getTranslateX()) {
            humanVsGoblin();
        } else if (humImg.getTranslateY() + 37 == treasImg.getTranslateY() && humImg.getTranslateX() == treasImg.getTranslateX()) {
            humImg.setTranslateY(humImg.getTranslateY() + 37);
            setHumanCoords();
            openTreasure();
        }
    }

 */