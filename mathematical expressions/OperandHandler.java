import java.util.*;

class OperandHandler implements Checker{
	private HashMap<Character, Integer> hm 	= new HashMap<Character, Integer>();
	private Stack stk<Character> = new Stack<Character>();

	private void populatePriorities(){
		hm.put('(', 3);
		hm.put(')', 3);
		hm.put('*', 2);
		hm.put('/', 2);
		hm.put('+', 1);
		hm.put('-', 1);
	}

	public OperandHandler(){
		populatePriorities();
	}

	@Override
	public int getPriority(char c){
		if(isOperand(c)){
			return hm.get(c);
		}else{
			return -1;
		}
	}

	@Override
	public boolean isOperand(char c){
		return hm.containsKey(c);
	}

	@Override
	public void insert(char c){
	}

	public static void main(String[] args){
		OperandHandler op 	= new OperandHandler();
		System.out.println(op.getPriority('+'));
	}
}