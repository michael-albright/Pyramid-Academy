import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GuessTheNumberTest {

    GuessTheNumber guessTheNumber;

    @BeforeEach
    void setUp() {
        guessTheNumber = new GuessTheNumber();
    }

    @DisplayName("Introduction and name build")
    @Test
    void intro() {
        String name = "Mike";
        assertEquals("Well, " + name + ", I am thinking of a number between 1 and 20.", GuessTheNumber.intro(name), "Intro did not execute.");
    }

    @DisplayName("Start guessing")
    @Test
    void startGuessing() {
        assertEquals("Take a guess.", GuessTheNumber.startGuessing(), "Output incorrect.");
    }

    @DisplayName("Testing users guesses")
    @Test
    void testTheGuess() {
       String name = "Mike";
        int count = 0;
        assertAll("Test each input response.",
                () -> assertEquals("Your guess is too high.", GuessTheNumber.testTheGuess(8, 5, name, 2),
                        "Output incorrect."),
                () -> assertEquals("Your guess is too low.", GuessTheNumber.testTheGuess(5, 8, name, 2),
                        "Output incorrect."),
                () -> assertEquals( name + "! You are a true CHAMPION!! Let's see you do it again!", GuessTheNumber.testTheGuess(8, 8, name, 1),
                        "Output incorrect."),
                () -> assertEquals("Good job, " + name + "! You guessed my number in " + count + " guesses!", GuessTheNumber.testTheGuess(8, 8, name, 0),
                        "Output incorrect."));

    }

    @AfterEach
    void tearDown() {
    }
}