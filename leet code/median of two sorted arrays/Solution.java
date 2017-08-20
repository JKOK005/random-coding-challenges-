import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution{
	private float findMedianSortedArrays(int[] list_A, int[] list_B){
		int size_A 	= list_A.length;
		int size_B 	= list_B.length;
		if(size_B > size_A) return findMedianSortedArrays(list_B, list_A);

		// List A is the longer list
		int low_A 			= 0;
		int high_A 			= size_A -1;
		int low_B			= 0;
		int high_B 			= size_B -1;

		int cut_left_A 		= 0;
		int cut_right_A 	= 0;
		int cut_left_B 		= 0;
		int cut_right_B 	= 0;

		while(low_B < high_B){			
			cut_left_A 		= low_A + (high_A - low_A) / 2;
			cut_right_A 	= low_A + (high_A - low_A +1) / 2;
		 	cut_left_B 		= low_B + (high_B - low_B) / 2;
			cut_right_B 	= low_B + (high_B - low_B +1) / 2;

			System.out.println(String.format("Cut A Left: %d,  Cut A right: %d, Cut B left: %d, Cut B right: %d", cut_left_A, cut_right_A, 
									cut_left_B, cut_right_B));

			System.out.println(String.format("Low A: %d,  High A: %d, Low B: %d, High B: %d", low_A, high_A, 
									low_B, high_B));

			if(list_A[cut_left_A] <= list_B[cut_right_B] && list_B[cut_left_B] <= list_A[cut_right_A]){
				// Cut is correct
				break;
			}else if(list_B[cut_left_B] > list_A[cut_right_A]){
				// Cut is too far right -> Move high_B left and low_A right
				low_A 		+= high_B - cut_left_B;
				high_B 		= cut_left_B;
				
			}else{
				// Cut is too far left -> Move low_B right and high_A left	
				high_A 		-= cut_right_B - low_B;
				low_B 		= cut_right_B;

			}
		}

		int a_L, a_R, b_L, b_R;

		if(low_B >= high_B){
			if(high_B == size_B -1){
				b_L 	= list_B[high_B];
				b_R 	= Integer.MAX_VALUE;
			}else{
				b_L 	= Integer.MIN_VALUE;
				b_R 	= list_B[0];
			}
		}else{
			b_L 	= list_B[cut_left_B];
			b_R 	= list_B[cut_right_B];
		}

		if(low_A >= high_A){
			if(high_A == size_A -1){
				a_L 	= list_A[high_A];
				a_R 	= Integer.MAX_VALUE;
			}else{
				a_L 	= Integer.MIN_VALUE;
				a_R 	= list_A[0];
			}
		}else{
			a_L 	= list_A[cut_left_A];
			a_R 	= list_A[cut_right_A];
		}

		return (float) (Math.max(a_L, b_L) + Math.min(a_R, b_R)) / 2;
	}

	public static void main(String[] args){
		int[] list_A 	= new int[]{5,6,7,6};
		int[] list_B 	= new int[]{1,2,3};
		Solution solver	= new Solution();
		float ans 		= solver.findMedianSortedArrays(list_A, list_B);
		System.out.println(ans);
	}	
}