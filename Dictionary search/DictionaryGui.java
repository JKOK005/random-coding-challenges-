import java.io.*;
import java.util.*;
import java.lang.*;

public class DictionaryGui{
	private TrieNode root; 

	private static void loadDict(){
		System.out.println("============ Loading dictionary file ============");
	}

	public static void main(String[] args){
		Storage store = new Storage("store/data.ser");
		System.out.println(store.isExists());
	}
}