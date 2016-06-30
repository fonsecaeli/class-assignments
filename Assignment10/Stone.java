import java.awt.*; // for Color

public class Stone extends Critter {

public Attack fight(String opponent) {
	return Attack.ROAR;
}

public Color getColor() {
	return Color.GRAY;
}

public String toString() {
	return "S";
}
}




