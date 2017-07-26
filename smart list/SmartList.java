import java.util.*;
import java.io.*;
import java.lang.*;

public class SmartList<T> implements Setter<T>, Commons{
	private T val;

	@Override
	public void set(T value){
		val 	= value;
	}

	@Override
	public void print(){
		System.out.println(val);
	}

	public static void main(String[] args){
		SmartList<String> lst_a 	= new SmartList<String>();
		lst_a.set("Hello");

		SmartList<Integer> lst_b 	= new SmartList<Integer>();
		lst_b.set(100);

		SmartList<Double> lst_c 	= new SmartList<Double>();
		lst_c.set(0.1923);

		Commons[] common 			= new Commons[3];
		common[0] 	= (Commons) lst_a;
		common[1] 	= (Commons) lst_b;
		common[2] 	= (Commons) lst_c;

		for(int i =0; i < common.length; i++){
			common[i].print();
		}
	}
}