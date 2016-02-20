//Eli F.
//Section B
//Assignment #5 milestone 1
//Description: desgin doc for guessingame.java
//Version 1.0
//Class Name: Design
//10/29/2015

import java.util.*;

public class Design {

	/**
	* The upper bound of the random number the user will guess
	*/
	public static final int MAX_NUMBER = 100;

	/**
	* Total games the user has played
	*/
	public static int TOTAL_GAMES = 0;

	/**
	* Total guesses the user has guessed
	*/
	public static int TOTAL_GUESSES = 0; 

	/**
	* Lets the user play where they try to guess a random number given hints
	*
	*
	* @param args user input from the console
	*/
	public static void main(String[] args) {
      //make random object 
      //make scanner object 
      //haiku();
      //int best = playGames(random, input);
      //stats(TOTAL_GAMES, TOTAL_GUESSES, best);	 
	}

	/**
	* Prints a haiku to explain the program
	*/
	public static void haiku() {
	}

	/**
	* Plays through the game as many times as the user would like
	* also updates statistics for game session
	* 
	* @param random Random object used to generate random number 
	* @param input Scanner object used to get input from the user 
	* @return the number of guesses in the users best game 
	*/
	public static int playGames(Random random, Scanner input) {
   return 0;
	}

	/**
	* Plays one time through the guessing game
	*
	* @param answer the random number that is trying to be guessed!
	* @param input Scanner object used to get input from the user 
	* @return the number of guesses the used guessed in the game
	*/
	public static int oneGame(Random random, Scanner input) {
   return 0;
	}

	/**
	* Displays the statistics of the game session 
	*
	* @param totalGames total number of games the user played 
	* @param totalGuesses total number of guessses the user had
	* @param best the number of guesses the user had in the best game 
	*/
	public static void stats(int totalGames, int totalGuesses, int best) {
	}

	/**
	* Tests to see if the user would like to play the game again
	*
	* @param input Scanner object used to ask user if they would like to play again
	* @retrun boolean value that indicates whether or not the user wants to play again
	*/
	public static boolean again(Scanner input) {
   return true;
	}
}

/* Test plan
When the users guess is:    What happens:
56 (within range)           Shows a hint for what the correct number is and then reprompts user 
-1 (below the min)          Error message will say input is out of the range, user will be reprompted
101 (above the max)         Error message will say input is out of the range, user will be reprompted
4.3 (double instead of int) Error message will say that an int must be entered and will then reprompt
5 (correct number!)		    Game will end and user will be prompted to see if they want to play again
ksdjfsd (non-number input)  An error message will say input must be an integer, user will be reprompted

For play again method:      What happens:
yuppers                     Will have the user play another game 
Yes                         Will have the user play another game 
noope                       Will display game session statistics and then exit program
Naw                         Will display game session statistics and then exit program 
hjdkjshf                    Will display game session statistics and then exit program
*/