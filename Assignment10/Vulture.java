//Eli F.
//Section C
//Assignment 10
//Description: represents a specific type of bird, a vulture
//Version 1.0
//Class Name: Vulture 
//2/22/2016

import java.awt.*;

public class Vulture extends Bird {

	private boolean isHungry;

	/**
	 * constructor for a Vulture
	 */
	public Vulture() {
		this.isHungry = true;
	}

	/**
	 * determines the color of the Vulture
	 * vultures are always black
	 * 
	 * @return color of vulture 
	 */
	@Override 
	public Color getColor() {
		return Color.BLACK;
	}

	/**
	 * determines the eating strategy of the vulture
	 * initially hungry, if it eats then i becomes not Hungry 
	 * if it fights then it becomes hungry again
	 * 
	 * @return if the vulture is hungry or not
	 */
	@Override
	public boolean eat() {
		if(isHungry) {
			isHungry = false;
			return true;
		}
		else {
			return false;
		}
	} 

	/**
	 * determines the fighting strategy for the vulture
	 * 
	 * @param  opponent opponents toString
	 * @return the Vulture's attack
	 */
	@Override
	public Attack fight(String opponent) {
		if(opponent.equals("%")) {
			isHungry = true;
			return Attack.ROAR;
		}
		else {
			isHungry = true;
			return Attack.POUNCE;
		}
	}
}