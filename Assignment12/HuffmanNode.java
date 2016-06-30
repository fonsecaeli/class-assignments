// Eli F.
// Section: C
// Assignment 12
// Description: implements a node to represent each different character value and frequency
// Class name: HuffmanNode
// Version 1.0
// 3/9/16

public class HuffmanNode implements Comparable<HuffmanNode> {

	//main information stored by each node
	private int charValue;
	private int frequency;

	//parent node
	private HuffmanNode parent;

	//child nodes
	private HuffmanNode leftChild;
	private HuffmanNode rightChild;

	//field used for reading all of the character codes from a binary tree
	//of Huffman nodes
	private String code;

	/**
	 * constructs a node to be incorporated into a Huffman tree
	 * 
	 * @param  charValue the nodes value
	 * @param  frequency the frequency the nodes value appears in the file being encoded or decoded
	 */
	public HuffmanNode(int charValue, int frequency) {
		this.code = "";
		this.charValue = charValue;
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

	/**
	 * setter for the code field of this class
	 *  
	 * @param code the new code for this node
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * getter method for the code field of this class
	 * 
	 * @return the code associated with this node
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * used to compare on Huffman Node to another
	 * nodes are compared based off there frequency
	 * 
	 * @param  other the node to compare to this one
	 * @return the hierarchy between the nodes being compared       
	 */
	public int compareTo(HuffmanNode other) {
		if(this.frequency > other.getFrequency()) {
			return 1;
		}
		else if(this.frequency < other.getFrequency()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	/**
	 * sets the left child of this node to a specified node
	 * 
	 * @param n the node to set as this nodes left child
	 */
	public void setLeftChild(HuffmanNode n) {
		this.leftChild = n;
	}

	/**
	 * sets the right child of this node to a specified node
	 * 
	 * @param n the node to set as this nodes right child
	 */
	public void setRightChild(HuffmanNode n) {
		this.rightChild = n;
	}

	/**
	 * sets the parent node of this node to a specified node
	 * 
	 * @param n the node to set as this nodes parent
	 */
	public void setParent(HuffmanNode n) {
		this.parent = n;
	}

	/**
	 * gets the right child of this node
	 * 
	 * @return the right child of this node
	 */
	public HuffmanNode getRightChild() {
		return this.rightChild;
	}

	/**
	 * gets the left child of this node
	 * 
	 * @return the left child of this node
	 */
	public HuffmanNode getLeftChild() {
		return this.leftChild;
	}

	/**
	 * gets the parent node of this node
	 * 
	 * @return the parent node of this node
	 */
	public HuffmanNode getParent() {
		return this.parent;
	}

	/**
	 * gets the frequency associated with this node
	 * 
	 * @return the frequency of this node
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * gets the character value of this node
	 * 
	 * @return the character value of this node
	 */
	public int getCharValue() {
		return this.charValue;
	}
}