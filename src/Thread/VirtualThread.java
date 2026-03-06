package Thread;

import java.lang.management.ManagementFactory;
import jdk.management.VirtualThreadSchedulerMXBean;

public class VirtualThread {
    public static void main(String[] args) {
        Thread.ofPlatform().start(() -> System.out.println("Started thread"));//no need to start it
        Thread th = Thread.ofPlatform().unstarted(()-> System.out.println("Unstarted thread"));//need to start it
        th.start();

        Thread.ofVirtual().start(()-> System.out.println("Virtual thread started"));
        Thread t2 = Thread.ofVirtual().unstarted(() -> System.out.println("unstarted virtual thread"));
        t2.start();//it start but as it's demon thread before it start main thread will shoutdown
        // So to keep main thread running until this thread finished
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //how many max thread can run at a time in OS
        System.out.println(Runtime.getRuntime().availableProcessors());
        //explain behind the scene of Virtual thread
        VirtualThreadSchedulerMXBean mxBean = ManagementFactory.getPlatformMXBean(VirtualThreadSchedulerMXBean.class);
        System.out.println(mxBean);
    }
}
