package utils;

/**
 * SelfBinaryTree
 */
public class BinaryTree<E extends Comparable<E>> {

	/* // Testing 
	static java.util.ArrayList<String> elements;
	public static void main(String[] args) {
		BinaryTree<String> tree = new BinaryTree<String>();
		tree.insert("hi");
		tree.insert("there");
		tree.insert("wassup");
		tree.insert("dedified");
		tree.insert("xenophobia");
		tree.insert("apples");
		tree.insert("fork");
		tree.insert("the");
		tree.insert("the");
		tree.insert("the");
		tree.insert("the");
		tree.insert("the");
		tree.insert("barium");
		
		// Testing
		System.out.println(tree.find("wassup").item);
		System.out.println(tree.contains("apples"));
		System.out.println(tree.size());
		System.out.println(tree.sumChars());
		System.out.println("'the' appears "+tree.countElement("the")+" times.");
		// System.out.println(tree.sumTotal()); // Should Error in attempting to cast String to Integer

		// Testing to print all in order
		elements = new java.util.ArrayList<String>();
		tree.complexSizeBool(tree.root, (TreeNode<String> e)->{elements.add(e.item);return false;} );
		System.out.println(elements);
	}
	*/

	protected TreeNode<E> root;

	public BinaryTree() {}
	public BinaryTree(E rootItem) {this(new TreeNode<E>(rootItem));}
	public BinaryTree(TreeNode<E> root) {
		this.root = root;
	}

	public void insert(E item) {
		TreeNode<E> n = new TreeNode<E>(item);
		if (this.root == null) {root=n;return;}
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

	public TreeNode<E> find(E item) {
		// Recursive
		return find(item,this.root);

		// Iterative
		/* {
			TreeNode<E> runner = this.root;
			while (runner != null) {
				if (item.compareTo(runner.item) < 0) return runner;
				if (item.compareTo(runner.item) > 0) runner = runner.right;
				else runner = runner.left;
			}
			return null;
		} */
	}
	public TreeNode<E> find(E item,TreeNode<E> root) {
		if (root == null) return null;
		if (item.compareTo(root.item) == 0) return root;
		if (item.compareTo(root.item) > 0) return find(item,root.right);
		if (item.compareTo(root.item) < 0) return find(item,root.left);
		return null; // Should never reach.
	}

	public boolean contains(E item) {
		return this.find(item) != null;
	}

	public int size() {return this.size(this.root);}
	public int size(TreeNode<E> root) {return this.complexSizeBool(this.root,(TreeNode<E> e)->true);}
	public int complexSizeBool(TreeNode<E> root,Function<TreeNode<E>,Boolean> tester) {return this.complexSize(root, (TreeNode<E> e)->(tester.run(e)?1:0) );}
	public int complexSize(TreeNode<E> root,Function<TreeNode<E>,Integer> tester) {
		if (root == null) return 0;
		return this.complexSize(root.left,tester) + tester.run(root) + this.complexSize(root.right,tester);
	}

	public int countElement(E item) {
		return this.complexSizeBool(this.root, (TreeNode<E> e)->(e.item.equals(item)) );
	}

	/**
	 * For Integer Trees Only.
	 */
	public int sumTotal() throws ClassCastException {
		return this.complexSize(this.root, (TreeNode<E> e)->(int)e.item );
	}

	/**
	 * For String Trees Only.
	 */
	public int sumChars() throws ClassCastException {
		return this.complexSize(this.root, (TreeNode<E> e)->((String)e.item).length() );
	}
	
}