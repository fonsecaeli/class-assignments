//Eli F.
//Section B
//Assignment 6
//Description: draws regions of the USA to a panel based off user input  
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
   * displays give regions of USA based of files describing those regions
   *
   * @param args user input from the console 
   */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in); 
      explanation();
      System.out.println();
      do {
         String regionName = getRegionName("What state would you like to see? ", input);
         Scanner fileScan = getScanner(regionName);
         drawOutline(fileScan, regionName);
      }
      while(again(input));
   }

   /**
   * prints an overview of program to the console
   */
   public static void explanation() {
      System.out.println("This program draws a visualization of");
      System.out.println("data from a given file. Enter");
      System.out.println("- the 2 letter abbreviation for each state");
      System.out.println("- USA for all of the US by state, or");
      System.out.println("- USA-county for all of the US by county.");
   }
   
   /**
   * will parse through the file and get the coordinates that will 
   * create the polygonal shapes of the region.
   * regions will be drawn using DrawingPanelPlus and Polygon2D 
   *
   * @param fileScan a Scanner object used to read the data from the files 
   * describing the area to be drawn 
   * @param regionName the name of the region being drawn
   */
   public static void drawOutline(Scanner fileScan, String regionName) {
      //collect bouding box information
      Double minLong = fileScan.nextDouble(); // minlongitude
      Double minLat = fileScan.nextDouble(); //minlatitude
      Double maxLong = fileScan.nextDouble(); //maxlongitude
      Double maxLat = fileScan.nextDouble(); //maxlatitude

      //calcualte size of canvas based off of scale factor 
      int height = (int)((maxLat-minLat)*SCALE);
      int width = (int)((maxLong-minLong)*SCALE);
      
      DrawingPanelPlus window = new DrawingPanelPlus(width, height);
      window.setCanvasSize(width, height);
      window.setYscale(minLat, maxLat);
      window.setXscale(minLong, maxLong);

      int subRegions = fileScan.nextInt();   
   
      for(int ii = 0; ii < subRegions; ii++) {
         fileScan.nextLine();
         fileScan.nextLine();
         fileScan.nextLine();
         fileScan.next();
         int vertices = fileScan.nextInt();
         Polygon2D poly = new Polygon2D(vertices);
         for(int jj = 0; jj < vertices; jj++) {
             poly.addPoint(fileScan.nextDouble(), fileScan.nextDouble());
         }
         window.polygon(poly);
      }
   }
   
   /**
   * will open a scanner on a specified file
   *
   * @param fileName the name of the file the scanner will be opened on
   * @return the scanner opened on the specified file or a null value 
   * indicating the file cannot be found 
   */
   public static Scanner getScanner(String fileName) {
      File f = new File(DIRECTORY+fileName+".txt");
      Scanner fileScan = null; 
      try {
         fileScan = new Scanner(f);
         return fileScan;
      }
      catch (FileNotFoundException e) {
         return null; 
      }
   }
   
   /**
   * asks the user what they would like drawn and collects this input 
   * 
   * @param prompt the phrase the user will be prompted with
   * @param input Scanner object used to collect input 
   * @return the name of the region to be draw                                                                                                                                                                                                                            
   */
   public static String getRegionName(String prompt, Scanner input) {
      System.out.print(prompt);
      String regionName = input.next();
      while(getScanner(regionName) == null) {
         System.out.println("File not found. Try again. ");
         System.out.print(prompt);
         regionName = input.next();
      }
      return regionName;
   }
   
   /**
   * tests to see if the user would like to draw anything else
   *
   * @param input Scanner object used to ask user if they would like to play again
   * @return boolean value that indicates whether or not the user wants to play again
   */
   public static boolean again(Scanner input) {
      System.out.print("Do you want to draw something else? ");
      String answer = input.next();
      return (answer.startsWith("y") || answer.startsWith("Y"));
   }
}