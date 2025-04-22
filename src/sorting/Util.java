package sorting;

import java.util.Arrays;

public class Util {
    public static void swap(int[] arr, int swapPosition1, int swapPosition2){
        if(swapPosition1 != swapPosition2){
            int tmp = arr[swapPosition1];
            arr[swapPosition1] = arr[swapPosition2];
            arr[swapPosition2] = tmp;
        }
    }
    public static int[] merge(int[] arr1, int[] arr2){
        //create a new array with a combined length
        int[] arr = new int[arr1.length + arr2.length];
        int index = 0;
        int arr1Index = 0;
        int arr2Index = 0;
        //if both array has remaining value
        while(arr1Index<arr1.length && arr2Index<arr2.length){
            if(arr1[arr1Index] < arr2[arr2Index]){
                arr[index++] = arr1[arr1Index++];
            }else{
                arr[index++] = arr2[arr2Index++];
            }
        }
        //if arr1 has remaining value
        while(arr1Index<arr1.length){
            arr[index++] = arr1[arr1Index++];
        }
        //if arr2 has remaining element
        while (arr2Index < arr2.length){
            arr[index++] = arr2[arr2Index++];
        }
        return arr;
    }
    public static void merge(int[] arr, int start, int mid, int end){
        int[] left = Arrays.copyOfRange(arr, start, mid+1);
        int[] right = Arrays.copyOfRange(arr, mid+1, end+1);
        int[] mergedData = merge(left, right);
        //populate into existing arr
        for(int i=0; i<mergedData.length; i++){
            arr[start+i] = mergedData[i];
        }
    }
}
