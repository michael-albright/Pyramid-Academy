import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        int count = 0;
        int guess;
        boolean yes;
        Scanner scan = new Scanner(System.in);
        Random randomNumber = new Random();
        int magicNumber = randomNumber.nextInt(20) + 1;
        System.out.println("Hello! What is your name?");
        String name = scan.next();
        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");
        do {
            do {
                count++;
                System.out.println("Take a guess.");
                guess = scan.nextInt();
                if (guess > magicNumber) {
                    System.out.println("Your guess is too high.");
                } else if (guess < magicNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Good job, " + name + "! You guessed my number in " + count + " guesses!");
                }
            } while (magicNumber != guess);
            System.out.println("Would you like to play again? (y or n)");
            String playAgain = scan.next();
            if (playAgain.equals("y")) {
                yes = true;
            } else {
                yes = false;
                System.out.println("Thanks for playing!");
            }
        } while(yes);
    }
}
