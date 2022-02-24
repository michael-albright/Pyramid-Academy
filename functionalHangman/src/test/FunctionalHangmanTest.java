import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class FunctionalHangmanTest {

    FunctionalHangman functionalHangman;

    @BeforeEach
     void setUp() {
        functionalHangman = new FunctionalHangman();
    }

    @Test
    void checkForLoss() {
        assertTrue(FunctionalHangman.checkForLoss(7), "Incorrect return of boolean");
        assertFalse(FunctionalHangman.checkForLoss(5), "Incorrect return of boolean");
    }

    @AfterEach
    void tearDown() {
    }
}