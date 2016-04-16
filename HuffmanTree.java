/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmantrees;



import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import static java.lang.Math.E;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to represent and build a Huffman tree.
 * @author Koffman and Wolfgang
 **/
public class HuffmanTree implements Serializable  {

    StringBuilder buildx = new StringBuilder();
    // Nested Classes
    /** A datum in the Huffman tree. */
    public static class HuffData implements Serializable {
        // Data Fields

        /** The weight or probability assigned to this HuffData. */
        private double weight;
        /** The alphabet symbol if this is a leaf. */
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        public Character getSymbol() {return symbol;}
    }
    // Data Fields
    /** A reference to the completed Huffman tree. */
    protected BinaryTree<HuffData> huffTree;

    /** A Comparator for Huffman trees; nested class. */
    private static class CompareHuffmanTrees
            implements Comparator<BinaryTree<HuffData>> {

        /**
         * Compare two objects.
         * @param treeLeft The left-hand object
         * @param treeRight The right-hand object
         * @return -1 if left less than right,
         * 0 if left equals right,
         * and +1 if left greater than right
         */
        @Override
        public int compare(BinaryTree<HuffData> treeLeft,
                BinaryTree<HuffData> treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }

    /*<listing chapter="6" number="10">*/
    /**
     * Builds the Huffman tree using the given alphabet and weights.
     * @post  huffTree contains a reference to the Huffman tree.
     * @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue<BinaryTree<HuffData>> theQueue =
                new PriorityQueue<BinaryTree<HuffData>>(symbols.length,
                new CompareHuffmanTrees());
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree<HuffData> aBinaryTree =
                    new BinaryTree<HuffData>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree<HuffData> left = theQueue.poll();
            BinaryTree<HuffData> right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree<HuffData> newTree =
                    new BinaryTree<HuffData>(sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }
    /*</listing>*/

    /**
     * Outputs the resulting code.
     * @param out A PrintStream to write the output to
     * @param code The code up to this node
     * @param tree The current node in the tree
     */
    private void printCode(PrintStream out, String code,BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != null) {
            if (theData.symbol.equals(' ')) {
                out.println("space: " + code);
            } else {
                out.println(theData.symbol + ": " + code);
            }
        } else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /**
     * Outputs the resulting code.
     * @param out A PrintStream to write the output to
     */
    public void printCode(PrintStream out) {
        printCode(out, "", huffTree);
    }


    
    
    
    
    /*<listing chapter="6" number="11">*/
    /**
     * Method to decode a message that is input as a string of
     * digit characters '0' and '1'.
     * @param codedMessage The input message as a String of
     *        zeros and ones.
     * @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }
    
    /**
     * Print codes and their wiegths as string
    */
    public String toString(){
        
        String encodedCodes = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        this.printCode(ps);
        //Htree.printCode(ps);
        
        try {
            encodedCodes = os.toString("UTF8");
            System.out.println("Encoded Codes : \n"+encodedCodes);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HuffmanTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encodedCodes;
    }
    
    /*</listing>*/

// Insert solution to programming exercise 1, section 6, chapter 6 here
    

    
    
        
    /**      
        *  Method to encode string  message into Huffman encodes.
        * @param message The input message as a String
        *  which is composed on the specified alphabet in the book
        * @param huffmanTree It’s created huffman code for the alphabet
        * @return The encoded message as a String zero and ones.      
        */       
        public String encode(String message) throws CloneNotSupportedException{
           
            StringBuilder build = new StringBuilder();
            BinaryTree<HuffData> tempTree;
            
            for (int i = 0; i < message.length(); i++){
                tempTree = huffTree;
                
                //Call wrapper function for a character code
                String str = encode(message.charAt(i), tempTree);
                
                if(str==null){
                    System.err.println("Nulll");
                    return null;
                }else
                    build.append(str); 
                
            }
            
            return build.toString();
        }
        /**
         * 
         * @param ch 
         * @param tempTree search character in tree 
         * @return string
         * 
         * if tree is contain character return encode,not null
         */
        private String encode(char ch,BinaryTree<HuffData> tempTree){
          
           //if not found character return null
           if(tempTree.isLeaf() && !tempTree.getData().getSymbol().equals(ch) ){
                return null;
           }
           
           //if find character return emty string
           if(tempTree.isLeaf() && tempTree.getData().getSymbol().equals(ch)){
                return "";
           }
           
           
           //search in left side
            String left = encode(ch,tempTree.getLeftSubtree());
            if(left!=null){
                return "0" + left;
            }

            //search in right side
            String right = encode(ch,tempTree.getRightSubtree());
            if(right!=null){
		return "1" + right; 
            }
          
           return null;
       }
}

/*</listing>*/
