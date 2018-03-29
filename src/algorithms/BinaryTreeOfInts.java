
public class BinaryTreeOfInts {
	// If we are making a class to represent a BinaryTreeOfInts,
	// what does this class need as an instance variable?  
	// e.g. our DynamicArrayOfInts had an int[] of size 8 "under the hood"
	// our LinkedListOfStrings had a pointer to a Node called 'head' "under the hood"
	TreeNode root;

	
	public static void main(String[] args) {
		BinaryTreeOfInts tree = new BinaryTreeOfInts();
		tree.root = new TreeNode(16);
		tree.root.left = new TreeNode(8);
		tree.root.right = new TreeNode(23);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.left.right = new TreeNode(5);
		tree.root.left.right = new TreeNode(15);
		tree.root.right.right = new TreeNode(42);
		
		/*				16
		 * 			/	 	\
		 * 		  8			   23
		 * 		/   \         /   \
		 * 	   4     15		 n     42
		 *    / \    / \           / \
		 *   n   5   n  n         n   n
		 */	
		
		// Test getCount 
		System.out.println("Count nodes with " + tree.root.item + " as the root: " + tree.getCount(tree.root)); // should be 7
		System.out.println("Count nodes with " + tree.root.left.item + " as the root: " + tree.getCount(tree.root.left)); // should be 4
		
		System.out.print("Performing preorder print...");
		tree.preorderPrint(tree.root);
		System.out.println();
		
		System.out.print("Performing inorder print...");
		tree.inorderPrint(tree.root);
		System.out.println();
		
		System.out.print("Performing postorder print...");
		tree.postorderPrint(tree.root);
		System.out.println();
	}
	

	public int getCount(TreeNode root) {
		int sum=0;
		if (root.left != null) sum+=1+getCount(root.left);
		if (root.right != null) sum+=1+getCount(root.right);
		return sum;
	}
	
	
	
	
	/*				16
	 * 			/	 	\
	 * 		  8			   23
	 * 		/   \         /   \
	 * 	   4     15		 n     42
	 *    / \    / \           / \
	 *   n   5   n  n         n   n
	 */	
	public void preorderPrint(TreeNode root) {
		if(root == null)
			return;
		
		System.out.print(root.item + " ");  //*** print myself
		preorderPrint(root.left); // then recurse down left hand side
		preorderPrint(root.right); // ...finally recurse down right hand side
	} // Should be:  
	
	
	
	
	/*				16
	 * 			/	 	\
	 * 		  8			   23
	 * 		/   \         /   \
	 * 	   4	     15		 n     42
	 *    / \    / \           / \
	 *   n   5   n  n         n   n
	 */	
	public void inorderPrint(TreeNode root) {
		if(root == null)
			return;
		
		inorderPrint(root.left); // recurse down left hand side
		System.out.print(root.item + " "); //*** then print myself
		inorderPrint(root.right); // ...finally recurse down right hand side
	} // Should be 
	
	
	
	
	/*				16
	 * 			/	 	\
	 * 		  8			   23
	 * 		/   \         /   \
	 * 	   4	     15		 n     42
	 *    / \    / \           / \
	 *   n   5   n  n         n   n
	 */	
	public void postorderPrint(TreeNode root) {
		if(root == null)
			return;
		
		postorderPrint(root.left); // recurse down left hand side
		postorderPrint(root.right); // then recurse down right hand side
		System.out.print(root.item + " "); //*** finally print myself
	} // Should be 
	
	
	
	
	
	
	
	
}
