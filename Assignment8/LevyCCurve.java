//draws Levy's C curve using a recusive algorithm
//author: eli f

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public class LevyCCurve {  
   
   private static final int SIZE = 1000;
   private static int SEGMENT_LENGTH = SIZE/2;
   private static Point LOCATION = new Point(SIZE/2, SIZE/2);
   private static final String FILE_NAME = "LevyCCurve";
   private static final String FILE_EXTENSION = ".txt";
   private static DrawingPanel panel = null;
   private static Graphics g = null; 
   
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);     
      LOCATION = new Point(SIZE/2, SIZE/6);
      int level = Inputter.getNumber(input, "What level would you like? ", 1, 100);
      for(int ii = 0; ii < level; ii++) {
         SEGMENT_LENGTH *= 1/Math.sqrt(1.9); //so the curve will not experience growth as you up the level of recursion
      }
      String path = generate(level);
      PrintStream fileWriter = Inputter.getFileWriter(FILE_NAME+FILE_EXTENSION); 
      fileWriter.print(path);
      System.out.println("Drawing Curve...");
      panel = new DrawingPanel(SIZE, SIZE);
      g = panel.getGraphics();
      draw();      
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
   
   public static void draw() {
      File f = new File(FILE_NAME+FILE_EXTENSION);
      Scanner fileScan = null;
      try {
      fileScan = new Scanner(f);
      }
      catch(FileNotFoundException e) {
         System.out.println("You Messed up");
      }
      String path = fileScan.next();
      int theta = 0;
      for(int ii = 0; ii < path.length(); ii++) {
         if(path.charAt(ii) == 'L') {
            theta -= 45;
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
            panel.sleep(1);
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
      g.setColor(Color.RED);
      g.drawLine(LOCATION.x, LOCATION.y, next.x, next.y);
      LOCATION = next;     
   }
   
   public static Color randColor() {
      int r = (int) (Math.random()*255);
      int g = (int) (Math.random()*255);
      int b = (int) (Math.random()*255);
      return new Color(r, g, b);
   }
}
   