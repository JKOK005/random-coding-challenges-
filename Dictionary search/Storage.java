import java.io.*;

public class Storage{
	private String data_loc;

	public Storage(String loc){
		data_loc 	= loc;
	}

	public void write(TrieNode root){
		try{
			FileOutputStream file 	= new FileOutputStream(data_loc);
			OutputStream buffer 	= new BufferedOutputStream(file);
			ObjectOutputStream out 	= new ObjectOutputStream(file);	
			try{
				out.writeObject(root);
			}
			finally{
				out.close();		
			}
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}

	public TrieNode read(){
		TrieNode root 	= null;
		try{
			InputStream file 		= new FileInputStream(data_loc);
			InputStream buffer 		= new BufferedInputStream(file);
			ObjectInputStream in 	= new ObjectInputStream(buffer);
			try{
				root = (TrieNode) in.readObject();
			}
			finally{
				in.close();
			}
		}
		catch(IOException i){
			i.printStackTrace();
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		return root;
	}

	public boolean isExists(){
		File tmp = new File(data_loc);
		return tmp.exists();
	}

	public static void main(String[] args){
	}
}