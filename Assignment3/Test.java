// Eli F.
// Section: B
// Assignment 3
// Description: Uses the affine cipher to encrypt and then decrypt an inputed string
// Class name: Cipher
// Version 1.0
// 10/2/15

import java.util.*;

public class Test {
   
   /**
   * length of alphabet the user will be using
   */
   public static final int ALPHABET = 26;
   
   /**
   * encrypts and then decrypts an inputed string using rot13 and affine cipher
   * will only encrypt letters special symbols will be ignored
   *
   * @param args user input from the console
   */
   public static void main(String[] args) {
      //creates new scanner object
      Scanner console = new Scanner(System.in);
      
      //gathers input data
      explainer();
      int a = promptForA(console);
      int b = promptForB(console);
      String phrase = promptForString(console);
      
      //generates encrypted and decrypted versions of the input phrase
      //based off of the rot13 and affine ciphers
      String encrypted_Phrase_Rot13 = rot13(phrase);
      String decrypted_Phrase_Rot13 = rot13(encrypted_Phrase_Rot13); //rot13 is its own inverse
      String encrypted_Phrase_AffineCipher = affineCipher(a, b, phrase);
      String decrypted_Phrase_AffineCipher = decryptionFunctionAffineCipher(a, b, encrypted_Phrase_AffineCipher);
      affineResults(encrypted_Phrase_AffineCipher, decrypted_Phrase_AffineCipher, phrase);
      rot13Results(encrypted_Phrase_Rot13, decrypted_Phrase_Rot13, phrase);
   }
   
   /**
   * prints an explanation of what the program will do
   */
   public static void explainer() {
      //explains program and asks for input
      System.out.println("This program will encode and then decode");
      System.out.println("a phrase using the ROT13 and Affine Ciphers.");
      System.out.println("An Affine Cipher encodes character with an equation");
      System.out.println();
      System.out.println("(ax + b) mod 26");
      System.out.println();
      System.out.println("where x is the ASCII value of the character.");
      System.out.println("ROT13 is a special case of the Affine Cipher,");
      System.out.println("where a is 1 and b is 13.");
      System.out.println();
   }
   
   /**
   * prompts the user for the multiplicative factor for the affine cipher
   *
   * @param input scanner object used to collect user input
   * @return a new valid value for a
   */
   public static int promptForA(Scanner input) {
      System.out.print("Please enter an odd numeric value for a (1-25, not 13): ");
         int a = input.nextInt();
      if(testA(a, input) > 0) {
         return testA(a, input);
      }
      else {
         return a;
      }
   }
   
   /**
   * prompts the user for the shift factor for the affine cipher
   *
   * @param input scanner object used to collect user input
   * @return input.nextInt() value for b
   */
   public static int promptForB(Scanner input) {
      System.out.print("Please enter a numeric value for b: ");
         return input.nextInt();
   }
   
   /**
   * prompts the user the phrase that will be encoded
   *
   * @param input scanner object used to collect user input
   * @return input.nextLine() the phrase program will encode and decode 
   */
   public static String promptForString(Scanner input) {
      System.out.print("Enter the phrase you'd like to convert: ");
      input.nextLine();
      return input.nextLine();
   }
   
   /**
   * prints the encoded and decoded phrase using affine cipher
   * prints the result of a test which compares the decode phrase with the original
   *
   * @param result result of applying the affine cipher to the original phrase
   * @param decrypted_Phrase result of applying my decryption equation for the affine cipher to the decrypted phrase
   * @param originalphrase the original phrase that will be compared to the decrypted phrase
   */
   public static void affineResults(String result, String decrypted_Phrase, String originalphrase) {
      System.out.println();
      System.out.println("The Affine encoded phrase is: " + result);
      System.out.println("The Affine decoded phrase is: " + decrypted_Phrase);
      succesTest(originalphrase, decrypted_Phrase);
   }
   
   /**
   * prints the encoded and decoded phrase using rot13 (special case of affine cipher)
   * prints the result of a test which compares the decode phrase with the original
   *
   * @param result result of applying rot13 to the original phrase
   * @param decrypted_Phrase result of applying my decryption equation for rot13 to the decrypted phrase
   * @param originalphrase the original phrase that will be compared to the decrypted phrase
   */
   public static void rot13Results(String result, String decrypted_Phrase, String originalphrase) {
      System.out.println();
      System.out.println("The ROT13 encoded phrase is: " + result);
      System.out.println("The ROT13 decoded phrase is: " + decrypted_Phrase);
      succesTest(originalphrase, decrypted_Phrase);
   }
   
   /**
   * encrypts String using rot13, and also decrypts Strings encrypted using rot13
   *
   * @param phrase the phrase that will be encrypted or decrypted using rot13
   * @return encrypted_Phrase the encrypted phrase or decrypted phrase depending on the output
   */
   public static String rot13(String phrase) {
      String encrypted_Phrase = "";
      for(int ii = 0; ii < phrase.length(); ii++) {
         //grab characters from entered string to encrypt
         char character = phrase.charAt(ii);
         //if it is a uppercase letter apply formula but use uppercase a to calculate indexes
         if(character <= 'Z' && character >= 'A') {
            int index = (((int) character - 'A' + 13)%ALPHABET + 'A');
            encrypted_Phrase += (char) index;
         }
         else {
            //if it is a lowercase letter apply formula but use lowercase a to calculate indexes
            if(character <= 'z' && character >= 'a') {
               int index = (((int) character - 'a' + 13)%ALPHABET + 'a');
               encrypted_Phrase += (char) index;
            }
            //if not a letter don't change the character
            else {
               encrypted_Phrase += character;
            }
         }
      }
      return encrypted_Phrase;
   }
   
   /**
   * encrypts String using affine cipher
   *
   * @param a the multiplicative factor used for encrypting
   * @param b the shift factor used for encrypting
   * @param phrase the phrase that will be encrypted using affine cipher
   * @return encrypted_Phrase the phrase encrypted using affineCipher 
   */
   public static String affineCipher(int a, int b, String phrase) {
      String encrypted_Phrase = "";
      for(int ii = 0; ii < phrase.length(); ii++) {
         //grab characters from entered string to encrypt
         char character = phrase.charAt(ii);
         //if it is a uppercase letter apply formula but use uppercase a to calculate indexes
         if(character <= 'Z' && character >= 'A') {
            int index = ((a * (int) character - 'A' + b)%ALPHABET + 'A');
            encrypted_Phrase += 	 (char) index;
         }
         else {
            //if it is a lowercase letter apply formula but use lowercase a to calculate indexes
            if(character <= 'z' && character >= 'a') {
               int index = ((a * (int) character - 'a' + b)%ALPHABET + 'a');
               encrypted_Phrase += (char) index;;
            }
            //if not a letter don't change the character
            else {
               encrypted_Phrase += character;
            }
         }
      }
      return encrypted_Phrase;
   }
   
   /**
   * decrypts String encrypted by affine cipher
   *
   * @param a the multiplicative factor used for encrypting
   * @param b the shift factor used for encrypting
   * @param phrase the phrase that will be decrypted
   * @return dencrypted_Phrase the result of applying the decryption algorithm to an encrypted phrase 
   */
   public static String decryptionFunctionAffineCipher(int a, int b, String phrase) {
      String decrypted_Phrase = "";
      for(int ii = 0; ii < phrase.length(); ii++) {
         char character = phrase.charAt(ii);
         //check for uppercase letters
         if(character <= 'Z' && character >= 'A') {
            int index = aPrime(a) * ((int) character - 'A' - b);
            //to handle negative index since mod is java is not true mod
            if(index < 0) {
               index = Math.abs(Math.abs(index)-ALPHABET)%ALPHABET+'A';
            }
            else{
               index = index%ALPHABET+'A';
            }
            decrypted_Phrase += (char) index;
         }
         else {
            //check for lowercase letters
            if(character <= 'z' && character >= 'a') {
               int index = aPrime(a) * ((int) character - 'a' - b);
               //to handle negative index since mod in java is not a true mod
               if(index < 0) {
                  index = Math.abs(Math.abs(index)-ALPHABET)%ALPHABET+'a';
               }
               else{
                  index = index%ALPHABET+'a';
               }
               decrypted_Phrase += (char) index;
            }
            //if it is a special character then ignore
            else {
               decrypted_Phrase += character;
            }
         }
      }
      return decrypted_Phrase;
   }
   
   /**
   * finds modular multiplicative inverse of a.  since x must satisfy the equation a*x mod m = 1 can search for x
   *
   * @param a the multiplicative factor used to find the modular multiplicative inverse
   * @return aPrime modular multiplicative inverse of a
   */
   public static int aPrime(int a) {
      int aPrime = 0;
      for(int ii = 0; ii < ALPHABET; ii++) {
         int test = (ii*a);
         if(test%ALPHABET == 1) {
            aPrime = ii;
         }
      }
      return aPrime;
   }
   
   /**
   * tests to see if phrases were correctly encrypted and decrypted
   *
   * @param original_Phrase the original phrase the user inputed
   * @param decrypted_Phrase the phrase which has been encrypted and then decrypted
   */
   public static void succesTest(String original_Phrase, String decrypted_Phrase) {
      if(original_Phrase.equals(decrypted_Phrase)) {
         System.out.println("The conversion worked correctly.");
      }
      else {
         System.out.println("The conversion did not work.");
      }
   }
   
   /**
   * tests the input for the multiplicative factor for affine cipher
   * needs to be co-prime with the length of the alphabet used for the affine cipher to be decrypt-able
   *
   * @param a multiplicative factor that will be tested
   * @param input Scanner object used to collect input from user
   * @return aa new valid value for a
   */
   public static int testA(int a, Scanner input) {
      int aa = 0;
      if(a%2 != 0 && a < 26) {
         if(a == 13) {
            System.out.println("You cant enter 13!");
            System.out.println();
            System.out.print("Please enter a new value:");
            aa = input.nextInt();
            //System.exit(0);
         }
         else {
            return 0;
         }
      }
      else {
         System.out.println("You cant enter a number greater than 25 or an even number!");
         System.out.println();
         System.out.print("Please enter a new value:");
         aa = input.nextInt();
         //System.exit(0);
      }
      return aa;
   }
}