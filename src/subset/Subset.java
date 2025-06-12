package subset;

import java.util.ArrayList;
import java.util.List;

// [1,2,3] => [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
public class Subset {

    public static List<List<Integer>> findSubsets(int[] nums){
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> subsetList = new ArrayList<>();
        subsetList.add(subset); //if we don't add this and send empty arraylist it will not go inside for loop
        System.out.println(subsetList);
        return subsets(nums, 0, subsetList);
    }
    private static List<List<Integer>> subsets(int[] nums, int index, List<List<Integer>> subsetList){
        if(index == nums.length){
            return subsetList;
        }
        List<List<Integer>> newSubsetList = new ArrayList<>(); // we have to insert in new list otherwise concurrent modification exception will occure
        for(List<Integer> subset : subsetList){ // as subsetList is not empty it has a empty list inside so it will come inside for loop
            List<Integer> newset = new ArrayList<>(subset);
            newset.add(nums[index]);   // add current value with the existing list
            newSubsetList.add(subset); // add this existing list to the newSubsetList
            newSubsetList.add(newset); // also add this new current list to the newSubsetList
        }
        System.out.println(newSubsetList);
        return subsets(nums, index+1, newSubsetList);
    }
    // better way to implement
    public static List<List<Integer>> findSubsets_better(int[] nums){
        return dfs(nums, 0, new ArrayList<>());
    }
    private static List<List<Integer>> dfs(int[] nums, int index, List<Integer> subset){
        List<List<Integer>> result = new ArrayList<>();
        if(index >= nums.length){
            result.add(subset);
            return result;
        }
        result.addAll(dfs(nums, index+1, new ArrayList<>(subset)));
        List<Integer> newSubset = new ArrayList<>(subset);
        newSubset.add(nums[index]);
        result.addAll(dfs(nums, index+1, newSubset));
        return result;
    }
    public static void main(String[] args) {
        List<List<Integer>> res = findSubsets_better(new int[]{1,2,3});
        System.out.println(res);
        //List<List<Integer>> result = findSubsets(new int[]{1,2,3});
    }
}
//              []
//             /  \
//           []   [1]
//          / \    / \
//       []   [2] [1] [1,2]