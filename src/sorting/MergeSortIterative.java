package sorting;

import java.util.Arrays;

import static sorting.Util.merge;

public class MergeSortIterative {
    public static void sort(int[] arr){
        //think if arr has only 2/4/8/16 element, then it will not work with i<arr.length
        int i=2;
        while (i<=arr.length){
            for(int j=0; j<arr.length; j=j+i){
                int start = j;
                //j=4,i=4 then j+i-1=7, it can have only 6 element then end=5 not 7
                int end = (j+i-1 < arr.length)? j+i-1 : arr.length-1;
                int mid = (start+end)/2;
                merge(arr, start, mid, end);
            }
            //for 3 element arr, when i=2, 2*2=4, 4>3, so it will not come inside while loop
            //to bring inside while loop, we need below logic to increment i value
            //in case of i reach end, we have to increase it, otherwise loop will never end
            i = (i < arr.length) ? Math.min(i * 2, arr.length) : i * 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,7,3,8,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr2 = {2,8,5,3,1,7,4,3};
        sort(arr2);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = {1,3,5,7};
        sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
