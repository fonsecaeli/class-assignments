//Eli F.
//Section C
//Assignment 10
//Description: represents a hippo 
//Version 1.0
//Class Name: Hippo 
//2/22/2016

import java.awt.*;
import java.util.*;

public class Hippo extends Critter {

	private int hunger;
	private Random rand;
	private Direction direction;
	private int steps;

	/**
	 * constructs a hippo 
	 * 
	 * @param hunger how hungry the hippo is
	 */
	public Hippo(int hunger) {
		this.rand = new Random();
		this.direction = Direction.values()[rand.nextInt(Direction.values().length-1)];
		this.hunger = hunger;
		this.steps = 0; //so we are indexed properly to use % in getMove
	}

	/**
	 * determines the color of the hippo
	 * color changes based off of hunger
	 * 
	 * @return the color of the hippo
	 */
	@Override 
	public Color getColor() {
		if(hunger > 0) {
			return Color.GRAY;
		}
		else {
			return Color.WHITE;
		}
	}

	/**
	 * eating strategy of hippo
	 * based of the hunger of the hippo
	 * 
	 * @return returns a boolean indicating whether the hippo is hungry or not
	 */
	@Override 
	public boolean eat() {
		if(hunger > 0) {
			hunger--;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * determines the fighting strategy of the hippo
	 * 
	 * @param opponents the toString of the opponent
	 * @return the attack strategy of this hippo  
	 */
	@Override 
	public Attack fight(String opponents) {
		if(hunger > 0) {
			return Attack.SCRATCH;
		}
		else {
			return Attack.POUNCE;
		}
	}

	/**
	 * determines the moving behavior of the hippo
	 * 
	 * @return the direction the hippo will move next
	 */
	@Override 
	public Direction getMove() {
		int numDirections = Direction.values().length;
		steps++;
		if(steps %  numDirections == 0) {
			direction = Direction.values()[rand.nextInt(numDirections-1)];
		}		
		return direction;
	}

	/**
	 * returns the string to represent the hippo
	 * 
	 * @return string representing the hippo
	 */
	@Override 
	public String toString() {
		return ""+hunger;
	}
}