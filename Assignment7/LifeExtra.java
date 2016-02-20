//Eli F.
//Section B
//Assignment 7
//Description: an implementation of conways game of life using DrawingPanel
//will create an animation and also write the final world to a .txt file   
//Version 1.0
//Class Name: Life.java
//11/11/15

import java.awt.event.*;
import javax.swing.event.MouseInputListener;

import java.util.*;
import java.io.*;
import java.awt.*;

public class LifeExtra {
	/**
	* sets the pixels size for each cell
	*/
   public static final int SIZE = 7;

   public static boolean play = false;

   public static boolean[][] currentWorld;
   public static boolean[][] nextWorld;

   public static DrawingPanel panel;

   public static int time;

   /**
   * the entry point to the program where it all begins
   *
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {
      System.out.println("This program runs Conway's Game of Life");
      Scanner input = new Scanner(System.in);
      int maxFrames = 10000;
      Scanner fileScan = Inputter.getScanner(input, "Input file name? ");
      
      PrintStream fileWriter = Inputter.getFileWriter(input, "Output file name? "); 
      int frames = Inputter.getNumber(input, "Number of frames to run simulation: ", 0, maxFrames);
      time = Inputter.getNumber(input, "Time between steps (ms): ", 1, 1000);
      
      int columns = fileScan.nextInt();
      int rows = fileScan.nextInt();
      currentWorld = new boolean[rows][columns];
      createWorldArray(fileScan, currentWorld, columns, rows);
      nextWorld = new boolean[rows][columns];

      intro();
      panel = new DrawingPanel(columns*SIZE, rows*SIZE);
      panel.addMouseListener(new MyMouseListener()); 
      simulation(currentWorld, nextWorld, time, frames, panel);
      writeToFile(nextWorld, fileWriter);
      System.out.println("Simulation successful!");
      //System.exit(0);
	}

   public static void intro() {
      System.out.println("The current window contains initial world for conways game of life");
      System.out.println("You can add cells and kill them by clicking on the window");
      System.out.println("When you are ready to initial the simulation please click in the top left corner of the window");
      System.out.println("You will still be able to add and kill cells after the animation start");
   }

	/**
	* simulates the game of life on a DrawingPanel
	*
	* @param currentWorld a 2D array which serves as a reference to create the nextWorld
	* @param nextWorld a 2D array that becomes populated using currentWorld as a reference
	* @param time the time between each frame in ms
	* @param frames the number of frames the simulation will run for
	*/
	public static void simulation(boolean[][] currentWorld, boolean[][] nextWorld, int time, int frames, DrawingPanel panel)   {
		int columns = currentWorld[0].length; //all the rows will be the same size
		int rows = currentWorld.length;  
      Graphics g = panel.getGraphics();
      draw(currentWorld, g);
      run(currentWorld, nextWorld, g, panel, time);
   }

   public static void run(boolean[][] currentWorld, boolean[][] nextWorld, Graphics g, DrawingPanel panel, int time) {
      Point mousePosition = new Point();
      while(play) {
         generateNext(currentWorld, nextWorld);
         clone(currentWorld, nextWorld); //copies contents of nextWorld to currentWorld
         clear(g, currentWorld);
         draw(currentWorld, g);
         panel.sleep(time);
      }
   }

   public static void clear(Graphics g, boolean[][] world) {
      g.setColor(Color.WHITE);
      g.fillRect(0,0,world[0].length*SIZE,world.length*SIZE);
   }

   public static void addPoint(boolean[][] currentWorld, Point p) {
      double x = p.getX();
      double y = p.getY();
      int column = (int)(x/SIZE);
      int row = (int)(y/SIZE);
      if(currentWorld[row][column]) {
         currentWorld[row][column] = false;
      }
      else {
         currentWorld[row][column] = true;
      }
   }

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
            	Color random = Color.BLACK;//randomColor();
            	g.setColor(random);
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
	* writes a 2D array represeting a world to a file
	*
	* @param world the world to be drawn 
	* @param fileWriter the PrintStream object used to wrtie the array to a file
	*/
   public static void writeToFile(boolean[][] world, PrintStream fileWriter) {
   	for(boolean[] rows: world) {
   		for(boolean cells: rows) {
            if(cells == true) {
   			   fileWriter.print("x");
            }
            else {
               fileWriter.print(".");
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
            int neighbors = neighbors(currentWorld, ii, jj, alive);

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
	* @param alive a boolean describing if the cell is alive or dead
	* @return the number of live neighbors the specified cell has  
	*/   
   public static int neighbors(boolean[][] currentWorld, int row, int index, boolean alive) {
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
      for(int ii = above; ii <= bellow; ii++) {
         for(int jj = left; jj <= right; jj++) {
            try {
               if(currentWorld[ii][jj]) {
                  aliveNeighbors++;
               }
            }   
            catch(ArrayIndexOutOfBoundsException e) {
               //do nothing, this means the cell is on the edge of the world  
            }
         }
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
   public static void createWorldArray(Scanner fileScan, boolean[][] world, int columns, int rows) {	
      fileScan.nextLine();
      String row = "";
      for(int ii = 0; ii < rows; ii++) {
      	try {
         	row = fileScan.nextLine();
      	}
      	catch(NoSuchElementException e) {
      		System.out.println("Error found in the input file. Halting simulation.");
      		System.exit(0);
      	}
         for(int jj = 0; jj < columns; jj++) {
            world[ii][jj] = (row.charAt(jj) == 'x');
         }
      } 
   }
}