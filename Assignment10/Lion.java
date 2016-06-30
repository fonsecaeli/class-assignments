//Eli F.
//Section C
//Assignment 10
//Description: lion critter used to decimate other lions
//Version 1.0
//Class Name: Lion
//2/22/2016

import java.awt.*;
import java.util.*;

public class Lion extends Critter {

   //instance variables
   private String[] neighbors; //in order NORTH, SOUTH, EAST, WEST
   private boolean shouldCluster;
   private boolean hasMated;
   private String opponent;
   private String lostToo;
   private Random rand;
   private Attack attemptAgainstLion;
   private boolean justFoughtLion;
   private boolean wasSleeping;
   private boolean isMating;
   private boolean movedXLast;
   private Direction direction;
   private int steps;
   private int noMatedCounter;

   /**
    * if the strategy to use against other lions has been determined
    */
   private static boolean lionStratDetermined;

   /**
    * the strategy/attack to use against other lions
    */
   private static Attack lionStrat;

   /**
    * the point where all the lions try to gather at to mate 
    */
   private static Point clusterPoint;

   /**
    * the max number of lions that will be allowed to die before 
    * a new strategy is employed against a opponent that the lion should be beating
    * basically assuming that by this time most of the normal critters have been killed off
    */
   private static final int MAX_DEAD_PERMITTED = 13;

   /** 
    * max steps a lion is allowed to remain waiting for a mate to come to center
    * of map.  After this number has been exceeded the lion goes out into the world
    */
   private static final int MAX_STEPS_NOT_MATED = 200;

   /**
    * keeps track of how many lions have died overall in this simulation
    */
   private static int dead;

   /**
    * constructs a new lion 
    */
   public Lion() {
      this.clusterPoint = new Point((getWidth()/2), (getHeight()/2));
      dead = 0;
      this.isMating = false;
      this.wasSleeping = false;
      this.rand = new Random();
      this.neighbors = new String[Direction.values().length-1]; //then fill it up with the neighbors getNeighbors 
      //only allow the lion to find 4 neighbors because it does not work with diagonals :(
      updateNeighbors();
      this.shouldCluster = true;
      this.hasMated = false;
   
   }

   /**
    * method called when mating ends
    * changes the internal conditions of the lion
    */
   @Override 
   public void mateEnd() {
      this.isMating = false;
      this.hasMated = true;
      this.shouldCluster = false;
   }

   /**
    * called when the lion mates
    * sets value of isMating to true
    */
   @Override 
   public void mate() {
      this.isMating = true;
   }

   /** 
   * toString method for the lion, returns a 0 to try to trick other lions
   * this seemed like the easiest case to possibly slip through other peoples lions 
   * undetected 
   *
   * @return returns an "0"
   */
   @Override 
   public String toString() {
      return "0";
   }

   /**
    * decides the color the lion will have on the screen
    * returns red because lions are red!
    * 
    * @return the color of the lion 
    */
   @Override 
   public Color getColor() {
      return Color.RED;
   }

   /**
    * this lion is always hungry, seems like the best way to get points is to eat
    * even though you get a sleeping penalty, food is the only unlimited source of 
    * points in the game
    * 
    * @return the lion is always hungry
    */
   @Override 
   public boolean eat() {
      return true;
   }

   /**
    * clusters the lions in the center at first to maximize mating 
    * after lions have mated then they go out and random movement 
    * during the whole time if the lion sees food it will diverge from its current course
    * to eat. My lions are extremely hungry beasts. 
    * 
    * @return the direction to move next
    */
   @Override 
   public Direction getMove() {
      //this.clusterPoint = new Point((getWidth()/2), (getHeight()/2));

      updateNeighbors(); 
      Direction nearFood = foodAround();
      if(shouldCluster && !hasMated && (noMatedCounter < MAX_STEPS_NOT_MATED)) {
         noMatedCounter++;
         if(!(nearFood == null)) { //eat food if its there
            return nearFood;
         }
         return goTo(this.clusterPoint);
      }
      else {
         if(!(nearFood == null)) {
            return nearFood;
         }
         int numDirections = Direction.values().length;
         if(steps %  numDirections == 0) {
            int random = rand.nextInt(numDirections-1); //because we don't include center
            direction = Direction.values()[random];
         }     
         steps++;
         return direction;
      }
   }

   /**
    * will return a Direction to get this Lion closer to a given point
    * 
    * @param p the target point
    * @return direction value that begins to minimize he distance between the lion and the point
    */
   public Direction goTo(Point p) {
      if(getX() < p.x && !movedXLast) {
         movedXLast = true;
         return Direction.EAST;
      }
      else if(getY() < p.y && movedXLast) {
         movedXLast = false;
         return Direction.SOUTH;
      }
      else if(getX() > p.x && !movedXLast) {
         movedXLast = true;
         return Direction.WEST;
      }
      else if(getY() > p.y && movedXLast) {
         movedXLast = false;
         return Direction.NORTH;
      }
      else {
         movedXLast = !movedXLast;
         return Direction.CENTER;
      }
   }

   /**
    * designed to beat all other critters, since we know their fighting strategies 
    * will use helper class or method to develop a strategy to beat other lions
    * attempts to change strategy if lion keeps loosing to something it shouldn't 
    * namely a lion hiding another another string 
    *
    * @param opponent string representation of opponent
    * @return the attack weapon 
    */
   @Override
   public Attack fight(String opponent) {
      this.opponent = opponent;
      boolean isHippo = false;
      int hunger = 0;
      try {
         hunger = Integer.parseInt(opponent);
         isHippo = true;
      } 
      catch(NumberFormatException e) {
         isHippo = false;
      }
      if(dead > MAX_DEAD_PERMITTED && opponent.equals(lostToo)) { //lostToo should only be set to something if it was the lions toString()
         //this way if my lions sustain heavy losses to something that is hiding as another critter i can try to throw random 
         //attacks at it instead of having a static response
         return randomAttackForOtherLion();
      }
      //to deal with Birds and Vultures Hippos Ants and Stones
      else if(opponent.equals("^") || opponent.equals("V") || opponent.equals(">") || opponent.equals("<")) {
         return Attack.SCRATCH;
      }
      else if(opponent.equals("%")) {
         return Attack.ROAR;
      }
      else if(opponent.equals("S")) {
         return Attack.POUNCE;
      }
      else if(isHippo) {
         if(hunger > 0) {
            return Attack.ROAR;
         }  
         else {
            return Attack.SCRATCH;
         }
      }
      else if(!lionStratDetermined) {
         return randomAttackForOtherLion();
      }
      else {
         return lionStrat; 
      }
      //if ant dies to a string it should have beaten it will log the string to a private field in all
      //lions they will adjust there fight strategy accordingly
      //should deal with lions that are pretending to be something else 
      //unless they are constantly changing there toString() method
   }

   /**
    * generates a random attack to be used against an opposing lion
    * @return random attack
    */
   public Attack randomAttackForOtherLion() {
      int pick = rand.nextInt(Attack.values().length);
      Attack weapon = Attack.values()[pick];
      attemptAgainstLion = weapon;
      justFoughtLion = true;
      return weapon;
   }

   /**
    * method called when lion wins a fight
    * will adapt to a enemy lion using a static attack 
    */
   @Override 
   public void win() {
      if(justFoughtLion) {
         lionStratDetermined = true;
         lionStrat = this.attemptAgainstLion;
         lionStratDetermined = true;
      }
   }

   /**
    * method called when lion loses a battle 
    * will update the strategy the lion was using against the opponent it 
    * lost too.  Will also determine if this lion just lost to another lion 
    * this is based of the fact that the lion should only lose to normal critters
    * when it is sleeping or mating
    */
   @Override
   public void lose() {
      dead++;
      if(justFoughtLion && lionStratDetermined) {
         lionStratDetermined = false;
      }
      if(!wasSleeping && !isMating) {
         this.lostToo = opponent;
      }
   }
   
   /**
    * updates the array containing the list of this lions neighbors
    * will use the getNeighbors(Direction n) method provided
    */
   private void updateNeighbors() {
      for(int ii = 0; ii < Direction.values().length-1; ii++) { 
         neighbors[ii] = getNeighbor(Direction.values()[ii]);
      }
   }

   /** 
    * determines if there is any food around the lion 
    * if there is it returns the direction the lion should go to eat that food 
    *
    * @return  the direction the lion should go to eat or null 
    * to represent that there is no food around
    */
   private Direction foodAround() {
      for(int ii = 0; ii < neighbors.length; ii++) {
            if(neighbors[ii].equals(".")) {
               return Direction.values()[ii];
            }
      }  
      return null;   
   }
}