import java.io.*;
import java.util.*;
import java.lang.*;

public class TreePrinter{

	private static ArrayList<String> gather(TrieNode start, String full_word, ArrayList<String> collections){
		HashMap<Character, TrieNode> child_map = start.getChildMap();
		String copy 		= new String(full_word);
		if(start.isEnd()){
			collections.add(full_word);
		}
		for(Map.Entry<Character, TrieNode> entry : child_map.entrySet()){
			char c 			= entry.getKey();
			TrieNode child 	= entry.getValue();
			copy 			+= c;
			gather(child, copy, collections);
			copy 			= new String(full_word);
		}
		return collections;
	}

	public static void printTraverse(TrieNode start){
		ArrayList<String> collections 	= new ArrayList<String>();
		String full_word 	= "";
		gather(start, full_word, collections);
		collections.stream().forEach(System.out::println);
	}

	public static void main(String[] args){

	}
}