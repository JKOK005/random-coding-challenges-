public class Adapter{
	public static Items strToItem(String[] str){
		int id 		= Integer.parseInt(str[0]);
		int price 	= Integer.parseInt(str[1]);
		int length 	= Integer.parseInt(str[2]);
		int width 	= Integer.parseInt(str[3]);
		int height 	= Integer.parseInt(str[4]);
		int weight 	= Integer.parseInt(str[5]);

		return new Items(id, price, length, width, height, weight);
	}
}