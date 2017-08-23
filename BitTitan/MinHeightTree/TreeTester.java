import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class TreeTester{
	MinHeightTree handle;

	@Before
	public void setup(){
		handle 	= new MinHeightTree();
	}

	public void traverse(Node root, Vector<Integer> cmp){
		if(root == null) return;
		traverse(root.getLeft(), cmp);
		cmp.add(root.getVal());
		traverse(root.getRight(), cmp);
	}

	public boolean compareArr(int[] arr, Vector<Integer> cmp){
		if(arr.length != cmp.size()) return false;
		
		for(int i=0; i < arr.length; i++){
			if(arr[i] != cmp.get(i)) return false;
		}
		return true;
	}

	@Test 
	public void isBinaryTree(){
		// Test for a complete binary tree
		int no_nodes 		= 100;

		int[] arr 	= new int[no_nodes];

		for(int i=0; i < arr.length; i++){
			arr[i] 	= i;
		}

		// Construct tree, get root
		Node root 	= handle.constructTree(arr);

		// Perform inorder traversal of the tree
		Vector<Integer> cmp 	= new Vector<Integer>(no_nodes);
		traverse(root, cmp);

		// Assert elements and order are the same
		Assert.assertTrue(compareArr(arr, cmp));
	}

	@Test 
	public void emptyArr(){
		// Test for empty array input
		int[] arr 	= {};
		Node root 	= handle.constructTree(arr);
		Assert.assertEquals(root, null);
	}

	@Test
	public void treeCompleteHeight_5(){
		// Test for a complete binary tree
		int no_nodes 		= 31;

		int[] arr 	= new int[no_nodes];
		for(int i=0; i < arr.length; i++){
			arr[i] 	= i;
		}

		// Construct tree, get root
		Node root 	= handle.constructTree(arr);

		// Get max height
		int height 	= handle.maxHeight(root);

		// 2^(k-1) < total nodes < 2^k where k is height
		Assert.assertTrue(no_nodes < Math.pow(2, height));
		Assert.assertTrue(no_nodes > Math.pow(2, height -1));
	}

	@Test
	public void treePartialHeight_4(){
		// Test for a partially complete binary tree
		int no_nodes 		= 9;

		int[] arr 	= new int[no_nodes];
		for(int i=0; i < arr.length; i++){
			arr[i] 	= i;
		}

		// Construct tree, get root
		Node root 	= handle.constructTree(arr);

		// Get max height
		int height 	= handle.maxHeight(root);

		// 2^(k-1) < total nodes < 2^k where k is height
		Assert.assertTrue(no_nodes < Math.pow(2, height));
		Assert.assertTrue(no_nodes > Math.pow(2, height -1));
	}
}