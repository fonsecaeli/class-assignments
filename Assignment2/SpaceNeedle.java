// Eli F.
// Section: B
// Assignment 2
// Description: This program prints a spaceneedle which size can be varied to the console
// Class name: SpaceNeedle
// Version 1.0
// 9/21/15

public class SpaceNeedle {

   /**
   * the class constant SIZE determines the size of spaceneedle.  
   */
   public static final int SIZE = 6;
   
   /**
   * the main method prints a spaceneedle to the console using different symbols
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
   * prints the spire to the console 
   */
   public static void spire() {
      //each iteration of for loop represents on line of output
      for(int lineNumber = 0; lineNumber < SIZE; lineNumber++) {
         int numSpaces = SIZE*3; //calulates number of spaces 

         printChar(numSpaces," "); //prints spaces
         System.out.println("||");
      }
   }
   
   /**
   * prints the mid section of the dome to the console 
   */
   public static void domeMidSection() {
      domeTop();
      domeFloor(); 
   }

   /**
   * prints the top of dome to the console 
   */
   public static void domeTop() {
      //each iteration of for loop represents one line of output
      for(int lineNumber = 0; lineNumber < SIZE; lineNumber++) {
         int numSpaces = (SIZE*3-3)-(3*lineNumber); //calculates number of spaces 
         int numSemiColons = 3*lineNumber; //calculates number of semicolons 

         printChar(numSpaces," "); //prints spaces 
         System.out.print("__" + "/"); 
         printChar(numSemiColons,":"); //prints semicolons 
         System.out.print("||");
         printChar(numSemiColons,":"); //prints semicolons
         System.out.println("\\" + "__");
      }
   }

   /**
   * prints the dome floor to the console
   */ 
   public static void domeFloor() {
      int numQuotes = SIZE*6; //calcualtes number of quotes 

      System.out.print("|");
      printChar(numQuotes,"\""); //prints quotations 
      System.out.println("|");
   }

   /**
   * prints bottom of the dome to the console  
   */
   public static void domeBottom() {
      //each iteration of for loop represents on line of output
      for(int lineNumber = 0; lineNumber < SIZE; lineNumber++) {
         int numSpaces = 2*lineNumber; //calcualtes number of spaces
         int numV = (SIZE*3-2)-(2*lineNumber); //calculates number of V's

         printChar(numSpaces," "); //prints spaces
         System.out.print("\\_/");
         printChar(numV,"\\/"); //prints V's
         System.out.println("\\_/");
      } 
   }
   
   /**
   * prints body of needle to the console
   */
   public static void body() {
      //each iteration of for loop represents one line of output
      for(int lineNumber = 0; lineNumber < SIZE*SIZE; lineNumber++) { 
         int numSpaces = 2*SIZE+1; //calculates number of spaces
         int numPercents = SIZE-2; //calculates number of percents

         printChar(numSpaces," "); //prints spaces
         System.out.print("|");
         printChar(numPercents,"%"); //prints %s
         System.out.print("||");
         printChar(numPercents,"%"); //prints %s
         System.out.println("|");
      }
   }

   /**
   * prints a string a given number of times to the console
   * 
   * @param numberOfTimes integer that specifies the number of times you print the string
   * @param symbol the string that will be printed a certain number of times based off of numberOfTimes
   */
   public static void printChar(int numberOfTimes, String symbol) { 
      for(int ii = 0; ii < numberOfTimes; ii++) {
         System.out.print(symbol);
      }
   }
}