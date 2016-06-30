// Eli F.
// Section: C
// Assignment 11
// Description: creates a rectangle tile 
// Class name: RectangleTile
// Version 1.0
// 3/9/16

import java.awt.*;

public class RectangleTile extends Tile {

	/**
	 * constructs a rectangle tile
	 * 
	 * @param  x xcoord of tile
	 * @param  y ycoord of tile
	 * @param  w width of tile
	 * @param  h height of tile
	 * @param  c color of tile
	 */
	public RectangleTile(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	/**
	 * draws the tile to the gui
	 * 
	 * @param g Graphics object to draw
	 */
	@Override 
	public void draw(Graphics g) {
      g.setColor(getColor());
      g.fillRect(getX(), getY(), getWidth(), getHeight());
      g.setColor(Color.BLACK);
      g.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	
	/**
	 * determines if this tile has been hit
	 * 
	 * @param  x xcoord clicked 
	 * @param  y ycoord clicked
	 * @return   if the tile has been hit or not
	 */
	@Override
   public boolean isHit(int x, int y) {  
      return x < getX()+getWidth() && x > getX() && y < getY()+getHeight() && y > getY(); 
   }
}