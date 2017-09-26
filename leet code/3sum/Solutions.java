import java.io.*;
import java.util.*;
import java.lang.*;

public class Solutions{

	private HashMap<Integer, Integer> genHashMap(int[] arr){
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i =0; i < arr.length; i++){
			if(hm.containsKey(arr[i])){
				hm.put(arr[i], hm.get(arr[i]) +1);
			}
			else{
				hm.put(arr[i], 1);	
			}
		}
		return hm;	
	}

	private boolean isExist(int num, int exclude_a, int exclude_b, HashMap<Integer, Integer> hm){
		int expect_count 	= 1;
		if(num == exclude_a) expect_count++;
		if(num == exclude_b) expect_count++;
		if(hm.containsKey(num) && hm.get(num) >= expect_count) return true;
		return false;
	}

	private List<List<Integer>> filter(List<List<Integer>> collector){
		return new ArrayList<List<Integer>>(new HashSet<List<Integer>>(collector));
	}

	public List<List<Integer>> threeSum(int[] arr){
		HashMap<Integer, Integer> hm 	= genHashMap(arr);
		Arrays.sort(arr);
		List<List<Integer>> collector 	= new ArrayList<List<Integer>>();
		for(int i =0; i < arr.length; i++){
			for(int j =i +1; j < arr.length; j++){
				int c 	= -1*(arr[i] + arr[j]);
				if(c >= arr[j] && isExist(c, arr[i], arr[j], hm)){
					List<Integer> lst 	= new ArrayList<Integer>(Arrays.asList(arr[i], arr[j], c));
					collector.add(lst);
				}
			}
		}
		return filter(collector);
	}

	public static void main(String[] args){
		Scanner sc 		= new Scanner(System.in);
		String[] input 	= sc.nextLine().split(",");
		int[] arr 		= new int[input.length];
		for(int i =0; i < arr.length; i++){
			arr[i] 		= Integer.parseInt(input[i]);
		}

		Solutions sol 	= new Solutions();
		List<List<Integer>> collector = sol.threeSum(arr);

		for(List<Integer> each : collector){
			System.out.println(each);
		}
	}
}