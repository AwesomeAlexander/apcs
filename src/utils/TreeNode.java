package utils;

/**
 * TreeNode
 */
public class TreeNode<T extends Comparable<?>> {
    public TreeNode<T> left, right;
    public T item;

    public TreeNode(T data) {
        this.item = data;
    }
}