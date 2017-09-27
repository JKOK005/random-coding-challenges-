package LeetCode.ReverseKNodes;

import java.io.*;
import java.util.*;
import java.lang.*;

public class ListUtils{
	public static Node makeAscendingList(int start, int N, int inc){
		Node root 	= new Node(start);
		Node tmp_A 	= root;
		Node tmp_B;
		for(int i =1; i < N; i++){
			tmp_B 	= new Node(start + i*inc);
			tmp_A.setNext(tmp_B);
			tmp_A 	= tmp_B;
		}
		return root;
	}

	public static void print(Node root){
		Node tmp 	= root;
		while(tmp != null){
			System.out.print(String.format("%d -> ", tmp.getValue()));
			tmp 	= tmp.getNext(); 
		}
		System.out.println("Null");
	}
}