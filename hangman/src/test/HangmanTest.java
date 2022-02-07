import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class HangmanTest {

    Hangman hangman;

    @BeforeEach
    void setUp() {
        hangman = new Hangman();
    }

    @DisplayName("Check user guess is valid")
    @Test
    void checkForValidGuess() {
        assertEquals(false, Hangman.checkForValidGuess("4"), "Incorrect value returned.");
    }

    @Test
    void outputCorrectLetters() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("a");
        assertEquals("a ", Hangman.outputCorrectLetters(list1), "List to string not correct.");
    }

    @Test
    void outputIncorrectLetters() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("a");
        assertEquals("a ", Hangman.outputCorrectLetters(list1), "List to string not correct.");

    }

    @Test
    void createGameBoard() {
        //assertEquals("[  HANGMAN,   +-----+,   |     |,         |,         |,         |,         | /,         |/,     =========]", Hangman.createGameBoard(), "Game board created incorrectly");
    }

    @Test
    void setGameBoard() {
        //int deathCount = 1;
        //ArrayList<String> arr = new ArrayList<>();
        //assertEquals("  HANGMAN  +-----+  |     |  O     |        |        |        | /        |/    =========", Hangman.setGameBoard(deathCount, arr), "Game board reanimated incorrectly");
    }

    @Test
    void getRandomWord() {

    }

    @AfterEach
    void tearDown() {

    }

}