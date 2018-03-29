package algorithms.adts;

/**
 * TreeNode
 */
class TreeNode<T extends Comparable<?>> {
    TreeNode<T> left, right;
    T item;

    public TreeNode(T data) {
        this.item = data;
    }
}