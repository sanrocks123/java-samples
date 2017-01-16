package com.sanjeev.sample;

import org.testng.annotations.Test;

public class BSTTest {

    @Test
    public void Test() {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(15);
        bst.insert(5);

        System.out.println(bst.size());

    }
}
