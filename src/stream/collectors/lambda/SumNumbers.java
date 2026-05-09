package stream.collectors.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SumNumbers {
    static Integer sumNumbers(List<Integer> numbers){
        return numbers.stream().collect(Collectors.summingInt(n -> n));
    }

    static Integer sumNumbers_1(List<Integer> numbers){
        /*return numbers.stream().collect(Collector.of(
                //ArrayList::new,     // Immutable type is not accepted, like INTEGER, String etc..
                () -> new ArrayList<Integer>(1),
                (List<Integer> list, Integer n) -> list.set(0, n+(list.size()>0?list.get(0) : 0)),
                (list1, list2) -> {
                    list1.set(0, list1.get(0)+list2.get(0));
                    return list1;
                },
                list -> list.get(0)
        ));*/
        return numbers.stream().collect(Collector.of(
/*supplier*/    () -> new int[1],
/*accumulator*/ (nums, num) -> {
                    nums[0] += num;
                },
/*combiner*/    (nums1, nums2) -> {
                    nums1[0] = nums1[0] + nums2[0];
                    return nums1;
                },
/*Finisher*/    nums -> nums[0]
        ));
    }

    static void main() {
        System.out.println(sumNumbers(Arrays.asList(4, 6,3, 2,1,5)));
        System.out.println(sumNumbers_1(Arrays.asList(4, 6,3, 2,1,5)));
    }
}
