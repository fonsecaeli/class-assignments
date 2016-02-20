/* Eli F.
 * Section: B
 * Assignment 1
 * Description: This program prints a funny song about computer science to the console.
 * Class Name: Song
 * Version 1.0
 * 9/10/15
 */   
   
public class Song {

/**
* The main method prints a six verse long song to the console.
*
* @param args user input 
*/
   public static void main(String[] args) { 
      verse1();
      System.out.println();
      verse2();
      System.out.println();
      verse3();
      System.out.println();
      verse4();
      System.out.println();
      verse5();
      System.out.println();
      verse6();
      System.out.println();
      verse7();
   }
   
   /**
   * The method prints the first verse of the song to the console.
   */
   static void verse1() {
      System.out.println("I once wrote a program that wouldn't compile");
      twoLineMiniVerse();
   }
   
   /**
   * The method prints the second verse of the song to the console.
   */
   static void verse2() {
      System.out.println("My program did nothing");
      System.out.println("So I started typing.");
      threeLineMiniVerse();
   }
   
   /**
   * The method prints the third verse of the song to the console.
   */
   static void verse3() {
      System.out.println("\"Parse error,\" cried the compiler");
      System.out.println("Luckily I'm such a code baller.");
      fourLineMiniVerse();
   }
   
   /**
   * The method prints the fourth verse of the song to the console.
   */
   static void verse4() {  
      System.out.println("Now the compiler wanted an identifier");
      System.out.println("And I thought the situation was getting dire.");
      fiveLineMiniVerse();
   }
   
   /**
   * The method prints the fifth verse of the song to the console.
   */
   static void verse5() { 
      System.out.println("Java complained it expected an enum");
      System.out.println("Boy, these computers really are dumb!");
      sixLineMiniVerse();
   }
   
   /**
   * The method prints the sixth verse of the song to the console.
   */
   static void verse6() {
      System.out.println("Adding more methods is clearly the answer,");
      System.out.println("But then I got a run time error!");
      sixLineMiniVerse();
   }  

   /**
   * The method prints the seventh verse of the song to the console.
   */
   static void verse7() {
      System.out.println("Too much code, to little time,");
      System.out.println("I knew I would never finish the assignment in time :(");
      sixLineMiniVerse();
   }
   
   /**
   * The method prints a string which is repeated in the last two verses 
   * of the song to the console.
   */
   static void sixLineMiniVerse() {
      System.out.println("I added a public class and called it Scum,");
      fiveLineMiniVerse();
   }  
   
   /**
   * The method prints a string which is repeated in the last three verses 
   * of the song to the console. 
   */
   static void fiveLineMiniVerse() {   
      System.out.println("I added a main method with its String[] args,");
      fourLineMiniVerse(); 
   }
   
   /**
   * The method prints a string which is repeated in the last four verses
   * of the song to the console.
   */      
   static void fourLineMiniVerse() {
      System.out.println("I added a backslash to escape the quotes,");
      threeLineMiniVerse();
   }
   
   /**
   * The method prints a string which is repeated in all 
   * but the first verse of the song to the console.
   */
   static void threeLineMiniVerse() {
      System.out.println("I added System.out.println(\"I <3 coding\"),"); 
      twoLineMiniVerse();
   }
   
   /**
   * The method prints the string which is repeated 
   * at the end of each line of the song to the console.  
   */
   static void twoLineMiniVerse() {   
      System.out.println("I don't know why it wouldn't compile,");
      System.out.println("My TA just smiled.");
   }
}

