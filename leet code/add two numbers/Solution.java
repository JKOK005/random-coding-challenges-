import java.io.*;
import java.util.*;
import java.lang.*;

class Solution{
	public ListNode addTwoNumbers(ListNode root_A, ListNode root_B){
		int sum;
		int carry_ovr 	= 0;
		ListNode head;
		ListNode tmp;

		head 	= new ListNode(-1);
		tmp 	= head;

		while(root_A != null && root_B != null){
			sum 			= root_A.val + root_B.val + carry_ovr;
			carry_ovr 		= sum / 10;
			tmp.next		= new ListNode(sum - carry_ovr * 10);

			root_A 			= root_A.next;
			root_B 			= root_B.next;
			tmp 			= tmp.next;
		}

		if(root_A != null && root_B == null){
			// Case: A is longer than B
			while(carry_ovr == 1 && root_A != null){
				sum 		= root_A.val + carry_ovr;
				carry_ovr 	= sum / 10;
				tmp.next 	= new ListNode(sum - carry_ovr * 10);

				root_A 		= root_A.next;
				tmp 		= tmp.next;
			}
			tmp.next 		= root_A;

		}else if(root_A == null && root_B != null){
			// Case: B is longer than A
			while(carry_ovr == 1 && root_B != null){
				sum 		= root_B.val + carry_ovr;
				carry_ovr 	= sum / 10;
				tmp.next 	= new ListNode(sum - carry_ovr * 10);

				root_B 		= root_B.next;
				tmp 		= tmp.next;
			}
			tmp.next 		= root_B;
		}

		if(carry_ovr == 1){
			tmp.next		= new ListNode(1);
		}
		return head.next;
	}

	public static void main(String[] args){
		ListNode lst_1 	= Constructor.construct(321321);
		ListNode lst_2 	= Constructor.construct(123123);
		Solution solver	= new Solution();
		ListNode added 	= solver.addTwoNumbers(lst_1, lst_2);
		Printer.printList(added);
	}
}