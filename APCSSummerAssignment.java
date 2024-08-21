/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apcs.summer.assignment;

/**
 * ULTIMATE WORD GAME 2D
 * @author Cheech Li
 * @Beta 1.0.0 6/29/2024
 * @Release 1.0.0 8/20/2024
 */
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.Date;
public class APCSSummerAssignment {
    
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GRAY = "\u001B[37m";
    public static Map<Character, String> coloredText = new HashMap<>(); // Hashtable for coloring keyboard
    
    public static void main(String[] args) throws IOException{
        int streak = 0;
        double wins = 0, total = 0;
        Scanner scanner = new Scanner(System.in);
        Vector wordVec = new Vector<String>(); // Creates word vector
        makeWordVector(wordVec); // Creates wordlist from fucntion
        char playAgain = 'Y';
        System.out.println("ULTIMATE WORD GAME 2D"); // Mobile game inspired title
        System.out.println("Keep on playing until you give up!");
        do{
            mainMenu();
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    // Variables
                    String word;
                    int attempts = -1;
                    word = newWord(wordVec); // Sets word
                    int size = 6; // Sets array size;
                    String[][] board = new String[6][5]; // Makes game board
                    String keyboard = RESET + "\nQWERTYUIOP\nASDFGHJKL\nZXCVBNM" + RESET;
                    coloredText.clear(); // Clears keyboard
                    // Functions
                    fill(board);
                    displayBoard(board);
                    for (int i = 0; i < size; i++){ // Loops through board
                        String input = "HELLO";
                        while (true){ // Checks if word is a valid 5 letter word
                            input = scanner.nextLine();
                            input = input.toUpperCase();
                            attempts++;
                            if (validateWord(input, wordVec)) // Breaks if valid word
                                break;
                            else if (input.toLowerCase().equals("give up")){ // Checks if player gives up
                                System.out.println("Better luck next time! The word was: " + word); // Unique message
                                streak = 0;
                                total++;
                                stats(attempts - 1, i, word, streak, true, wins, total); // Output player stats
                                return;
                            }
                            else if (validateWord(input, wordVec) == false && attempts > 0 && input.length() > 5) // Error trap
                                System.out.println("Too many letters!");
                            else if (validateWord(input, wordVec) == false && attempts > 0 && input.length() < 5) // Error trap
                                System.out.println("Not enough letters!");
                            else if (validateWord(input, wordVec) == false && attempts > 0) // Error trap
                                System.out.println("Not in word list!");
                        }
                        checkWord(input, word, board, i); // Checks for similarity
                        System.out.println();
                        displayBoard(board); //Displays board
                        keyboard = updateKeyboard(input, word, keyboard); // Updates player keyboard
                        System.out.println(keyboard);
                        System.out.println();
                        if (input.equals(word)){ // Checks if input is equal to the word
                            checkWord(input, word, board, i);
                            System.out.println("CONGRATULATIONS, YOU GUESSED THE WORD IN " + (attempts) + " ATTEMPTS"); // Unique message
                            streak++; // Add a point to winstreak
                            wins++;
                            total++;
                            stats(attempts, i + 1, word, streak, false, wins, total); // Output player stats
                            break;
                        }
                        if (i == 5){
                            System.out.println("Better luck next time! The word was: " + word); // Unique message
                            streak = 0; // Resets streak
                            total++;
                            stats(attempts, i + 1, word, streak, false, wins, total); // Output player stats
                            break;
                        }
                    }   System.out.println("Play Again? Y | N"); // Play again
                    String play = scanner.nextLine();
                    if (play.toUpperCase().equals("Y"))
                        playAgain = 'Y';
                    else
                        playAgain = 'N';
                    break;
                case 2:
                    File wordFileObj = new File("history.txt"); // Reads history file
                    Scanner input = new Scanner(wordFileObj);
                    while (input.hasNext())
                        System.out.println(input.nextLine()); // Outputs player history
                    break;
                default:
                    System.out.println("Thank you for playing! See you next time!");
                    System.out.println("A Yingqi 'Cheech' Li project");
                    System.exit(0);
            }
        } while (playAgain == 'Y');
    }
    
    public static void mainMenu(){ // Menu
        System.out.println("1. Play");
        System.out.println("2. History");
        System.out.println("3. Exit");
    }
    
    public static void makeWordVector(Vector<String> wordVector) throws IOException{ // Transfers all words from txt to a vector
        File wordFileObj = new File("words.txt");
        Scanner input = new Scanner(wordFileObj);
        while (input.hasNext()){
            String word = input.nextLine();
            wordVector.add(word); // Adds element
        }
    }
    
    public static String newWord(Vector<String> wordVector){ // Randomly generates a new word
        Random rand = new Random();
        String randomWord;
        int randomWordIndex = rand.nextInt(wordVector.size()); // Random number from words
        randomWord = wordVector.get(randomWordIndex); // Assigns random word
        return randomWord;
    }
    
    public static void fill(String[][] array){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 5; j++){
                array[i][j] = "-"; // Fills array with -
            }
        }
    }
    
    public static void displayBoard(String[][] array){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(array[i][j]); //Outputs array elements
            }
            System.out.println();
        }
    }
    
    public static String updateKeyboard(String input, String word, String keyboard){
        Map<Character, Integer> actualWordCharCounts = new HashMap<>(); //Creates a hashmap to count the characters and their frequency
        for (char c : word.toCharArray()) // Loops through every letter of the word
            actualWordCharCounts.put(c, actualWordCharCounts.getOrDefault(c, 0) + 1); // Checks if char is already in the hashmap, if not, incriment the count for it by 1, else continue
        
        for (int i = 0; i < input.length(); i++) { // Loops every character in input
            char ch = input.charAt(i);
            if (word.charAt(i) == ch) { // Checks if character from input is in the same index in word
                coloredText.put(ch, GREEN); // Makes the character green and inserts it into the map
                actualWordCharCounts.put(ch, actualWordCharCounts.get(ch) - 1); // Subtracts the count of that character by 1
            }
        }

        for (int i = 0; i < input.length(); i++) { // Loops every character in input
            char ch = input.charAt(i);
            if (word.charAt(i) != ch && word.indexOf(ch) != -1 && actualWordCharCounts.getOrDefault(ch, 0) > 0 && (!coloredText.containsKey(ch) || !coloredText.get(ch).equals(GREEN))) { // Checks if the input character is in the word but not in the correct index
                coloredText.put(ch, YELLOW); // Makes the character yellow and inserts it into the map
                actualWordCharCounts.put(ch, actualWordCharCounts.get(ch) - 1); // Subtracts the count of that character by 1
            }
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (!coloredText.containsKey(ch)) // Checks if character from input is not in the word
                coloredText.put(ch, GRAY); // Makes the character gray and inserts it into the map
        }
        return colorCharsInString(keyboard); // Calls the function to color in the keyboard
    }
    
    public static String colorCharsInString(String word){
        StringBuilder updateKey = new StringBuilder(); // Creates a stringbuilder to append strings
        for (char ch: word.toCharArray()){
            if (coloredText.containsKey(ch))
                updateKey.append(coloredText.get(ch)).append(ch).append(RESET); // Appends character in color
            else
                updateKey.append(ch); // Appends character
        }
        return updateKey.toString(); // Converts to string
    }
    
    public static boolean validateWord(String input, Vector<String> words){
        for (String word : words){
            if (input.toUpperCase().equals(word.toUpperCase())) // Checks if the word is a valid word in wordlist
                return true;
        }
        return false;
    }
    
    public static void stats(int attempts, int correct, String word, int streak, Boolean gaveUp, double wins, double total) throws IOException{
        Date date = new java.util.Date();
        System.out.println("Total Attempts: " + attempts);
        System.out.println("Accepted Words: " + correct);
        System.out.println("Invalid Words: " + (attempts - correct));
        System.out.println("Winrate: " + (wins/total) * 100 + "%");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + (total - wins));
        System.out.println("Total Games: " + total);
        System.out.println("Streak: " + streak);
        try (FileWriter writer = new FileWriter("history.txt", true)){ // Writes messages to history file
            writer.write("\n" + date + "\n");
            writer.write("Word: " + word + "\n");
            writer.write("Total Attempts: " + attempts + "\n");
            writer.write("Accepted Words: " + correct + "\n");
            writer.write("Invalid Words: " + (attempts - correct + "\n"));
            writer.write("Winrate: " + (wins/total) * 100 + "%\n");
            writer.write("Wins: " + wins + "%\n");
            writer.write("Losses: " + (total - wins) + "%\n");
            writer.write("Total Games: " + total + "%\n");
            writer.write("Streak: " + streak + "\n");
            if (gaveUp) // Checks if player gave up
                writer.write("GAVE UP" + "\n");
            writer.close();
        }
    }
    
    public static void checkWord(String input, String word, String[][] array, int position){ // Processes the input word
        String color;
        Vector<Character> letters = new Vector<>();
        //This part took me forever LOOOOOOOOOOL 7/28/2024
        for (int i = 0; i < word.length(); i++) // Loops word to add each letter to letters vector
            letters.add(word.charAt(i));
        //Filter the letters by removing duplicates.
        Set<Character> set = new LinkedHashSet<>(letters); // Removes duplicate letters by inserting to a set
        letters.clear(); // Clears letters
        letters.addAll(set); // Appends set elements to letters
        for (int i = 0; i < input.length(); i++){ // Loops input characters
            if (input.charAt(i) == word.charAt(i)){ // If input index is equal to word index
                color = GREEN + input.charAt(i) + GRAY; // Character is green
                array[position][i] = color; // Change to character
            }
            else{
                boolean found = false;
                for (int j = 0; j < letters.size(); j++){
                    if (input.charAt(i) == letters.get(j)){ // If input character is found within the word
                        found = true;
                        break;
                    }
                }
                if (found){
                    color = YELLOW + input.charAt(i) + GRAY; // Character is yellow
                    array[position][i] = color; // Change to character
                }
                else{
                    color = GRAY + input.charAt(i); // Character is gray
                    array[position][i] = color; // Change to character
                }
            }
        }
    }
}