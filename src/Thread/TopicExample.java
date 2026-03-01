package Thread;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class TopicExample {
    private final Queue<String> topic = new LinkedList<>();
    private final int max_size = 3;

    public void producer(String str){
        System.out.println("thread id producer = "+ Thread.currentThread().threadId());
        synchronized (topic){
            while (topic.size() == max_size){
                try {
                    topic.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("thread id producer available = "+ Thread.currentThread().threadId());
            topic.offer(str);
            topic.notify();
        }
    }

    public String consumer(){
        System.out.println("thread id consumer = "+ Thread.currentThread().threadId());
        synchronized (topic){
            while (topic.isEmpty()){
                try {
                    topic.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("thread id consumer available = "+ Thread.currentThread().threadId());
            String str = topic.poll();
            topic.notify();
            return str;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F", "G","H","I");
        TopicExample topic = new TopicExample();

        Thread t1 = new Thread(() -> topic.producer("A"));
        Thread t2 = new Thread(() -> topic.producer("B"));
        Thread t3 = new Thread(() -> topic.producer("C"));
        Thread t4 = new Thread(() -> topic.producer("D"));
        Thread t5 = new Thread(() -> topic.producer("E"));
        Thread t6 = new Thread(() -> topic.producer("F"));

        Thread tt1 = new Thread(() -> System.out.println(topic.consumer()));
        Thread tt2 = new Thread(() -> System.out.println(topic.consumer()));
        Thread tt3 = new Thread(() -> System.out.println(topic.consumer()));
        Thread tt4 = new Thread(() -> System.out.println(topic.consumer()));
        Thread tt5 = new Thread(() -> System.out.println(topic.consumer()));
        Thread tt6 = new Thread(() -> System.out.println(topic.consumer()));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        tt1.start();
        tt2.start();
        tt3.start();
        tt4.start();
        tt5.start();
        tt6.start();
    }
}
