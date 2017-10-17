package LeetCode.ReverseKNodes;

public class ParamFactory{
	private static ParamFactory param_factory_obj = null;
	
	public Node cur_ptr 	= null;
	public Node head_ptr 	= null;
	public Node nxt_ptr 	= null;

	public int k_max;
	public int counter;
	public int quotient;
	public int remainder;
	public boolean flag 		= false;
	public boolean stop_recur 	= false;

	private ParamFactory(){}

	public static ParamFactory get(){
		if(ParamFactory.param_factory_obj == null){
			ParamFactory.param_factory_obj 	= new ParamFactory();
		}
		return ParamFactory.param_factory_obj;
	}

	public void setK_max(int val){
		this.k_max 		= val;
	}

	public void setCounter(int val){
		this.counter 	= val;
	}
}