//Eli F.
//Section B
//Assignment 4
//Description: animates a ball bouncing in a window using Drawingpanel
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
   public static final int SIZE = 15;
   
   /**
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
      panel.setBackground(Color.BLACK);
      animateBall(panel, inX, inY, xVelocity, yVelocity, width, height);
   }
   
   /**
   * Prompts the user for an number with a specific question
   *
   * @param input scanner object used to get input from user
   * @param question the question that will prompt the user
   * @return the value the user inputed cast to a int
   */
   public static int prompt(Scanner input, String question) {
      System.out.print(question);
      int userInput = (int) input.nextDouble();
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
   * @param w width of panel
   * @param h height of panel
   */
   public static void animateBall(DrawingPanel panel,int inX,int inY,int xV,int yV,int w,int h) {
      int eraseX = inX;
      int eraseY = inY;
      
      Graphics graphics = panel.getGraphics(); //graphcis object to draw
      graphics.fillOval(inX, inY, SIZE, SIZE); //draws circle with diameter SIZE
      
      for(int ii = 0; ii < STEPS; ii++) {
         drawBall(graphics, eraseX, eraseY, xV, yV);
         panel.sleep( 1000 / FRAME_RATE);
         //update the erase coordinates
         eraseX += xV;
         eraseY += yV;
         if(eraseY+SIZE >= h && yV > 0) { //if it hits bottom, the yV > 0 is for graivty
            yV *= -1;
            //yV = (int) (-(200*yV)/201); //so you lose some energy every time you hit ground for gravity
         }
         if(eraseY <= 0 && yV < 0) { //if it hits top
            yV = -yV;
         }
         if(eraseX+SIZE >= w || eraseX <= 0) { //if it hits right sides
            xV = -xV;
         }
         //yV++; //for gravity
      }
      System.exit(0); //so window will close after loop is finished executing
   }
   
   /**
   * Erases previous ball then draws new ball
   * each iteration of the ball will be a random color
   *
   * @param graphics Graphics object used draw in the drawing panel
   * @param eraseX x coord of ball to be erased
   * @param eraseY y coord of ball to be erased
   * @param xV x velocity of ball
   * @param yV y velocity of ball
   */
   public static void drawBall(Graphics graphics, int eraseX, int eraseY, int xV, int yV) {
      //creates random color for ball for each frame
      int r = (int) (Math.random()*255);
      int g = (int) (Math.random()*255);
      int b = (int) (Math.random()*255);
      Color random = new Color(r, g, b);
      
      graphics.setColor(Color.BLACK);
      graphics.fillRect(eraseX, eraseY, SIZE, SIZE); //draws rect to erase previous circle
      graphics.setColor(random);
      graphics.fillOval(eraseX+xV, eraseY+yV, SIZE, SIZE); //draws new circle based on xV and yV
   }
}