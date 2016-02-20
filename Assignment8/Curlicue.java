public class Curlicue {
   public static void main(String[] args) {
      DrawingPanelPlus panel = new DrawingPanelPlus();
      panel.setCanvasSize(1000, 1000);
      panel.setYscale(0, 1000);
      panel.setXscale(0, 1000);
      //draw(panel);
      plot(panel);
   }
   
   public static void plot(DrawingPanelPlus panel) {
      for(double ii = -10; ii < 10; ii = ii + .001) {
         double y = 500.0*(integrate(0, ii, 1000, true));
         double x = 500.0*(integrate(0, ii, 1000, false));
         panel.filledCircle(-x, -y, 1);
      }
   }
   
   //numericaly calculates the integral of a function using trapazoid riemine sum method
   public static double integrate(double a, double b, int N, boolean c) {
      double h = (b - a) / N;
      double sum;
      if(c) {              
         sum = 0.5 * (c(a) + c(b));  
      }
      else {
         sum = 0.5 * (s(a) + s(b));
      } 
      for (int i = 1; i < N; i++) {
         double x = a + h * i;
         if(c) {
            sum = sum + c(x);
         }
         else {
            sum = sum + s(x);
         }
      }

      return sum * h;
   }
   
   public static double c(double x) {
      return Math.cos(x*x);
   }
   
   public static double s(double x) {
      return Math.sin(x*x);
   }
   
}