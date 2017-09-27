package LeetCode.ReverseKNodes;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Solutions{
	private static Node cur_ptr;
	private static Node nxt_ptr;
	private static Node new_root = null;
	private static Node root 	= null;
	private static int k_max;

	private Node reverse(Node head, int K){
		if(head == null) return head;

		if(K == 1){
			if(Solutions.new_root == null) Solutions.new_root = head;
			Solutions.root 		= head;
			Solutions.cur_ptr 	= head;
			Solutions.nxt_ptr 	= head.getNext();
		}else{
			reverse(head.getNext(), K -1);
			Solutions.cur_ptr.setNext(head);
			Solutions.cur_ptr = head;
		}

		if(K == Solutions.k_max){
			Solutions.root 	= Solutions.nxt_ptr;
			head.setNext(reverse(Solutions.nxt_ptr, Solutions.k_max));
			System.out.println(String.format("%d ", head.getNext().getValue()));
		}
		// System.out.println(String.format("%d ", root.getValue()));
		return Solutions.root;		
	}

	public Node reverseKGroup(Node head, int k_max){
		Solutions.k_max 	= k_max;
		reverse(head, k_max);
		return Solutions.new_root;
	}

	public static void main(String[] args){
		Node root 		= ListUtils.makeAscendingList(0, 10, 2);
		ListUtils.print(root);
		Solutions sol 	= new Solutions();
		Node new_root 	= sol.reverseKGroup(root, 2);
		ListUtils.print(new_root);
	}
}