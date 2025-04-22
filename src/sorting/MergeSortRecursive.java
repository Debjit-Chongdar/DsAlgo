package sorting;

import java.util.Arrays;

import static sorting.Util.merge;

public class MergeSortRecursive {

    //Simple sort with multiple array creation
    public static int[] sort(int[] arr){
        if(arr.length < 2){
            return arr;
        }
        int mid = arr.length/2; //if length =5, {0,1} {2,3,4} -> {0}{1} {2}{3,4}
        int[] left = sort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = sort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    //Sort with start end range
    public static void mergeSort(int[] arr){
        splitMerge(arr, 0, arr.length-1);
    }
    private static void splitMerge(int[] arr, int start, int end){
        if(start == end){
            return;
        }
        int mid = (start+end)/2;//if length=5, {0,1,2} {3,4} -> {0,1}{2} {3}{4}
        splitMerge(arr, start, mid);
        splitMerge(arr, mid+1, end);
        merge(arr, start, mid, end);
    }

    public static void main(String[] args) {
        int[] arr = {2,8,5,3,1,7,4,3};
        int[] result = sort(arr);
        System.out.println(Arrays.toString(result));
        int[] arr1 = {1,3,5,7};
        int[] result1 = sort(arr1);
        System.out.println(Arrays.toString(result1));

        int[] arr2 = {2,8,5,3,1,7,4,3};
        mergeSort(arr2);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = {1,3,5,7};
        mergeSort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
