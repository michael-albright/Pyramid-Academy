import java.util.Scanner;

public class DragonCave {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println(intro());
            try {
                System.out.println(caveChoice(Integer.parseInt(scan.next())));
                break;
            } catch(Exception e) {
                System.out.println("You must enter an Integer.");
            }
        }
    }
    public static String intro() {
        return "You are in a land full of dragons. In front of you appears two caves. In one " +
                "cave, you will find Jacob the Dragon, a friendly fire breather, who loves to share his treasure. " +
                "In the other cave lies a greedy and very hungry dragon named Adam who loves to eat people whole." +
                "You must choose your destiny. Which cave will you choose? (1 or 2) Choose '0' to exit the program.";
    }
    public static String caveChoice(int x) {
        if (x == 1) {
            return "You approach the cave... It is dark and spooky... A dragon jumps out in front " +
                    "of you! He opens his jaws and... Into his belly you go with just one bite! Welcome to " +
                    "Adam the Dragon's lair.";
        } else if (x == 2) {
            return "You approach the cave... It is dark and spooky... A dragon jumps out in front " +
                    "of you! He opens his jaws and... Gives you an extra-teethy grin breathing out, 'Welcome to Jacob " +
                    "the Dragon's lair! What's yours is mine, the treasure you find, will fill your pockets until the " +
                    "end of time!";
        } else if (x == 0) {
            return "Bye!";
        } else {
            return "You have entered an invalid integer and the caves have collapsed. Bye!";
        }
    }
}

