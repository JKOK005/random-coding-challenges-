import java.io.*;
import java.util.*;
import java.lang.*;

public class Printer{
	public static void printList(ListNode root){
		while(root != null){
			System.out.print(String.format("%d -> ", root.val));
			root = root.next;
		}
		System.out.println(" ");
	}
}