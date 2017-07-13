package handlers;

import java.util.*;

public abstract class Checker{
	protected HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
	protected Stack<Character> stk = new Stack<Character>();

	protected abstract void populatePriorities();
	
	public int getPriority(char c){
		if(isOperand(c)){
			return hm.get(c);
		}else{
			return -1;
		}
	}

	public boolean isOperand(char c){
		return hm.containsKey(c);
	}

	public boolean isEmptyStack(){
		return stk.empty();
	}

	public char peekStack(){
		if(!isEmptyStack()){
			return stk.peek();
		}
		return '\u0000';
	}

	public char popStack(){
		if(!isEmptyStack()){
			return stk.pop();
		}
		return '\u0000';
	}

	public void printStk(){
		System.out.println(Arrays.toString(stk.toArray()));
	}

	public void pushStack(char c){
		stk.push(c);
	}
}
