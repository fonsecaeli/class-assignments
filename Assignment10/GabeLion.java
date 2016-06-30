//Gabe W
//C
//Assignment 10
//CS3
//Lion
//2/24/16

import java.util.*;
import java.awt.*;


public class GabeLion extends Critter
{
   private boolean nearBird;

   /*
   * The Lion constructor details theinitial variables of the Lion object.
   */
   public GabeLion()
   {
      this.nearBird = false;
   }
   
   @Override
   /*
   * The eat method determines whether the Lion performs an eat action.
   *@return The returned boolean represents whether or not the Lion eats.
   */
   public boolean eat() {
      //always eat 
		return true;
	}

   /*
   * The fight method returns a enum of the Attack data type.
   *@param opponent This opponents String is used to direct the Lion's attack.
   *@return The returned Attack is a new data type that represents which attack the critter uses.
   */
	public Attack fight(String opponent) {
      if (opponent.equals("%")) {
         return Attack.ROAR;
      } else if (opponent.equals(">")  || opponent.equals("<") || opponent.equals("^") || opponent.equals("V"))  {
         return Attack.POUNCE;
      }  
      Scanner scan = new Scanner(opponent);
      if (scan.hasNextInt()) {
         if (scan.nextInt() == 0) {
            return Attack.SCRATCH;
         }
         return Attack.ROAR;
      }
      return Attack.SCRATCH;
	}

   @Override
   /*
   * The getColor method returns a the color the critter displays.
   *@return The returned Color determines the color of the returned to string value.
   */
	public Color getColor() {
		return Color.RED;
	}

   /*
   * The getMove method direction the critter moves represented with the Direction data type.
   *@return The returned Direction is a new data type that represents which way the critter moves.
   */
	public Direction getMove() {
      /*go north until column clear, move once right and repeat.
      if getNeighbor in the north is a stone    
         return West
      else 
         return North
      
      */
      return Direction.NORTH;
	}
   
   public void scanForBird() {
      /*check get neighbor east is bird 
      check get neighbor south is bird
      check get neighbor north is bird
      check get neighbor west is bird
      if any true 
         set nearBird to true
      */
   }

   @Override
   /*
   * The toString method is overriden to return a character that is displayed for the critter.
   *@return The returned String a character that is displayed for the critter.
   */
	public String toString() {
      /*
      if nearbird is true
	      return %
      otherwise
         return S
      */
      return "%";
	}
}