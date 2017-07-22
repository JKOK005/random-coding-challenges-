public class Items{
	private final int id;
	private final int price;
	private final int length;
	private final int width;
	private final int height;
	private final int vol;
	private final int weight;

	public Items(int id, int price, int length, int width, int height, int weight){
		this.id 			= id;
		this.price 			= price;
		this.weight 		= weight;
		this.length 		= length;
		this.width 			= width;
		this.height			= height;
		this.vol 			= length *height *width;
	}

	public int getId(){
		return id;
	}

	public int getPrice(){
		return price;
	}

	public int getVol(){
		return vol;
	}

	public int getWeight(){
		return weight;
	}
}