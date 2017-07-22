import java.util.*;
import java.lang.*;
import java.io.*;

class CustomerPrize{
	private static void populateMatrix(int[][] price_mat, ArrayList<Items> item_container){
		Items cur_item;
		int pick, dont_pick, vol_limit;

		int rows 	= price_mat.length;
		int cols 	= price_mat[0].length;

		for(int i =1; i < rows; i++){
			for(int j =1; j < cols; j++){
				cur_item 	= item_container.get(j -1);
				vol_limit 	= i;
				pick 		= 0;
				// Pick item on volume limit
				if(cur_item.getVol() < vol_limit){
					pick 			= cur_item.getPrice() + price_mat[vol_limit - cur_item.getVol()][j -1];
				}

				// Do not pick that item
				dont_pick 			= price_mat[vol_limit][j -1];
				price_mat[i][j] 	= Math.max(pick, dont_pick);
			}
		}
	}

	public static void main(String[] args){
		Scanner sc 	= new Scanner(System.in);
		ArrayList<Items> item_container = new ArrayList<Items>();
		String line;
		String[] spec;
		Items tmp;
		final int max_vol 	= 40*35*30;

		while(sc.hasNextLine()){
			line 		= sc.nextLine();
			spec 		= line.split(",");
			tmp 		= Adapter.strToItem(spec);
			if(tmp.getVol() <= max_vol){
				item_container.add(Adapter.strToItem(spec));
			}
		}

		int[][] price_mat = new int[max_vol +1][item_container.size() +1];
		populateMatrix(price_mat, item_container);
		Printer.printArr(price_mat);

	}
}
