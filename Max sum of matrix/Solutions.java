import java.util.*;
import java.io.*;
import java.lang.*;

public class Solutions{
	public void print(ArrayList<ArrayList<Integer>> matrix){
		Iterator<ArrayList<Integer>> rows 	= matrix.iterator();
		while(rows.hasNext()){
			Iterator<Integer> cols 	= rows.next().iterator();
			while(cols.hasNext()){
				System.out.print(String.format("%d ", cols.next()));
			}
			System.out.println(" ");
		}
	}

	public void print(int[] arr){
		System.out.println(Arrays.toString(arr));
	}

	private int[] compressColsBySum(ArrayList<ArrayList<Integer>> matrix){
		int cols			= matrix.get(0).size();
		int[] sumVector 	= new int[cols];
		Arrays.fill(sumVector, 0);		// Initializ summ vector elements to 0

		Iterator<ArrayList<Integer>> rowItr = matrix.iterator();
		while(rowItr.hasNext()){
			Iterator<Integer> colItr = rowItr.next().iterator();
			int count = 0;
			while(colItr.hasNext()){
				sumVector[count++] += colItr.next();
			}
		}
		return sumVector;
	}

	private int getMaxSum(int[] sumVect){
		int negCount 		= 0;
		int negVal 			= 0;
		int posVal 			= 0;
		int result 			= 0; 
		int toSubtractNeg 	= Integer.MAX_VALUE;
		int toSubtractPos 	= Integer.MAX_VALUE;
		for(int i : sumVect){
			if(i <= 0){
				int j 	= Math.abs(i);
				negVal 	+= j;
				negCount++;
				if(j < toSubtractNeg) toSubtractNeg = j;
			}	
			else{
				posVal += i;
				if(i < toSubtractPos) toSubtractPos = i;
			}
		}

		if(negCount % 2 == 0){
			result 		= posVal + negVal;
		}
		else{
			int subtract;
			subtract 	= (toSubtractNeg < toSubtractPos) ? toSubtractNeg : toSubtractPos;
			result 		=  posVal + negVal - 2*subtract;
		}
		return result;
	}

	public int solve(ArrayList<ArrayList<Integer>> matrix){
		int[] sumVect = this.compressColsBySum(matrix);
		return this.getMaxSum(sumVect);
	}

	public static void main(String[] args){
		Scanner sc 	= new Scanner(System.in);
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		while(sc.hasNextLine()){
			String[] line 			= sc.nextLine().split("\\s+");
			ArrayList<Integer> tmp 	= new ArrayList<Integer>();
			for(String str : line){
				tmp.add(Integer.parseInt(str));
			}
			matrix.add(tmp);
		}
		Solutions sol 	= new Solutions();
		int sum 		= sol.solve(matrix);
		System.out.println(sum);
	}
}
