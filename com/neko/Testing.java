package com.neko;

import com.neko.BSTFunc.BSTNode;
import com.neko.AVLFunc.AVLNode;

public class Testing {


    public static void main(String[] args)
    {

        // Question 5, part a
        int[] rarr = Toolset.getSortedArray(100);
        BSTNode bst = Toolset.buildBSTRec(rarr);
        AVLNode avl = Toolset.buildAVLIter(rarr);
        System.out.println("BST:");
        bst.print();
        System.out.println("\n\nAVL:");
        avl.print();

        // Question 5. part c
        rarr = Toolset.getSortedArray(100);
        BSTNode bst2 = Toolset.buildBSTIter(rarr);
        AVLNode avl2 = Toolset.buildAVLIter(rarr);
        System.out.println("BST:");
        bst2.print();
        System.out.println("\n\nAVL:");
        avl2.print();

        // Question 6, part a

    }

    /* Question 5, part b
        it seems that getRandomArray(10000) not working due to print statement error or memory error. (not enough space allocated)

     */
}
