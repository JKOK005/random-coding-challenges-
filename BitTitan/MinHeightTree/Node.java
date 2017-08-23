public class Node{
	private int val;
	private Node left; 
	private Node right;

	public Node(int x){
		val 	= x;
	}

	public int getVal(){
		return val;
	}

	public Node getLeft(){
		return left;
	}

	public Node getRight(){
		return right;
	}

	public void setVal(int x){
		val 	= x;
	}

	public void setLeft(Node n){
		left 	= n;
	}

	public void setRight(Node n){
		right 	= n;
	}
}