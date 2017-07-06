import java.util.*;
import java.lang.*;
import java.io.*;

class SkiingInSingapore{
    private static void printArr(int[][] arr){
        System.out.println(Arrays.deepToString(arr));
    };

    private static void printArr(boolean[][] arr){
        System.out.println(Arrays.deepToString(arr));
    };

    private static Pair<int,int> getStartingLocation(int[][] cost_map){

    }

    // Can refractor: too many arguments
    private static int explore(int X, int Y, int[][] map, int[][] cost_map, boolean[][] has_explored){
        if(has_explored[X][Y]) return cost_map[X][Y];

        int max_row = map.length;
        int max_col = map[0].length;
        int res_n, res_e, res_s, res_w;
        res_n = res_e = res_s = res_w = 0;

        //Explore north
        if(X -1>=0 && map[X][Y] > map[X -1][Y]){
            res_n     = 1 + explore(X -1, Y, map, cost_map, has_explored);
        }
        //Explore east
        if(Y +1<max_col && map[X][Y] > map[X][Y +1]){
            res_e     = 1 + explore(X, Y +1, map, cost_map, has_explored);
        }
        //Explore south
        if(X +1<max_row && map[X][Y] > map[X +1][Y]){
            res_s     = 1 + explore(X +1, Y, map, cost_map, has_explored);
        }
        //Explore west
        if(Y -1>=0 && map[X][Y] > map[X][Y -1]){
            res_w     = 1 + explore(X, Y -1, map, cost_map, has_explored);
        }
        cost_map[X][Y]      = Math.max(res_n, Math.max(res_e, Math.max(res_s, res_w)));
        has_explored[X][Y]  = true;
        return cost_map[X][Y];
    }

    public static void getLongestPath(int[][] map){
        int rows    = map.length;
        int cols    = map[0].length;

        // Construct has_explored and cost map
        boolean[][] has_explored    = new boolean[rows][cols];
        int[][] cost_map            = new int[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(!has_explored[i][j]){
                    explore(i, j, map, cost_map, has_explored);
                }
            }
        }


        printArr(cost_map);
        printArr(has_explored);
    }

    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        int rows    = sc.nextInt();
        int cols    = sc.nextInt();
        int[][] map = new int[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                map[i][j]   = sc.nextInt();
            }
        }
        getLongestPath(map);
    };
};
