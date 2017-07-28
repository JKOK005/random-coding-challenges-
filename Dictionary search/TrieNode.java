import java.util.*;
import java.io.*;
import java.lang.*;

class TrieNode{
	private HashMap<Character, TrieNode> child_map = new HashMap<Character, TrieNode>(); 
	private boolean word_end 	= false; 

	private void getAllChilds(){
		for(char key : child_map.keySet()){
			System.out.println(key);
		}
	}

	private TrieNode getChild(char c){
		if(child_map.containsKey(c)){
			return child_map.get(c);
		}
		return null;
	}

	private TrieNode addChild(char c){
		if(child_map.containsKey(c)){
			return null;
		}
		TrieNode new_node = new TrieNode();
		child_map.put(c, new_node);
		return new_node;
	}

	private void appendStr(String str, TrieNode node){
		if(!str.isEmpty()){
			if(str.length() == 1){
				word_end 	= true;
			}
			char c 			= str.charAt(0);
			TrieNode next 	= node.addChild(c);
			appendStr(str.substring(1), next);
		}
		return;
	}

	public void insertWord(String word, TrieNode root){
		if(word.isEmpty()){
			return;
		}
		char c 			= word.charAt(0);
		TrieNode child 	= root.getChild(c);
		if(child != null){
			insertWord(word.substring(1), child); 		// Checks to see if a substring already exists from root
		}else{
			appendStr(word, root); 						// If substring does not exist from root, start appending a new branch to it
		}
		return;
	}

	public static void main(String[] args){
		Scanner sc 		= new Scanner(System.in);
		String word 	= sc.nextLine();
		TrieNode root 	= new TrieNode();
		TrieNode proxy 	= new TrieNode();
		proxy.insertWord(word, root);
		root.getAllChilds();
	}
}