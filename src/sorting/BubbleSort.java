package sorting;

import java.util.Arrays;

import static sorting.Util.swap;

public class BubbleSort {
    public static void sort(int[] arr){
        for(int i=arr.length-1; i>=0; i--){
            for(int j=1; j<=i; j++){
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr2 = {2,8,5,3,1,7,4,3};
        sort(arr2);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = {1,3,5,7};
        sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
