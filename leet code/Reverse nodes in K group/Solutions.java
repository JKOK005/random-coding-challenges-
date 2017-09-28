package LeetCode.ReverseKNodes;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Solutions{
	private static Node cur_ptr 	= null;
	private static Node head_ptr 	= null;
	private static Node nxt_ptr 	= null;
	private static Node new_root 	= null;

	private static int k_max;
	private static int counter;
	private static int quotient;
	private static int remainder;
	private static boolean flag 	= false;

	private void reverse(Node head, int count){
		if(head != null){
			reverse(head.getNext(), count +1);
		}else{
			return;
		}

		if(!Solutions.flag){
			Solutions.quotient 		= count / Solutions.k_max;
			Solutions.remainder 	= count - Solutions.quotient *Solutions.k_max;
			Solutions.flag 			= true;
		}

		if(Solutions.remainder > 1){
			Solutions.remainder 	-= 1;
			return;
		}else if(Solutions.remainder == 1){
			Solutions.nxt_ptr 		= head;
			Solutions.remainder 	-= 1;
			return;
		}

		if(Solutions.quotient <= 1 && Solutions.new_root == null){
			Solutions.new_root 	= head;
		}

		if(Solutions.counter == Solutions.k_max){
			Solutions.counter 	-= 1;
			Solutions.head_ptr 	= head;
			Solutions.cur_ptr 	= head;
			return;
		}else if(Solutions.counter > 1){
			Solutions.counter 	-= 1;
			Solutions.cur_ptr.setNext(head);
			Solutions.cur_ptr 	= head;
			return;
		}else{
			Solutions.counter 	= Solutions.k_max;
			Solutions.cur_ptr.setNext(head);
			head.setNext(Solutions.nxt_ptr);
			Solutions.nxt_ptr 	= Solutions.head_ptr;
			Solutions.quotient 	-= 1;
			return;
		}	
	}

	public Node reverseKGroup(Node head, int k_max){
		Solutions.k_max 	= k_max;
		Solutions.counter 	= k_max;
		reverse(head, 1);
		return Solutions.new_root;
	}

	public static void main(String[] args){
		Node root 		= ListUtils.makeAscendingList(0, 10, 2);
		ListUtils.print(root);
		Solutions sol 	= new Solutions();
		Node new_root 	= sol.reverseKGroup(root, 10);
		ListUtils.print(new_root);
	}
}