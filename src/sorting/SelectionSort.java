package sorting;

import java.util.Arrays;

import static sorting.Util.swap;

public class SelectionSort {
    public static void sort(int[] arr){
        for(int i=0; i<arr.length; i++){
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
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
