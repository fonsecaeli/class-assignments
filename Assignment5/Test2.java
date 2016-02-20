//Eli F.
//Section B
//Assignment #5
//Description: Collects robust input from the user will handel different errors 
//Version 1.0
//Class Name: Inputter
//10/29/2015

import java.util.*;

public class Test2 {

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      getNumber(input, "Enter a number ", 1, 100);
   }

   /**
   * Collects robust input from the user
   * 
   * @param input Scanner object used to get input from user
   * @param prompt the phrase the user will be prompted with
   * @param min the minimum value the user can input 
   * @param max the maximum value the user can input
   */
	public static int getNumber(Scanner input, String prompt, int min, int max) {
      while(true) {
         System.out.print(prompt);
         while(!input.hasNextInt()) {
            input.next();
            System.out.println("You must enter an *integer* between " + min + " and " + max + ".");
            System.out.print(prompt);
         }
         int number = input.nextInt();
	      if(number < min || number > max) {
            System.out.println("Your number needs to be between " + min + " and " + max + ".");
         }
         else {
            return number;
         }
	   }
   }
   
   public static void binarySearchStats(int totalGames, int totalGuesses, int best, double usersRatio) {
      double ratio = Math.round((10*((double)(totalGuesses)/totalGames)))/10.0;
      System.out.println();
      System.out.println("BinarySearch results:");
      System.out.printf("%-14s", "Total games");
      System.out.println("= " + totalGames);
      System.out.printf("%-14s", "Total guesses");
      System.out.println("= " + totalGuesses);
      System.out.printf("%-14s", "Guesses/game");
      System.out.println("= " +  ratio);
      System.out.printf("%-14s", "Best game");
      System.out.println("= " + best);
      System.out.println();
      if(ratio < usersRatio) {
         System.out.println("You got destroyed!");
      }
      else if(ratio > usersRatio) {
         System.out.println("You won!");
      }
      else if(ratio == usersRatio) {
         System.out.println("That's respectable, you tied.");
      }
   }

}