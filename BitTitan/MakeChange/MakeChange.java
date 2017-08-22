import java.util.*;
import java.io.*;
import java.lang.*;

public class MakeChange{
	private int getMaxPerm(int total, int[] coins){
		if(coins == null) return 0;
		// Ensure that coins are sorted first

		int pick, dont_pick;
		int cols 		= coins.length +1;
		int rows 		= total +1;
		int[][] mat 	= new int[rows][cols];

		Arrays.fill(mat[0], 1);

		for(int i =1; i < rows; i++){
			for(int j=1; j < cols; j++){

				int c_indx 	= j -1;
				// Pick the coin
				if(coins[c_indx] <= i){
					pick 	= mat[i - coins[c_indx]][j]; 
				}else{
					pick 	= 0;
				}

				// Do not pick the coin
				dont_pick 		= mat[i][j -1];
				mat[i][j]		= pick + dont_pick;
			}
		}
		Printer.printArr(mat);
		return mat[rows -1][cols -1];
	}

	public static void main(String[] args){
		int[] coins 	= {1,2,3};
		int total 		= 2;
		MakeChange c 	= new MakeChange();
		System.out.println(c.getMaxPerm(total, coins));
	}
}