import java.util.*;

public class OperandHandler extends Checker{
	@Override
	protected void populatePriorities(){
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

	public boolean checkLeftBrac(char c){
		return c == '(';
	}

	public boolean checkRightBrac(char c){
		return c == ')';
	}

	public static void main(String[] args){
		OperandHandler op 	= new OperandHandler();
		System.out.println(op.getPriority('+'));
	}
}