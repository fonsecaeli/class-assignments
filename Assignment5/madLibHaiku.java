//Eli F.
//Section B
//Assignment #5 challenge
//Description: allow user to make a custom haiku (kinda like a madLib haiku)
//Version 1.0
//Class Name: madLibHaiku
//10/29/2015

import java.util.*;

public class madLibHaiku {

	public static void getHaiku(Scanner input) {
		String noun1 = getWord(input, "noun", 2);
		String noun2 = getWord(input, "noun", 1);
		String noun3 = getWord(input, "noun", 2);
		String noun4 = getWord(input, "noun", 1);
		String month = getWord(input, "month", 2);
		String adj1 = getWord(input, "adjective", 2);
		String adj2 = getWord(input, "adjective", 2);

		System.out.println(noun1 + " on the " + noun2);
		System.out.println("A " + adj1 + " " + noun3 + " " + adj2);
		System.out.println("In the " + month + " " + noun4 );
   }
	public static String getWord(Scanner input, String wordType, int sylableCount) {
		System.out.print("Please enter a " + sylableCount + " syllable " + wordType + ": ");
		return input.next();
	}
}