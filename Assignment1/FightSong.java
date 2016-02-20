/* Eli Fonseca
 * Section: B
 * BJP 3 Exercise 1.12
 * Description: This program prints a song called fight song to the console.
 * Class Name: FightSong
 * Version 1.0
 * 9/9/15
 */

public class FightSong
{
  /**
  * The main method prints the fight song to the console. 
  * The method also uses println to print blank lines in-between verses of the song.
  * 
  * @param args user input   
  */
   public static void main(String[] args)
   {
      goTeam();
      System.out.println();
      bigVerse();
      System.out.println();
      bigVerse();
      System.out.println();
      goTeam();
   }
   
   /**
   * The method prints the strings which make up the big verse in the fight song to the console.
   */     
      static void bigVerse()
      {
         goTeam();
         bestInWest();
         goTeam();
      }
      
   /**
   * The method prints the strings which make up the small verse in the song.  
   */  
      static void goTeam()
      {
         System.out.println("Go, team, go!");
         System.out.println("You can do it.");
      }
      
   /**
   * The method prints the strings which make up a repeated line within the big verse of the song.
   */  
      static void bestInWest()
      {
         System.out.println("You're the best,");
         System.out.println("In the West.");
      }
}