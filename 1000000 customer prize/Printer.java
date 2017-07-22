import java.io.*;

public class Printer{
	public static void printItem(Items item){
		System.out.println(String.format("ID: %d, Price: %d, Vol: %d, Weight: %d", item.getId(), item.getPrice(), item.getVol(), item.getWeight()));
	}

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