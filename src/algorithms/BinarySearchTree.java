package algorithms;

import utils.BinaryTree;
import utils.TreeNode;

/**
 * BinarySearchTree
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    public void put(E item) {this.insert(item);}
    public int getFrequency(E item) {return this.countElement(item);}
    public void printRange(E start,E end) {this.doToRange(start,end,(TreeNode<E> e)->System.out.println(e.item));}
}