//eli f
//generates random initial worlds for conways game of life

import java.util.*;
import java.awt.*;
import java.io.*;

public class WorldGenerator {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		PrintStream fileWriter = Inputter.getFileWriter(input, "New world name? ");
		int width = Inputter.getNumber(input, "World length: ", 1,1000);
		int length = Inputter.getNumber(input, "World width: ", 1, 1000);
		double density = Inputter.getNumber(input, "World density: ", 0, 10);
		char[][] world = new char[width][length];
		populateWorld(world, density);
		writeToFile(world, fileWriter, width, length);
	}

	public static void populateWorld(char[][] world, double density) {
		for(int ii = 0; ii < world.length; ii++) {
			for(int jj = 0; jj < world[ii].length; jj++) {
				if(Math.random() > density/10.0) {
					world[ii][jj] = 'x';
				}
				else {
					world[ii][jj] = '.';
				}
			}
		}
	}

	public static void writeToFile(char[][] world, PrintStream fileWriter, int width, int length) {
	fileWriter.println(length+" "+width);
   	for(char[] rows: world) {
   		for(char cells: rows) {
   			fileWriter.print(cells);	
   		}
   		fileWriter.println();
      }
   }
}