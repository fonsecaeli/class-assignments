public class RandomAndWhile {
   public static void main(String[] args) {
      rollDice(50);
   }
   
   static void rollDice(int numRolls) {
      
      for(int ii = 0; ii < numRolls; ii++) {
         System.out.print(" " + (1+((int)(Math.random()*6))));
      }
   }
}