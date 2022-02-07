import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        boolean playGame = true;
        String word = "";
        String guess;
        Scanner scan = new Scanner(System.in);
          //Create array to ensure same word does not repeat
        ArrayList<String> noDoubles = new ArrayList<>();
        System.out.println("Hello! Ready to play Hangman?");
          //START OF LOOP FOR GAME SET-UP
        while (playGame) {
            boolean repeatWord = true;
            int deathCount = 0;
            System.out.println("Choose 'e' for an easy round or 'h' for a hard round!");
            String choice = scan.next().toLowerCase();
              //Make sure word does not repeat
            while(repeatWord) {
                word = getRandomWord(choice);
                if(!noDoubles.contains(word)) {
                    noDoubles.add(word);
                    repeatWord = false;
                }
            }
              //Create lists to hold random word, correct letters, and incorrect letters
            ArrayList<String> correct = new ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                correct.add("_");
            }
            ArrayList<String> incorrect = new ArrayList<>();
            incorrect.add("Missed letters: ");
            ArrayList<String> magicWord = new ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                magicWord.add(String.valueOf(word.charAt(i)));
            }
            //create game board
            ArrayList<String> gameBoard = new ArrayList<String>(createGameBoard());
            //START OF LOOP FOR GAMEPLAY
            while (deathCount < 7) {
                boolean validLetter = false;
                System.out.println(setGameBoard(deathCount, gameBoard));
                System.out.println(outputCorrectLetters(correct));
                System.out.println(outputIncorrectLetters(incorrect));
                //check for valid guess
                do {
                    System.out.println("Guess a letter.");
                    guess = scan.next().toLowerCase();
                    validLetter = checkForValidGuess(guess);
                    if (!validLetter) System.out.println("Invalid entry.");
                } while (!validLetter);
                //add character to necessary array
                if (correct.contains(guess) || incorrect.contains(guess)) {
                    System.out.println("What a waste of a guess!");
                    deathCount++;
                } else if (magicWord.contains(guess)) {
                    for (int i = 0; i < magicWord.size(); i++) {
                        if (magicWord.get(i).equals(guess)) {
                            correct.set(i, guess);
                        }
                    }
                } else {
                    incorrect.add(guess);
                    deathCount++;
                }
                //check to see if player has won
                if (correct.equals(magicWord)) {
                    System.out.println(outputCorrectLetters(correct) + "!");
                    System.out.println("Congratulations, you won the game!");
                    deathCount = 8;
                }
                //output when player loses
                if (deathCount == 7) {
                    System.out.println(setGameBoard(7, gameBoard));
                    System.out.println("You played a good life, but not everyone gets out of the gallows alive.");
                }
            }
            //OUTSIDE OF GAME LOOP
            System.out.println("Press 'y' if you would like to play again.");
            String play = scan.next();
            if (!play.equalsIgnoreCase("y") && !play.equalsIgnoreCase("yes")) {
                System.out.println("It was fun playing with you, come back and play again soon!");
                playGame = false;
            }
        }
    }

    //Methods
    public static boolean checkForValidGuess(String s) {
        int checkForLetter = s.charAt(0);
        boolean n;
        n = checkForLetter >= 97 && checkForLetter <= 122 && s.length() <= 1;
        return n;
    }

    public static String outputCorrectLetters(ArrayList<String> list1) {
        StringBuilder str = new StringBuilder();
        for (String s : list1) {
            str.append(s);
            str.append(" ");
        }
        return String.valueOf(str);
    }

    public static String outputIncorrectLetters(ArrayList<String> list1) {
        StringBuilder str = new StringBuilder();
        for (String s : list1) {
            str.append(s);
            str.append(" ");
        }
        return String.valueOf(str);
    }

    public static ArrayList<String> createGameBoard() {
        String top = "  +-----+";
        String top2 = "  |     |";
        String mid = "        |";
        String bottom1 = "        | /";
        String bottom2 = "        |/";
        String base = "    =========";

        ArrayList<String> gameBoard = new ArrayList<>();
        gameBoard.add("  HANGMAN");
        gameBoard.add(top);
        gameBoard.add(top2);
        gameBoard.add(mid);
        gameBoard.add(mid);
        gameBoard.add(mid);
        gameBoard.add(bottom1);
        gameBoard.add(bottom2);
        gameBoard.add(base);

        return gameBoard;
    }

    public static String setGameBoard(int deathCount, ArrayList<String> arr) {
        String top2 = "  |     |";
        String head = "  O     |";
        String head2 = "  x     |";
        String body = "  |     |";
        String legL = " /      |";
        String legs = " / \\    |";
        String armL = " /|     |";
        String arms = " /|\\    |";
        String bottom3 = " / \\    |____";

        if (deathCount == 1) arr.set(3, head);
        if (deathCount == 2) arr.set(4, body);
        if (deathCount == 3) arr.set(5, legL);
        if (deathCount == 4) arr.set(5, legs);
        if (deathCount == 5) arr.set(4, armL);
        if (deathCount == 6) arr.set(4, arms);
        if (deathCount == 7) {
            arr.set(3, top2);
            arr.set(4, top2);
            arr.set(5, head2);
            arr.set(6, arms);
            arr.set(7, bottom3);
        }
        StringBuilder output = new StringBuilder();
        for (String i : arr) {
            output.append(i);
            output.append("\n");
        }
        return String.valueOf(output);
    }

    public static String getRandomWord(String choice) {
        String gameWord;
        Random randomNumber = new Random();
        int num = randomNumber.nextInt(100);
        String[] hardWordList = {"abruptly", "awkward", "axiom", "azure", "bagpipes", "bikini", "buzzard",
                "buzzing", "buzzwords", "cobweb", "cycle", "daiquiri", "duplex", "dwarves", "embezzle", "equip",
                "espionage", "exodus", "faking", "fishhook", "fuchsia", "funny", "gabby", "galaxy", "glyph",
                "gnarly", "gnostic", "gossip", "grogginess", "haphazard", "hyphen", "icebox", "injury", "ivory",
                "ivy", "jaundice", "jawbreaker", "jaywalk", "jazziest", "jogging", "jukebox", "jumbo", "kayak",
                "kazoo", "kilobyte", "kiosk", "knapsack", "larynx", "luxury", "lymph", "marquis", "matrix",
                "mnemonic", "mystify", "naphtha", "nightclub", "nowadays", "numbskull", "nymph", "ovary",
                "oxidize", "oxygen", "pajama", "psyche", "puppy", "quartz", "queue", "quiz", "quizzes",
                "rhubarb", "rhythm", "snazzy", "sphinx", "staff", "subway", "swivel", "syndrome", "thriftless",
                "transplant", "twelfth", "unknown", "unworthy", "unzip", "uptown", "vaporize", "voodoo",
                "vortex", "voyeurism", "walkway", "wimpy", "witchcraft", "wizard", "woozy", "wristwatch",
                "xylophone", "yachtsman", "youthful", "yummy", "zephyr", "zigzagging"};

        String[] easyWordList = {"disk", "menu", "song", "soup", "bath", "bird", "tale", "dirt", "mode", "gate",
                "hall", "road", "loss", "gene", "lake", "hair", "poet", "meat", "wood", "area", "week", "idea",
                "exam", "year", "role", "unit", "love", "king", "cell", "goal", "oven", "user", "news", "food",
                "wife", "city", "poem", "math", "meal", "beer", "fact", "girl", "debt", "desk", "army", "town",
                "data", "mall", "mood", "lady", "media", "chest", "event", "virus", "buyer", "ratio", "month",
                "thing", "guest", "piano", "apple", "blood", "scene", "river", "paper", "child", "union", "world",
                "owner", "pizza", "video", "death", "truth", "salad", "error", "photo", "tooth", "depth", "honey",
                "phone", "topic", "youth", "steak", "skill", "night", "actor", "heart", "movie", "queen", "story",
                "drama", "woman", "basis", "bread", "bonus", "power", "uncle", "music", "shirt", "hotel"};
        if (choice.equals("h") || choice.equals("hard")) {
            gameWord = hardWordList[num];
        } else gameWord = easyWordList[num];
        return gameWord;
        }
    }


  /*      if (choice.equals("h") || choice.equals("hard")) {
            gameWord = hardWordList[num];
            return gameWord;
        } else if (choice.equals("e") || choice.equals("easy")) {
            gameWord = easyWordList[num];
            return gameWord;
        } else {
            System.out.println("Invalid entry, but I'll start you off on easy.");
            gameWord = easyWordList[num];
            return gameWord;
        }
    }

*/

/*
    public static String buildGameBoard() {
        String top = "     +-----+";
        String top2 = "     |     |";
        String mid = "           |";
        String bottom1 = "           | /";
        String bottom2 = "           |/";
        String base = "       =========";
        String gameBoard = top + "\n" + top2 + "\n" + mid + "\n" + mid + "\n" +
                mid + "\n" + bottom1 + "\n" + bottom2 + "\n" + base + "\n";

        return gameBoard;
    }

    public static String getRandomWord(String choice) {
        String gameWord = "";
        Random randomNumber = new Random();
        int num = randomNumber.nextInt(101);
        String[] hardWordList = { "argument", "database", "republic", "painting", "category", "politics", "guidance",
                "director", "addition", "reaction", "teaching", "feedback", "judgment", "presence", "medicine",
                "magazine", "audience", "homework", "birthday", "language", "analysis", "response", "midnight",
                "decision", "contract,", "signature", "statement", "childhood", "committee", "marketing", "knowledge",
                "reception", "situation", "direction", "newspaper", "operation", "economics", "secretary", "president",
                "historian", "promotion", "chemistry", "tradition", "professor", "apartment", "attention", "criticism",
                "inspector", "confusion", "recording", "percentage", "suggestion", "conclusion", "department",
                "television", "psychology", "foundation", "technology", "investment", "difficulty", "indication",
                "girlfriend", "discussion", "expression", "atmosphere", "population", "profession", "assignment",
                "employment", "university", "inspection", "government", "friendship", "comparison", "possession",
                "possibility", "consequence", "maintenance", "development", "requirement", "environment", "information",
                "explanation", "appointment", "application", "competition", "examination", "supermarket", "grandmother",
                "improvement", "engineering", "imagination", "interaction", "association", "measurement", "performance",
                "personality", "opportunity", "combination", "negotiation"};

        String[] easyWordList = {"disk", "menu", "song", "soup", "bath", "bird", "tale", "dirt", "mode", "gate",
                "hall", "road", "loss", "gene", "lake", "hair", "poet", "meat", "wood", "area", "week", "idea",
                "exam", "year", "role", "unit", "love", "king", "cell", "goal", "oven", "user", "news", "food",
                "wife", "city", "poem", "math", "meal", "beer", "fact", "girl", "debt", "desk", "army", "town",
                "data", "mall", "mood", "lady", "media", "chest", "event", "virus", "buyer", "ratio", "month",
                "thing", "guest", "piano", "apple", "blood", "scene", "river", "paper", "child", "union", "world",
                "owner", "pizza", "video", "death", "truth", "salad", "error", "photo", "tooth", "depth", "honey",
                "phone", "topic", "youth", "steak", "skill", "night", "actor", "heart", "movie", "queen", "story",
                "drama", "woman", "basis", "bread", "bonus", "power", "uncle", "music", "shirt", "hotel"};

        if(choice.equals("h") || choice.equals("hard")) {
            gameWord = hardWordList[num];
            return gameWord;
        } else if(choice.equals("e") || choice.equals("easy")) {
            gameWord = easyWordList[num];
            return gameWord;
        } else {
            System.out.println("Invalid entry, but I'll start you off on easy.");
            gameWord = easyWordList[num];
            return gameWord;
        }
    }
}

    public static String missedLetters(String guess) {
        ArrayList<String> missed = new ArrayList<>();
        missed.add("Missed Letters:");
        String output = "";
        for(String i : missed) {
            output += i + " ";
        }
        return output;
    }

    public static String correctLetters(String word, String guess) {
        String correctLetters = "";
        ArrayList<Character> dashArr = new ArrayList<>();
        ArrayList<Character> wordArr = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            wordArr.add(word.charAt(i));
        }

            if(wordArr.contains(guess.charAt(0))) {
                for (int i = 0; i < word.length(); i++) {

                }
            }

    }


    public static void checkGuess(String word, String guess) {
        ArrayList<Character> wordChoice = new ArrayList<>();
        for(int i=0; i<word.length(); i++) {
            wordChoice.add(word.charAt(i));
        }
        if(wordChoice.contains(guess.charAt(0))) {
            correctLetters(guess);
        } else {
            missedLetters(guess);
        }
    }


      for (String s : correct) {
                    System.out.print(s + " ");
                }
                //spacing line
                System.out.println();
                //output Missed Letters
                for (String s : incorrect) {
                    System.out.print(s + " ");
                }
                //spacing line
                System.out.println();








        //Create and output magicWord to solve
        ArrayList<String> dashAnswer = new ArrayList<>();
        ArrayList<String> realAnswer = new ArrayList<>();
        String blankSpaces = "Your word: ";
        //showAnswer.add("Your word: ");
        for(int i=0; i<magicWord.length(); i++) {
            dashAnswer.add(String.valueOf(magicWord.charAt(i)));
        }
        for(String i : showAnswer) {
            blankSpaces += "_ ";
        }
        System.out.println(blankSpaces);

        //Create and output Missed Letters
        ArrayList<String> missed = new ArrayList<>();
        missed.add("Missed Letters:");
        String output = "";
        for(String i : missed) {
            output += i + " ";
        }
        System.out.println(output);

        //Start gameplay
        System.out.println("Guess a letter.");
        String guess = scan.next().toLowerCase();


             String one = "  HANGMAN\n" + top + "\n" + top2 + "\n" + head + "\n" + mid + "\n" +
                mid + "\n" + bottom1 + "\n" + bottom2 + "\n" + base;
        String two = "  HANGMAN\n" + top + "\n" + top2 + "\n" + head + "\n" + body + "\n" +
                mid + "\n" + bottom1 + "\n" + bottom2 + "\n" + base;
        String three = "  HANGMAN\n" + top + "\n" + top2 + "\n" + head + "\n" + body + "\n" +
                legL + "\n" + bottom1 + "\n" + bottom2 + "\n" + base;
        String four = "  HANGMAN\n" + top + "\n" + top2 + "\n" + head + "\n" + body + "\n" +
                legs + "\n" + bottom1 + "\n" + bottom2 + "\n" + base;
        String five = "  HANGMAN\n" + top + "\n" + top2 + "\n" + head + "\n" + armL + "\n" +
                legs + "\n" + bottom1 + "\n" + bottom2 + "\n" + base;
        String six = "  HANGMAN\n" + top + "\n" + top2 + "\n" + head + "\n" + arms + "\n" +
                legs + "\n" + bottom1 + "\n" + bottom2 + "\n" + base;
        String seven = "  HANGMAN\n" + top + "\n" + top2 + "\n" + top2 + "\n" + top2 + "\n" +
                head2 + "\n" + arms + "\n" + bottom3 + "\n" + base;

 */



