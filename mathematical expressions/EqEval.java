import handlers.*;

import java.io.*;
import java.util.*;
import java.lang.*;

public class EqEval{ 
	private OperandHandler oper 	= new OperandHandler();
	private VariableHandler varh 	= new VariableHandler();

	private String formatString (String str){
		StringBuilder new_str =  new StringBuilder();

		for(int i=0; i < str.length(); i++){
			char c 	= str.charAt(i);

			if(oper.isOperand(c)){
				new_str.append(' ').append(c).append(' ');
			}else{
				new_str.append(c);
			}
		}
		return new_str.toString();
	}

	private boolean assertValidString(String line) throws Exception{
		Stack<Character> stk = new Stack<Character>();
		int flag = -1;
		char curr, tmp;

		String[] str_arr = line.trim().split("\\s+"); 	// Split by white space

		for(String itr : str_arr){
			curr 		= itr.charAt(0);

			if(varh.isInt(itr)){
				if(flag == 1){
					throw new Exception("Syntax error (int)");
				}
				flag = 1;
			}
			else if(oper.isOperand(curr)){
				if(oper.checkLeftBrac(curr) && flag != 1){
					stk.push(curr);

				}else if(flag == 0){
					throw new Exception("Syntax error (oper)");

				}else if(oper.checkRightBrac(curr)){
					if(stk.empty()) throw new Exception("No bracket closure");
					stk.pop();
					flag = 1;

				}else{
					flag = 0;

				}
			}
			else{
				throw new Exception("Unrecognized input error");
			}
		}
		return true;
	}

	private void stackCalc(){
		int result;
		int val_B 	= varh.popStack();
		int val_A 	= varh.popStack();
		char op 	= oper.popStack();

		result 		= oper.getResult(val_A, val_B, op);
		varh.pushStack(result);
	}

	private int parseExpr(String line) throws Exception{
		char op_curr;
		String[] str_arr = line.trim().split("\\s+"); 	// Split by white space

		for(String itr : str_arr){
			op_curr 	= itr.charAt(0);

			if(varh.isInt(itr)){
				varh.pushStack(itr); 	
			}else if(oper.isOperand(op_curr)){
				// Right bracket encountered
				if(oper.checkRightBrac(op_curr)){
					while(!oper.checkLeftBrac(oper.peekStack())){
						stackCalc();
					}
					oper.popStack(); 		// Removes left bracket
				}
				else{
					if(!oper.checkLeftBrac(op_curr) && oper.getPriority(op_curr) < oper.getPriority(oper.peekStack())){
						stackCalc();
					}
					oper.pushStack(op_curr);
				}
			}
		}

		// Flush remaining contents in stack
		while(!oper.isEmptyStack()){
			stackCalc();
		}
		return varh.popStack();
	}

	public int evaluate(String line) throws Exception{
		String formated_line 	= formatString(line);
		assertValidString(formated_line);
		return parseExpr(formated_line);
	}

	public static void main(String[] args){
		int result;
		Scanner sc 	= new Scanner(System.in);
		EqEval eq_eval = new EqEval();

		while(sc.hasNextLine()){
			String line	= sc.nextLine();

			try{
				result 	= eq_eval.evaluate(line);
				System.out.println(String.format("%s 		-> 		%d", line, result));
			}catch(Exception ex){
				ex.printStackTrace(new PrintStream(System.out));
			}
		}
	}
}