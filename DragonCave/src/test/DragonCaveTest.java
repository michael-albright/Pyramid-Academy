import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DragonCaveTest {

    DragonCave dragonCave ;

    @BeforeEach
    void setUp() {
        dragonCave = new DragonCave();
    }

    @Test
    void intro() {
        assertEquals("You are in a land full of dragons. In front of you appears two caves. In one " +
                        "cave, you will find Jacob the Dragon, a friendly fire breather, who loves to share his treasure. " +
                        "In the other cave lies a greedy and very hungry dragon named Adam who loves to eat people whole." +
                        "You must choose your destiny. Which cave will you choose? (1 or 2) Choose '0' to exit the program.",
                dragonCave.intro(), "Intro message incorrect.");
    }

    @Test
    void caveChoice() {
        assertAll("Cave choices by number",
                () -> assertEquals("You approach the cave... It is dark and spooky... A dragon jumps out in front " +
                        "of you! He opens his jaws and... Into his belly you go with just one bite! Welcome to " +
                        "Adam the Dragon's lair.", dragonCave.caveChoice(1), "Cave choice '1' output incorrect."),
                () -> assertEquals("You approach the cave... It is dark and spooky... A dragon jumps out in front " +
                        "of you! He opens his jaws and... Gives you an extra-teethy grin breathing out, 'Welcome to Jacob " +
                        "the Dragon's lair! What's yours is mine, the treasure you find, will fill your pockets until the " +
                        "end of time!", dragonCave.caveChoice(2), "Cave choice '2' output incorrect."),
                () -> assertEquals("Bye!", dragonCave.caveChoice(0), "Cave choice '1' output incorrect."),
                () -> assertEquals("You have entered an invalid integer and the caves have collapsed. Bye!",
                        dragonCave.caveChoice(3), "Cave choice '!= '1, 2, or 0' output incorrect."));
    }

    @AfterEach
    void tearDown() {

    }
}