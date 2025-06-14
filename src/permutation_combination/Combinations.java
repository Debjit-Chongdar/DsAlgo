package permutation_combination;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    // n = 3, k = 2 => [[1,2], [1,3], [2,3]]
    public static List<List<Integer>> combine(int n, int k) {
        return combine(1, n, k, new ArrayList<>());
    }

    //if n=3
    private static List<List<Integer>> combine(int s, int n, int k, List<Integer> combination) {
        List<List<Integer>> result = new ArrayList<>();
        if (s > n+1) {      //main important part is this if statement, if combination has 3 but s=4, then we should not return empty list
            return result;  //when s > n it's not returning empty list
        }
        if (combination.size() == k) {
            result.add(combination);
            return result;
        }
        result.addAll(combine(s + 1, n, k, combination));
        List<Integer> newCombination = new ArrayList<>(combination);
        newCombination.add(s);
        result.addAll(combine(s + 1, n, k, newCombination));
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = combine(3, 2);
        System.out.println(result);
        List<List<Integer>> result1 = combine(4, 2);
        System.out.println(result1);
    }
}
