package Thread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class RandomCallable implements Callable<String>{
    private String val;

    public RandomCallable(String val){
        this.val = val;
    }

    @Override
    public String call(){
        return new Random().nextInt() +" - "+ val+ " , ";
    }
}
public class CallableExample {

    public static void testCallable(List<String> strings){
        Future<String>[] futureArray = new Future[strings.size()];

        for(int i=0; i<futureArray.length; i++){
            RandomCallable randomCallable = new RandomCallable(strings.get(i));
            FutureTask<String> fTask = new FutureTask<>(randomCallable);
            futureArray[i] = fTask;
            new Thread(fTask).start();
        }

        for(int i=0; i<futureArray.length; i++){
            try{
                String val= futureArray[i].get();
                System.out.print(val);
            }catch(InterruptedException ie){
            }catch (ExecutionException ee){
            }catch (CancellationException ce){}
        }
    }

    public static void test_ForkJoin_pool(List<String> list){
        List<Callable<String>> callableList = list.stream().map(
                val -> (Callable<String>)()-> new Random().nextInt() +" - "+ val + " , "
        ).collect(Collectors.toList());

        ForkJoinPool pool = ForkJoinPool.commonPool();

        List<ForkJoinTask<String>> forkJoinTasks = callableList.stream().map(
                callable -> pool.submit(callable)
        ).collect(Collectors.toList());

        forkJoinTasks.forEach(task -> System.out.print(task.join()));

        // another way
        try {
            pool.invokeAll(callableList).forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
    }

    public static void test_executor_service(List<String> list){
        List<Callable<String>> callableList = list.stream().map(
                val -> (Callable<String>)()-> Thread.currentThread().getName() +" - "+ val + " , "
        ).collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(
                Math.min(Runtime.getRuntime().availableProcessors(), list.size()));
        try {
            List<Future<String>> futures = executorService.invokeAll(callableList);
            futures.forEach(f -> {
                try {
                    System.out.print(f.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }

    public static void test_executor_service_2(List<String> list){
        ExecutorService executorService = Executors.newFixedThreadPool(
                Math.min(Runtime.getRuntime().availableProcessors(), list.size()));

        list.stream().map(c-> CompletableFuture.supplyAsync(() -> new CallableExample().method(c), executorService))
                .forEach(f -> System.out.print(f.join()));
        executorService.shutdown();
    }
    private String method(String s){
        return Thread.currentThread().getName() +" - "+ s + " , ";
    }
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A","B","C","D","E","F","G","H");
        testCallable(list);
                System.out.println();
        test_ForkJoin_pool(list);
                System.out.println();
        test_executor_service(list);
                System.out.println();
        test_executor_service_2(list);
    }
}
