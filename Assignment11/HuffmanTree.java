// Eli F.
// Section: C
// Assignment 12
// Description: implemtents the binary tree that will be used to decode and encoded based on huffman
// algorithm
// Class name: HuffmanTree
// Version 1.0
// 3/9/16

import java.util.*;
import java.io.*;

public class HuffmanTree {

	private static int RIGHT_VALUE = 1;
	private static int LEFT_VALUE = 0;

	//private ArrayList<Integer> charCode;
	private PriorityQueue<HuffmanNode> queue;
	private HuffmanNode eof;
	private HuffmanNode root; //the root of the HuffmanTree

	private String charCode;
	private int codeNum;

	public HuffmanTree(int[] counts) {
		this.queue = new PriorityQueue<HuffmanNode>(counts.length);
		//this.charCode = new ArrayList<Integer>(counts.length);
		this.charCode = "";
		this.codeNum = 0;
		getEOF(counts);
		fillQueue(counts);
		createTree();
	}

	private HuffmanNode desendTree(HuffmanNode node) {
		HuffmanNode leftChild = node.getLeftChild();
		HuffmanNode rightChild = node.getRightChild();
		if(leftChild == null && rightChild == null) {
			return node;
		}
		else if(leftChild == null) {
			charCode += LEFT_VALUE;
			return desendTree(rightChild);
		}
		else if(rightChild == null) {
			charCode += RIGHT_VALUE;
			return desendTree(leftChild);
		}
		else {
			charCode += RIGHT_VALUE;
			return desendTree(rightChild); //if node has two intacts children then just go down to the right
		}
	}

	private void createTree() {
		if(queue.size() == 1) {
			root = queue.poll();
		}
		else {
			HuffmanNode firstRemoved = queue.poll();
			HuffmanNode secondRemoved = queue.poll();
			//for brancking nodes i made the value -1 so they can be distinguished from other nodes representing letters
			HuffmanNode newNode = new HuffmanNode(-1, (firstRemoved.getFrequency()+secondRemoved.getFrequency()));
			newNode.setLeftChild(firstRemoved);
			newNode.setRightChild(secondRemoved);
			firstRemoved.setParent(newNode);
			secondRemoved.setParent(newNode);
			queue.add(newNode); 
			createTree();
		}
	}

	private void fillQueue(int[] counts) {
		//adds all of the nodes from counts
		for(int ii = 0; ii < counts.length; ii++) {
			if(counts[ii] != 0) { //dont want to add anything that has frequncy of zero
				codeNum++;
				queue.add(new HuffmanNode(counts[ii], ii));
			}
		}
		queue.add(eof);
	}


	//determines largest charValue
	private void getEOF(int[] counts) {
		int largest = -1; //because no -1 index of array
		for(int ii = 0; ii < counts.length; ii++) {
			if(counts[ii] == 0) {
				largest = ii; 
			}
		}
		if(largest == -1) {
			largest = counts.length;
		}
		eof = new HuffmanNode(largest+1, 1); //creates a node that represents the end of file
	}

	public void write(PrintStream output) {
		HuffmanNode currentNode = root;
		for(int ii = 0; ii < codeNum; ii++) {
			HuffmanNode leafNode = desendTree(currentNode);

			output.println(leafNode.getCharValue());
			output.println(charCode);
			charCode = ""; //reset the charCode variable so it can be used again

			currentNode = leafNode.getParent();
			deleteNode(leafNode);
		}
	}

	private void deleteNode(HuffmanNode node) {
		node = null; //sets the node to null effectivly removing it from the binary tree 
	}

	/*public HuffmanTree(Scanner input) {

	}

	public void decode(BitInputStream input, PrintStream output, int eof) {

	}
*/
}