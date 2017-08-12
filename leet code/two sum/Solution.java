import java.io.*;
import java.util.*;
import java.lang.*;

class Solution{
	private int sum(int A, int B){
		return A + B;
	}

	private int[] search(int[] arr, int num_A, int num_B){
		int[] tmp 	= new int[]{-1,-1};
		for(int i =0; i < arr.length; i++){
			if(arr[i] == num_A && tmp[0] == -1){
				tmp[0] 	= i;
			}else if(arr[i] == num_B && tmp[1] == -1){
				tmp[1] 	= i;
			}
		}
		Arrays.sort(tmp);
		return tmp;
	}

	public int[] twoSum(int[] nums, int target){
		int arr_len 	= nums.length;
		int[] cloned 	= nums.clone();

		int first, second;
		first 	= 0;
		second 	= arr_len -1;
		Arrays.sort(nums);

		while(second > first){
			int added 	= sum(nums[first], nums[second]);
			if(added == target){
				return search(cloned, nums[first], nums[second]);
			}else if(added > target){
				second--;
			}else{	
				first++;
			}
		}
		return null;
	}

	public static void main(String[] args){
		Solution solver = new Solution();
		int[] nums 		= new int[]{9,5,2,1,10,10,412};
		int target 		= 20;
		int[] sol 		= solver.twoSum(nums, target);
		System.out.println(String.format("%d %d", sol[0], sol[1]));
	}	
}		