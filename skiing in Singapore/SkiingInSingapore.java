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

    private static int traceBack(int[][] map, int[][] cost_map, PairClass start){
        int X           = start.getLeft();
        int Y           = start.getRight();
        int max_row     = map.length;
        int max_col     = map[0].length;
        int tb_n, tb_e, tb_s, tb_w;
        tb_n = tb_e = tb_s = tb_w = 9999;       // Choose a number that is greater than 1500

        PairClass next  = new PairClass(-1,-1);
        if(cost_map[X][Y] == 0){
            return map[X][Y];   // Return value of the end point 
        }else{
            // Scan north
            if(X -1>=0 && cost_map[X][Y] == cost_map[X -1][Y] +1){
                next.set(X -1, Y);
                tb_n = traceBack(map, cost_map, next);
            }
            // Scan east
            if(Y +1<max_col && cost_map[X][Y] == cost_map[X][Y +1] +1){
                next.set(X, Y +1);
                tb_e = traceBack(map, cost_map, next);
            }
            // Scan south
            if(X +1<max_row && cost_map[X][Y] == cost_map[X +1][Y] +1){
                next.set(X +1, Y);
                tb_s = traceBack(map, cost_map, next);
            }
            // Scan west
            if(Y -1>=0 && cost_map[X][Y] == cost_map[X][Y -1] +1){
                next.set(X, Y -1);
                tb_w = traceBack(map, cost_map, next);
            }
            return Math.min(tb_n, Math.min(tb_e, Math.min(tb_s, tb_w)));
        }
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

    private static int getLargestDrop(int[][] map, int[][] cost_map, int max_cost){
        int lowest_pt, drop;
        int max_drop        = 0;
        int rows            = map.length;
        int cols            = map[0].length;
        PairClass start     = new PairClass(-1,-1);
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(cost_map[i][j] == max_cost){
                    start.set(i,j);
                    lowest_pt   = traceBack(map, cost_map, start);
                    drop        = map[i][j] - lowest_pt;
                    if(drop > max_drop){
                        max_drop = drop;
                    }
                }

            }

        }
        return max_drop;
    }

    public static void getLongestPath(int[][] map){
        int rows    = map.length;
        int cols    = map[0].length;
        int longst_path     = 0;
        int val;

        // Construct has_explored and cost map
        boolean[][] has_explored    = new boolean[rows][cols];
        int[][] cost_map            = new int[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(!has_explored[i][j]){
                    val = explore(i, j, map, cost_map, has_explored);
                    if(val > longst_path){
                        longst_path = val;
                    }
                }
            }
        }
        System.out.println(String.format("Longest path: %d", longst_path +1));
        int largest_drop    = getLargestDrop(map, cost_map, longst_path);
        System.out.println(String.format("Largest drop: %d", largest_drop));
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
