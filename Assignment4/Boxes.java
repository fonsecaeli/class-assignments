import java.util.*;
import java.awt.*;

public class Boxes {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("What is the number of iterations? ");
		int iterations = s.nextInt();

		DrawingPanel panel = new DrawingPanel(400, 400);
		Graphics g = panel.getGraphics();
		panel.setBackground(Color.YELLOW);
		drawSquare(g, 400, 400, iterations);
	}

	public static void drawSquare(Graphics g, int width, int height, int iterations) {
		int x = 0;
		int y = 0;
		int w = width;
		int h = height;
		g.setColor(Color.RED);
		int deltax = w/iterations/2;
		int deltay = h/iterations/2;
		for(int ii = 0; ii < iterations; ii++) {
			g.drawRect(x,y,w,h);
			x += deltax;
			y += deltay;
			w -= 2*deltax;
			h -= 2*deltay;
		}
	}
}