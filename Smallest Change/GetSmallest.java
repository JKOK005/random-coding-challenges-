import java.io.*;
import java.util.*;
import java.lang.*;

public class GetSmallest{
	private static int[][] solve(ArrayList<Integer> coin_sets, int max_val){
		int rows 		= max_val +1;
		int cols 		= coin_sets.size() +1;
		int[][] mat 	= new int[rows][cols];

		for(int i =1; i < rows; i++){
			for(int j=1; j < cols; j++){
				
				int pick 		= coin_sets.get(j -1);
				if(i -pick > 0){
					pick 		+= mat[i -pick][j];
				}

				int dont_pick 	= mat[i][j -1];

				if(dont_pick != 0){
					mat[i][j] 	= Math.min(pick, dont_pick);
				}else{
					mat[i][j] 	= pick;
				}
			}
		}
		return mat;
	}

	public static void main(String[] args){
		ArrayList<Integer> coin_sets 	= new ArrayList<Integer>();
		Scanner sc 	= new Scanner(System.in);
		String set 	= sc.nextLine();
		int val 	= sc.nextInt();

		String[] splitted 	= set.split("\\s+");
		for(String each : splitted){
			coin_sets.add(Integer.parseInt(each));
		}

		Collections.sort(coin_sets);		// Sort coin set by smallest value
		int[][] mat 	= GetSmallest.solve(coin_sets, val);

		int rows 		= mat.length;
		int cols 		= mat[0].length;
		Printer.printArr(mat);
		System.out.println(String.format("Smallest value >= %d from set: %d", val, mat[rows -1][cols -1]));
	}
}