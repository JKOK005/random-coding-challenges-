import java.util.*;
import java.lang.*;
import java.io.*;

class RadixSortTest{
	private int[] redixSort(int[] toSortArray){

	}

	private <T> T[] stringToArray(String rawString, String delim, Class<T> c){
		String[] stringArray = rawString.split(delim);
		List<String> tmpStringList = new ArrayList<String>();
		for(String eachString : stringArray){
			tmpStringList.add(eachString)
		}
		T[] result 	= (T[]) Array.newInstance(c, tmpStringList.size());
		result 		= tmpStringList.toArray(result);
		return (T[]) result;
	}

	public static void main(String[] args){
		String toSortNumbers;
		RadixSortTest sorter = new RadixSortTest<Integer>();

		Scanner sc 	= new Scanner(System.in);
		while(sc.hasNextLine()){
			String line	= sc.nextLine();

			try{
				
			}catch(Exception ex){
				ex.printStackTrace(new PrintStream(System.out));
			}
		}
	}
}