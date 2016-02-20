import java.util.*;

public class test {
public static void main(String[] args) { 
   sameFlip(new Random());
}
public static int sameFlip(Random r)
   {
      int count = 0; 
      int lastFlip = -1; 
      int flip = -2; 
      while ( lastFlip != flip )
      {
         lastFlip = flip; 
         flip = r.nextInt(2);
         count++;
         System.out.print((flip == 1? "H" : "T")); 
      }
      System.out.println();
      return count; 
    }
}