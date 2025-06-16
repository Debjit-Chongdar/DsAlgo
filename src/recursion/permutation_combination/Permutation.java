package recursion.permutation_combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {

    public static List<List<Integer>> getAllPermutations(int[] nums){
        boolean[] isUsed = new boolean[nums.length];
        return permutation(nums, isUsed, new ArrayList<>());
    }
    private static List<List<Integer>> permutation(int[] nums, boolean[] isUsed, List<Integer> perm){
        List<List<Integer>> result = new ArrayList<>();
        if(perm.size() == nums.length){
            result.add(new ArrayList<>(perm)); // this new Arraylist is important, otherwise always it will return empty
            return result;
        }
        for(int i=0; i<nums.length; i++){
            if(isUsed[i] == false){
                perm.add(nums[i]);
                isUsed[i] = true;
                result.addAll(permutation(nums, isUsed, perm));
                isUsed[i] = false;
                perm.remove(perm.size()-1);
            }
        }
        return result;
    }
    public static List<List<Integer>> getAllPermutations_remove_duplicate(int[] nums){
        boolean[] isUsed = new boolean[nums.length];
        return permutation_remove_duplicate(nums, isUsed, new ArrayList<>());
    }
    private static List<List<Integer>> permutation_remove_duplicate(int[] nums, boolean[] isUsed, List<Integer> permu){
        Set<List<Integer>> result = new HashSet<>();
        if(permu.size() == nums.length){
            result.add(new ArrayList<>(permu));
            return new ArrayList<>(result);
        }
        for(int i=0; i<nums.length; i++){
            if(!isUsed[i]){
                isUsed[i] = true;
                permu.add(nums[i]);
                result.addAll(permutation_remove_duplicate(nums, isUsed, permu));
                isUsed[i] = false;
                permu.remove(permu.size()-1);
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        System.out.println(getAllPermutations(new int[]{1,2,3}));
        System.out.println(getAllPermutations_remove_duplicate(new int[]{1,2,1}));
    }
}

