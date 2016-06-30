// CircleTile.java
// This class implements a a circular tile on the screen
//
// Version 1.0 - Lauren Bricker 3/3/15

import java.awt.*;

public class CircleTile extends Tile 
{
    
   /** 
    *  Constructs a new circular shaped tile with the given coordinates, size, and color. 
    *  @param x The top left x coordinate of the bounding box of this tile
    *  @param y The top left y coordinate of the bounding box of this tile
    *  @param width The width of the bounding box of this tile
    *  @param height The height of the bounding box of this tile
    *  @param color The fill color of this tile
    */

   public CircleTile(int x, int y, int w, int h, Color c) 
   {
      super(x, y, w, h, c);
   }
    
   /** 
    * Draws this tile using the given graphics pen. 
    * @param g The graphics context on which to draw this tile. 
    */
   @Override
   public void draw(Graphics g) 
   {  
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
      g.fillOval(finalx, finaly, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(finalx, finaly, diameter, diameter);
   }

    
   /** 
    * Determines if this circle tile has been "hit" - This method 
    * takes into account the circle inside this bounding box is smaller
    * than the bounding box itself. 
    * @param x The x coordinate of the potential hit
    * @param y The y coordinate of the potential hit
    * @return Returns whether this circle tile was hit.  
    */
   @Override
   public boolean isHit(int x, int y) 
   { 
      int centerx = getX() + getWidth() / 2; 
      int centery = getY() + getHeight() / 2; 
      int diameter = Math.min(getWidth(), getHeight()); 
      
      return distance(x, y, centerx, centery) < diameter / 2; 
   }
   
   /**
    * Return the distance between two points
    * @param x1 The x coordinate of the first point
    * @param y1 The y coordinate of the first point
    * @param x2 The x coordinate of the second point
    * @param y2 The y coordinate of the second point 
    * @return The distance between the two points
    */ 
   private double distance(int x1, int y1, int x2, int y2)
   {
      int xdist = x2 - x1; 
      int ydist = y2 - y1; 
      return Math.sqrt(xdist * xdist + ydist * ydist); 
   }
   
   
    /** Returns a text representation of this tile, such as "Rectangle: (x=57,y=148,w=26,h=53)". */
   @Override
   public String toString() {
      return "Circle " + super.toString(); 
   }
}
