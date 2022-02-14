import java.util.*;

public class Treasure {
    String dagger = "Dagger"; // 10
    String sword = "Sword"; // 4
    String armor = "Armor"; // 6
    String drink1 = "Magic Potion"; // 1
    String drink2 = "Poison"; // 1
    String[] treasures = new String[22];
    int row;
    int col;

    Treasure() {

    }

    Treasure(String[] treasures, int row, int col) {
        this.treasures = treasures;
        this.row = row;
        this.col = col;
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
            treasures[i] = drink1;
        }
        for(int i=21; i<22; i++) {
            treasures[i] = drink2;
        }
        return treasures;
    }

}
