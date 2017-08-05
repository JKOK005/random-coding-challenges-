import java.util.*;
import java.io.*;
import java.lang.*;

public class TrieNode implements Serializable{
	// Private method specifiers.
	private HashMap<Character, TrieNode> child_map = new HashMap<Character, TrieNode>(); 
	private boolean word_end = false; 

	private TrieNode getChild(char c){
		if(child_map.containsKey(c)){
			return child_map.get(c);
		}
		return null;
	}

	private void setWordEnd(boolean val){
		word_end 	= val;
	}

	private TrieNode addChild(char c){
		if(child_map.containsKey(c)){
			return null;
		}
		TrieNode new_node = new TrieNode();
		child_map.put(c, new_node);
		return new_node;
	}

	private void appendStr(String str){
		if(str.isEmpty()){
			this.setWordEnd(true);
		}else{
			char c 			= str.charAt(0);
			TrieNode next 	= this.addChild(c);
			next.appendStr(str.substring(1));
		}
		return;
	}

	// Public method specifiers.
	public void insertWord(String word){
		if(word.isEmpty()){
			this.setWordEnd(true);
			return;
		}
		char c 			= word.charAt(0);
		TrieNode child 	= this.getChild(c);
		if(child != null){
			child.insertWord(word.substring(1)); 		// Checks to see if a substring already exists from root
		}else{
			this.appendStr(word); 						// If substring does not exist from root, start appending a new branch to it
		}
		return;
	}

	public TrieNode search(String init){
		if(init.isEmpty()){
			return this;
		}
		char c 			= init.charAt(0);
		TrieNode child 	= this.getChild(c);
		if(child != null){
			return child.search(init.substring(1));
		}
		return null;
	}

	public HashMap<Character, TrieNode> getChildMap(){
		return child_map;
	}

	public boolean isEnd(){
		return word_end;
	}

	public static void main(String[] args){
		Scanner sc 		= new Scanner(System.in);
		TrieNode root 	= new TrieNode();

		while(sc.hasNext()){
			String word = sc.nextLine();
			root.insertWord(word);
		}
		TreePrinter.printTraverse(root);
		Storage store 	= new Storage("store/data.ser");
		store.write(root);
	}
}