// Eli F.
// Section: C
// Assignment 12
// Description: implements a node to represent each different character value and frequency
// Class name: HuffmanNode
// Version 1.0
// 3/9/16

public class HuffmanNode implements Comparable<HuffmanNode> {

	private int charValue;
	private int frequency;

	//parent node
	private HuffmanNode parent;

	//child nodes
	private HuffmanNode leftChild;
	private HuffmanNode rightChild;

	public HuffmanNode(int charValue, int frequency) {
		this.charValue = charValue;
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

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

	public void setLeftChild(HuffmanNode n) {
		this.leftChild = n;
	}

	public void setRightChild(HuffmanNode n) {
		this.rightChild = n;
	}

	public void setParent(HuffmanNode n) {
		this.parent = n;
	}

	public HuffmanNode getRightChild() {
		return this.rightChild;
	}

	public HuffmanNode getLeftChild() {
		return this.leftChild;
	}

	public HuffmanNode getParent() {
		return this.parent;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public int getCharValue() {
		return this.charValue;
	}

}