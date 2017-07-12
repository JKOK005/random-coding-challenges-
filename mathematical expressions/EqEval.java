import java.io.*;
import java.util.*;
import java.lang.*;

public class EqEval{ 
	private OperandHandler oper 	= new OperandHandler();
	private VariableHandler varh 	= new VariableHandler();

	public String formatString (String str){
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

	public boolean assertValidString(String line) throws Exception{
		Stack<Character> stk = new Stack<Character>();
		int flag = -1;
		char curr, tmp;

		String[] str_arr 	 = line.trim().split("\\s+"); 	// Split by white space

		for(String itr : str_arr){
			curr 		= itr.charAt(0);

			if(varh.isInt(itr)){
				if(flag == 1){
					throw new Exception("2 adjacent ints detected");
				}
				flag = 1;
			}
			else if(oper.isOperand(curr)){
				if(oper.checkLeftBrac(curr) && flag != 1){
					stk.push(curr);

				}else if(flag == 0){
					throw new Exception("2 adjacent operands detected");

				}else if(oper.checkRightBrac(curr)){
					if(stk.empty()) throw new Exception("Failure for bracket closure");
					stk.pop();
					flag = 1;

				}else{
					flag = 0;

				}
			}
			else{
				throw new Exception("Invalid input detected");
			}
		}
		return true;
	}

	public int parseExpr(String line){
		String[] str_arr 	= line.trim().split("\\s+"); 	// Split by white space
		char op_curr, op_tmp;
		int var_A, var_B;

		for(String itr : str_arr){
			if(varh.isInt(itr)){
				varh.pushStack(itr); 	
			}else if(oper.isOperand(itr.charAt(0))){
				op_curr 	= itr.charAt(0);

				// Right bracket encountered
				if(oper.checkRightBrac(op_curr)){
				}

				oper.pushStack(itr.charAt(0));
			}
			// System.out.println(itr);
		}
		return 1;
	}

	public static void main(String[] args){
		Scanner sc 	= new Scanner(System.in);
		String line	= sc.nextLine();
		EqEval eq_eval = new EqEval();
		try{
			String formated_line = eq_eval.formatString(line);
			eq_eval.assertValidString(formated_line);
			eq_eval.parseExpr(formated_line);
		}catch(Exception ex){
			ex.printStackTrace(new PrintStream(System.out));
		}
	}
}