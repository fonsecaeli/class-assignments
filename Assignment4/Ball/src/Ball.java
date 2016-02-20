//Eli F.
//Section B
//Assignment 4
//Description:
//Class Name: Ball
//Version 1.0
//10/16/15

import java.util.*;

import java.awt.*;

public class Ball {

	/**
	* Framerate used to draw ball
	*/
	public static final int FRAME_RATE = 50;

	/**
	* Default size of ball
	*/
	public static final int SIZE = 9;

	/*
	* Number of times to run the simulation
	*/
	public static final int STEPS = 1000;

	/**
	* Gets input from user for size of drawingpane and then animates a bouncing ball 
	*
	* @param args user input from the console 
	*/
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);

		System.out.println("This program will simulate a ball bouncing in a window");
		int width = prompt(console, "How wide would you like the panel (in pixels)? ");
		int height = prompt(console, "How tall would you like the panel (in pixels)? ");
		int inX = prompt(console, "What is the initial X position of the ball? ");
		int inY = prompt(console, "What is the initial Y position of the ball? ");
		int xVelocity = prompt(console, "What is the initial X velocity of the ball? ");
		int yVelocity = prompt(console, "What is the initial Y velocity of the ball? ");

		DrawingPanel panel = new DrawingPanel(width, height);
      //panel.setBackground(Color.GRAY);
      animateBall(panel, inX, inY, xVelocity, yVelocity, width, height);
	}

	/**
   * Prompts the user for an integer value
   *
   * @param input scanner object used to get input from user
   * @param question the question that will prompt the user
   * @return userInput the int the user inputed
   */
   public static int prompt(Scanner input, String question) {
      System.out.print(question);
      int userInput = input.nextInt();
      return userInput;
   }

   /**
   * Animates a ball in a panel, ball will bounce off sides of panel
   * 
   * @param panel DrawingPanel object used to make forloop release control so ball can be drawn
   * @param inX initial x position of ball
   * @param inY initial y position of ball
   * @param xV x velocity of ball
   * @param yV y velocity of ball
   * @param width width of panel 
   * @param height height of panel
   */
   public static void animateBall(DrawingPanel panel, int inX, int inY, int xV, int yV, int width, int height) {
      int eraseX = inX;
      int eraseY = inY;
      int newX = eraseX+xV;
      int newY = eraseY+yV;

      Graphics graphics = panel.getGraphics();
      graphics.fillOval(inX, inY, SIZE, SIZE); //draws circle with diameter SIZE

      for(int ii = 0; ii < STEPS; ii++) { 
         drawBall(panel, eraseX, eraseY, newX, newY);
         eraseX += xV;
         eraseY += yV; 
         newX += xV;
         newY += yV;
         if(eraseY+SIZE >= height && yV > 0) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval(eraseX, eraseY, SIZE, SIZE);
            yV = (int) -(99*yV)/100; //so you lose some energy every time you hot ground
            eraseY = newY-yV;
         }
         else if(eraseY+SIZE <= 0 && yV < 0) {
            yV = -yV;
            eraseY = newY-yV;
         }
         else if(eraseX+SIZE >= width && xV > 0) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval(eraseX, eraseY, SIZE, SIZE);
            xV = -xV;
            eraseX = newX-xV;
         }
         else if(eraseX+SIZE <= 0 && xV < 0) {
            xV = -xV;
            eraseX = newX-xV;
         } 
         else {
            yV++;  
            eraseY--;
         }
      }
      //System.exit(0);
   }

   /**
   * Erases previous ball then draws new ball
   * 
   * @param panel DrawingPanel object used to sleep panel
   * @param eraseX x coord of ball to be erased
   * @param eraseY y coord of ball to be erased
   * @param newX x coord of new ball to be drawn
   * @param newY y coord of new ball to be drawn
   */
  	public static void drawBall(DrawingPanel panel, int eraseX, int eraseY, int newX, int newY) {
      Graphics graphics = panel.getGraphics();
      Random ran = new Random();
      float r = ran.nextFloat();
      float g = ran.nextFloat();
      float b = ran.nextFloat();
      Color random = new Color(r, g, b);

  		graphics.setColor(Color.WHITE);
   	graphics.fillRect(eraseX, eraseY+1, SIZE, SIZE); //draws rect to erase previous circle
  		graphics.setColor(Color.BLACK);
  		graphics.fillOval(newX, newY, SIZE, SIZE); 	
  		panel.sleep( 1000 / FRAME_RATE); 
  	}
}