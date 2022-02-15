import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class HumanTest {

    Human human;
    Human human2;
    Goblin goblin;
    ArrayList<String> inventory = new ArrayList<String>();
    HashMap<Integer, String[]> hash = new HashMap<>();

    @BeforeEach
    void setUp() {
        goblin = new Goblin();
        human = new Human(inventory);
        human2 = new Human(5, 4, 5, 5, "Mike");
    }

    @Test
    void useItem() {
        inventory.add("dagger");
        inventory.add("sword");
        inventory.add("armor");
        String[] tempWeapon = {"no weapon"};
        assertAll("Test each case.",
                () -> assertEquals("Your strength during this battle will be raised by 1.", human.useItem("dagger",
                        human, goblin, tempWeapon), "Incorrect output for dagger"),
                () -> assertEquals("Your strength during this battle will be raised by 3.", human.useItem("sword",
                        human, goblin, tempWeapon), "Incorrect output for sword"),
                () -> assertEquals("Your armor reduced the strength of your opponent to 1 point during this battle.",
                        human.useItem("armor", human, goblin, tempWeapon), "Incorrect outpu for armor."),
                () -> assertEquals("Your arsenal does not contain that item.", human.useItem("sward", human, goblin, tempWeapon),
                        "Incorrect output for 'not in inventory'"));
    }

    @Test
    void printInventory() {
        //StringBuilder sb = new StringBuilder();
        inventory.add("dagger");
        assertEquals(": dagger ", human.printInventory(inventory), "Does not output a string");
    }


    @AfterEach
    void tearDown() {
    }

}