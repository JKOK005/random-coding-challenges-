import java.io.*;
import java.util.*;
import java.lang.*;

public class DictionaryGui{
	private TrieNode root; 

	private static TrieNode loadDict(Storage store){
		System.out.println("============ Loading dictionary file ============");
		String word;
		TrieNode root = new TrieNode();

		try{
			FileInputStream f_stream 	= new FileInputStream("input.txt");
			DataInputStream data_in 	= new DataInputStream(f_stream);
			BufferedReader br 			= new BufferedReader(new InputStreamReader(data_in));
			while( (word = br.readLine()) != null){
				root.insertWord(word);
			}
			f_stream.close();
			store.write(root);
		}catch(IOException i){
			i.printStackTrace();
		}finally{
			System.out.println("============ Load complete ============");
		}
		return root;
	}

	public static void main(String[] args){
		Storage store 	= new Storage("store/data.ser");
		Scanner sc  	= new Scanner(System.in);
		TrieNode root, tmp;
		String init;

		root = (store.isExists() == true) ? store.read() : loadDict(store);

		while(true){
			System.out.println("Please enter your search keys: ");
			init 		= sc.nextLine();
			tmp 		= root.search(init);
			if(tmp != null){
				System.out.println("Search results: ");
				TreePrinter.printN(tmp, 10, init);
			}else{
				System.out.println("No such keys found.");
			}
		}
	}
}