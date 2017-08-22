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

	private Node constructTree(int[] arr){
		if(arr == null) return null;
		int end 	= arr.length -1;
		return constructTree(arr, 0, end);
	}

	public static void main(String[] args){
		int[] arr 	= {1,2,3,4,5};
		MinHeightTree tree 	= new MinHeightTree();
		tree.constructTree(arr);
	}
}