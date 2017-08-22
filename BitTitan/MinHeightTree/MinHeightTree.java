import java.util.*;
import java.lang.*;
import java.io.*;

public class MinHeightTree{
	private Node constructTree(int[] arr, int start, int end){
		if(start > end) return null;
		else if(start == end) return new Node(arr[start]);

		int mid 	= (start + end) / 2;
		Node root 	= new Node(arr[mid]);

		root.setLeft(constructTree(arr, start, mid -1));
		root.setRight(constructTree(arr, mid +1, end));
		return root;
	}

	public Node constructTree(int[] arr){
		if(arr.length == 0) return null;
		int end 	= arr.length -1;
		return constructTree(arr, 0, end);
	}

	public int maxHeight(Node root){
		if(root == null) return 0;
		int left_height 	= 1 + maxHeight(root.getLeft());
		int right_height 	= 1 + maxHeight(root.getRight());
		return Math.max(left_height, right_height);
	}

	public static void main(String[] args){
		int[] arr 	= {1,2,3,4,5};
		MinHeightTree tree 	= new MinHeightTree();
		Node root 	= tree.constructTree(arr);
		System.out.println(tree.maxHeight(root));
	}
}