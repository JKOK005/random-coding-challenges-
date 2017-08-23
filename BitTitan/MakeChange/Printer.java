import java.io.*;
import java.util.*;

public class Printer{
	public static void printArr(int[][] arr){
		int rows 	= arr.length;
		int cols 	= arr[0].length;
		for(int i =0; i < rows; i++){
			for(int j =0; j < cols; j++){
				System.out.print(String.format("%d ", arr[i][j]));
			}
			System.out.println(" ");
		}
	}
}