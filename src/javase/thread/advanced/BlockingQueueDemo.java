package javase.thread.advanced;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: CHNjerry
 * @description:
 * ArrayBlockingQueue 基于数组的有界阻塞队列FIFO
 * LinkedBlockingQueue 基于链表的有界（默认Integer.MAX_VALUE）阻塞队列FIFO，吞吐量高于Array
 * SynchronousQueue 不存储元素的阻塞队列，插入要等另一个线程移除操作,只有一个元素的队列
 * @date: 2020/03/26 20:19
 * 1 队列
 * 2 阻塞队列
 *     2.1阻塞队列 可以让人等待
 *     2.2不得不阻塞，如何管理
 *     核心方法
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("............");
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));

//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());

    }
}
