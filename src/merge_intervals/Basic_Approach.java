package merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// [[1,3], [6,9], [2,5]] => [[1,5], [6,9]]
// [[1,3], [6,9], [2,7]] => [[1,9]]
public class Basic_Approach {

    public int[][] mergeIfOverlap(int[][] arr){
        if(arr.length < 2){
            return arr;
        }
        // first sort the array based on start/first of each interval
        Arrays.sort(arr, (a,b) -> a[0]-b[0]);
        // as we don't know the size then we have to use List
        List<int[]> result = new ArrayList<>();
        int[] lastArr = arr[0];
        for (int i=1; i<arr.length; i++){
            int[] interval = arr[i];
            if(interval[0] > lastArr[1]){
                result.add(lastArr);
                lastArr = interval;
                //if this is the last index
                if(i == arr.length-1){
                    result.add(interval);
                }
            }else{
                if(lastArr[1] < interval[1]){
                    lastArr[1] = interval[1];
                }
                //if this is the last index
                if(i == arr.length-1){
                    result.add(lastArr);
                }
            }
        }
        return result.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        Basic_Approach b = new Basic_Approach();
        System.out.println(Arrays.deepToString(b.mergeIfOverlap(new int[][]{{1,3}, {6,9}, {2,5}})));
        System.out.println(Arrays.deepToString(b.mergeIfOverlap(new int[][]{{1,3}, {6,9}, {2,7}})));
    }
}
