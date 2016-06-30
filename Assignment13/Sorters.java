// Eli F.
// Section: C
// Assignment 13
// Description: sample of three sorting algorithms
// Class name: Sorters
// Version 1.0
// 4/20/16


import java.util.*;

public class Sorters {

   public static void main(String[] args) {
      Integer[] test = {-74, -14, -53, -7, 19, 16, 48, -53, 34, 14};
   
      //testing insertion sort
      System.out.println("Before Insertion Sort " + Arrays.toString(test));
      Integer[] insertionArray = Arrays.copyOf(test, test.length);
      insertionSort(insertionArray);
      System.out.println("After Insertion Sort " + Arrays.toString(insertionArray));
   
      System.out.println();
   
      //testing selection sort
      System.out.println("Before Selection Sort " + Arrays.toString(test));
      Integer[] selectionArray = Arrays.copyOf(test, test.length);
      selectionSort(selectionArray);
      System.out.println("After Selection Sort " + Arrays.toString(selectionArray));
   
      System.out.println();
   
      //testing merge sort
      System.out.println("Before Merge Sort " + Arrays.toString(test));
      Integer[] mergeArray = Arrays.copyOf(test, test.length);
      mergeSort(mergeArray);
      System.out.println("After Merge Sort " + Arrays.toString(mergeArray));
   
      System.out.println();
   
      //testing quick sort
      System.out.println("Before Quick Sort " + Arrays.toString(test));
      Integer[] quickArray = Arrays.copyOf(test, test.length);
      quicksort(quickArray);
      System.out.println("After Quick Sort " + Arrays.toString(quickArray));
   
      System.out.println();
   
      //testing heap sort
      System.out.println("Before Heap Sort " + Arrays.toString(test));
      Integer[] heapArray = Arrays.copyOf(test, test.length);
      heapsort(heapArray);
      System.out.println("After Heap Sort " + Arrays.toString(heapArray));
   }


   /* 
    * Sorts an array of Comparable objects using the selection sort algorithm 
    * (moving each element to its proper place). 
    * copied from: http://www.ics.uci.edu/~stasio/winter06/Lectures/Lec7code/ComparableExample/Sorting.java
    * 
    * @param  list an array of Comparable objects to sort. 
    *         Pre: array order unknown
    *         Post: array in order
    */

   public static void selectionSort(Comparable[] list) 
   {
      int length = list.length;
      for(int ii = 0; ii < length-1; ii++) {
         int minIndex = ii;
         for(int jj = ii+1; jj < length; jj++) {
            if(list[jj].compareTo(list[minIndex]) == -1) {
               minIndex = jj;
            }
            if(minIndex != -1) {
               Comparable temp = list[ii];
               list[ii] = list[minIndex];
               list[minIndex] = temp;
            }
         }
      }
   }
   
    /* 
    * Sorts an array of Comparable objects using the insertion sort algorithm 
    * (moving each element to its proper place). 
    * copied from: http://www.ics.uci.edu/~stasio/winter06/Lectures/Lec7code/ComparableExample/Sorting.java
    *   
    * @param  list an array of Comparable objects to sort. 
    * Pre: array order unknown
    * Post: array in order
    */

   public static void insertionSort (Comparable[] list)
   {
      for (int index = 1; index < list.length; index++)
      {
         Comparable key = list[index];
         int position = index;
      //  Shift larger values to the right
         while (position > 0 && key.compareTo(list[position-1]) < 0)
         {
            list[position] = list[position-1];
            position--;
         }  
         list[position] = key;
      }
   }

   /* 
    * Sorts an array of Comparable objects using the merge sort algorithm. 
    * This version copies the array to two smaller sub arrays. 
    * copied from: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/code/MergeSort.java 
    * 
    * @param  list an array of Comparable objects to sort. 
    *         Pre: array order unknown 
    *         Post: array in order
    */
   public static void mergeSort(Comparable[] a) {
      Comparable[] tmp = new Comparable[a.length];
      mergeSort(a, tmp,  0,  a.length - 1);
   }


   private static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right) {
      if( left < right )
      {
         int center = (left + right) / 2;
         mergeSort(a, tmp, left, center);
         mergeSort(a, tmp, center + 1, right);
         merge(a, tmp, left, center + 1, right);
      }
   }


   private static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd) {
      int leftEnd = right - 1;
      int k = left;
      int num = rightEnd - left + 1;
   
      while(left <= leftEnd && right <= rightEnd)
         if(a[left].compareTo(a[right]) <= 0)
            tmp[k++] = a[left++];
         else
            tmp[k++] = a[right++];
   
      while(left <= leftEnd)    // Copy rest of first half
         tmp[k++] = a[left++];
   
      while(right <= rightEnd)  // Copy rest of right half
         tmp[k++] = a[right++];
   
      // Copy tmp back
      for(int i = 0; i < num; i++, rightEnd--)
         a[rightEnd] = tmp[rightEnd];
   }
   
   /**
    * Quicksort algorithm.
    * quick sort methods copied from: http://users.cis.fiu.edu/~weiss/dsj2/code/Sort.java
    * 
    * @param a an array of Comparable items.
    */
   public static void quicksort( Comparable [ ] a )
   {
      quicksort( a, 0, a.length - 1 );
   }

   private static final int CUTOFF = 10;

   /**
    * Method to swap to elements in an array.
    * @param a an array of objects.
    * @param index1 the index of the first object.
    * @param index2 the index of the second object.
    */
   public static final void swapReferences( Object [ ] a, int index1, int index2 )
   {
      Object tmp = a[ index1 ];
      a[ index1 ] = a[ index2 ];
      a[ index2 ] = tmp;
   }

   /**
    * Internal quicksort method that makes recursive calls.
    * Uses median-of-three partitioning and a cutoff of 10.
    * @param a an array of Comparable items.
    * @param low the left-most index of the subarray.
    * @param high the right-most index of the subarray.
    */
   private static void quicksort( Comparable [ ] a, int low, int high )
   {
      if( low + CUTOFF > high )
         insertionSort( a, low, high );
      else
      {
             // Sort low, middle, high
         int middle = ( low + high ) / 2;
         if( a[ middle ].compareTo( a[ low ] ) < 0 )
            swapReferences( a, low, middle );
         if( a[ high ].compareTo( a[ low ] ) < 0 )
            swapReferences( a, low, high );
         if( a[ high ].compareTo( a[ middle ] ) < 0 )
            swapReferences( a, middle, high );
      
             // Place pivot at position high - 1
         swapReferences( a, middle, high - 1 );
         Comparable pivot = a[ high - 1 ];
      
             // Begin partitioning
         int i, j;
         for( i = low, j = high - 1; ; )
         {
            while( a[ ++i ].compareTo( pivot ) < 0 )
               ;
            while( pivot.compareTo( a[ --j ] ) < 0 )
               ;
            if( i >= j )
               break;
            swapReferences( a, i, j );
         }
      
             // Restore pivot
         swapReferences( a, i, high - 1 );
      
         quicksort( a, low, i - 1 );    // Sort small elements
         quicksort( a, i + 1, high );   // Sort large elements
      }
   }

   /**
    * Internal insertion sort routine for subarrays
    * that is used by quicksort.
    * @param a an array of Comparable items.
    * @param low the left-most index of the subarray.
    * @param n the number of items to sort.
    */
   private static void insertionSort( Comparable [ ] a, int low, int high )
   {
      for( int p = low + 1; p <= high; p++ )
      {
         Comparable tmp = a[ p ];
         int j;
      
         for( j = p; j > low && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
            a[ j ] = a[ j - 1 ];
         a[ j ] = tmp;
      }
   }

   /**
    * Standard heapsort.
    * heap sort methods copied from: http://users.cis.fiu.edu/~weiss/dsj2/code/Sort.java
    * 
    * @param a an array of Comparable items.
    */
   public static void heapsort( Comparable [ ] a )
   {
      for( int i = a.length / 2; i >= 0; i-- )  /* buildHeap */
         percDown( a, i, a.length );
      for( int i = a.length - 1; i > 0; i-- )
      {
         swapReferences( a, 0, i );            /* deleteMax */
         percDown( a, 0, i );
      }
   }

   /**
    * Internal method for heapsort.
    * @param i the index of an item in the heap.
    * @return the index of the left child.
    */
   private static int leftChild( int i )
   {
      return 2 * i + 1;
   }

   /**
    * Internal method for heapsort that is used in
    * deleteMax and buildHeap.
    * @param a an array of Comparable items.
    * @index i the position from which to percolate down.
    * @int n the logical size of the binary heap.
    */
   private static void percDown( Comparable [ ] a, int i, int n )
   {
      int child;
      Comparable tmp;
   
      for( tmp = a[ i ]; leftChild( i ) < n; i = child )
      {
         child = leftChild( i );
         if( child != n - 1 && a[ child ].compareTo( a[ child + 1 ] ) < 0 )
            child++;
         if( tmp.compareTo( a[ child ] ) < 0 )
            a[ i ] = a[ child ];
         else
            break;
      }
      a[ i ] = tmp;
   }
} 


