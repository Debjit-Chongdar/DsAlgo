package stream.collectors.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelExecution {

    public void maintain_sequential_ParralelStream(List<Integer> list) {
        // Collectors.toList() maintain encounter order
        List<Integer> nlist = list.parallelStream().map(i -> i - 1).collect(Collectors.toList());
        nlist.forEach(n -> System.out.print(n+" - "));
        System.out.println(" Using collect");
        // output order as per input is not guaranteed
        list.parallelStream().forEach(n -> System.out.print(n + " - "));
        System.out.println(" forEach ");
        // maintain input order in output * after process , buffer result, reassemble in order
        list.parallelStream().forEachOrdered(n -> System.out.print(n + " - "));
        System.out.println(" forEachOrdered ");
        // force sequential at the end
        list.parallelStream().map(i -> i - 1).sequential().forEach(n -> System.out.print(n+" - "));
        System.out.println(" map(..).sequential().foreach");
        // forkJoinPool.commonPool() -> Multiple worker thread -> merge Result
    }

    static void main() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        new ParallelExecution().maintain_sequential_ParralelStream(list);
    }
}
