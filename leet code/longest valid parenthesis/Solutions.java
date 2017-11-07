import java.io.*;
import java.util.*;
import java.lang.*;

public class Solutions{
	public void printArr(boolean[] arr){
		for(int i =0; i < arr.length; i++){
			if(arr[i]) System.out.print(1);
			else System.out.print(0);
		}
	}

	private int getMaxLength(boolean[] arr){
		int maxLength 	= 0;
		int tmp 		= 0;
		for(int i =0; i < arr.length; i++){
			if(arr[i]){
				tmp++;
			}else{
				maxLength 	= Math.max(maxLength, tmp);
				tmp 		= 0;
			}
		}
		return Math.max(maxLength, tmp);
	}

	public int longestValidParentheses(String str){
		boolean[] validBracArray 		= new boolean[str.length()];
		Stack<Integer> leftBracStack 	= new Stack<Integer>();

		for(int i =0; i < str.length(); i++){
			char cur 	= str.charAt(i);
			if(cur == '('){
				leftBracStack.push(i);
			}else if(!leftBracStack.empty()){
				int start 	= leftBracStack.pop();
				validBracArray[start] 	= true;
				validBracArray[i] 		= true;
			}	
		}
		return getMaxLength(validBracArray);
	}

	public static void main(String[] args){
		Solutions sol 	= new Solutions();
		Scanner sc 		= new Scanner(System.in);
		String inputStr	= sc.nextLine();
		int maxLength 	= sol.longestValidParentheses(inputStr);
		System.out.println(maxLength);
	}
}