import java.awt.*;

public class GraphicHelloWorld {
	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(200, 100);
		panel.setBackground(Color.CYAN);

		Graphics g = panel.getGraphics();

		g.drawString("Hello World!!!!!", 20, 20);
		System.out.println(Color.CYAN.toString());
	}
}