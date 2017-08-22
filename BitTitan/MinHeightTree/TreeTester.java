import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TreeTester{
	MinHeightTree handle;

	@Before
	public void setup(){
		handle 	= new MinHeightTree();
	}

	@Test 
	public void isBinaryTree(){
		// // Test for a complete binary tree
		// int no_nodes 		= 31;

		// int[] arr 	= new int[no_nodes];
		// for(int i=0; i < arr.length; i++){
		// 	arr[i] 	= i;
		// }

		// // Construct tree, get root
		// Node root 	= handle.constructTree(arr);
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