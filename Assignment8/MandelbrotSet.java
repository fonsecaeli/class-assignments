import java.util.*;
import java.awt.*;

public class MandelbrotSet {
   public static final int SIZE = 3000;
   public static void main(String[] args) {
      DrawingPanelPlus panel = new DrawingPanelPlus();
      panel.setCanvasSize(SIZE, SIZE);
      panel.setYscale(-2, 2);
      panel.setXscale(-2, 2);
      for(double ii = -2; ii < 2; ii += .0001) {
         double a = ii;
         for(double jj = -2; jj < 2; jj += .0001) {
            double b = jj;
            double[] initialValue = new double[2];
            initialValue[0] = a;
            initialValue[1] = b;
            int numberIterations = escapeNum(initialValue);
            //System.out.println("Number of iterations to escape: "+numberIterations);
            if(numberIterations == 265) {
               panel.setPenColor();
               panel.filledCircle(a, b, .001);
            }
            // else {
//                panel.setPenColor(getColor(numberIterations));
//                panel.filledCircle(a, b, .001);
//             }
         }
      }
   }
   
   public static Color getColor(int n) {
      if(n <= 50) {
         return Color.RED;         
      }else if(n <= 60) {
         return Color.GREEN;
      }else if(n <= 100) {
         return Color.BLUE;
      }else if(n <= 150) {
         return Color.ORANGE;
      }else if(n <= 220) {
         return Color.YELLOW;
      }
      return Color.CYAN;
   }

      
   public static int escapeNum(double[] z0) {
      double[] zn = new double[2];
      zn[0] = z0[0];
      zn[1] = z0[1];
      double modulus = Math.sqrt(zn[0]*zn[0]+zn[1]*zn[1]);
      int n = 0;
      double[] zNext = new double[2];
      while(modulus < 2 && n < 265) {
         zNext[0] = zn[0]*zn[0] + z0[0] - zn[1]*zn[1];
         zNext[1] = z0[1] + 2*zn[1]*zn[0];
         zn[0] = zNext[0];
         zn[1] = zNext[1];
         n++;
         modulus = Math.sqrt(zn[0]*zn[0]+zn[1]*zn[1]);
         //System.out.println(n + " " + Arrays.toString(zn));
      } 
      return n;
   }
}