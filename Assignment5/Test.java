//Eli F.
//Section B
//Assignment #5
//Description:
//Version 1.0
//Class Name: GuessingGame
//10/29/2015

import java.util.*;

public class Test {

	/**
	* The upper bound of the random number the user will guess
	*/
	public static final int MAX_NUMBER = 5;

	/**
	* Lets the user play where they try to guess a random number given hints
	*
	*
	* @param args user input from the console
	*/
	public static void main(String[] args) {
		Random random = new Random();
		Scanner input = new Scanner(System.in);
		haiku();
		int[] stats = playGames(random, input);
		int totalGuesses = stats[0];
      int numberOfGames = stats[1];
		int best = stats[2];
		stats(numberOfGames, totalGuesses, best);
	}

	/**
	* Prints a haiku to explain the program
	*/
	public static void haiku() {
		System.out.println("This Program Lets You");
		System.out.println("Play A Random Guessing Game ");
		System.out.println("It Is Really Fun!");
	}

	/**
	* Plays through the game as many times as the user would like
	* 
	* @param random Random object used to generate random numer 
	* @param input Scanner object used to get input from the user 
	* @return a list of the statistics from a game session
	*/
	public static int[] playGames(Random random, Scanner input) {
      int totalGuesses = 0;
		int numberOfGames = 0;
		int best = 1000000;

		do {
			int guesses = oneGame(random, input);
			if(guesses < best) {
				best = guesses;
			}
			//ask user if they want to play again
			//update counter and run game again if they want to!
         	numberOfGames++;
         	totalGuesses += guesses;
		}
		while(again(input));

		int[] stats = {totalGuesses, numberOfGames, best};
		return stats;
	}

	/**
	* Plays time through the guessing game
	*
	* @param answer the random number that is trying to be guessed!
	* @param input Scanner object used to get input from the user 
	*/
	public static int oneGame(Random random, Scanner input) {
		int numberOfGuesses = 1;
		int answer = random.nextInt(MAX_NUMBER)+1;
     	System.out.println();
		System.out.println("Correct number is " + answer);
		System.out.println("I'm thinking of a number between 1 and " + MAX_NUMBER + "...");
		int guess = Inputter.getNumber(input, "Your guess? ", 1, MAX_NUMBER);
		while(guess != answer) {
			System.out.print("Its ");
			if(guess > answer) {
				System.out.println("lower.");
			}
			else {
				System.out.println("higher.");
			}
			guess = Inputter.getNumber(input, "Your guess? ", 1, MAX_NUMBER);
			numberOfGuesses++;
		}
		if(guess == answer && numberOfGuesses == 1) {
			System.out.println("You got it right in 1 guess!");
		}
		if(guess == answer && numberOfGuesses != 1) {
			System.out.println("You got it right in " + numberOfGuesses + " guesses!");
		}
   	return numberOfGuesses;
	}

	/**
	* Displays the statistics of the game session 
	*
	* @param totalGames total number of games the user played 
	* @param totalGuesses total number of guessses the user had
	* @param best the number of guesses the user had in the best game 
	*/
	public static void stats(int totalGames, int totalGuesses, int best) {
		System.out.println();
		System.out.println("Overall results:");
		System.out.printf("%-14s", "Total games");
		System.out.println("= " + totalGames);
		System.out.printf("%-14s", "Guesses/game");
		System.out.println("= " + (double) (totalGuesses)/totalGames);
		System.out.printf("%-14s", "Best game");
		System.out.println("= " + best);
	}

	/**
	* Tests to see if the user would like to play the game again
	*
	* @param input Scanner object used to ask user if they would like to play again
	* @retrun boolean value that indicates whether or not the user wants to play again
	*/
	public static boolean again(Scanner input) {
		System.out.print("Do you want to play again? ");
		String answer = input.next();
      	Character c = new Character(answer.charAt(0));
		if(c.equals('y') || c.equals('Y')) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	* Implements a binary search algorithum to compare against a users results
	*
	* @param answer the randomly generated number which we are trying to guess
	* @retrun number of guesses it took algorithum to find the answer 
	*/
	public static void binarySearch(int answer, int totalGuesses) {
		int numberOfGuesses = 1;
		int guess = MAX_NUMBER/2;
		while(guess != answer) {
			if(guess > answer) {
				guess = (int)((MAX_NUMBER+guess)/2);
			}
			if(guess < answer) {
				guess = (int) (guess/2);
			}
			numberOfGuesses++;
		}
		if(totalGuesses > numberOfGuesses) {
			System.out.println("The binary search algorithum beat you!");
		}
		else {
			System.out.println("You beat the binary search algorithum!");
		}
	}
}