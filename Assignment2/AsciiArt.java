// Eli F.
// Section: B
// Challenge 1
// Description: This program prints a ascii art figure
// Class name: AsciiArt
// Version 1.0
// 9/25/15

public class AsciiArt {

	/**
	* class constant SIZE determines size of figure.  total number of lines is two times SIZE
	*/
	public static final int SIZE = 15;

	/**
	* prints a figure using symbols to the console  
	*
	* @param args user input 
	*/
	public static void main(String[] args) {
		printFigure();
	}

	/**
	* prints the figure to the console
	*/
	public static void printFigure() {
		for(int lineNumber = 0; lineNumber < SIZE; lineNumber++) {
			printChar(SIZE*2-2*lineNumber, '\\'); 	
			printChar(2*lineNumber, '<');
			printChar(2*lineNumber, '\\');
			printChar(SIZE*2-2*lineNumber, '<'); 	
			System.out.println();
		}
		for(int lineNumber = SIZE-1; lineNumber >= 0; lineNumber--) {
			printChar(SIZE*2-2*lineNumber, '>'); 	
			printChar(2*lineNumber, '\\');
			printChar(2*lineNumber, '>');
			printChar(SIZE*2-2*lineNumber, '\\'); 	
			System.out.println();
		}
	}

	/**
   * prints a character a given number of times
   * 
   * @param numberOfTimes integer that specifies the number of times you print the character 
   * @param character the char that will be printed a certian number of times
   */
   public static void printChar(int numberOfTimes, char character) { 
      for(int ii = 0; ii < numberOfTimes; ii++) {
         System.out.print(character);
      }
   }
}
