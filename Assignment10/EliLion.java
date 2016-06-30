//Eli F.
//Section C
//Assignment 10
//Description: 
//Version 1.0
//Class Name: Lion
//2/22/2016

import java.awt.*;
import java.util.*;

public class EliLion extends Hippo {

	private String[] neighbors; //in order NORTH, SOUTH, EAST, WEST
	private static Point target;
	private boolean shouldCluster;
	private boolean hasMated;
	private String opponent;
	private String lostToo;
	private Random rand;
	private Attack attemptAgainstLion;
	private boolean justFoughtLion;
	private static boolean lionStratDetermined;
	private static Attack lionStrat;
	private boolean wasSleeping;
	private boolean isMating;

	/**
	 * constructor for Lion
	 * initializes the neighbors array
	 * sets initial target location 
	 * and stets the shouldCluster field to true
	 */
	public EliLion() {
		super(9);
		this.isMating = false;
		this.wasSleeping = false;
		this.rand = new Random();
		this.neighbors = new String[4]; //then fill it up with the neighbors getNeighbors 
		//only allow the lion to find 4 neighbors because it does not work with diagonals :(
		updateNeighbors();
		this.shouldCluster = true;
		this.hasMated = false;

	}

	@Override 
	public void mateEnd() {
		this.isMating = false;
		this.hasMated = true;
		this.shouldCluster = false;
	}

	@Override 
	public void mate() {
		this.isMating = true;
	}

	/** 
	* toStirng method for the lion, at the moments it returns an L 
	* but i am considering returning a " " so that other lions cannot 
	* detect me using the getNeighbors method provided
	*
	* @return returns an L
	*/
	@Override 
	public String toString() {
		return "0";
	}

	/**
	 * decides the color the lion will have on the screen
	 * @return the color of the lion 
	 */
	@Override 
	public Color getColor() {
		//haven't decided yet
		return Color.RED;
	}

	/**
	 * uses information from the location fields I have created to go after as much food as
	 * possible since food gets lions points.  However I think there is a movement penalty for 
	 * eating too much so i will try to avoid the penalty as well
	 * if there are not neighbors around that could kill the lion it will eat otherwise it will pass the food
	 * @return if the lion is hungry
	 */
	@Override 
	public boolean eat() {
		if(noNeighbors()) {
			return true;	
		}
		else {
			return false;
		}
	}

	/**
	 * will use a helper method or class to coordinate movement between lions
	 * will try to use a clustering strategy to increase mating opportunities while providing protection 
	 * from other critters.  If all lions are gathered in the center of the board matting should be continuous if 
	 * lions are switched around enough.  
	 * 
	 * @return the direction to move next
	 */
	@Override 
	public Direction getMove() {
		this.target = new Point((getWidth()/2), (getHeight()/2));
		updateNeighbors(); //updates the neighbors array
		//if mating opportunity then take it
		//try to eat as much food as possible
		//otherwise move to the center to join up with other Lions
		//the path the lion takes to get to the target location will depend on the lions neighbors 
		//if there is food lion will take it
		//lion will try to avoid other lions, and combat unless the shortest path includes a fight
		if(shouldCluster && !hasMated) {
			if(getX() < target.x) {
				return Direction.EAST;
			}
			else if(getY() < target.y) {
				return Direction.NORTH;
			}
			else if(getX() > target.x) {
				return Direction.WEST;
			}
			else if(getY() > target.y) {
				return Direction.SOUTH;
			}
		}
		else {
			return super.getMove();
		}
		return null;
	}

	/**
	 * will coordinate all of the lions so that they cluster in the center for optimal mating conditions
	 */
	private static void cluster(Point p) {
		target = p;
	}

	/**
	 * designed to beat all other critters, since we know their fighting strategies 
	 * will use helper class or method to develop a strategy to beat other lions 
	 * yet to think of a strategy to combat lions that pretend to be other critters 
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
		//to deal with Birds and Vultures Hippos and Ants 
		if(opponent.equals("^") || opponent.equals("V") || opponent.equals(">") || opponent.equals("<")) {
			return Attack.SCRATCH;
		}
		else if(opponent.equals("%")) {
			return Attack.ROAR;
		}
		else if(isHippo) {
			if(hunger > 0) {
				return Attack.ROAR;
			}	
			else {
				return Attack.SCRATCH;
			}
		}
		//if it is not any of the known critters throw a random attack at it
		//then log the result, if lion won always use this attack against other lions
		//this will be effective if the other lion is not just generating random attacks
		else if(!lionStratDetermined) {
			int pick = rand.nextInt(Attack.values().length);
			Attack weapon = Attack.values()[pick];
			attemptAgainstLion = weapon;
			justFoughtLion = true;
    		return weapon;
		}
		else if(opponent.equals("S")) {
			return Attack.POUNCE;
		}
		else {
			return lionStrat; 
		}
		//if ant dies to a string it should have beaten it will log the string to a private field in all
		//lions they will adjust there fight strategy accordingly
		//should deal with lions that are pretending to be something else 
		//unless they are constantly changing there toString() method
	}

	@Override 
	public void win() {
		if(justFoughtLion) {
			lionStratDetermined = true;
			setLionStrat(this.attemptAgainstLion);
			setLionStratDetermined(true);
		}
	}

	public static void setLionStrat(Attack weapon) {
		lionStrat = weapon;
	}

	public static void setLionStratDetermined(boolean b) {
		lionStratDetermined = b;
	}

	@Override 
	public void sleep() {
		this.wasSleeping = true;
	}

	@Override 
	public void wakeup() {
		this.wasSleeping = false;
	}

	@Override
	public void lose() {
		System.out.println("just lost to: "+opponent);
		System.out.println("was sleeping: "+wasSleeping);
		System.out.println("was mating: "+isMating);
		if(justFoughtLion && lionStratDetermined) {
			setLionStratDetermined(false);
		}
		this.lostToo = opponent;
	}
	
	/**
	 * updates the array containing the list of this lions neighbors
	 * will use the getNeighbors(Direction n) method provided
	 */
	private void updateNeighbors() {
		for(int ii = 0; ii < Direction.values().length-1; ii++) { //Direction.values().length-1 because we don't want the CENTER direction
			neighbors[ii] = getNeighbor(Direction.values()[ii]);
		}
	}

	/** 
	 * detects if there any critter neighbors around this lions
	 *
	 * @return if there are any neighbors
	 */
	private boolean noNeighbors() {
		int danger = 0;  //represents the number of enemy critter around
		for(int ii = 0; ii < neighbors.length; ii++) {
			if(!neighbors[ii].equals(".") || !neighbors[ii].equals(toString()) || !neighbors[ii].equals(" ")) {
				danger++;
			}
		}
		if(danger > 5) {
			return false;
		}
		return true;
	}
}