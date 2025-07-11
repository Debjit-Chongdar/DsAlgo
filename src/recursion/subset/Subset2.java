package recursion.subset;

import java.util.*;

public class Subset2 {
    // [1,2,1] => [[], [1], [1], [2], [1, 1], [1, 2], [2, 1], [1, 1, 2]]
    // [1,2,1] => [[], [1], [2], [1, 1], [1, 2], [1, 1, 2]] but expected is this
    public static List<List<Integer>> findSubsets(int[] nums){
        Arrays.sort(nums);
        return subsets(nums, 0, new ArrayList<>());
    }
    private static List<List<Integer>> subsets(int[] nums, int index, List<Integer> subset){
        Set<List<Integer>> result = new HashSet<>();
        if(index == nums.length){
            result.add(subset);
            return new ArrayList<>(result);
        }
        result.addAll(subsets(nums, index+1, subset));
        List<Integer> newSubset = new ArrayList<>(subset);
        newSubset.add(nums[index]);   // add current value with the existing list
        result.addAll(subsets(nums, index+1, newSubset)); // add this existing list to the newSubsetList
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets(new int[]{1,2,1});
        System.out.println(result);
    }
}
