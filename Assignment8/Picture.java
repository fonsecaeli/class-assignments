//Eli F.
//Section B
//Assignment 8
//Description: Draws 'CS' to a the screen with a Levy C curve and a Euler Spiral as C and S.
//represents Computer Science but I used a Levy C curve to as the C and a Euler Spiral
//to represent the 'C' and 'S' respectively.  Bascally I have two really cool very 
//different algorithums that were used to represent Computer Science in a literal sense. 
//Levy C curve is recusive, while the Euler spiral is created by iterating two funcitons 
//through a set of numbers and then plotting the result.  To me this represents what computer
// science is all about.  Creating or understanding cool algorithums to solve problem. 
//The problem in this case was drawing 'CS' in a super awsome way.  
//**Note: this file cannot be called final.java because that would not follow java naming 
//conventions class name must be capitalized.  
//Version 1.0
//Class Name: Picture.java
//1/11/16

import java.awt.*;
import java.util.*;

public class Picture {  
   
   private static final int SIZE = 1000;
   private static int SEGMENT_LENGTH = SIZE/2;
   private static Point LOCATION = new Point(SIZE/3, SIZE/2);
   private static DrawingPanelPlus panel = null;
   
   /**
   * The entry point to the program, where all the magic happens
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);     
      int level = 16;//the level of recusion I will use to generate Levy C curve
      for(int ii = 0; ii < level; ii++) {
         SEGMENT_LENGTH *= 1/Math.sqrt(1.9); //to make sure the Levy C curve is scaled appropriatly
      }
           
      //setting up panel for Levy C cuvre to be drawn
      panel = new DrawingPanelPlus();
      panel.setCanvasSize(SIZE, SIZE);
      panel.setYscale(0, SIZE);
      panel.setXscale(0, SIZE);
      panel.clear(Color.RED); //colors are awsome, but they dont mean anything  
      panel.setPenColor(Color.GREEN);  
      
      //calcualting and drawing Levy C curve
      String path = generate(level);
      draw(path);  
      
      //set up panel so that Euler spiral can be drawn
      panel.setYscale(-SIZE/2, SIZE/2);
      panel.setXscale(-SIZE/2, SIZE/2); 
      panel.setPenRadius(.001);
      panel.setPenColor(Color.BLUE);  
      
      //plotting the points on Eurlers spiral
      plot(panel);   
   }
   
   /**
   * recusivly generates the Levy C curve using an L-System
   * generates a String, a list of instruction, to draw the curve
   *
   * @param level the level of recusion that is called for
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
   * @param path the String that represents the instrucitons
   */
   public static void draw(String path) {
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
            drawSegment(theta);
            //panel.sleep(1); //would have animated the curve
            //but it would have taken way too long!
         }
      }
   }
   
   /**
   * draws an individual segment of the Levy C curve
   * 
   * @param theta used to determine which direciton to draw the segment in
   */
   public static void drawSegment(int theta) {
      Point next = null;  //will be assigned a value no matter what in the switch statement
      switch(theta) { //switch statment seemed easiest here, but im not sure if it is proper
                      //convention to used these weird case numbers!
         case 0: next = new Point(LOCATION.x, LOCATION.y+SEGMENT_LENGTH);
            break;
         case 45: next = new Point(LOCATION.x+SEGMENT_LENGTH, LOCATION.y+SEGMENT_LENGTH);
            break;
         case 90: next = new Point(LOCATION.x+SEGMENT_LENGTH, LOCATION.y);
            break;
         case 135: next = new Point(LOCATION.x+SEGMENT_LENGTH, LOCATION.y-SEGMENT_LENGTH);
            break;
         case 180: next = new Point(LOCATION.x, LOCATION.y-SEGMENT_LENGTH);
            break;
         case 225: next = new Point(LOCATION.x-SEGMENT_LENGTH, LOCATION.y-SEGMENT_LENGTH);
            break;
         case 270: next = new Point(LOCATION.x-SEGMENT_LENGTH, LOCATION.y);
            break;
         case 315: next = new Point(LOCATION.x-SEGMENT_LENGTH, LOCATION.y+SEGMENT_LENGTH);
            break;
      }
      panel.line(LOCATION.x, LOCATION.y, next.x, next.y);
      LOCATION = next;     
   }
   
   /**
   * plots a version of the Euler spiral, needed to change its orientaition to make it look 
   * like an 'S'
   * Explination of Curve: Its curvature begins with zero at the straight section (the tangent)
   * and increases linearly with its curve length.  Curve has been reflected over the x axis as  
   * well as the y axis
   *
   * @param panel the panel that the spiral will be drawn on
   */
   public static void plot(DrawingPanelPlus panel) {
      for(double ii = -10; ii < 10; ii = ii + .001) {
         int yShift = 120; //constants to position the S so it is next to the C
         int xShift = 160;
         int y = (int)(((double)SIZE/4)*integrate(0, ii, 1000, true)-yShift);
         int x = (int)(((double)SIZE/4)*integrate(0, ii, 1000, false)-xShift);
         panel.filledCircle(-x, -y, 1);
      }
   }
   
   /**
   * numericaly calculates the integral of a function using a riemann sum
   * the Euler curve is based of to the two functions integral of cosx^2 and integral of sinx^2
   * so it seemed prudent to createa funtion to do a numerical approxiamtion
   * of an integral.  
   * Obviosuly increaing N will yeild higher precision
   *
   * @param a lower limit of integration
   * @param b upper limit of integration
   * @param N the number of traps used to approxiamte
   * @param c boolean represeting which function we will be integrated 
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
   * @return the result of the function the y coordinate
   */
   public static double c(double x) {
      return Math.cos(x*x);
   }
   
   /**
   * represents c(x) = sinx^2
   *
   * @param x the x value to be used
   * @return the result of the function the y coordinate
   */
   public static double s(double x) {
      return Math.sin(x*x);
   }
}
   