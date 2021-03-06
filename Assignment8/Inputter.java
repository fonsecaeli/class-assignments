//Eli F.
//Section B
//Assignment 7
//Description: Collects robust input from the user will handel different errors
//Version 1.2
//Class Name: Inputter
//12/16/2015

import java.util.*;
import java.io.*;

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

   /**
   * will open a scanner on a specified file
   *
   * @param input Scanner object used to collect input from the user
   * @param prompt String to prompt the user with
   * @return the scanner opened on the specified file
   */
   public static Scanner getScanner(Scanner input, String prompt) {
      System.out.print(prompt);
      String line = input.nextLine();
      Scanner lineScan = new Scanner(line);
      String fileName = lineScan.next();
      Scanner fileScan = null;
      while(fileScan == null) {
         File f = new File(fileName);
         try {
            fileScan = new Scanner(f);
            break;
         }
         catch (FileNotFoundException e) {
            System.out.print("File not found. Try again: "+prompt);
            line = input.nextLine();
            lineScan = new Scanner(line);
            fileName = lineScan.next();
         }
      }
      return fileScan;
   }

   /**
   * will create a file that can be writen too
   *
   * @param input Scanner object used to collect user input
   * @param prompt the String to prompt the user with
   * @return a PrintStream object that can be used to write to the file that was created
   */
   public static PrintStream getFileWriter(String fileName) {
      PrintStream output = null;
      File f = new File(fileName);
      try {
         output = new PrintStream(f);
      }
      catch (FileNotFoundException e){
         System.out.println("Error opening output file for writing. Curve not drawn.");
         System.exit(1);
      }
      return output;
   }
}
