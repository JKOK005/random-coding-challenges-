import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution{
	private int findMedianSortedArrays(int[] list_A, int[] list_B){
		int size_A 	= list_A.length;
		int size_B 	= list_B.length;
		if(size_B > size_A) return findMedianSortedArrays(list_B, list_A);

		// List A is the longer list
		int low_B			= 0;
		int high_B 			= size_B;

		int cut_left_A 		= (size_A -1) / 2;
		int cut_right_A 	= (size_A) / 2;

		while(low_B < high_B){
			int cut_left_B 		= (high_B - low_B -1) / 2;
			int cut_right_B 	= (high_B - low_B) / 2;

			if(list_A[cut_left_A] <= list_B[cut_right_B] && list_B[cut_left_B] <= list_A[cut_right_A]){
				// Cut is correct
				break;
			}else if(list_B[cut_left_B] > list_A[cut_right_A]){
				// Cut is too far right
				high_B 			= cut_left_B;
				cut_left_A 		+= (cut_right_B - low_B) / 2;
				cut_left_B 		+= (cut_right_B - low_B) / 2;
			}else{	
				low_B 			= cut_left_B;
				cut_left_A 		+= (high_B - cut_left_B) / 2;
				cut_left_B 		+= (high_B - cut_left_B) / 2;
			}
		}
		return (Math.max(list_A[cut_left_A], list_B[cut_left_B]) + Math.min(list_A[cut_right_A], list_B[cut_right_B])) /2;
	}

	public static void main(String[] args){
		int[] list_A 	= new int[]{1,2,3,4};
		int[] list_B 	= new int[]{4,5,6,7};
		Solution solver	= new Solution();
		solver.findMedianSortedArrays(list_A, list_B);
	}	
}