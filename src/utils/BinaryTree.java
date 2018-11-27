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

	public TreeNode<E> root;

	public BinaryTree() {}
	public BinaryTree(E rootItem) {this(new TreeNode<E>(rootItem));}
	public BinaryTree(TreeNode<E> root) {
		this.root = root;
	}

	public void insert(E item) {this.insert(this.root,new TreeNode<E>(item),true);}
	public boolean insert(E item,CompareTwo<E> comparer,boolean allowRepeats) {return insert(this.root,new TreeNode<E>(item),comparer,allowRepeats);}
	public boolean insert(TreeNode<E> root,TreeNode<E> n,boolean allowRepeats) {return insert(root,n,(E a,E b)->a.compareTo(b),allowRepeats);}
	public boolean insert(TreeNode<E> root,TreeNode<E> n,CompareTwo<E> comparer,boolean allowRepeats) {
		// System.out.println("\nPlacing "+n.item); // Debug
		if (this.root == null) { // Null root
			this.root=n; // TODO: Fix this.root to root
			return true; // Is there a way to find the pointer passed in?? otherwise can't
		}
		
		// Running through nodes
		for (TreeNode<E> runner=root;;) {
			// System.out.println(n.item+(comparer.compare(n.item,runner.item)>=0?" > ":" < ")+runner.item); // Debug
			if (!allowRepeats && n.item.equals(runner.item)) return false;
			if (comparer.compare(n.item,runner.item) > 0) {
				if (runner.right == null) {
					runner.right = n;
					// System.out.println("Putting "+n.item+" to the right of "+runner.item); // Debug
					return true;
				} else {
					runner = runner.right;
				}
			} else { // Equalities go on the left if it allows repeats
				if (runner.left == null) {
					runner.left = n;
					// System.out.println("Putting "+n.item+" to the left of "+runner.item); // Debug
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
		//System.out.println(root+" has "+root.left+" and "+root.right);
		applyAll(root.left, doThis);
		doThis.accept(root);
		applyAll(root.right, doThis);
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
	
	
	@Override
	public String toString() {
		return print(this.root,0);
	}
	
	private String print(TreeNode<E> root,int layer) {
		String out = root.item.toString(), spacing = "";
		for (int i=0;i<layer;i++) spacing+="  ";
		if (root.left != null) out += "\n"+spacing+"L - "+print(root.left,layer+1);
		if (root.right != null) out += "\n"+spacing+"R - "+print(root.right,layer+1);
		return out;
	}
	
	private String printFull(TreeNode<E> root,int layer) {
		if (root==null) return null;
		String out = root.item.toString(), spacing = "";
		for (int i=0;i<layer;i++) spacing+="  ";
		out += "\n"+spacing+"L - "+printFull(root.left,layer+1);
		out += "\n"+spacing+"R - "+printFull(root.right,layer+1);
		return out;
	}
}