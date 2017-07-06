class PairClass{
	private int X;
	private int Y;

	public PairClass(int i, int j){
		X 	= i;
		Y 	= j;
	} 

	public int getLeft(){
		return X;
	}

	public int getRight(){
		return Y;
	}

	public void set(int i, int j){
		X 	= i;
		Y 	= j;
	}
}