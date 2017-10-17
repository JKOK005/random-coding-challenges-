package LeetCode.ReverseKNodes;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Solutions{
	private ParamFactory params 	= ParamFactory.get();
	private static Node new_root 	= null;

	private void reverse(Node head, int count){
		if(head != null){
			reverse(head.getNext(), count +1);
		}else if(count <= params.k_max){
			params.stop_recur 	= true;
		}else{
			return;
		}

		if(params.stop_recur){
			if(count == 1) new_root = head;
			return;
		}

		if(!params.flag){
			params.quotient 	= count / params.k_max;
			params.remainder 	= count - params.quotient *params.k_max;
			params.flag 		= true;
		}

		if(params.remainder >= 1){
			if(params.remainder == 1) params.nxt_ptr = head;
			params.remainder 	-= 1;
			return;
		}

		if(params.quotient <= 1 && Solutions.new_root == null){
			Solutions.new_root 	= head;
		}

		if(params.counter > 1){
			if(params.counter == params.k_max){
				params.head_ptr = head;
			}else{
				params.cur_ptr.setNext(head);
			}
			params.cur_ptr 	= head;
			params.counter 	-= 1;
		}
		else{
			params.counter 	= params.k_max;
			params.cur_ptr.setNext(head);
			head.setNext(params.nxt_ptr);
			params.nxt_ptr 	= params.head_ptr;
			params.quotient 	-= 1;
		}	
		return;
	}

	public Node reverseKGroup(Node head, int k_max){
		if(k_max <= 1) return head;
		params.setK_max(k_max);
		params.setCounter(k_max);
		reverse(head, 1);
		return Solutions.new_root;
	}

	public static void main(String[] args){
		Node root 		= ListUtils.makeAscendingList(1, 2, 1);
		ListUtils.print(root);
		Solutions sol 	= new Solutions();
		Node new_root 	= sol.reverseKGroup(root, 2);
		ListUtils.print(new_root);
	}
}