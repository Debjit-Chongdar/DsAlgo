package graph.dijkstra_algo;

import java.util.PriorityQueue;

public class SwimInRaisingWater {

    public static int swimInWater(int[][] grid) {
        //minHeap sorting is based on third element of the array, which is weight
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> a[2]-b[2]);
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int row = 0;
        int col = 0;
        //we don't want to re visit a cell which was visited by less effort
        visited[row][col] = true;
        minHeap.offer(new int[]{row, col, 0});
        while (!minHeap.isEmpty()){
            int[] arr = minHeap.poll();
            row = arr[0];
            col = arr[1];
            int weight = arr[2];
            //if we reach the destination return the value
            if(row == grid.length-1 && col == grid[0].length-1){
                return weight;
            }
            if(row > 0 && !visited[row-1][col]){  //check left cell
                visited[row-1][col] = true;
                minHeap.offer(new int[]{row-1, col, Math.max(grid[row-1][col], weight)});
            }
            if(row<grid.length-1 && !visited[row+1][col]){ // check right cell
                visited[row+1][col] = true;
                minHeap.offer(new int[]{row+1, col, Math.max(grid[row+1][col], weight)});
            }
            if(col<grid[0].length-1 && !visited[row][col+1]){ // check down cell
                visited[row][col+1] = true;
                minHeap.offer(new int[]{row, col+1, Math.max(grid[row][col+1], weight)});
            }
            if(col>0 && !visited[row][col-1]){  // check up cell
                visited[row][col-1]=true;
                minHeap.offer(new int[]{row, col-1, Math.max(grid[row][col-1], weight)});
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,2,10},
                {9,14,4,13},
                {12,3,8,15},
                {11,5,7,6}
        };
        System.out.println(swimInWater(grid));
    }
}
