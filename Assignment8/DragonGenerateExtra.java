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

public class DragonGenerateExtra {

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
   /*
   * min size of drawingpanel
   */
   public static final int MIN_SIZE = 100;
   /**
   * default name of file to store L-system representing the dragon curve
   */
   public static final String FILE_NAME = "dragon";
   /*
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
      int level = Inputter.getNumber(input, "Enter the level of the fractal you'd like to see (1-25): ", 1, 25);
      int size = Inputter.getNumber(input, "Enter the size of your drawing panel, in pixels (100-2000): ", 100, 2000);
      LinkedList<Boolean> path = generate(level);
      System.out.println("Path generated, writing to file dragon.txt...");
      PrintStream fileWriter = Inputter.getFileWriter(FILE_NAME+FILE_EXTENSION); 
      System.out.println("Drawing Curve...");
      writeToFile(path, fileWriter);
      
      DragonDraw panel = new DragonDraw(size);
      panel.drawCurve(FILE_NAME+FILE_EXTENSION, level);   
   }
   
   public static void writeToFile(LinkedList<Boolean> path, PrintStream fileWriter) {
      for(int ii = 0; ii < path.size(); ii++) {
         if(path.get(ii)) {
            fileWriter.print("L");
         }
         else {
            fileWriter.print("R");
         }
      }
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
	*/
   public static LinkedList<Boolean> generate(int level) {
      if(level == 1) {
         LinkedList<Boolean> list = new LinkedList<Boolean>();
         list.add(false);
         return list;
      }
      LinkedList<Boolean> nextLevel = generate(level-1);
      nextLevel.add(false);
      nextLevel.addAll(complimentR(nextLevel));
      return nextLevel;
   }

	/**
	* generates the compliemnt of a set of instructions with iteration
	*
	* @param s the string to calculate the compliment of
	*/
   public static String compliment(String s) {
      String reverse = "";
      for(int ii = 0; ii < s.length(); ii++) {
         reverse += s.charAt(s.length()-(1+ii));
      }
      char[] converter = reverse.toCharArray();
      for(int ii = 0; ii < reverse.length(); ii++) {
         if(converter[ii] == 'R') {
            converter[ii] = 'L';
         }
         else {
            converter[ii] = 'R';
         }
         reverse = String.valueOf(converter);
      }
      return reverse;	
   }
   
   /*
   * generates the compliment of a string using two recurisve functions
   * 
   * @param s the string to find the compliment of 
   */
   public static LinkedList<Boolean> complimentR(LinkedList<Boolean> s) {
      if(s.size() == 1) {
         if(s.get(0)) { //true represents 'L'
            LinkedList<Boolean> list = new LinkedList<Boolean>();
            list.add(false);
            return list;
         }
         else {
            LinkedList<Boolean> list = new LinkedList<Boolean>();
            list.add(true);
            return list;
         }
      }
      boolean isL = s.get(0);
      s.remove(0);
      if(isL) {
         LinkedList<Boolean> test = complimentR(s);
         test.add(false);
         return test;
      }
      else {
         LinkedList<Boolean> test = complimentR(s);
         test.add(true);
         return test;
      }
   }
}