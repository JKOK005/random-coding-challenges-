import java.util.*;
import java.lang.*;

public class VariableHandler{
	protected Stack<Integer> stk = new Stack<Integer>();

	public boolean isInt(String str){
		for(int i=0; i<str.length(); i++){
			if(Character.isDigit(str.charAt(i)) == false){
				return false;
			}
		}
		return true;
	}

	public boolean isEmptyStack(){
		return stk.empty();
	}

	public int popStack(){
		if(!stk.empty()){
			return stk.pop();
		}return Integer.MIN_VALUE;
	}

	public int peekStack(){
		if(!stk.empty()){
			return stk.peek();
		}return Integer.MIN_VALUE;
	}

	public void pushStack(int c){
		stk.push(c);
	}

	public void printStk(){
		System.out.println(Arrays.toString(stk.toArray()));
	}

	public void pushStack(String str){
		pushStack(Integer.parseInt(str));
	}
}