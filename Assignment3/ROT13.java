// Eli F.
// Section: B
// Assignment 3
// Description: Uses the ROT13 cipher to encrypt and then decrypt an inputed string
// Class name: ROT13
// Version 1.0
// 10/2/15

import java.util.*;
   
public class ROT13 {

   /**
   * Length of alphabet the user will be using
   */
   public static final int ALPHABET = 26;

   /**
   * Encrypts and then decrypts an inputed string using the ROT13 cipher 
   * will only encrypt letters, special symbols will be ignored
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {

      //creates new scanner object
      Scanner console = new Scanner(System.in);
      
      //gathers input for the phrase that will be encoded and decoded
      explainer();
      String phrase = console.nextLine();

      //dislpays the results of appling the ROT13 cipher
      rot13Results(rot13(phrase), rot13(rot13(phrase)), phrase);
   }

   /**
   * Prints an explanation of what the program will do
   */
   public static void explainer() {
      System.out.println("This program will encode and then decode");
      System.out.println("a phrase using the ROT13 Cipher.");
      System.out.println("ROT13 encodes a phrase by rotating the");
      System.out.println("alphabetic characters in a string by 13 places.");
      System.out.println();
      System.out.print("Enter the phrase you'd like to convert: ");
   }

   /**
   * Prints the encoded and decoded phrase using ROT13 (special case of affine cipher)
   * also prints the result of a test which compares the decode phrase with the original
   *
   * @param result result of applying ROT13 to the original phrase
   * @param decryptedPhrase result of applying my decryption equation for ROT13 to the decrypted phrase
   * @param originalphrase the original phrase that will be compared to the decrypted phrase
   */
   public static void rot13Results(String result, String decryptedPhrase, String originalphrase) {
      System.out.println();
      System.out.println("The ROT13 encoded phrase is: " + result);
      System.out.println("The ROT13 decoded phrase is: " + decryptedPhrase);
      succesTest(originalphrase, decryptedPhrase);
   }

   /**
   * Tests to see if phrases were correctly encrypted and decrypted
   *
   * @param originalPhrase the original phrase the user inputed
   * @param decryptedPhrase the phrase which has been encrypted and then decrypted
   */
   public static void succesTest(String originalPhrase, String decryptedPhrase) {
      String test = (originalPhrase.equals(decryptedPhrase)) ? "The conversion worked correctly."
      :"The conversion did not work";  //sorry for weird line break trying have lines under 100char
      System.out.println(test);
   }
   
   /**
   * Check to see if a character is upper case or lower case
   *
   * @param character the character that will be tested
   * @return letter first letter of the alphabet depending on the case of the character
   * if character is not a letter then returns character
   */
   public static char caseCheck(char character) {
      if(character <= 'Z' && character >= 'A') {
         char letter = 'A';
         return letter;
      }
      else {
         if(character <= 'z' && character >= 'a') {
            char letter = 'a';
            return letter;
         }
         else { //if special character ignore
            char letter = character;
            return letter;
         }
      }
   }
   
   /**
   * Encrypts inputed phrase using ROT13, and also decrypts inputed phrase encrypted using ROT13
   * this is possible because ROT13 is its own inverse since the our alphabet is 26 character long
   *
   * @param phrase the phrase that will be encrypted or decrypted using ROT13
   * @return encryptedPhrase the encrypted phrase or decrypted phrase depending on the output
   */
   public static String rot13(String phrase) {
      int shiftFactor = 13; //rot13 means rotate/shift 13 spaces
      String encryptedPhrase = "";
      for(int ii = 0; ii < phrase.length(); ii++) {
         //grab characters from entered string to encrypt
         char character = phrase.charAt(ii);
         int letterCase = caseCheck(character);
         if(letterCase == 'A' || letterCase == 'a') {
            int index = ((character - letterCase + shiftFactor) % ALPHABET + letterCase);
            encryptedPhrase += (char) index;
         }
         else {
            encryptedPhrase += character; //if the character is a special symbol ignore
         }
      }
      return encryptedPhrase;
   }
}