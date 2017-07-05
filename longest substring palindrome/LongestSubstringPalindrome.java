import java.util.*;
import java.lang.*;
import java.io.*;

class LongestSubstringPalindrome{
    // Utility function for printing out array
    private static void printArr(int[][] arr, int rows, int cols){
        for(int i=0; i<rows; i++){
            for(int j=0; j<rows; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println(' ');
        }
        System.out.println(' ');
    };

    // Main function to get palindrome
    private static String getPalindrome(String main_str){
        int str_len     = main_str.length();
        int dp_arr[][]  = new int[str_len][str_len];
        int max_len, start, end, len;
        max_len = start = end  = 0;

        //Iterate rows and columns upwards and left to right
        for(int i=str_len -1; i>=0; i--){
            for(int j=i; j<str_len; j++){
                if(i == j) dp_arr[i][j] = 1;
                else if(main_str.charAt(i) == main_str.charAt(j)){
                    if(i+1 == j || dp_arr[i+1][j-1] == 1) dp_arr[i][j] = 1;
                }

                // Concurrently get the max substring palindrome in the first pass of array
                if(dp_arr[i][j] == 1){
                    len     = j -i +1;
                    if(len >= max_len){
                        start   = i;
                        end     = j;
                        max_len = len;
                    }
                }
            }
        }
        return main_str.substring(start, end +1);
    };

    public static void main(String[] args){
        Scanner sc      = new Scanner(System.in);
        int T           = sc.nextInt();
        String tmp;
        String[] result = new String[T];
        for(int i=0; i<T; i++){
            tmp       = sc.next();
            result[i] = getPalindrome(tmp);
            System.out.println(result[i]);
        }
    };
};
