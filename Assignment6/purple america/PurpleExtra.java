//Eli F.
//Section B
//Assignment 6
//Description: draw states with colors reflecting the election data for a specific year 
//Version 1.0
//Class Name: Purple 
//11/11/15

import java.io.*;
import java.util.*;
import java.awt.*;

public class PurpleExtra {

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
      explanation();
      System.out.println();
      do {
         String regionFile = getName("What state would you like to see? ", input);
         int year = Inputter.getNumber(input, "For what year (1960 to 2012) do you want to see data: ", 1960, 2012);
         Scanner fileScanDraw = getScanner(DIRECTORY+regionFile+".txt");
         drawOutline(fileScanDraw, year);
         if(!regionFile.equalsIgnoreCase("usa-county")) {
            stats(regionFile+year);
         }
         System.out.println();
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
   * prints out the electon data for each county
   *
   * @param colorFile the name of the file that contains the electon data
   */
   public static void stats(String colorFile) {
      Scanner fileScan = getScanner(DIRECTORY+colorFile+".txt");
      while(true) {
         try {
            fileScan.useDelimiter(",");
            String regionName = fileScan.next().trim();
            if(regionName.equals("")) {
               return;
            }
            System.out.println();
            System.out.println("Displaying "+regionName+" data");
            System.out.printf("%-6s   = "+fileScan.next(),"  Red");
            System.out.println();
            System.out.printf("%-6s   = "+fileScan.next(), "  Blue");
            System.out.println();
            System.out.printf("%-6s  = "+fileScan.next(), "  Green");
            System.out.println();
         }
         catch (NoSuchElementException e) {
            return;
         }
      }
   }
   /**
   * will parse through the file and get the coordinates that will create the polygonal shapes of the region.
   *
   * @param fileScanDraw a Scanner object used to read the data from the files desccribing the area to be drawn 
   * @param year the year of the election the user wants displayed
   */
   public static void drawOutline(Scanner fileScanDraw, int year) {
      Double minLong = fileScanDraw.nextDouble();
      Double minLat = fileScanDraw.nextDouble();
      Double maxLong = fileScanDraw.nextDouble();
      Double maxLat = fileScanDraw.nextDouble();
      int height = (int)((maxLat-minLat)*SCALE);
      int width = (int)((maxLong-minLong)*SCALE);

      DrawingPanelPlus window = new DrawingPanelPlus(width, height);
      DrawingPanelPlus window2 = new DrawingPanelPlus(width, height);
      window.setCanvasSize(width, height);
      window.setYscale(minLat, maxLat);
      window.setXscale(minLong, maxLong);
      window2.setCanvasSize(width, height);
      window2.setYscale(minLat, maxLat);
      window2.setXscale(minLong, maxLong);
      String stateName = ""; 

      FileData data = null;
      
      int subRegions = fileScanDraw.nextInt();   
      for(int ii = 0; ii < subRegions; ii++) {     
         fileScanDraw.nextLine();
         fileScanDraw.nextLine();
         String regionName = fileScanDraw.nextLine();
         String testStateName = fileScanDraw.next();
         
         if(!testStateName.equals(stateName)) {
            stateName = testStateName;
            String colorFile = DIRECTORY+stateName+year+".txt";
            Scanner fileScanColor = getScanner(colorFile);
            data = new FileData(fileScanColor);
         }
         int vertices = fileScanDraw.nextInt();
         Polygon2D poly = new Polygon2D(vertices);
         for(int jj = 0; jj < vertices; jj++) {
             poly.addPoint(fileScanDraw.nextDouble(), fileScanDraw.nextDouble());
         }
         window.setPenColor(createPurple(regionName, year, stateName, true, data));
         window.filledPolygon(poly);
         window.setPenColor();
         window.polygon(poly);
         window2.setPenColor(createPurple(regionName, year, stateName, false, data));
         window2.filledPolygon(poly);
         window2.setPenColor();
         window2.polygon(poly);
      }
   }
   
   /**
   * creates the correct color based off of the election data for a specific region
   *
   * @param regionName the name of the region to be colored 
   * @param year the election year
   * @param stateName the state the region is located in
   * @param purple a boolean indicated weather a shape of purple or solid color is to be generated
   * @param data a FileData object containing all the data for the state
   * @return the color matching the election data
   */
   public static Color createPurple(String regionName, int year, String stateName, boolean purple, FileData data) {    
      try {
         String regionNameFinal = "";
         if(regionName.endsWith("Parish")) {
            regionNameFinal = regionName.replaceFirst(" Parish", "").trim();
         }
         else if( regionName.endsWith("city")) {
            regionNameFinal = regionName.replaceFirst(" city", "").trim();
         }
         if(regionNameFinal.equals("")) {
            regionNameFinal = regionName;
         }
         int a1 = data.lookup(regionNameFinal,0);
         int a2 = data.lookup(regionNameFinal,1);
         int a3 = data.lookup(regionNameFinal,2);
         if(purple) {
            float r = (((float)a1/(a1+a2+a3)));
            float b = (((float)a2/(a1+a2+a3)));
            float g = (((float)a3/(a1+a2+a3)));
            Color purpleShade = new Color(r,g,b);
            return purpleShade;
         }
         else {
            if(a1 > a2 && a1 > a3) { //if a1 is greatest
               return Color.RED;
            }
            else if(a2 > a3) { //if a2 is greatest
               return Color.BLUE;
            }
            else { //if a3 is greatest
               return Color.GREEN;
            }
         }
      }
      catch(InputMismatchException e ) {
         return Color.WHITE;
      }
      catch(IllegalArgumentException e) {    
         return Color.WHITE;
      }
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
         fileScan = new Scanner(f);
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
   * @param prompt the phrase the user will be prompted with
   * @param input Scanner object used to collect input 
   * @return what the user wants to be drawn
   */
   public static String getName(String prompt, Scanner input) {
      System.out.print(prompt);
      String name = input.next();
      
      while(getScanner(DIRECTORY+name+".txt") == null) {
         System.out.println("File not found. Try again. ");
         System.out.print(prompt);
         name = input.next();
      }
      return name;
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