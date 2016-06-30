//Eli F.
//Section C
//Assignment 10
//Description: represents an ant
//Version 1.0
//Class Name: Ant
//2/22/2016

import java.awt.*;

public class Ant extends Critter {

	private boolean southFirst;
	private int count;

	/**
	 * constructor for the ant critter
	 * @param walkSouth if the ant should start moving south
	 */
	public Ant(boolean walkSouth) {
		this.count = 0;
		this.southFirst = walkSouth;
	}

	/**
	 * determines the moving patter of the ant
	 * @return the direction for the ant to move next
	 */
	@Override 
	public Direction getMove() {
		Direction next = null;
		int alternationFactor = 2;
		if(southFirst && count % alternationFactor == 0) {
			next = Direction.SOUTH;
		}
		else if(count % alternationFactor == 0) {
			next = Direction.NORTH;
		}
		else {
			next = Direction.EAST;
		}
		count++;
		return next;
	}

	/**
	 * determines the color of the ant
	 * @return the color of the ant 
	 */
	@Override 
	public Color getColor() {
		return Color.RED;
	}

	/**
	 * determines the eating patter of the ant
	 * @return if the ant in hungry
	 */
	@Override 
	public boolean eat() {
		return true;
	}

	/**
	 * determines the fighting patter of the ant
	 * @param  opponent the toString() of the critter the ant is fighting
	 * @return the weapon the ant is using
	 */
	@Override 
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}

	/**
	 * returns the string to represent the ant
	 * @return string representing the ant
	 */
	@Override 
	public String toString() {
		return "%";
	}
}