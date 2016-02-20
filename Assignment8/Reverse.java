public class Reverse {
   public static void main(String[] args) {
      //System.out.println(reverse("RRRL"));
      System.out.println(replace("RRRL"));
   }
   
   
   public static String reverse(String s) {
      if(s.length() == 1) {
         return Character.toString(s.charAt(0));
      }
      return reverse(s.substring(1, s.length())) + Character.toString(s.charAt(0));
   }
   
   public static String replace(String s) {
      String complement = "";
      if(s.isEmpty()) {
         return "";
      }
      boolean isF = (s.charAt(0) == 'L');
      if(isF) {
         complement += "R";
      }
      else {
         complement += "L";
      }
      return complement + replace(s.substring(1, s.length()));

   }
}