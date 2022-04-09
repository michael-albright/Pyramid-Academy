import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FunctionalHangman {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! Ready to play Hangman?");
        System.out.println("Choose 'e' for an easy round or 'h' for a hard round!");
        gameSetUp(scan);
        playGame(scan);
        System.out.println("Play again? (y or n)");
            if(playRound(scan)) {
                main(null);
            }
    }

    //////////****************Methods*****************//////////

    public static void gameSetUp(Scanner scan) throws IOException {
        String input = String.valueOf(scan.next().toLowerCase().charAt(0));
        String gameWord = getRandomWord(input);
        System.out.println("What is your name?");
        storePlayersName(scan, input);
        System.out.println("High score is based on speed, amount of letters guessed \nwhen solved, and " +
                "amount of lives remaining. \nGood Luck!\n");
        System.out.println("Current High Score: " + outputHighScore());
        System.out.println("Press any key to get started!");
        scan.next();
        checkWordForRepeat(gameWord);
            //Create lists to hold random word, correct letters, and incorrect letters
        createCorrectList(gameWord);
        createIncorrectList();
        createMagicWordList(gameWord);
    }

    public static void playGame(Scanner scan) throws IOException {
        //counter
        int deathCount = 0;
        long startTime;
        long endTime;
            // Start game play
        while(true) {
                //Create & output art
            startTime = System.currentTimeMillis();
            createGameBoard();
            System.out.println(setGameBoard(deathCount, createGameBoard()));
                // Output Correct & Incorrect Letters
            System.out.println(outputCorrectLetters());
            System.out.println(outputIncorrectLetters());
                //Take in guess, check it's valid, add guess to necessary array
            System.out.println("Guess a Letter or Guess the Word");
            String guess = scan.next().toLowerCase();
            if(guess.length() == 1) {
                if (checkGuess(guess, deathCount)) deathCount++;
            } else {
                if(checkAWordGuess(guess, deathCount)) deathCount++;
            }
                //Check for win or loss, add time to score sheet
            if(checkForWin()) {
                endTime = System.currentTimeMillis();
                getUsersTime(startTime, endTime);
                System.out.println(outputCorrectLetters());
                System.out.println("Congratulations! You won this round.");
                System.out.println(outputIfTopScore());
                System.out.println("Current High Score: " + outputHighScore());
                break;
            }
                //Play again or close and clear usedWords.txt
            if(checkForLoss(deathCount)) {
                System.out.println(setGameBoard(deathCount, createGameBoard()));
                System.out.println("You played a good life, but not everyone gets out of here alive.");
                break;
            }
        }
    }

    public static boolean checkAWordGuess(String s, int i) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        List<String> gameStats = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"));
        List<String> correct = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"));
        List<String> magicWord = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/magicWord"));
        List<String> wordGuess = List.of(s.split(""));
        //char[] tempArr = s.toCharArray();
        List<String> tempList = new ArrayList<>();
        IntStream.range(0, s.length())
                .forEach(a -> {
                    if((int) s.charAt(a) >= 97 && (int) s.charAt(a) <= 122) {
                        tempList.add(String.valueOf(s.charAt(a)));
                    }
                });
        if(!tempList.equals(wordGuess)) return false;
        int x;
        if(!wordGuess.equals(magicWord)) {
            return true;
        } else {
           x = (int) correct.stream()
                    .filter(a -> !a.equals(" - "))
                    .count();
        }
        gameStats.add(String.valueOf(x * 5));
        gameStats.add(String.valueOf(i * 5));
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"), gameStats, ascii);
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"), magicWord, ascii);
        return false;
    }

    public static boolean checkGuess(String s, int i) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        int checkForLetter = s.charAt(0);
        if(checkForLetter >= 97 && checkForLetter <= 122) {
            List<String> tempList = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"));
            List<String> correct = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"));
            List<String> incorrect = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/incorrectLetters"));
            List<String> magicWord = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/magicWord"));
            if (correct.contains(s) || incorrect.contains(s)) {
                return true;
            } else if (magicWord.contains(s)) {
                IntStream.range(0, magicWord.size())
                        .forEach(a -> {
                            if(magicWord.get(a).equals(s)) {
                                correct.set(a, s);
                            }
                        });
                Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"), correct, ascii);
                if(correct.equals(magicWord)) {
                    if(tempList.get(1).equals("e")) {
                        List<String> gameStats = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/easyHighScores"));
                        gameStats.add(String.valueOf(magicWord.size() * 5));
                        gameStats.add(String.valueOf(i * 5));
                        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"), gameStats, ascii);
                    }
                }
                return false;
            } else {
                incorrect.add(s);
                Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/incorrectLetters"), incorrect, ascii);
                return true;
            }
        } else return false;
    }

    public static void getUsersTime(long start, long end) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        List<String> tempList = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"));
        int timeInSecs = (int) ((end - start) / 1000);
        tempList.add(String.valueOf(timeInSecs));
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"), tempList, ascii);
    }

    public static void storePlayersName(Scanner scan, String gameType) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        String name = scan.next();
        List<String> tempList = new ArrayList<>();
        if (!gameType.equals("h")) gameType = "e";
        tempList.add(name);
        tempList.add(gameType);
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"), tempList, ascii);
    }

    public static String outputHighScore() throws IOException {

        List<String> tempList = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/tempNameAndScore"));
        List<String> easyFinalScores = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/easyHighScores"));
        List<String> hardFinalScores =  Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/hardHighScores"));

        if(tempList.get(1).equals("e") && easyFinalScores.size() > 0) {
            return easyFinalScores.get(0) + " - " + easyFinalScores.get(1);
        } else if(hardFinalScores.size() > 0) {
            return hardFinalScores.get(0) + " - " + hardFinalScores.get(1);
        }
        return "0";
    }

    public static boolean getScoreResults(List<String> userDetails, List<String> scores, List<String> finalInput, String userName, int userScore) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        ArrayList<Integer> compareScores = new ArrayList<>();
        if (scores.size() > 0) {
            IntStream.range(0, scores.size())
                    .forEach(a -> {
                        compareScores.add(Integer.valueOf(scores.get(a)));
                    });
            Collections.sort(compareScores);
            IntStream.range(0, compareScores.size())
                    .forEach(a -> {
                        if (userScore > compareScores.get(a)) {
                            scores.add(a, String.valueOf(userScore));
                            finalInput.add(a, userName);
                            finalInput.add(a + 1, String.valueOf(userScore));
                        }
                    });
            } else {
            finalInput.add(userName);
            finalInput.add(String.valueOf(userScore));
            scores.add(String.valueOf(userScore));
            }
            if(userDetails.get(1).equals("e")) {
                Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/easyScores"), scores, ascii);
                Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/easyHighScores"), finalInput, ascii);
            } else {
                Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/hardScores"), scores, ascii);
                Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/hardHighScores"), finalInput, ascii);
            }
        return scores.get(0).equals(String.valueOf(userScore));
        }

    public static String outputIfTopScore() throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        List<String> tempList = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/functionalHangman/resources/tempNameAndScore"));

        String name = tempList.get(0);
        int score = Integer.parseInt(tempList.get(2)) + Integer.parseInt(tempList.get(3)) + Integer.parseInt(tempList.get(4));

        List<String> easyFinalScores = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/easyHighScores"));
        List<String> easyScores = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/easyScores"));
        List<String> highScores = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/hardScores"));
        List<String> hardFinalScores = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/hardHighScores"));

        if (tempList.get(1).equals("e") && getScoreResults(tempList, easyScores, easyFinalScores, name, score)) {
            return "Congratulations!! You are the high scorer!";
        }
        else if(getScoreResults(tempList, highScores, hardFinalScores, name, score)) {
            return "Congratulations!! You are the high scorer!";
        } else {
            return "Great game, but you did not have enough to beat the high score.";
        }
    }

    public static boolean checkForLoss(int deathCount) {
        return deathCount == 7;
    }

    public static boolean checkForWin() throws IOException {
        List<String> correct = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"));
        List<String> magicWord = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/magicWord"));
        return (correct.equals(magicWord) && correct.size() > 0);

    }

    public static void createCorrectList(String s) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        List<String> correct = new ArrayList<>();
        Stream.of(s.split(""))
                .forEach(a -> correct.add(" _ "));
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"), correct, ascii);
    }

    public static void createIncorrectList() throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        ArrayList<String> incorrect = new ArrayList<>();
        incorrect.add("Missed letters: ");
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/incorrectLetters"), incorrect, ascii);
    }

    public static void createMagicWordList(String s) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        ArrayList<String> magicWord = new ArrayList<>(Arrays.asList(s.split("")));
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/magicWord"), magicWord, ascii);
    }

    public static boolean checkWordForRepeat(String s) throws IOException {
        //Scanner scan = new Scanner(System.in);
        Charset ascii = StandardCharsets.US_ASCII;
        List<String> noDoubles = List.of(s);
        List<String> words = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/usedWords.txt"));
        if (!words.contains(s)) {
            Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/usedWords.txt"), noDoubles, ascii,
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            return true;
        }
        return false;
        }

    public static boolean playRound(Scanner scan) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        String play = scan.next();
        if(play.trim().equalsIgnoreCase("y")) {
            return true;
        } else {
            List<String> words = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/usedWords.txt"));
            words.clear();
            Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/usedWords.txt"), words, ascii);
            return false;
        }
    }

    public static String outputCorrectLetters() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> correctLetters = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/correctLetters"));
        correctLetters.stream()
                .forEach(a -> {
                    sb.append(a);
                    sb.append(" ");
                });
        return String.valueOf(sb);
    }

    public static String outputIncorrectLetters() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> incorrectLetters = Files.readAllLines(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/incorrectLetters"));
        incorrectLetters.stream()
                .forEach(a -> {
                    sb.append(a);
                    sb.append(" ");
                });
        return String.valueOf(sb);
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

    public static String setGameBoard(int deathCount, ArrayList<String> gameBoard) throws IOException {
        Charset ascii = StandardCharsets.US_ASCII;
        StringBuilder sb = new StringBuilder();

        String top2 = "  |     |";
        String head = "  O     |";
        String head2 = "  x     |";
        String body = "  |     |";
        String legL = " /      |";
        String legs = " / \\    |";
        String armL = " /|     |";
        String arms = " /|\\    |";
        String bottom3 = " / \\    |____";


        if (deathCount >= 1 && deathCount < 7) gameBoard.set(3, head);
        if (deathCount >= 2 && deathCount < 7) gameBoard.set(4, body);
        if (deathCount >= 3 && deathCount < 7) gameBoard.set(5, legL);
        if (deathCount >= 4 && deathCount < 7) gameBoard.set(5, legs);
        if (deathCount >= 5 && deathCount < 7) gameBoard.set(4, armL);
        if (deathCount == 6) gameBoard.set(4, arms);
        if (deathCount == 7) {
            gameBoard.set(3, top2);
            gameBoard.set(4, top2);
            gameBoard.set(5, head2);
            gameBoard.set(6, arms);
            gameBoard.set(7, bottom3);
        }
        Files.write(Paths.get("/Users/michaelsalbright/Documents/GenSpark-Programs/Pyramid-Academy/functionalHangman/resources/gameBoard"), gameBoard, ascii);

        gameBoard.stream()
                .forEach(a -> {
                    sb.append(a);
                    sb.append("\n");
                });
        return String.valueOf(sb);
    }

    public static String getRandomWord(String s) throws IOException {
        String choice = s;
        String word;
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


        if (choice.equals("h")) {
            word = hardWordList[num];
        } else word = easyWordList[num];

        if(checkWordForRepeat(word)) {
            return word;
        } else {
            getRandomWord(choice);
        }
        return word;
    }
}

