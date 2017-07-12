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

	public int parseExpr(String line) throws Exception{
		String[] str_arr 	= line.trim().split("\\s+"); 	// Split by white space
		for(String itr : str_arr){
			if(varh.isInt(itr)){
				// Start parsing intger inputs
				varh.pushStack(itr); 
			}else if(oper.isOperand(itr.charAt(0))){
				// Start parsing operands
				oper.pushStack(itr.charAt(0));
			}else{
				throw new Exception("Invalid inputs detected.");
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
			eq_eval.parseExpr(formated_line);
		}catch(Exception ex){
			ex.printStackTrace(new PrintStream(System.out));
		}
	}
}