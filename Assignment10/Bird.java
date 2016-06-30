//Eli F.
//Section C
//Assignment 10
//Description: represents a bird 
//Version 1.0
//Class Name: Bird 
//2/22/2016

import java.awt.*;

public class Bird extends Critter {

	private Direction direction;
	private int counter;

	/**
	 * constructor for a Bird 
	 */
	public Bird() {
		this.counter = 1; //so that we are index properly for using %
		this.direction = Direction.NORTH;
	}

	/**
	 * determines the color of the Birds
	 * 
	 * @return the color of the Bird
	 */
	@Override 
	public Color getColor() {
		return Color.BLUE;
	}

	/**
	 * determines eating strategy for the Bird
	 * bird never eats 
	 * 
	 * @return false because bird never eat
	 */
	@Override 
	public boolean eat() {
		return false;
	}

	/**
	 * determines the fighting strategy of the Bird 
	 * 
	 * @param opponent toString of opponent
	 * @return the weapon the bird uses to attack
	 */
	@Override 
	public Attack fight(String opponent) {
		if(opponent.equals("%")) {
			return Attack.ROAR;
		}
		else {
			return Attack.POUNCE;
		}
	}

	/**
	 * determines the direction of the Bird 
	 * moves around in squares continual
	 * 
	 * @return the direction the bird will move next
	 */
	@Override 
	public Direction getMove() {
		Direction now = direction;
		int squareLength = 3;
		if(this.counter % squareLength == 0) {
			if(direction.equals(Direction.NORTH)) {
				direction = Direction.EAST; 
			}
			else if(direction.equals(Direction.EAST)) {
				direction = Direction.SOUTH;
			}
			else if(direction.equals(Direction.SOUTH)) {
				direction = Direction.WEST;
			}
			else if(direction.equals(Direction.WEST)) {
				direction = Direction.NORTH;
			}
		}
		this.counter++;
		return now;
	}

	/**
	 * determines the appearance of the Bird
	 * based of the direction the bird moved last 
	 * 
	 * @return string representing the Bird
	 */
	@Override 
	public String toString() {
		if(direction.equals(Direction.NORTH) || direction.equals(Direction.CENTER)) {
			return "^";
		}
		else if(direction.equals(Direction.EAST)) {
			return ">";
		}
		else if(direction.equals(Direction.SOUTH)) {
			return "V";
		}
		else {
			return "<";
		}

	}
}