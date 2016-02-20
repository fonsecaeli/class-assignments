//Eli F.
//Section B
//Assignment #5
//Description: program allows the user to play an interactive number guessing game
//Version 1.0
//Class Name: GuessingGame
//10/29/2015

import java.util.*;

public class GuessingGame {
   
   /**
   * the upper bound of the random number the user will guess
   */
   public static final int MAX_NUMBER = 100;
   
   /**
   * the lower bound of the random number the user will guess 
   */
   public static final int MIN_NUMBER = 1;
   
   /**
   * lets the user play where they try to guess a random number given hints
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {
      Random random = new Random();
      Scanner input = new Scanner(System.in);
      haiku();
      //madLibHaiku.getHaiku(input);
      //vars to keep track of stats
      int best = 1000000;  
      int totalGames = 0;
      int totalGuesses = 0; 
      do {
         int target = random.nextInt(MAX_NUMBER)+1;
         int guesses = oneGame(input, target);
         //binarySearch(target, guesses); 
         totalGames++;
         totalGuesses += guesses; 
         if(guesses < best) {
            best = guesses;
         }
      }
      while(again(input));
      stats(totalGames, totalGuesses, best);
   }
   
   /**
   * prints a haiku to explain the program
   */
   public static void haiku() {
      System.out.println("This program lets you");
      System.out.println("Play a random guessing game ");
      System.out.println("It is really fun!");
   }
   
   
   /**
   * plays one time through the guessing game
   *
   * @param input Scanner object used to get input from the user
   * @param target the number the user is trying to guess
   * @return the number of guesses the used guessed in the game
   */
   public static int oneGame(Scanner input, int target) {
      int numberOfGuesses = 1; //always takes at least 1 guess
      System.out.println();
      //System.out.println("Correct number is " + target);
      System.out.println("I'm thinking of a number between 1 and " + MAX_NUMBER + "...");
      int guess = Inputter.getNumber(input, "Your guess? ", MIN_NUMBER, MAX_NUMBER);
      while(guess != target) {
         System.out.print("It's ");
         if(guess > target) {
            System.out.println("lower.");
         }
         else {
            System.out.println("higher.");
         }
         guess = Inputter.getNumber(input, "Your guess? ", MIN_NUMBER, MAX_NUMBER);
         numberOfGuesses++;
      }
      System.out.print("You got it right in ");
      if(guess == target && numberOfGuesses == 1) {
         System.out.println("1 guess!");
      }
      else {
         System.out.println(numberOfGuesses + " guesses!");
      }
      return numberOfGuesses;
   }
   
   /**
   * displays the statistics of the game session
   *
   * @param totalGames total number of games the user played
   * @param totalGuesses total number of guessses the user had
   * @param best the number of guesses the user had in the best game
   */
   public static void stats(int totalGames, int totalGuesses, int best) {
      //rounds the ratio of guess/game to a double with one decimale place
      double ratio = Math.round((10*((double)(totalGuesses)/totalGames)))/10.0;
      System.out.println();
      System.out.println("Overall results:");
      System.out.printf("%-14s", "Total games");
      System.out.println("= " + totalGames);
      System.out.printf("%-14s", "Total guesses");
      System.out.println("= " + totalGuesses);
      System.out.printf("%-14s", "Guesses/game");
      System.out.println("= " + ratio);
      System.out.printf("%-14s", "Best game");
      System.out.println("= " + best);
   }
      
   /**
   * tests to see if the user would like to play the game again
   *
   * @param input Scanner object used to ask user if they would like to play again
   * @return boolean value that indicates whether or not the user wants to play again
   */
   public static boolean again(Scanner input) {
      System.out.print("Do you want to play again? ");
      String answer = input.next();
      return (answer.startsWith("y") || answer.startsWith("Y"));
   }
   
   /**
   * implements a binary search algorithm to compare against a users results
   * also prints how the user did agiant the binary search algorithm
   *
   * @param target the randomly generated number which we are trying to guess
   * @param usersGuesses the number of guesses the user took to find target number
   */
   public static void binarySearch(int target, int usersGuesses) {
      int numberOfGuesses = 1;
      int min = MIN_NUMBER;
      int max = MAX_NUMBER;
      //wanted to see how long the search algorithm took 
      long startTime = System.nanoTime(); 
      while(min <= max) {
         int guess = (min+max)/2;
         if(guess == target) {
            break;
         }
         if(guess < target) {
            min = guess+1;
            numberOfGuesses++;
         }
         else {
            max = guess-1;
            numberOfGuesses++;
         }
      }
      long endTime = System.nanoTime();
      
      if(usersGuesses > numberOfGuesses) {
         System.out.println("The computer beat you!");
         System.out.println("The comptuer only took " + numberOfGuesses + " guesses!");
         System.out.println();
      }
      else if(usersGuesses < numberOfGuesses) {
         System.out.println("You beat the compuer!");
         System.out.println("The computer needed " + numberOfGuesses + " guesses!");
      }
      else if(usersGuesses == numberOfGuesses) {
         System.out.println("You tied the computer with " + usersGuesses + " guesses!");
      }
      System.out.println();
      
      System.out.println("It only took the computer " + Math.pow((endTime-startTime),-9) 
      + " seconds to find the answer, wow that is fast!");
      System.out.println();
   }
}