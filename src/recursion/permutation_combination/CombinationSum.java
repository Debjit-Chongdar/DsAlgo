package recursion.permutation_combination;

import java.util.*;

public class CombinationSum {

    // [2,5,6,9] => [[2, 2, 5], [9]]
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        return allCombinationSum(nums, target, new ArrayList<>());
    }
    private static List<List<Integer>> allCombinationSum(int[] nums, int target, List<Integer> combination) {
        Set<List<Integer>> allCombination = new HashSet<>();
        if(target < 0){
            return new ArrayList<>(allCombination);
        }
        if(target == 0){
            Collections.sort(combination);
            allCombination.add(combination);
            return new ArrayList<>(allCombination);
        }
        for(int num : nums){
            List<Integer> newcombination = new ArrayList<>(combination);
            newcombination.add(num);
            allCombination.addAll(new HashSet<>(allCombinationSum(nums, target-num, newcombination)));
        }
        return new ArrayList<>(allCombination);
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,5,6,9}, 9));
        System.out.println(combinationSum(new int[]{3,4,5}, 16));
        System.out.println(combinationSum(new int[]{3,3}, 7));
    }
}
