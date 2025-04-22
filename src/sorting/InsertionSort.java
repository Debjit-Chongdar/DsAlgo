package sorting;

import java.util.Arrays;

import static sorting.Util.swap;

public class InsertionSort {

    public static void sort(int[] arr){
        for(int i=1; i<arr.length; i++){
            if(arr[i] < arr[i-1]){
                insert(arr, i);
            }
        }
    }
    //index value need to be insert into previous length
    private static void insert(int[] arr, int index){
        int i = index;
        while(i>=1 && arr[i] < arr[i-1]){
            swap(arr, i, i-1);
            i--;
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
