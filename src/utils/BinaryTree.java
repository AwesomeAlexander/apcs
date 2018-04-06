package utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.List;

/**
 * Binary Tree. Chock full of implementable, futureproofed generic functions based on functional interfaces (lambdas).
 * @author Alexander Ng
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

	public void insert(E item) {this.insert(this.root,new TreeNode<E>(item),true);}
	public boolean insert(E item,CompareTwo<E> comparer,boolean allowRepeats) {return insert(this.root,new TreeNode<E>(item),comparer,allowRepeats);}
	public boolean insert(TreeNode<E> root,TreeNode<E> n,boolean allowRepeats) {return insert(root,n,(E a,E b)->a.compareTo(b),allowRepeats);}
	public boolean insert(TreeNode<E> root,TreeNode<E> n,CompareTwo<E> comparer,boolean allowRepeats) {
		if (this.root == null) {this.root=n;return true;} // TODO: fix problem of root being null
		for (TreeNode<E> runner=root;;) {
			if (comparer.compare(n.item,root.item) >= 0) {
				if (!allowRepeats && n.item.equals(runner.item)) return false;

				if (runner.right == null) {
					runner.right = n;
					return true;
				} else {
					runner = runner.right;
				}
			} else {
				if (runner.left == null) {
					runner.left = n;
					return true;
				} else {
					runner = runner.left;
				}
			}
		}
	}

	public TreeNode<E> find(E item) {
		// Recursive
		return find(this.root,item);

		// Iterative, Simple
		/* {
			TreeNode<E> runner = this.root;
			while (runner != null) {
				if (item.equals(runner.item)) return runner;
				if (item.compareTo(runner.item) > 0) runner = runner.right;
				else runner = runner.left;
			}
			return null;
		} */
	}

	public TreeNode<E> find(TreeNode<E> root,E item) {return this.find(root, item, (E a,E b)->a.compareTo(b) );}
	public TreeNode<E> find(TreeNode<E> root,E item,CompareTwo<E> comparer) {
		if (root == null) return null;
		if (comparer.compare(item,root.item) == 0) return root;
		if (comparer.compare(item,root.item) > 0) return find(root.right,item,comparer);
		if (comparer.compare(item,root.item) < 0) return find(root.left,item,comparer);
		return null; // Should never reach.
	}

	public boolean contains(E item) {
		return this.find(item) != null;
	}

	// Apply some function to all
	public void applyAll(Consumer<TreeNode<E>> doThis) {this.applyAll(this.root,doThis);}
	public void applyAll(TreeNode<E> root,Consumer<TreeNode<E>> doThis) {
		if (root == null) return;
		applyAll(root.left, doThis);
		doThis.accept(root);
		applyAll(root.right, doThis);
	}

	// Get a list of items in the tree, determined by the predicate
	public List<E> getListOf(List<E> list,Predicate<E> tester) {return this.getListOf(this.root, list, tester);}
	public List<E> getListOf(TreeNode<E> root,List<E> list,Predicate<E> tester) {
		this.applyAll(root, (TreeNode<E> n)->{
			if (tester.test(n.item)) list.add(n.item);
		});
		return list;
	}

	// Do some function to a range
	public void doToRange(E start,E end,Consumer<TreeNode<E>> doThis) {this.doToRange(this.root,start,end,doThis);}
    public void doToRange(TreeNode<E> root,E start,E end,Consumer<TreeNode<E>> doThis) {
        if (root == null) return;
        if (start.compareTo(root.item)<=0) this.doToRange(root.left, start, end, doThis);
        if (start.compareTo(root.item)<=0 && end.compareTo(root.item)>=0) doThis.accept(root);
		if (end.compareTo(root.item)>=0) this.doToRange(root.right, start, end, doThis);
	}
	
	/* Size methods, apply to whole tree */

	public int size() {return this.size(this.root);}
	public int size(TreeNode<E> root) {return this.complexSizeBool(this.root,(TreeNode<E> n)->true);}
	public int complexSizeBool(TreeNode<E> root,Predicate<TreeNode<E>> tester) {return this.complexSize(root, (TreeNode<E> n)->(tester.test(n)?1:0) );}
	public int complexSize(TreeNode<E> root,Function<TreeNode<E>,Integer> tester) {
		if (root == null) return 0;
		return this.complexSize(root.left,tester) + tester.apply(root) + this.complexSize(root.right,tester);
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