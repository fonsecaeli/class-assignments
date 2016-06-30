// Eli F.
// Section: C
// Assignment 13
// Description: empirical tester for sorting algorithms
// Class name: Empirical
// Version 1.0
// 4/20/16

import java.util.*;

public class Empirical {

    /**
    * enum containing constants for all of the sorting algorithms this program tests
    */
   private enum Algorithm {
      SELECTION("selectionSort"),
      INSERTION("insertionSort"),
      MERGE("mergeSort"),
      ARRAYS("Arrays.sort"),
      HEAP("heapSort"),
      QUICK("quickSort");
      
      private final String name;

      /**
       * constructor for the Algorithm enum
       * @param  s string value for the enum
       */
      private Algorithm(String s) {
         name = s;
      }
      
      /**
       * to string method for algorithm enum
       * @return string representation of algorithm enum constant
       */
      public String toString() {
         return this.name;
      }
      
      /**
       * sorts an array using the algorithnm named by the enum constant
       * cacluates the time in miliseconds that the sorting took
       * @param  data array to be sorted
       * @return      the time in miliseconds that the sorting took
       */
      public int sort(Integer[] data) {
         long beginTime = 0;
         long endTime = 0; 
         System.gc();
         if(name.equals("insertionSort")) {
            beginTime = System.currentTimeMillis();
            Sorters.insertionSort(data);
         }
         else if(name.equals("selectionSort")) {
            beginTime = System.currentTimeMillis();
            Sorters.selectionSort(data);
         }
         else if(name.equals("Arrays.sort")) {
            beginTime = System.currentTimeMillis();
            Arrays.sort(data);
         }
         else if(name.equals("mergeSort")) {
            beginTime = System.currentTimeMillis();
            Sorters.mergeSort(data);
         } 
         else if(name.equals("quickSort")) {
            beginTime = System.currentTimeMillis();
            Sorters.quicksort(data);
         }
         else if(name.equals("heapSort")) {
            beginTime = System.currentTimeMillis();
            Sorters.heapsort(data);
         }
         endTime = System.currentTimeMillis();
         return (int)(endTime - beginTime);
 
      }
   }

   /**
    * enum contianing constants for all the types of tests to be conducted on sorting algorithms
    */
   private enum Test {
      INCREASING("Increasing"),
      DECREASING("Decreasing"),
      RANDOM("Random");
      
      private final String name;
      /**
       * constructor for a Test enum
       * @param  s string value for enum constant
       */
      private Test(String s) {
         name = s;
      }
      
      /**
       * to string for test enum
       * @return returns a string representation for enum constants
       */
      public String toString() {
         return this.name;
      }
      
      /**
       * gets a reference to an array list containing arrays conrisponding with the 
       * enum constants test type
       * @param  testNum corrisponded to the different length arrays stored in each array list
       * @return array to be used for testing a sorting algorithm that corrisonds to the given 
       * test type
       */
      public Integer[] getTestData(int testLength) {
         if(name.equals("Increasing")) {
            return Arrays.copyOf(increasingTests, testLength);
         }
         else if(name.equals("Decreasing")) {
            return Arrays.copyOf(decreasingTests, testLength);
         }
         else {
            return Arrays.copyOf(randomTests, testLength);
         }
      }
   }

   /**
    * random object used to generate random arrays
    */
   public static Random rand = new Random();

   /**
    * array containing the lenght of all the tests to be conducted on the sorting algorithms
    */
   public static final int[] testSizes = {1000, 5000, 10000, 20000, 50000};

   /**
    * ArrayList contianing Integer arrays for all of the decreasing tests
    */
   public static Integer[] decreasingTests = new Integer[testSizes[testSizes.length-1]];

   /**
    * ArrayList containing Integer arrays for all the increasing tests
    */
   public static Integer[] increasingTests = new Integer[testSizes[testSizes.length-1]];

   /**
    * ArrayList containing Integer arrays for all the random tests
    */
   public static Integer[] randomTests = new Integer[testSizes[testSizes.length-1]];  
   
   /**
    * entry point into the program, where it all begins!
    * 
    * @param args user input from the console 
    */
   public static void main(String[] args){
      fillTestLists();
      Test[] tests = Test.values();
      for(int ii = 0; ii < tests.length; ii++) {
         runTest(tests[ii]);
         System.out.println();
      }
   }
      
   /**
    * fills all of the array lists containg the arrays to be tested
    */
   public static void fillTestLists() {
      decreasingTests = genOrdered(testSizes[testSizes.length-1], false);
      increasingTests = genOrdered(testSizes[testSizes.length-1], true);
      randomTests = genRandom(testSizes[testSizes.length-1]);
   }
   
   /**
    * runs tests on ever sorting algorithm for a specifc type of test
    * runs the tests on all sorting algorithms in the Algorithm enum 
    * and then prints the results with table formating
    * prints the resutls of the test to the console
    * 
    * @param testType they type of test to be conducted on all sorting algorithms
    */
   public static void runTest(Test testType) { 
      System.out.printf("%15s", testType.toString()+" ");
      for(int ii = 0; ii < testSizes.length; ii++) {
         System.out.printf("%-8d", testSizes[ii]);
      }
      System.out.println();
      Algorithm[] algorithms = Algorithm.values();
      for(int ii = 0; ii < algorithms.length; ii++) {
         System.out.printf("%15s", algorithms[ii].toString()+" ");
         for(int jj = 0; jj < testSizes.length; jj++) {
            System.out.printf("%-8d", algorithms[ii].sort(testType.getTestData(testSizes[jj])));    
         }
         System.out.println();
      }
   }

   /**
    * generates an ordered or unorder array of specific length
    * @param  size       the size of the array to be generated
    * @param  increasing if the array should be in increasing order or decreasing order
    * @return            ordered or unordered array for testing
    */
   public static Integer[] genOrdered(int size, boolean increasing) {
      Integer[] data = new Integer[size];
      for(int ii = 0; ii < size; ii++) {
         if(increasing) {
            data[ii] = ii;
         }
         else {
            data[ii] = size-ii;
         }
      }
      return data;
   }

   /**
    * generates a randomly ordered array
    * @param  size the size of the array to be generated
    * @return      a randomly ordered array
    */
   public static Integer[] genRandom(int size) {
      Integer[] data = new Integer[size];
      for(int ii = 0; ii < size; ii++) {
         data[ii] = rand.nextInt(size+1); //all values between 0 and size inclusive
      }
      return data;
   }
}