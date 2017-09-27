package LeetCode.ReverseKNodes;

public class Node{
	private int value;
	private Node next = null;

	public Node(int X){
		value 	= X;
	}

	public int getValue(){
		return value;
	}

	public void setValue(int X){
		value 	= X;
	}

	public Node getNext(){
		return next;
	}

	public void setNext(Node N){
		next 	= N;
	}
}