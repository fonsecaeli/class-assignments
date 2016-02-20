public class PrintChar {
	public static void main(String[] args) {
		printChar(6," ");
	}

	public static void printChar(int numberOfTimes, String character) {
		for(int ii = 0; ii < numberOfTimes; ii++) {
			System.out.print(character);
		}
	}
}