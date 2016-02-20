import java.awt.image.BufferStrategy;

public class Buffer {

	public static void main(String[] args) {
		DrawingPanel p = new DrawingPanel(500, 500);
	 	BufferStrategy bs = getBufferStrategy();
	 	if(bs  == null) {
	 		try {
				createBufferStrategy(3);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.fillRect(30, 20, 30, 30);
		
		g.dispose();
		bs.show();
	 	}
	}