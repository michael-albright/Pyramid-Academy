import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {

    Land land;
    HashMap<Integer, int[]> hash1 = new HashMap<>();
    HashMap<Integer, int[]> hash2 = new HashMap<>();
    HashMap<Integer, int[]> hash3 = new HashMap<>();
    HashMap<Integer, int[]> hash4 = new HashMap<>();
    ArrayList<Goblin> arrList = new ArrayList<>();
    ArrayList<Treasure> arrList2 = new ArrayList<>();
    Goblin goblin = new Goblin(5, 4, 5, 4, "Goblin");
    Treasure treasure = new Treasure();


    @BeforeEach
    void setUp() {
        land = new Land();
    }

    @Test
    void testForElement() {
        int[] arr1 = { 5, 4 };
        int[] arr2 = { 5, 4 };
        hash1.put(1, arr1);
        hash2.put(1, arr1);
        assertTrue(land.testForElement(hash1, hash2), "Not true");
        int[] arr3 = { 5, 6 };
        int[] arr4 = { 5, 4 };
        hash3.put(1, arr3);
        hash4.put(1, arr4);
        assertTrue(land.testForElement(hash3, hash4), "Not false");
    }

    @Test
    void getGoblinToFight() {
        Goblin fighter = null;
        int[] arr1 = { 5, 4 };
        hash1.put(0, arr1);
        arrList.add(goblin);
        assertEquals(goblin, land.getGoblinToFight(hash1, arrList), "Did not return a Goblin object");
    }

    @Test
    void getTreasure() {
        int[] arr1 = { 5, 4 };
        hash1.put(0, arr1);
        treasure.row = 5;
        treasure.col = 4;
        arrList2.add(treasure);
        assertEquals(treasure, land.getTreasure(hash1, arrList2), "Did not return a Treasure object");
    }

    @Test
    void checkForWin() {
        int[] arr1 = { 111 };
        int[] arr2 = { 111 };
        int[] arr3 = { 5, 7 };

        hash1.put(0, arr1);
        hash1.put(1, arr2);
        hash2.put(0, arr3);
        hash2.put(1, arr1);
        assertTrue(land.checkForWin(hash1), "Incorrect outcome for true statement");
        assertFalse(land.checkForWin(hash2), "Incorrect outcome for false statement");

    }

    @AfterEach
    void tearDown() {
    }

}