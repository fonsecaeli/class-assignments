// Eli F.
// Section: C
// Assignment 12
// Description: implements a binary tree that will be used to decode and encoded based on huffman
// algorithm
// Class name: HuffmanTree
// Version 1.0
// 3/9/16

import java.util.*;
import java.io.*;

public class HuffmanTree {
    
    /**
    * string representing a right step on a Huffman Tree
    */
    private static String RIGHT_VALUE = "1";
    /**
    * string representing a left step on a Huffman Tree
    */
    private static String LEFT_VALUE = "0";
    
    private ArrayList<String[]> charCodes;
    private PriorityQueue<HuffmanNode> queue;
    private HuffmanNode root; //the root of the HuffmanTree
    
    //-------------------------------------------------------------------------------------------//
    // The following section of this class provides an implementation of Huffman coding used to  //
    // compress text files to around half of their original size.  The following code can be used//
    // to generate a code file which contains new encoding for each character present in file    //
    // provided to actually encoded a file one can use the Encode class.  You must generate      //
    // the code file before attempting to compress a file.                                       //
    //-------------------------------------------------------------------------------------------//
    
    /**
    * constructs a Huffman Tree from an array containing information about
    * the frequency of characters from a given text file
    *
    * @param  counts Array containing the frequencies of all the characters within a file
    */
    public HuffmanTree(int[] counts) {
        this.queue = new PriorityQueue<HuffmanNode>(counts.length);
        this.charCodes = new ArrayList<String[]>(counts.length);
        HuffmanNode eof = getEOF(counts);
        fillQueue(counts, eof);
        createTree();
        fillCharCodes(root);
    }
    
    /**
    * recursively traverses the Huffman Tree to collect the new encodings for every character
    * present in the file to be compressed, should be called on the root node of a Huffman tree
    * in order to work properly
    *
    * @param node the root node of your Huffman Tree
    */
    private void fillCharCodes(HuffmanNode node) {
        HuffmanNode leftChild = node.getLeftChild();
        HuffmanNode rightChild = node.getRightChild();
        if(leftChild != null) {
            leftChild.setCode(node.getCode() + LEFT_VALUE); //builds up the codes on the tree
            fillCharCodes(leftChild);

            rightChild.setCode(node.getCode() + RIGHT_VALUE);
            fillCharCodes(rightChild);
        }
        else {
            String[] code = {String.valueOf(node.getCharValue()), node.getCode()}; //(value, code)
            charCodes.add(code); 
        }
    }
    
    /**
    * creates the initial Huffman tree used to determine new encoding for characters
    */
    private void createTree() {
        if(queue.size() == 1) {
            root = queue.poll();
        }
        else {
            HuffmanNode firstRemoved = queue.poll();
            HuffmanNode secondRemoved = queue.poll();
            //for branching nodes i made the value -1 so they can be 
            //distinguished from other nodes representing letters
            HuffmanNode newNode = new HuffmanNode(
                -1, (firstRemoved.getFrequency()+secondRemoved.getFrequency()));
            newNode.setLeftChild(firstRemoved);
            newNode.setRightChild(secondRemoved);
            firstRemoved.setParent(newNode);
            secondRemoved.setParent(newNode);
            queue.add(newNode);
            createTree();
        }
    }
    
    /**
    * fills the priority queue with nodes representing all the characters and there frequencies
    * from the file to be compressed
    *
    * @param counts array containing the frequencies of all the characters within the file to
    * be compressed
    * @param eof node representing the end of file character
    */
    private void fillQueue(int[] counts, HuffmanNode eof) {
        //adds all of the nodes from counts
        for(int ii = 0; ii < counts.length; ii++) {
            if(counts[ii] != 0) { //dont want to add anything that has frequncy of zero
                queue.add(new HuffmanNode(ii, counts[ii]));
            }
        }
        queue.add(eof);
    }
    
    /**
    * determines the value of the end of file character based off of the
    * number of characters within the given file
    *
    * @param counts array containing the frequencies of all the characters within the file to
    * be compressed
    * @return the node representing the end of character in the file
    */
    private HuffmanNode getEOF(int[] counts) {
        int largest = -1; //because no -1 index of array
        for(int ii = 0; ii < counts.length; ii++) {
            if(counts[ii] == 0) {
                largest = ii;
            }
        }
        if(largest == -1) {
            largest = counts.length;
        }
        return new HuffmanNode(largest+1, 1); //creates a node that represents the end of file
    }
    
    /**
    * writes the new encoding for all the characters in the file to be compressed to a text file
    * will write the encoding along with the characters ASCII encoding to be used to decoded
    * encoded file
    *
    * @param output PrintStream used to write the encodings to a file
    */
    public void write(PrintStream output) {
        for(int ii = 0; ii < charCodes.size(); ii++) {
            output.println(charCodes.get(ii)[0]);
            output.println(charCodes.get(ii)[1]);
        }
    }
    
    //-------------------------------------------------------------------------------------------//
    // The following section of this class can be used to decoded a file encoded using the code  //
    // file that can be generated by using the first section of this class. You must generate the//
    // code file using a Huffman Tree that corresponds to the original text file in order to use //
    // the following code  													                     //
    //-------------------------------------------------------------------------------------------//
    
    /**
    * constructs a Huffman tree given a scanner on the file containing the
    * codes generated by using the Huffman Algorithm
    *
    * @param  input Scanner on the file containing the alternate character encodings
    */
    public HuffmanTree(Scanner input) {
        root = new HuffmanNode(-1,0); //root node, will branch off of it to create the Tree
        while(input.hasNextLine()) {
            int charValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            branchOut(code, root, charValue);
        }
    }
    
    /**
    * method that does most of the heavy lifting for recreating the Huffman Tree
    * method is implemented recursively
    *
    * @param code      the character code generated by the original Huffman Tree
    * @param node      the current node
    * @param charValue the value of leaf node which will eventually be created
    */
    private void branchOut(String code, HuffmanNode node, int charValue) {
        HuffmanNode rightChild = node.getRightChild();
        HuffmanNode leftChild = node.getLeftChild();
        String nextDirection = null;
        if(!code.isEmpty()) nextDirection = Character.toString(code.charAt(0));
        HuffmanNode nextNode = null;
        if(code.length() == 1) {
            nextNode = new HuffmanNode(charValue, 0);
            if(nextDirection.equals(RIGHT_VALUE)) node.setRightChild(nextNode);
            else if(nextDirection.equals(LEFT_VALUE)) node.setLeftChild(nextNode);
        }
        else if(nextDirection.equals(RIGHT_VALUE) && rightChild == null) {
            nextNode = new HuffmanNode(-1, 0);
            node.setRightChild(nextNode); //place holder node inserted into tree
            branchOut(code.substring(1,code.length()), nextNode, charValue);
        }
        else if(nextDirection.equals(LEFT_VALUE) && leftChild == null) {
            nextNode = new HuffmanNode(-1, 0);
            node.setLeftChild(nextNode); //place holder node inserted into tree
            branchOut(code.substring(1,code.length()), nextNode, charValue);
        }
        else if(nextDirection.equals(RIGHT_VALUE) && rightChild != null) {
            branchOut(code.substring(1,code.length()), rightChild, charValue);
        }
        else if(nextDirection.equals(LEFT_VALUE) && leftChild != null) {
            branchOut(code.substring(1,code.length()), leftChild, charValue);
        }
    }
    
    /**
    * decodes a given file from its compressed form to the original
    *
    * @param input  BitInputStream used to read the bits from an encoded file
    * @param output PrintStream used to write the decoded test to a file
    * @param eof    representation of the end of file character used to determine stopping point
    */
    public void decode(BitInputStream input, PrintStream output, int eof) {
        HuffmanNode currentNode = root;
        while(currentNode.getCharValue() != eof) {
            int nextBit = input.readBit();
            HuffmanNode leftChild = currentNode.getLeftChild();
            HuffmanNode rightChild = currentNode.getRightChild();
            if(leftChild == null && rightChild == null) {
                output.write(currentNode.getCharValue());
                currentNode = getNextNode(nextBit, root);
            }
            else {
                currentNode = getNextNode(nextBit, currentNode);
            }
        }
    }
    
    /**
    * descends the Huffman Tree according to the directions given by the bits in the encoded file
    * helper method for the decode method
    *
    * @param  nextBit the next bit from the encoded file
    * @param  node    the current node we are on
    * @return the next node down the tree to be examined
    */
    private HuffmanNode getNextNode(int nextBit, HuffmanNode node) {
        if(String.valueOf(nextBit).equals(RIGHT_VALUE)) {
            return node.getRightChild();
        }
        else {
            return node.getLeftChild();
        }
    }
}