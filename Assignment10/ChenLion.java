// Tyler Chan
// This program defines the Lion class, which is an extension to the Critter class
// Lion.java
// v1.0 
// February 26, 2016

   /**
   *   This imports java.awt to allow for use of Color and Color functions
   */  
   
import java.awt.*; 

public class ChenLion extends Critter
{

   /**
   *   This static variable keeps track of the total number of lions created. 
   */  

private static int numberOfLions; 

   /**
   *   This variable says whether or not the lion moves horizontally or vertically.
   */  


private boolean horizontal;

   /**
   *   This is a constructor method for the Lion class. 
   */  

   public ChenLion()
   {
   numberOfLions++;
   if(numberOfLions%2 == 0)
   {
   this.horizontal = true;
   }
   else
   {
   this.horizontal = false;
   }
   }

   /**
   *   This accessor method returns the color of the Lion.
   *   @return Color - This method returns the color of the Lion.
   */  

   public Color getColor()
   {
      return Color.GREEN;
   }

   /**
   *   This accessor method returns a boolean saying wheter or not the Lion will eat when it encounters food. 
   *   @return boolean - This method returns a boolean saying whether or not the Lion will eat when it encounters food. 
   */  

   public boolean eat()
   {
      return true;
   }

   /**
   *   This method takes in a string to know what opponent the Lion is fighting. It then returns the attack type of the Lion.
   *   @param opponent - This method takes in a string to know what opponent it is fighting.
   *   @return Attack - This method returns an attack type to say which attack the Lion is going to use.
   */  

   public Attack fight(String opponent)
   {
      if(opponent.equals("0") || opponent.equals("^") || opponent.equals("<") || opponent.equals(">") || opponent.equals("V")) 
      {
         return Attack.SCRATCH;
      }
      else if(opponent.equals("S"))
      {
      return Attack.POUNCE;
      }
      else
      { 
         return Attack.ROAR;
      }
   }

   /**
   *   This accessor method returns a direction to say which way the Lion is going to move. 
   *   @return Direction - This method returns a direction to say which way the Lion is going to move.
   */  

   public Direction getMove()
   { 
      if(horizontal)
      {
      return Direction.EAST;
      }
      else
      {
      return Direction.NORTH;
      }
   }
   
   /**
   *   This method returns the string representation of the Lion.
   *   @return String - This method returns the string representation of the Lion.
   */  
   
   public String toString()
   {
      return "V"; //I chose to make my symbol V, so that when I attack other lions will think I am a bird/vulture and will use scratch on me.
         }

}