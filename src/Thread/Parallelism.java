package Thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Parallelism {
    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

    //api call are blocking * CommonThreadPool blocked * other services suffer
    public void usingParallelStream(List<Integer> list){
        list.parallelStream().forEach(num -> System.out.print(callApi(num)));
    }
    // Fork join pool
    public void usingForkJoinPool(List<Integer> list){
        ForkJoinPool pool = new ForkJoinPool(8);
        try {
            pool.submit(() ->
                list.parallelStream().forEach(num -> System.out.print(callApi(num)))
            ).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
            pool.shutdown();
        }
    }

    //Full Control * no common pool blocking * better resiliency
    public void usingExecutorService(List<Integer> list){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<CompletableFuture<String>> futures = list.stream().map(num -> {
            return CompletableFuture.supplyAsync(() -> callApi(num), executorService);
        }).collect(Collectors.toList());

        List<String> strings = futures.stream().map(future -> future.join()).collect(Collectors.toList());
        strings.forEach(System.out::print);
    }

    private String callApi(int num){
        return "_"+num+"_";
    }

    /*@Async
    public ComplitableFuture<EMP> callRestApi(List<Integer> ids){
        return ComplitableFuture.completedFuture(restApi.get(ids));
    }*/

    public static void main(String[] args) {
        new Parallelism().usingParallelStream(list);
        new Parallelism().usingForkJoinPool(list);
        new Parallelism().usingExecutorService(list);
    }
}
