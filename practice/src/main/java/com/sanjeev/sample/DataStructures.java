package com.sanjeev.sample;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class DataStructures {

    public static void main(String[] args) {

        BST bst = new BST();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);

        System.out.println("Lookup  :" + bst.lookup(5));
        System.out.println("MaxDepth : " + bst.maxDepth());
        System.out.println("MinValue : " + bst.minValue());
        bst.printInOrderTraversal();
        bst.printPostOrderTraversal();

    }

}
