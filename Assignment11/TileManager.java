// Eli F.
// Section: C
// Assignment 11
// Description: manages the tiles that are part of the GUI
// Class name: TileManager
// Version 1.0
// 3/9/16

import java.util.*;
import java.awt.*;
import java.io.*;

public class TileManager {

	private ArrayList<Tile> tiles;
	private Random rand;

	//extra feilds
	/*ArrayList<Class<?>> classesThatExtendTile = new ArrayList<Class<?>>();*/
	/*private File[] files;*/

	/**
	 * the file extension for a java class
	 */
	/*private static final String CLASS_EXTENSION = "class";*/

	/**
	 * an array of Class Objects representing the types in what should appear in a tile class's constructor
	 */
	/*private static Class[] cArgs = {int.class, int.class, int.class, int.class, Color.class};*/

	/**
	 * constructor used to initilize TileManager objects
	 */
	public TileManager() {
		tiles = new ArrayList<Tile>();
		rand = new Random();

		//Extra stuff:
		/*try {
			files = new File(".").listFiles(); //gets all files in the current working directory
		}
		catch(Exception e) {
			System.out.println("Files not found");
			e.printStackTrace();
		}
		for(File f: files) {
			try {
				String extension = getFileExtension(f);
				String fileName = getFileNameWithoutExtension(f);
				//determines in the file is a class file that extends Tile
				if(extension.equals(CLASS_EXTENSION) 
					&& !fileName.equals("Tile") 
					&& Tile.class.isAssignableFrom(Class.forName(fileName))) {
					classesThatExtendTile.add(Class.forName(fileName));
				}
			}
			catch(Exception e) {
				System.out.println("Stuff messed up finiding Classes extending Tile,"
					+"printing stack trace");
				e.printStackTrace();
			}
		}*/
	}

	/**
	 * This method generate a new subclass of Tile. It will randomly decide which type to return.
	 * based off of the different classes that extend Tile in the current directory
	 * assumes the Random and Color objects that are passed in are valid (not null).
	 * 
	 * @param  rand   Random object used to select type of Tile
	 * @param  x      xcoord for the Tile to be created
	 * @param  y      ycoord for the Tile to be created
	 * @param  width  width of Tile to be created
	 * @param  height height of Tile to be created
	 * @param  color  color of the Tile to be created
	 * @return        a new Tile based to be added to the DrawingPanel
	 */
	public Tile generateNewTile(Random rand, int x, int y, int width, int height, Color color) {
		/*try {
			return (Tile)classesThatExtendTile
				.get(rand.nextInt(classesThatExtendTile.size()))
				.getConstructor(cArgs)
				.newInstance(x,y,width,height,color); //selects a random class from the ArraList
		}
		catch(Exception e) {
			System.out.println("Messed up creating new Tile object");
			return null;
		}*/
		//rand.nextInt(2) becaue there are 2 possible tiles to choose from 
		if(rand.nextInt(2) == 0) {
			return new CircleTile(x,y,width,height,color);
		}
		else {
			return new RectangleTile(x,y,width,height,color);
		}	 
	}

	/**
	 * gets the name of a file without the extension
	 * 
	 * @param  file file to get the name of
	 * @return      name of file 
	 */
	private static String getFileNameWithoutExtension(File file) {
		String fileName = file.getName();
		int position = fileName.lastIndexOf(".");
		if (position > 0) {
		    return fileName.substring(0, position);
		}
		return "";
	}

	/**
	 * gets the extension of a given file
	 * 
	 * @param  file the file to get the extension of
	 * @return      extension of the file
	 */
	private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(!(fileName.lastIndexOf(".") < 0)) {
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        else return "";
    }

	/**
	 * add the given tile to the end of your tile manager's list of tiles. Returns true if the tile 
	 * was added, false if the Tile object passed in was null.
	 * 
	 * @param  tile the tile to be added to this manager
	 * @return      indicates whether this tile had been added successfully or not
	 */
	public boolean addTile(Tile tile) {
		if(tile == null) {
			return false;
		}
		else return tiles.add(tile);
	}

	/**
	 * causes all of the tiles in the tile manager to draw themselves on the screen using the given graphical pen
	 * 
	 * @param g graphics pen used to draw
	 */
	public void drawAll(Graphics g) {
		for(Tile tile: tiles) {
			tile.draw(g);
		}
	}

	/**
	 * Called when the user left-clicks. If the coordinate passed in touch any tiles 
	 * the topmost of these tiles is moved to the very top (end) of the list.
	 * 
	 * @param x xcoord clicked 
	 * @param y ycoord clicked
	 */
	public void raise(int x, int y) {
		int hitIndex = getTopIndex(x,y);
		if(hitIndex != -1) {
			Tile currentTile = tiles.get(hitIndex);
			tiles.remove(currentTile);
			tiles.add(currentTile);
		}
	}

	/**
	 * gets the top tile hit by a users click 
	 * 
	 * @param  x xcoord clicked
	 * @param  y ycoord clicked
	 * @return   the index in the list of the topmost tile 
	 */
	private int getTopIndex(int x, int y) {
		for(int ii = tiles.size()-1; ii >= 0; ii--) {
			Tile currentTile = tiles.get(ii);
			if(currentTile.isHit(x,y)) {
				return ii;
			}
		}
		return -1; //negative one represents the fact that no tile was hit
	}

	/**
	 * Called when the user Shift-left-clicks. If these coordinates touch any tiles, the topmost
	 * of these tiles is moved to the very bottom (beginning) of the list.
	 * 
	 * @param x xcoord cliked
	 * @param y ycoord cliked
	 */
	public void lower(int x, int y) {
		int hitIndex = getTopIndex(x,y);
		if(hitIndex != -1) {
			Tile currentTile = tiles.get(hitIndex);
			tiles.remove(currentTile);
			tiles.add(0,currentTile);
		}
	}

	/**
	 * Called when the user right-clicks. If the input coordinates touch any tiles, 
	 * the topmost of the tiles from the list will be deleted
	 * 
	 * @param x xcoord clicked
	 * @param y ycoord clicked
	 */
	public void delete(int x, int y) {
		int hitIndex = getTopIndex(x,y);
		if(hitIndex != -1) {
			Tile currentTile = tiles.get(hitIndex);
			tiles.remove(currentTile);
		}
	}

	/**
	 * Called when the user Shift-right-clicks. If the input coordinates touch any tiles, 
	 * all such tiles will be deleted from the list
	 * 
	 * @param x xcoord clicked
	 * @param y ycoord clicked
	 */
	public void deleteAll(int x, int y) {
		Iterator<Tile> iterator = tiles.iterator();
		while(iterator.hasNext()) {
			Tile currentTile = iterator.next();
			if(currentTile.isHit(x,y)) {
				iterator.remove(); //removes the current tile
			}
		}
	}

	/**
	 * Called when the user types S.  All the tiles are reordered in the list into a random order
	 * and all tiles get new random x/y pixel positions
	 * 
	 * @param width  width of the gui
	 * @param height height of the gui
	 */
	public void shuffle(int width, int height) {
		//gives each tile new random x and y coordinates
		for(Tile tile: tiles) {
			tile.setX(rand.nextInt(width-tile.getWidth())+1);
			tile.setY(rand.nextInt(height-tile.getHeight())+1);
		}
		Collections.shuffle(tiles); //shuffles the Z-order of the tiles
	}
}