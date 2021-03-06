package org.fireplume.algorithms.tree_with_balance.binary;

/**
 * Created by cloudjumper on 3/6/17.
 */

/**
 * Here is the AVL-Node class for Completenesse
 **/
public class AvlNode {
    public AvlNode left;
    public AvlNode right;
    public AvlNode parent;
    public int key;
    public int balance;

    public AvlNode(int k) {
        left = right = parent = null;
        balance = 0;
        key = k;
    }

    public String toString() {
        return "" + key;
    }

}