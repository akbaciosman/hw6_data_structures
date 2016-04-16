/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmantrees;

import huffmantrees.BinarySearchTree.TreeIterator;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author osman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException,
            UnsupportedEncodingException, CloneNotSupportedException{
        Main main = new Main();
        
        Scanner scIn = new Scanner(System.in);
        System.out.println("1(Part1)\n2(Part2)\n3(Part3)\nPlease enter>>");
        int select = scIn.nextInt();
        
        if(select==1){
            main.part1();
        }else if(select==2){
            main.part2();
        }else if(select==3){
            main.part3();
        }else{
            System.err.println("Wrong Select part");
        }
        
    }
    
    
    void part1() throws UnsupportedEncodingException, CloneNotSupportedException {
                HuffmanTree Htree = new HuffmanTree();
        
        // Create symbol array
        HuffmanTree.HuffData[] symbols = {
                            new HuffmanTree.HuffData(186, '_'),
                            new HuffmanTree.HuffData(103, 'e'),
                            new HuffmanTree.HuffData(80, 't'),
                            new HuffmanTree.HuffData(64, 'a'),
                            new HuffmanTree.HuffData(63, 'o'),
                            new HuffmanTree.HuffData(57, 'i'),
                            new HuffmanTree.HuffData(57, 'n'),
                            new HuffmanTree.HuffData(51, 's'),
                            new HuffmanTree.HuffData(48, 'r'),
                            new HuffmanTree.HuffData(47, 'h'),
                            new HuffmanTree.HuffData(32, 'd'),
                            new HuffmanTree.HuffData(32, 'l'),
                            new HuffmanTree.HuffData(23, 'u'),
                            new HuffmanTree.HuffData(22, 'c'),
                            new HuffmanTree.HuffData(21, 'f'),
                            new HuffmanTree.HuffData(20, 'm'),
                            new HuffmanTree.HuffData(18, 'w'),
                            new HuffmanTree.HuffData(16, 'y'),
                            new HuffmanTree.HuffData(15, 'g'),
                            new HuffmanTree.HuffData(15, 'p'),
                            new HuffmanTree.HuffData(13, 'b'),
                            new HuffmanTree.HuffData(8, 'v'),
                            new HuffmanTree.HuffData(5, 'k'),
                            new HuffmanTree.HuffData(1, 'j'),
                            new HuffmanTree.HuffData(1, 'q'),
                            new HuffmanTree.HuffData(1, 'x'),
                            new HuffmanTree.HuffData(1, 'z')
        };
        
        // Build hufffman tree 
        Htree.buildTree(symbols);
        
        // Print huffman codes of the symbols
        String EncodedSymbolList = Htree.toString();
        BinarySearchTree<String> str = new BinarySearchTree<String>();
        
        // Decode huffman codes to symbıls
       /* String code = "110000100111111100101000011";
          String decodedCode = Htree.decode(code);
          System.out.println("Code to Message : \n"+code+" : \t"+decodedCode);
        
        */
        //System.out.println("ToString : " + Htree.toString());
        System.out.println("Data : "+ "osman \nCode : " + Htree.encode("osman"));
    
    
    }
    
    void part2(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(4);
        tree.add(2);
        tree.add(14);
        tree.add(3);
        tree.add(8);
        tree.add(6);
        tree.add(15);
        tree.add(22);
        tree.add(11);
        tree.add(32);
        tree.add(64);
        tree.add(40);
        tree.add(10);
        tree.add(7);
        tree.add(5);
       TreeIterator it = tree.getIterator();
       System.out.print("Elements inOrder = [");
        while(it.hasNext()){
           System.out.print(it.next()+ "");
           if(it.hasNext())
                System.out.print(",");
        }
        System.out.println("]");
        
    }
    
    void part3(){
        Scanner scIn = new Scanner(System.in);
        System.out.println("1(ArrayQueue)\n2(LinkedQueue)\n3(VectorQueue)\nPlease enter>>");
        int select = scIn.nextInt();
        
        System.out.println("--100 elemets random--");
        if(select==1){
            arrayTest();
        }else if(select==2){
            linkedTest();
        }else if(select==3){
            vectorTest();
        }else{
            System.err.println("Queue wrong Select");
        }
        
    }
    
    
    private void arrayTest(){
        
        PriorityQueueArrayList array = new PriorityQueueArrayList();
        Random rand = new Random();

        System.out.println("EnQueue"); 
        long start_time = startTime();
        
        for (int i = 0; i < 100; i++) {
            int  n = rand.nextInt(100) + 1;
            array.add(n);
        }

        endTime(start_time);
        
        System.out.println("DeQueue");
        start_time = startTime();
        
        for (int i = 0; i < 100; i++) {
            System.out.print(array.poll()+",");
        }
        
        endTime(start_time);
    
    }
    

    
    
    private void linkedTest(){
        
        PriorityQueueLinkedList array = new PriorityQueueLinkedList();
        Random rand = new Random();

        System.out.println("EnQueue"); 
        long start_time = startTime();
        
        for (int i = 0; i < 100; i++) {
            int  n = rand.nextInt(100) + 1;
            array.add(n);
        }

        endTime(start_time);
        
        System.out.println("DeQueue");
        start_time = startTime();
        
        for (int i = 0; i < 100; i++) {
            System.out.print(array.poll()+",");
        }
        
        endTime(start_time);
    
    }


    private void vectorTest(){
        
        PriorityQueueUnsrotedVector array = new PriorityQueueUnsrotedVector();
        Random rand = new Random();

        System.out.println("EnQueue"); 
        long start_time = startTime();
        
        for (int i = 0; i < 100; i++) {
            int  n = rand.nextInt(100) + 1;
            array.add(n);
        }

        endTime(start_time);
        
        System.out.println("DeQueue");
        start_time = startTime();
        
        for (int i = 0; i < 100; i++) {
            System.out.print(array.poll()+",");
        }
        
        endTime(start_time);
    
    }
        
        
        
    public long startTime(){
        long startTime = System.currentTimeMillis();
        return startTime; 
    }
    
    public void endTime(long startTime){
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("\nEnd - start time  : "+totalTime);
        
    }
}
