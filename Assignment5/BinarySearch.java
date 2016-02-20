import java.util.Random;

public class BinarySearch {
   public static final int MIN_NUMBER = 1;
   public static final int MAX_NUMBER = 10000;
   
   public static void main(String[] args) {
      Random ran = new Random();
      int answer = ran.nextInt(MAX_NUMBER)+1;
      System.out.print(binarySearch(answer, 4));
   }
   
   public static int binarySearch(int target, int totalGuesses) {
      int[] set = new int[MAX_NUMBER];
      for(int ii = 0; ii < MAX_NUMBER; ii++) {
         set[ii] = ii + 1;   
      }
      int min = 0;
      int max = MAX_NUMBER-1;
      int mid = 0;
      int guesses = 1;
      while(min <= max) {
         mid = (min+max)/2;
         if(set[mid] == target) {
            System.out.println(guesses);
            return mid;
         }
         else if(set[mid] < target) {
            min = mid+1;
            guesses++;
         }
         else if(set[mid] > target) {
            max = mid-1;
            guesses++;                               
         }
      }
      return 1;
   }
}