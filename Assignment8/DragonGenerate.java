//Eli F.
//Section B
//Assignment 8
//Description: generates the dragon curve, describing it in a text file
//then renders the curve using drawingPanel
//Version 1.0
//Class Name: DragonGenerate.java
//1/4/16

import java.util.*;
import java.io.*;

public class DragonGenerate {

   /**
   * max level of recusion allowed
   */
   public static final int MAX_LEVEL = 25;
   
   /**
   * min level of recusion allowed
   */
   public static final int MIN_LEVEL = 1;
   
   /**
   * max size of drawingpanel
   */
   public static final int MAX_SIZE = 2000;
   
   /**
   * min size of drawingpanel
   */
   public static final int MIN_SIZE = 100;
   
   /**
   * default name of file to store L-system representing the dragon curve
   */
   public static String FILE_NAME = "dragon";
   
   /**
   * the sterage files extension
   */
   public static final String FILE_EXTENSION = ".txt";

	/**
	* The entry point into the program, where it all begins
	*
	*
	* @param args user input from the console
	*/
   public static void main(String[] args) {
      explination();
      Scanner input = new Scanner(System.in);
      int level = Inputter.getNumber(input, "Enter the level of the fractal you'd like to see ("+
      MIN_LEVEL+"-"+MAX_LEVEL+"): ", MIN_LEVEL, MAX_LEVEL);
      FILE_NAME += level;
      int size = Inputter.getNumber(input, "Enter the size of your drawing panel, in pixels ("+
      MIN_SIZE+"-"+MAX_SIZE+"): ", MIN_SIZE, MAX_SIZE);
      String path = generate(level);
      System.out.println("Path generated, writing to file " + FILE_NAME+FILE_EXTENSION + "...");
      PrintStream fileWriter = Inputter.getFileWriter(FILE_NAME+FILE_EXTENSION); 
      System.out.println("Drawing curve...");
      fileWriter.print(path);
      
      //draw the curve with a DragonDraw object
      DragonDraw panel = new DragonDraw(size);
      panel.drawCurve(FILE_NAME+FILE_EXTENSION, level);   
   }
   
   /*
   * prints an explination of the program
   */
   public static void explination() {
      System.out.println("This program will generate a fractal called the Dragon Curve");
      System.out.println("first explored by John Heighway, Bruce Banks, and William Harter");
      System.out.println("at NASA in the 1960's");
      System.out.println();
   }  

	/**
	* generates the string representing the dragon curve
	*
	* @param level the level of recursion to be calculated 
   * @return the string derscribing the dragon curve
	*/
   public static String generate(int level) {
      if(level == 1) {
         return "R";
      }
      String nextLevel = generate(level-1);
      return nextLevel+"R"+compliment(nextLevel);
   }

	/**
	* generates the compliemnt of a set of instructions with iteration
	*
	* @param s the string to calculate the compliment of
   * @return the compliemnt of the inputted string 
	*/
   public static String compliment(String s) {
      String reverse = "";
      for(int ii = 0; ii < s.length(); ii++) {
         if(s.charAt(s.length()-(1+ii)) == 'R') {
            reverse += "L";
         }
         else {
            reverse += "R";
         }
      }
      return reverse;	
   }
   
   /*
   * generates the compliment of a string a recusive algorithm   
   * 
   * @param s the string to find the compliment of 
   * @return the compliemnt of the inputted string 
   */
   public static String complimentR(String s) {
      if(s.length() == 1) {
         boolean isL = (s.charAt(0) == 'L');
         if(isL) {
            return "R";
         }
         else {
            return "L";
         }
      }
      boolean isL = (s.charAt(0) == 'L');
      if(isL) {
         return complimentR(s.substring(1, s.length())) + "R";
      }
      else {
         return complimentR(s.substring(1, s.length())) + "L";
      }
   }
}