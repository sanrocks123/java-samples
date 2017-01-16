/** SYMANTEC: Copyright 2015 Symantec Corporation. All rights reserved.
 * THIS SOFTWARE CONTAINS CONFIDENTIAL INFORMATION AND TRADE SECRETS OF
 * SYMANTEC CORPORATION.USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED
 * WITHOUT THE PRIOR EXPRESS WRITTEN PERMISSION OF SYMANTEC CORPORATION.
 * The Licensed Software and Documentation are deemed to be commercial
 * computer software as defined in FAR 12.212 and subject to restricted
 * rights as defined in FAR Section 52.227-19 "Commercial Computer Software
 * - Restricted Rights" and DFARS 227.7202, "Rights in Commercial Computer
 * Software or Commercial Computer Software Documentation", as applicable,
 * and any successor regulations.  Any use, modification, reproduction
 * release, performance, display or disclosure of the Licensed Software
 * and Documentation by the U.S. Government shall be solely in accordance
 * with the terms of this Agreement.
 */
/********************************************************************
 * File Name:    BST.java
 *
 * Date Created: Feb 4, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.sample;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class BST {

    Node root = null;

    private static class Node {
        Integer data;
        Node left = null;
        Node right = null;

        Node(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

    }

    public void insert(Integer data) {
        root = insert(root, data);
    }

    public boolean lookup(int data) {
        return lookup(root, data);
    }

    public int size() {
        return size(root);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    public int minValue() {
        return minValue(root);
    }

    /**
     * @param root2
     * @return
     */
    private int minValue(Node root2) {
        if (null == root2.left)
            return root2.data;
        else
            return minValue(root2.left);
    }

    /**
     * @param root
     * @return
     */
    private int maxDepth(Node root) {
        int maxDepth = 0;
        if (null == root)
            return maxDepth;
        else {
            int ldepth = maxDepth(root.left);
            int rdepth = maxDepth(root.right);
            maxDepth = Math.max(ldepth, rdepth) + 1;
        }
        return maxDepth;
    }

    /**
     * @param root
     * @return
     */
    private int size(Node root) {
        if (null == root)
            return 0;
        return size(root.left) + 1 + size(root.right);
    }

    /**
     * @param node
     * @param data
     * @return
     */
    private boolean lookup(Node node, int data) {
        boolean isLookup = false;
        if (null == node) {
            return isLookup;
        } else if (data == node.data) {
            isLookup = true;
        } else if (data < node.data) {
            isLookup = lookup(node.left, data);
        } else {
            isLookup = lookup(node.right, data);
        }
        return isLookup;
    }

    /**
     * @param node
     * @param data
     * @return
     */
    private Node insert(Node node, Integer data) {
        if (null == node) {
            node = new Node(data);
        } else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    public void printInOrderTraversal() {
        System.out.print("InOrder Traversal : ");
        InOrderTraversal(root);
    }

    /**
     * @param root2
     */
    private void InOrderTraversal(Node root2) {
        if (root2 == null) {
            return;
        }
        InOrderTraversal(root2.left);
        System.out.print(root2.data + " ");
        InOrderTraversal(root2.right);
    }

    /**
     * 
     */
    public void printPostOrderTraversal() {
        System.out.print("\nPostOrder Traversal : ");
        postOrderTraversal(root);
    }

    /**
     * @param root2
     */
    private void postOrderTraversal(Node root2) {
        if (root2 == null) {
            return;
        }
        postOrderTraversal(root2.left);
        postOrderTraversal(root2.right);
        System.out.print(root2.data + " ");

    }

}
