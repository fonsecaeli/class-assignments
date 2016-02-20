//Eli F.
//Section B
//Assignment 6
//Description: Collects robust input from the user will handel different errors 
//Version 1.0
//Class Name: Inputter
//10/29/2015

import java.util.*;

public class Inputter {

   /**
   * Collects robust input from the user
   * 
   * @param input Scanner object used to get input from user
   * @param prompt the phrase the user will be prompted with
   * @param min the minimum value the user can input 
   * @param max the maximum value the user can input
   * @return number to be used in main program
   */
   
	public static int getNumber(Scanner input, String prompt, int min, int max) {
         System.out.print(prompt);
      while(true) {
         while(!input.hasNextInt()) {
            input.next();
            System.out.println("File not found. Try again.");
            System.out.print(prompt);
         }
         int number = input.nextInt();

	      if(number < min || number > max || (number%4 != 0)) {
            System.out.println("File not found. Try again.");
            System.out.print(prompt);
         }
         else {
            String extraStuff = input.nextLine();
            return number;
         }
	   }
   }
}