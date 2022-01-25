import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) {
        String intro = "You are in a land full of dragons. In front of you appears two caves. In one " +
                "cave, you will find Jacob the Dragon, a friendly fire breather, who loves to share his treasure.\n" +
                "In the other cave lives a greedy and very hungry dragon named Adam who loves to eat people whole.\n" +
                "You must choose your destiny. Which cave will you choose? (1 or 2) Choose '0' to exit the program.";
        System.out.println(intro);
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if(choice == 1) {
            System.out.println("You approach the cave...\nIt is dark and spooky...\nA dragon jumps out in front " +
                    "of you! He opens his jaws and...\nInto his belly you go with just one bite! Welcome to " +
                    "Adam the Dragon's lair.");
        } else if(choice == 2) {
            System.out.println("You approach the cave...\nIt is dark and spooky...\nA dragon jumps out in front " +
                    "of you! He opens his jaws and...\nGives you an extra-teethy grin breathing out, 'Welcome to Jacob" +
                    " the Dragon's lair! What's yours is mine, the treasure you find, will fill your pockets until the" +
                    " end of time!'");
        } else if(choice == 0) {
                System.out.println("Bye!");
        } else {
                System.out.println("You have entered an invalid response. Please try again.\n " + intro);
                choice = scan.nextInt();
            }
        }
    }

