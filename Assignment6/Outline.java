//Eli F.
//Section B
//Assignment 6
//Description:  
//Version 1.0
//Class Name: Outline.java
//11/11/15

import java.io.*;
import java.util.*;

public class Outline {

   /**
   * scaling factor for drawing the states or US
   */
   public static final int SCALE = 30;
   
   /**
   * contains the directory name where the data files are located
   */
   public static final String DIRECTORY = "data\\";
   
   /**
   * The entry point into the program, where it all begins
   *
   * @param args user input from the console 
   */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      File f = new File("dsfsd");
   }
   
   /**
   * will parse through the file and get the coordinates that will create the polygonal shapes of the region.
   *
   *  
   */
   public static void drawOutline() {
   
   }
   
   /**
   * will open a scanner on a specified file
   *
   * @param fileName the name of the file the scanner will be opened on
   * @return the scanner opened on the specified file
   */
   public static Scanner getScanner(String fileName) {
      File f = new File(fileName);
      Scanner fileScan = null; 
      try {
         fileScan = new Scanner (f);
         return fileScan;
      }
      catch (FileNotFoundException e) {
         System.out.println("Error opening file " + fileName);
         return fileScan;
      }
   }
   
   /**
   * asks the user what they would like drawn and collects this input 
   *
   * @param the phrase you want to prompt the user with 
   * @param input Scanner object used to collect input 
   * @return what the user wants to be drawn
   */
   public static String getState(String prompt, Scanner input) {
      System.out.print("What state would you like to see ?");
      return "s";
   }
   
   /**
   *
   *
   *
   *
   */
  }