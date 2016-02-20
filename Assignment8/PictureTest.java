//Eli F.
//Section B
//Assignment 8
//Description: Draws 'CS' to the screen with a Levy C curve and a Euler Spiral as C and S.
//represents Computer Science but I used a Levy C curve to as the C and a Euler Spiral
//to represent the 'C' and 'S' respectively.  Basically I have two really cool very 
//different algorithms that were used to represent Computer Science in a literal sense. 
//Levy C curve is recursive, while the Euler spiral is created by iterating two functions 
//through a set of numbers and then plotting the result.  To me this represents what computer
//science is all about.  Creating or understanding cool algorithms to solve problems. 
//The problem in this case was drawing 'CS' in a super awesome way.  
//**Note: this file cannot be called final.java because that would not follow java naming 
//conventions class name must be capitalized.  
//Version 1.0
//Class Name: Picture.java
//1/11/16

import java.awt.*;
import java.util.*;

public class Picture {  
  
   /**
   *Location is the initial point of the Levy C curve
   *I have used 260, 280 as the starting location to put the C 
   *next to the S and also balance its position in terms of y coordinates
   */
   private static Point LOCATION = new Point(260, 280);
   
   /**
   * The entry point to the program, where all the magic happens
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);     
      int level = 16;//the level of recursion I will use to generate Levy C curve   
              
      //setting up panel for Levy C curve to be drawn
      int size = 800;
      DrawingPanel panel = new DrawingPanel(size, size);
      Graphics g = panel.getGraphics();
      
      //calculating and drawing Levy C curve
      String path = generate(level);
      drawLevyC(path, g);  
           
      //plotting the points on Euler's spiral
      plotEulerSpiral(g);   
   }
   
   /**
   * recursive algorithm generates the Levy C curve using an L-System
   * generates a String, a list of instruction, to draw the curve
   *
   * @param level the level of recursion that is called for
   * @return the String representing instructions to draw the Levy C curve
   */
   public static String generate(int level) {
      if(level == 1) {
         return "F";
      }
      String nextLevel = generate(level-1);
      return nextLevel.replaceAll("F", "LFRRFL");
   }
   
   /**
   * draws the Levy C curve based off a String which is a list of instructions
   *
   *
   * @param path the String that represents the instructions
   * @param g the graphics object used to draw the curve
   */
   public static void drawLevyC(String path, Graphics g) {
      int theta = 0;
      int turnAngle = 45;
      int maxAngle = 360;
      for(int ii = 0; ii < path.length(); ii++) {
         if(path.charAt(ii) == 'L') {
            theta -= turnAngle;
            if(theta < 0) {
               theta += maxAngle;
            } 
         }
         else if(path.charAt(ii) == 'R') {
            theta += turnAngle;
            if(theta == maxAngle) {
               theta -= maxAngle;
            }
         }
         else if(path.charAt(ii) == 'F'){
            drawSegment(theta, g);
            //panel.sleep(1); //would have animated the curve
            //but it would have taken way too long!
         }
      }
   }
   
   /**
   * draws an individual segment of the Levy C curve
   * 
   * @param theta used to determine which direction to draw the segment in
   * @param g the Graphics object the C curve will be drawn with 
   */
   public static void drawSegment(int theta, Graphics g) {
      int segmentLength = 1;
      Point next = null;  //will be assigned a value no matter what in the switch statement
      switch(theta) { //switch statement seemed easiest here
         case 0: next = new Point(LOCATION.x, LOCATION.y+segmentLength);
            break;
         case 45: next = new Point(LOCATION.x+segmentLength, LOCATION.y+segmentLength);
            break;
         case 90: next = new Point(LOCATION.x+segmentLength, LOCATION.y);
            break;
         case 135: next = new Point(LOCATION.x+segmentLength, LOCATION.y-segmentLength);
            break;
         case 180: next = new Point(LOCATION.x, LOCATION.y-segmentLength);
            break;
         case 225: next = new Point(LOCATION.x-segmentLength, LOCATION.y-segmentLength);
            break;
         case 270: next = new Point(LOCATION.x-segmentLength, LOCATION.y);
            break;
         case 315: next = new Point(LOCATION.x-segmentLength, LOCATION.y+segmentLength);
            break;
      }
      g.drawLine(LOCATION.x, LOCATION.y, next.x, next.y);
      LOCATION = next;     
   }
   
   /**
   * plots a version of the Euler spiral, needed to change its orientation to make it look 
   * like an 'S'
   * Explanation of Curve: Its curvature begins with zero at the straight section (the tangent)
   * and increases linearly with its curve length.  Curve has been reflected over the x axis as  
   * well as the y axis to make it look like an S
   *
   * @param g the graphics object used to draw the spiral
   */
   public static void plotEulerSpiral(Graphics g) {
      //constants to position the S so it is next to the C
      int yShift = 420; 
      int xShift = 565;
      int scaleFactor = 240; //to size the curve properly
      //iterating from -10 to 10 with an incriment of .001 
      //provides enough points to make a Euler Spiral that looks good
      for(double ii = -10; ii < 10; ii = ii + .001){
         int y = (int)(scaleFactor*integrate(0, ii, 1000, true)+yShift);
         int x = (int)(scaleFactor*integrate(0, ii, 1000, false)-xShift);
         g.fillOval(-x, y, 1, 1);
      }
   }
   
   /**
   * numerically calculates the integral of a function using a Riemann sum
   * the Euler curve is based of to the two functions integral of cosx^2 and integral of sinx^2
   * so it seemed prudent to create a function to do a numerical approximation
   * of an integral.  
   * increasing N will yield higher precision
   *
   * @param a lower limit of integration
   * @param b upper limit of integration
   * @param N the number of rects used to approximate
   * @param c boolean representing which function we will be integrated 
   * since the spiral is based of two functions s(x) and c(x) this allows 
   * this method to be used for both approximations 
   * @return the value of integrating over the interval [a, b]
   */
   public static double integrate(double a, double b, int N, boolean c) {
      double h = (b-a)/N;
      double sum = 0;
      for (int ii = 1; ii < N; ii++) {
         double x = a+h*ii;
         if(c) {
            sum += c(x)*h;
         }
         else {
            sum += s(x)*h;
         }
      }
      return sum;
   }
   
   /**
   * represents c(x) = cosx^2
   *
   * @param x the x value to be used
   * @return the result of the function on a given input, the y coordinate
   */
   public static double c(double x) {
      return Math.cos(x*x);
   }
   
   /**
   * represents c(x) = sinx^2
   *
   * @param x the x value to be used
   * @return the result of the function on a given input, the y coordinate
   */
   public static double s(double x) {
      return Math.sin(x*x);
   }
}
   