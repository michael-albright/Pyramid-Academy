import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        int magicNumber;
        int guess;
        boolean incorrect = true;
        Random randomNumber = new Random();
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String name = scan.next();
        System.out.println(intro(name));
        while(incorrect) {
            magicNumber = randomNumber.nextInt(20) + 1;
            int count = 0;
            do {
                count++;
                System.out.println(startGuessing());
                while(true) {
                    try {
                        guess = Integer.parseInt(scan.next());
                        break;
                    } catch(Exception e) {
                        System.out.println("You must guess an integer between 1 and 20.");
                        System.out.println(startGuessing());
                    }
                }
                System.out.println(testTheGuess(guess, magicNumber, name, count));
            } while (magicNumber != guess);

            // Ask about method!!! METHOD BELOW!!
            System.out.println("Press 'y' if you would like to play again.");
            if (!scan.next().equalsIgnoreCase("y")) {
                incorrect = false;
                System.out.println("Thanks for playing!");
            }
        }
    }
    public static String intro(String name) {
        return "Well, " + name + ", I am thinking of a number between 1 and 20.";
    }

    public static String startGuessing() {
        return "Take a guess.";
    }

    public static String testTheGuess(int guess, int magicNumber, String name, int count) {
        if (guess > magicNumber) {
            return "Your guess is too high.";
        } else if (guess < magicNumber) {
            return "Your guess is too low.";
        } else {
            if(count == 1) {
                return name + "! You are a true CHAMPION!! Let's see you do it again!";
            }
            return "Good job, " + name + "! You guessed my number in " + count + " guesses!";
        }
    }
}



/*    public static void replay(Scanner scan, boolean x) {
        System.out.println("Press 'y' if you would like to play again.");
            if (!scan.next().equalsIgnoreCase("y")) {
                x = false;
                System.out.println("Thanks for playing!");
            }

    }

    replay(scan, incorrect);

 */