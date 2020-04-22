package javase.thread.advanced;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: CHNjerry
 * @description: 阻塞队列演示
 * SynchronousQueue(同步队列)
 * @date: 2020/03/26 20:56
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+"put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+"put 3");
                blockingQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();


        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+""+blockingQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+""+blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+""+blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
