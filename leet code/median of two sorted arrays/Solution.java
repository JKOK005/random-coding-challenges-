import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution{
	private double findMedianSortedArrays(int[] list_A, int[] list_B){
		int size_A 	= list_A.length;
		int size_B 	= list_B.length;
		if(size_B > size_A) return findMedianSortedArrays(list_B, list_A);

		// List A is the longer list
		int low_A 			= 0;
		int high_A 			= size_A -1;
		int low_B			= 0;
		int high_B 			= size_B -1;

		if(size_B == 0){
			return (double) (list_A[(high_A - low_A) / 2] + list_A[(high_A - low_A +1) / 2]) / 2;
		}

		int cut_left_A 		= 0;
		int cut_right_A 	= 0;
		int cut_left_B 		= 0;
		int cut_right_B 	= 0;

		while(low_B <= high_B){			
			cut_left_A 		= low_A + (high_A - low_A) / 2;
			cut_right_A 	= low_A + (high_A - low_A +1) / 2;
		 	cut_left_B 		= low_B + (high_B - low_B) / 2;
			cut_right_B 	= low_B + (high_B - low_B +1) / 2;

			if(list_A[cut_left_A] <= list_B[cut_right_B] && list_B[cut_left_B] <= list_A[cut_right_A]){
				// Cut is correct
				return (double) (Math.max(list_A[cut_left_A], list_B[cut_left_B]) + Math.min(list_A[cut_right_A], list_B[cut_right_B])) / 2;

			}else if(list_B[cut_left_B] > list_A[cut_right_A]){
				// Cut is too far right -> Move high_B left and low_A right
				low_A 		+= Math.max(high_B - cut_left_B, 1);
				high_B 		-= Math.max(high_B - cut_left_B, 1);
				
			}else{
				// Cut is too far left -> Move low_B right and high_A left	
				high_A 		-= Math.max(cut_right_B - low_B, 1);
				low_B 		+= Math.max(cut_right_B - low_B, 1);

			}
		}

		if(size_A == size_B){
			if(low_B == 0){
				return (double) (list_B[low_B] + list_A[high_A]) /2;
			}else{
				return (double) (list_B[high_B] + list_A[low_A]) /2;
			}
		}else{
			cut_left_A 		= low_A + (high_A - low_A) / 2;
			cut_right_A 	= low_A + (high_A - low_A +1) / 2;

			if(low_B >= size_B){
				return (double) (Math.max(list_A[cut_left_A], list_B[high_B]) + list_A[cut_right_A]) /2;
			}else{
				return (double) (list_A[cut_left_A] + Math.min(list_A[cut_right_A], list_B[low_B])) /2;
			}
		}
	}

	public static void main(String[] args){
		int[] list_A 	= new int[]{1,3,5,7};
		int[] list_B 	= new int[]{2,4,6,8};
		Solution solver	= new Solution();
		double ans 		= solver.findMedianSortedArrays(list_A, list_B);
		System.out.println(ans);
	}	
}
