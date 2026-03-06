package Thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

//Parallel stream internally use ForkJoinPool
public class ForkJoinTest {
    public List<String> getOutPutUsingForkJoin(List<Integer> list){
        // if we don't mention 2 then it will create threads as per processor
        ForkJoinPool pool = new ForkJoinPool(2); // only create 2 thread
        ForkJoinTask<List<String>> tasks = pool.submit(
                //without parallelStream it will not create multiple thread
                () -> list.parallelStream().map(i -> Thread.currentThread().threadId()+" new "+i).collect(Collectors.toList())
                );
        pool.shutdown();
        //good to shutdown the pool
        return tasks.join();
    }

    public void forkJoin(List<Integer> nums){
        ForkJoinPool pool = ForkJoinPool.commonPool(); //A shared, static global pool used by the entire JVM
        pool.submit(() -> nums.parallelStream().forEach(
                n -> System.out.print(Thread.currentThread().threadId()+" * "+n+" -/- ")
                )).join();
    }

    public List<String> testInvokeAll(List<Integer> list) throws InterruptedException {
        List<Callable<String>> tasks = List.of(
                () -> "Task-1",
                () -> "Task-2",
                () -> "Task-3"
        );

        List<Callable<String>> taskList = list.stream().map(
                i -> (Callable<String>)() -> Thread.currentThread().threadId()+ " - Task - "+i
                )
                .collect(Collectors.toList());

        ForkJoinPool pool = new ForkJoinPool(5);

        List<Future<String>> futures = pool.invokeAll(taskList);

        pool.shutdown();
        return futures.parallelStream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinTest forkJoinTest = new ForkJoinTest();
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        System.out.println(forkJoinTest.getOutPutUsingForkJoin(nums));
        forkJoinTest.forkJoin(nums);
        System.out.println(forkJoinTest.testInvokeAll(nums));
    }
}
