//Eli F.
//Section B
//Assignment #5
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
            String extraStuff = input.nextLine();
            return number;
         }
	   }
   }
}