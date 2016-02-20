//Eli F.
//Section B
//Assignment 7
//Description: an implementation of conways game of life using DrawingPanel
//will create an animation and also write the final world to a .txt file
//Version 1.0
//Class Name: Life
//12/16/15

import java.util.*;
import java.io.*;
import java.awt.*;

public class Life {
   
   /**
   * sets the pixels size for each cell
   */
   public static final int SIZE = 5;
   
   /**
   * the entry point to the program, where all the magic happens
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {
      System.out.println("This program runs Conway's Game of Life");
      Scanner input = new Scanner(System.in);
      
      int maxFrames = 10000;
      int maxTime = 10000;
      
      Scanner fileScan = Inputter.getScanner(input, "Input file name? ");
      PrintStream fileWriter = Inputter.getFileWriter(input, "Output file name? ");
      int frames = Inputter.getNumber
      (input, "Number of frames to run the simulation: ", 0, maxFrames);

      int time = Inputter.getNumber(input, "Time between steps (ms): ", 1, maxTime);
      
      int columns = fileScan.nextInt();
      int rows = fileScan.nextInt();
      boolean[][] currentWorld = new boolean[rows][columns];
      boolean[][] nextWorld = new boolean[rows][columns];
      populateWorld(fileScan, currentWorld, columns, rows);
      
      simulation(currentWorld, nextWorld, time, frames);
      writeToFile(nextWorld, fileWriter);
      System.out.println("Simulation successful!");
      System.exit(0);
   }
   
   /**
   * simulates the game of life on a DrawingPanel
   *
   * @param currentWorld a 2D array which serves as a reference to create the nextWorld
   * @param nextWorld a 2D array that becomes populated using currentWorld as a reference
   * @param time the time between each frame in ms
   * @param frames the number of frames the simulation will run for
   */
   public static void simulation(boolean[][] currentWorld,boolean[][] nextWorld,int time,int frames){
      int columns = currentWorld[0].length; //all the rows will be the same size
      int rows = currentWorld.length;
      DrawingPanel panel = new DrawingPanel(columns*SIZE, rows*SIZE);
      Graphics g = panel.getGraphics();
      draw(currentWorld, g); //draws first frame of simulation
      
      //will run forever is frames == 0
      for(int ii = 1; ii != frames; ii++) {
         generateNext(currentWorld, nextWorld);
         clone(currentWorld, nextWorld); //copies contents of nextWorld to currentWorld
         clear(g, currentWorld); //clears the panel for next frame to be drawn
         draw(currentWorld, g); //draws next frame
         panel.sleep(time);
      }
   }
   
   /**
   * clears the drawing panel to a white canvas
   *
   * @param g graphics object on the drawingPanel you want to clear
   * @param world the array reprenting the world being displayed
   */
   public static void clear(Graphics g, boolean[][] world) {
      g.setColor(Color.WHITE);
      g.fillRect(0,0,world[0].length*SIZE,world.length*SIZE);
      //there was a DrawingPanel method to do this but the animation is less flashy with using this
   }
   
   /**
   * clones a given array to another, will copy all of the elements but will not change
   * the location in memory
   *
   * @param currentWorld the world to be changed
   * @param nextWorld the world to be copied from
   */
   public static void clone(boolean[][] currentWorld, boolean[][] nextWorld) {
      for(int ii = 0; ii < currentWorld.length; ii++) { //assumes the 2D arrays are the samelength
         for(int jj = 0; jj < currentWorld[ii].length; jj++) {
            currentWorld[ii][jj] = nextWorld[ii][jj];
         }
      }
   }
   
   /**
   * draws one frame of the simulation
   *
   * @param world the 2D array representing the current world
   * @param g the Graphics object used to draw the world on the panel
   */
   public static void draw(boolean[][] world, Graphics g) {
      for(int ii = 0; ii < world.length; ii++) {
         for(int jj = 0; jj < world[ii].length; jj++) {
            if(world[ii][jj] == true) {
               //Color random = randomColor(); //if you wanted random colors
               g.setColor(Color.BLACK);
               g.fillRect(SIZE*jj, SIZE*ii, SIZE, SIZE);
            }
         }
      }
   }
   
   /**
   * creates a random color
   *
   * @return the random color
   */
   public static Color randomColor() {
      int r = (int) (Math.random()*255);
      int g = (int) (Math.random()*255);
      int b = (int) (Math.random()*255);
      return new Color(r, g, b);
   }
   
   /**
   * writes a 2D array represeting a world to a given file
   *
   * @param world the world to be drawn
   * @param fileWriter the PrintStream object used to wrtie the array to a file
   */
   public static void writeToFile(boolean[][] world, PrintStream fileWriter) {
      for(boolean[] rows: world) {
         for(boolean cells: rows) {
            if(cells == true) {
               fileWriter.print('x');
            }
            else {
               fileWriter.print('.');
            }
         }
         fileWriter.println();
      }
   }
   
   /**
   * generates the next world per the rules describing conways game of life
   *
   * @param currentWorld the array holding the infomation to be used as reference
   * @param nextWorld the array to be populated using currentWorld and a set of rules
   */
   public static void generateNext(boolean[][] currentWorld, boolean[][] nextWorld) {
      for(int ii = 0; ii < currentWorld.length; ii++) {
         for(int jj = 0; jj < currentWorld[ii].length; jj++) {
            boolean alive = currentWorld[ii][jj];
            int neighbors = neighbors(currentWorld, ii, jj);
            if(alive) {
               if(neighbors < 2) {
                  nextWorld[ii][jj] = false;
               }
               if(neighbors == 2 || neighbors == 3) {
                  nextWorld[ii][jj] = true;
               }
               if(neighbors > 3) {
                  nextWorld[ii][jj] = false;
               }
            }
            else if(neighbors == 3) {
               nextWorld[ii][jj] = true;
            }
            else {
               nextWorld[ii][jj] = false;
            }
         }
      }
   }
   
   /**
   * calcualtes the number of live neighbors for a given cell
   *
   * @param currentWorld the world to checked for neighbors of a specific cell
   * @param row the row the current cell is in
   * @param index the index within the row of the current cell
   * @return the number of live neighbors the specified cell has
   */
   public static int neighbors(boolean[][] currentWorld, int row, int index) {
      boolean alive = currentWorld[row][index];
      int right = index+1;
      int left = index-1;
      int above = row-1;
      int bellow = row+1;
      int rowLength = currentWorld[row].length-1;
      int length = currentWorld.length-1;
      int aliveNeighbors;
      if(alive) {
         aliveNeighbors = -1; //because the following will count current cell
      }
      else {
         aliveNeighbors = 0;
      }
      for(int ii = above; ii <= bellow; ii++) { //cycles through all the cells neighbors
         for(int jj = left; jj <= right; jj++) {
            try {
               if(currentWorld[ii][jj]) {
                  aliveNeighbors++;
               }
            }
            catch(ArrayIndexOutOfBoundsException e) {
               //this is expeced to happen when we are dealing with a cell on the edge of the world
            }
         }
      }
      if(aliveNeighbors == -1) {
         aliveNeighbors = 0; //not nessisary for the program to function correctly
         //however you cant really have -1 neighbors!!
      }
      return aliveNeighbors;
   }
   
   /**
   * populates an array based off the contents of a specified world .txt file
   *
   * @param fileScan a Scanner Object on a the file containing the world information
   * @param world the 2D array to be populated
   * @param columns the number of columns in the world described by the file
   * @param rows the number of rows in the world described by the file
   */
   public static void populateWorld(Scanner fileScan, boolean[][] world, int columns, int rows) {
      fileScan.nextLine();
      String row = "";
      for(int ii = 0; ii < rows; ii++) {
         try {
            row = fileScan.nextLine();
         }
         catch(NoSuchElementException e) {
            System.out.println("Error found in the input file. Halting simulation.");
            System.exit(1);
         }
         for(int jj = 0; jj < columns; jj++) {
            world[ii][jj] = (row.charAt(jj) == 'x');
         }
      }
   }
}
