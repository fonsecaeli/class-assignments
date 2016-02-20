//draws Levy's C curve using a recusive algorithm
//author: eli f

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public class Picture {  
   
   private static final int SIZE = 1000;
   private static int SEGMENT_LENGTH = SIZE/2;
   private static Point LOCATION = new Point(SIZE/3, SIZE/2);
   private static final String FILE_NAME = "LevyCCurve";
   private static final String FILE_EXTENSION = ".txt";
   private static DrawingPanelPlus panel = null;
   private static Graphics g = null; 
   
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);     
      //LOCATION = new Point(SIZE/2, SIZE/6);
      int level = 16;//Inputter.getNumber(input, "What level would you like? ", 1, 100);
      for(int ii = 0; ii < level; ii++) {
         SEGMENT_LENGTH *= 1/Math.sqrt(1.9); //so the curve will not experience growth as you up the level of recursion
      }
      String path = generate(level);
      //PrintStream fileWriter = Inputter.getFileWriter(FILE_NAME+FILE_EXTENSION); 
      //fileWriter.print(path);
      //System.out.println("Drawing Curve...");
      panel = new DrawingPanelPlus();
      panel.setCanvasSize(SIZE, SIZE);
      panel.setYscale(0, SIZE);
      panel.setXscale(0, SIZE);

      //g = panel.getGraphics();
      draw(path);  
      panel.setYscale(-SIZE/2, SIZE/2);
      panel.setXscale(-SIZE/2, SIZE/2); 
      panel.setPenRadius(.001);
      plot(panel);   
   }
   
   public static boolean again(Scanner input) {
      System.out.print("Would you like to draw anything else? ");
      String answer = input.next();
      return answer.startsWith("y") || answer.startsWith("Y");
   }
   
   public static String generate(int level) {
      if(level == 1) {
         return "F";
      }
      String nextLevel = generate(level-1);
      return nextLevel.replaceAll("F", "LFRRFL");
   }
   
   public static void draw(String path) {
      int theta = 0;
      int turnAngle = 45;
      for(int ii = 0; ii < path.length(); ii++) {
         if(path.charAt(ii) == 'L') {
            theta -= turnAngle;
            if(theta < 0) {
               theta += 360;
            } 
         }
         else if(path.charAt(ii) == 'R') {
            theta += 45;
            if(theta > 315) {
               theta -= 360;
            }
         }
         else if(path.charAt(ii) == 'F'){
            drawSegment(theta);
            //panel.sleep(1);
         }
      }
   }
   
   public static void drawSegment(int theta) {
      Point next = null;  //will be assigned a value no matter what in the switch statement
      switch(theta) {
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
      //g.setColor(Color.RED);
      panel.line(LOCATION.x, LOCATION.y, next.x, next.y);
      LOCATION = next;     
   }
   
   public static void plot(DrawingPanelPlus panel) {
      for(double ii = -10; ii < 10; ii = ii + .001) {
         double y = ((double)SIZE/4)*integrate(0, ii, 1000, true)-120;
         double x = ((double)SIZE/4)*integrate(0, ii, 1000, false)-160;
         panel.filledCircle(-x, -y, 1);
      }
   }
   
   //numericaly calculates the integral of a function using trapazoid riemine sum method
   public static double integrate(double a, double b, int N, boolean c) {
      double h = (b - a) / N;
      double sum;
      if(c) {              
         sum = 0.5 * (c(a) + c(b));  
      }
      else {
         sum = 0.5 * (s(a) + s(b));
      } 
      for (int i = 1; i < N; i++) {
         double x = a + h * i;
         if(c) {
            sum = sum + c(x);
         }
         else {
            sum = sum + s(x);
         }
      }
      return sum * h;
   }
   
   public static double c(double x) {
      return Math.cos(x*x);
   }
   
   public static double s(double x) {
      return Math.sin(x*x);
   }
}
   