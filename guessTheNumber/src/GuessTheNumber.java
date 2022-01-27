import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        int magicNumber;
        int guess;
        String playAgain;
        String name = "";
        boolean incorrect = true;
        Random randomNumber = new Random();
        Scanner scan = new Scanner(System.in);
        intro(scan);
        while(incorrect) {
            magicNumber = randomNumber.nextInt(20) + 1;
            int count = 0;
            do {
                count++;
                startGuessing();
                while(true) {
                    try {
                        guess = Integer.parseInt(scan.next());
                        break;
                    } catch(Exception e) {
                        System.out.println("You must guess an integer between 1 and 20.");
                        startGuessing();
                    }
                }
                if (guess > magicNumber) {
                    System.out.println("Your guess is too high.");
                } else if (guess < magicNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Good job, " + name + "! You guessed my number in " + count + " guesses!");
                }
            } while (magicNumber != guess);

            System.out.println("Press 'y' if you would like to play again.");
            playAgain = scan.next().toLowerCase();

            if (!playAgain.equals("y")) {
                System.out.println("Thanks for playing!");
                incorrect = false;
            }
        }
    }
    public static void intro(Scanner scanner) {
        System.out.println("Hello! What is your name?");
        String name = scanner.next();
        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");
    }

    public static void startGuessing() {
        System.out.println("Take a guess.");
    }

}
