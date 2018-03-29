package algorithms.adts;

/**
 * SelfBinaryTree
 */
public class SelfBinaryTree<E extends Comparable<?>> {
	TreeNode<E> root;

	public SelfBinaryTree() {}
	public SelfBinaryTree(TreeNode<E> root) {
		this.root = root;
	}

	public void insert(E item) {
		TreeNode<E> n = new TreeNode<E>(item);
		for (TreeNode<E> runner=this.root;;) {
			if (n.item.compareTo(runner.item) >= 0) {
				if (runner.right == null) {
					runner.right = n;
					return;
				} else {
					runner = runner.right;
				}
			} else {
				if (runner.left == null) {
					runner.left = n;
					return;
				} else {
					runner = runner.left;
				}
			}
		}
	}

	public TreeNode<E> find(E item) {return find(item,this.root)}
	public TreeNode<E> find(E item,TreeNode<E> root) {
		if (root == null) return null;
		if (item.compareTo(root.item) == 0) return root;
		if (item.compareTo(root.item) > 0) return find(item,root.right);
		if (item.compareTo(root.item) < 0) return find(item,root.left);
		return null; // Should never reach.
	}
}