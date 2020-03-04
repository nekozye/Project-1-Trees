package com.neko;

import java.util.Arrays;
import java.util.Random;
import com.neko.AVLFunc.AVLNode;
import com.neko.BSTFunc.BSTNode;

public class Toolset {

    //part a and b on question 3

    static int[] getRandomArray(int size){
        int[] arr = getSortedArray(size);
        int[] orig = getSortedArray(size);

        Random rand = new Random();  // Random number generator
        while(Arrays.equals(arr,orig)) {
            for (int i = 0; i < arr.length; i++) {
                int randomPosition = rand.nextInt(arr.length);
                int temp = arr[i];
                arr[i] = arr[randomPosition];
                arr[randomPosition] = temp;
            }
        }

        return arr;
    }

    static int[] getSortedArray(int size){
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
        {
            arr[i] = size-i;
        }
        return arr;
    }

    static BSTNode buildBSTRec(int[] list){
        BSTNode tree = new BSTNode(list[0]);
        for(int i = 1; i < list.length; i++)
        {
            BSTFunc.insertRec(tree,list[i]);
        }
        return tree;
    }

    static BSTNode buildBSTIter(int[] list){
        BSTNode tree = new BSTNode(list[0]);
        for(int i = 1; i < list.length; i++)
        {
            BSTFunc.insertIter(tree,list[i]);
        }
        return tree;
    }

    static AVLNode buildAVLIter(int[] list){
        AVLNode tree = new AVLNode(list[0]);
        for(int i = 1; i < list.length; i++)
        {
            AVLFunc.insertIter(tree,list[i]);
        }
        return tree;
    }
}
