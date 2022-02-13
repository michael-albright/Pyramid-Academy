import java.util.*;

public class Treasure {
    String dagger = "Dagger"; // 10
    String sword = "Sword"; // 4
    String brass = "Brass Armor"; // 10
    String gold = "Gold Armor"; // 4
    String drink1 = "Magic Potion"; // 1
    String drink2 = "Poison"; // 1
    String[] treasures = new String[30];
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
        for(int i=10; i<20; i++) {
            treasures[i] = brass;
        }
        for(int i=20; i<24; i++) {
            treasures[i] = sword;
        }
        for(int i=24; i<28; i++) {
            treasures[i] = gold;
        }
        for(int i=28; i<29; i++) {
            treasures[i] = drink1;
        }
        for(int i=29; i<30; i++) {
            treasures[i] = drink2;
        }
        return treasures;
    }

}
