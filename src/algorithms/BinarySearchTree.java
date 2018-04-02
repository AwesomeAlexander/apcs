package algorithms;

import utils.BinaryTree;
import utils.TreeNode;

/**
 * BinarySearchTree
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    public void put(E item) {this.insert(item);}
    public int getFrequency(E item) {return this.countElement(item);}

    public void printRange(E start,E end) {this.printRange(this.root,start,end);}
    public void printRange(TreeNode<E> root,E start,E end) {
        if (root == null) return;
        if (start.compareTo(root.item)<=0) this.printRange(root.left, start, end);
        if (start.compareTo(root.item)<=0 && end.compareTo(root.item)>=0) System.out.println(root.item);
        if (end.compareTo(root.item)>=0) this.printRange(root.right, start, end);
    }
}