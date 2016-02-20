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

public class DragonGenerateLSystem {

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
      long time1 = System.nanoTime();
      LinkedList<Character> path = generate(level);
      long time2 = System.nanoTime();
      System.out.println("Path generated, writing to file " + FILE_NAME+FILE_EXTENSION + "...");
      System.out.println("Took " + ((time2-time1)/Math.pow(10,9)) + " seconds to compute");
      PrintStream fileWriter = Inputter.getFileWriter(FILE_NAME+FILE_EXTENSION); 
      System.out.println("Drawing Curve...");
      writeToFile(path, fileWriter);
      
      DragonDraw panel = new DragonDraw(size);
      panel.drawCurve(FILE_NAME+FILE_EXTENSION, level);   
   }
   
   public static void writeToFile(LinkedList<Character> path, PrintStream fileWriter) {
      for(int ii = 0; ii < path.size(); ii++) {
         fileWriter.print(path.get(ii));
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
   public static LinkedList<Character> generate(int level) {
      if(level == 1) {
         return new LinkedList<Character>(Arrays.asList('f'));
      }
      
      //System.out.println(level);
      LinkedList<Character> nextLevel = generate(level-1);
      //System.out.println("f00");
      //System.out.println(nextLevel);
      LinkedList<Character> test = replaceAll('h', 'f', nextLevel);
      //System.out.println("tester1");
      return test;   
   }
   
   public static LinkedList<Character> replaceAll(char a, char b, LinkedList<Character> list) {
      //System.out.println("Starting replaceAll");
      for(int ii = 0; ii < list.size(); ii++) {
         if(list.get(ii) == b) {
            //System.out.println("f");
            list.remove(ii);
            list.add(ii, 'f');
            list.add(ii+1,'-');
            list.add(ii+2, 'h');
            ii += 2; //to skip of the section that was just added
         }
         else if(list.get(ii) == a) {
            //System.out.println("h");
            list.remove(ii);
            list.add(ii, 'f');
            list.add(ii+1,'+');
            list.add(ii+2, 'h');
            ii += 2; //to skip of the section that was just added
         }
      }
      return list;
   }
}