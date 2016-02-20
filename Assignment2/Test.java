/* Eli F.
* Section: B
* Assignment 2
* Description: This program prints a spaceneedle which size can be varied to the console
* Class name: SpaceNeedle
* Version 1.0
* 9/21/15
*/

public class Test {
   /**
   * class constant SIZE determines size of spaceneedle
   * changing SIZE will scale the spaceneedle
   */

   public static final int SIZE = 4;
   
   /**
   * This method prints a spaceneedle different symbols
   *
   * @param args user input
   */
   public static void main(String[] args) {
      spire();
      domeMidSection();
      domeBottom();
      spire();
      body();
      domeMidSection();
   }
   
   /**
   * prints the spire
   */
   public static void spire() {
      for(int ii = 0; ii < SIZE; ii++) {
         printChar(SIZE*3," "); //prints spacces
         System.out.println("||");
         }
    }
   
   /**
   * prints the top of the dome
   */
   public static void domeMidSection() {
      int lineNumber = 1;
      //prints dome top
      for(int ii = 0; ii < SIZE; ii++) {
         printChar((SIZE*3-3)-(3*lineNumber-3)," "); //prints spaces 
         System.out.print("__" + "/"); 
         printChar(3*lineNumber-3,":"); //prints semicolons 
         System.out.print("||");
         printChar(3*lineNumber-3,":"); //prints semicolons
         System.out.println("\\" + "__");
         lineNumber++; //updates line number
      }
         //prints the line of quotes
         System.out.print("|");
         printChar(SIZE*6,"\""); //prints quotations 
         System.out.println("|");
   }
   
   /**
   * prints bottom of top of needle
   */
   public static void domeBottom() {
      int lineNumber = 1;
      for(int ii = 0; ii < SIZE; ii++) {
         printChar((lineNumber-1)*2," "); //prints spaces
         System.out.print("\\_/");
         printChar((SIZE*3-2)-(2*lineNumber-2),"\\/"); //prints V's
         System.out.println("\\_/");
         lineNumber++; //updates line number
         } 
   }
   
   /**
   * prints body of needle
   */
   public static void body() {
      for(int i = 0; i < SIZE * SIZE; i++) {
         printChar(2 * SIZE + 1," "); //prints spaces
         System.out.print("|");
         printChar(SIZE - 2,"%"); //prints %s
         System.out.print("||");
         printChar( SIZE - 2,"%"); //prints %s
         System.out.println("|");
      }
   }

   /**
   * prints character a given number
   */
   public static void printChar(int numberOfTimes, String character) {
      for(int ii = 0; ii < numberOfTimes; ii++) {
         System.out.print(character);
      }
   }
}