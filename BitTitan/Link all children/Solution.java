import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution{
	private void link(Vector<Node> node_vec){
		if(node_vec.isEmpty()){
			return;
		}

		Node cur;
		if(node_vec.size() != 1){
			// Link child from left to right
			for(int i =1; i < node_vec.size(); i++){
				cur 		= node_vec.elementAt(i -1);
				cur.Right 	= node_vec.elementAt(i);
			}
		}

		// Create new vector that includes all childs 
		Vector<Node> tmp_vec 	= new Vector<Node>();
		for(int i =0; i < node_vec.size(); i++){
			cur 	= node_vec.elementAt(i);
			if(cur.Children != null){
				for(int j=0; j < cur.Children.length; j++){
					tmp_vec.add(cur.Children[j]);
				}
			}
		}
		node_vec 	= tmp_vec; 		
		tmp_vec 	= null; 		// Dereference pointer to current layer to save space
		link(node_vec);
		return;
	}

	public void linkAll(Node root){
		if(root != null){
			Vector<Node> node_vec = new Vector<Node>();
			node_vec.add(root);
			link(node_vec);
		}
		return;
	}

	public static void main(String[] args){
	}
}