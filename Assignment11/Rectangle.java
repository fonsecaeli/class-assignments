import java.awt.*;

public class RectangleTile extends Tile {

	RectangleTile(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	@Override 
	public void draw(Graphics g) {
		// First calculate the smaller dimension: width or height. 
      int diameter = Math.min(getWidth(), getHeight()); 
      int finalx = getX(); 
      int finaly = getY(); 
      int finalwidth = getWidth(); 
      int finalheight = getHeight(); 
      
      // figure out the actual location of the bouncing box of the circle 
      // which means moving either the x or y coordinate of the given bounding box. 
      if ( getWidth() < getHeight() ) 
      {
         finaly = getY() + (getHeight() - diameter) / 2; 
      }
      else 
      {
         finalx = getX() + (getWidth() - diameter) / 2;
      }
      g.setColor(getColor());
      g.fillRect(finalx, finaly, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawRect(finalx, finaly, diameter, diameter);
	}

	@Override
   public boolean isHit(int x, int y) 
   { 
      int centerx = getX() + getWidth() / 2; 
      int centery = getY() + getHeight() / 2; 
      int diameter = Math.min(getWidth(), getHeight()); 
      
      return distance(x, y, centerx, centery) < diameter / 2; 
   }

   private double distance(int x1, int y1, int x2, int y2)
   {
      int xdist = x2 - x1; 
      int ydist = y2 - y1; 
      return Math.sqrt(xdist * xdist + ydist * ydist); 
   }
}