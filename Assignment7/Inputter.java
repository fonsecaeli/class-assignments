//Eli F.
//Section B
//Assignment 7
//Description: Collects robust input from the user will handel different errors
//Version 1.1
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
      String error = "Input is not valid, you need to enter a number";
      while(true) {
         System.out.print(prompt);
         while(!input.hasNextInt()) {
            input.next();
            System.out.println(error);
            System.out.print(prompt);
         }
         int number = input.nextInt();
         if(number < min) {
            System.out.println(error);
            System.out.print(prompt);
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
   public static PrintStream getFileWriter(Scanner input, String prompt) {
      System.out.print(prompt);
      String line = input.nextLine();
      Scanner lineScan = new Scanner(line);
      String fileName = lineScan.next();
      PrintStream output = null;
      while(output == null) {
         File f = new File(fileName);
         try {
            output = new PrintStream(f);
            break;
         }
         catch (FileNotFoundException e){
            System.out.println("Error opening file for write: "+prompt);
            line = input.nextLine();
            lineScan = new Scanner(line);
            fileName = lineScan.next();
         }
      }
      return output;
   }
}
