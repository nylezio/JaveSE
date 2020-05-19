package javase.thread.advanced;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: CodeJerry
 * @description: 生产消费者模式阻塞队列
 * volatile/CAS/AtomicInteger/BlockQueue/线程交互/AtomicReference
 * @date: 2020/03/26 23:29
 */

class Shop {
    /**
     * flag 默认开启，进行生产+消费
     */
    private volatile boolean flag = true;
    /**
     * 商品
     */
    private final AtomicInteger goods = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    /**
     * 构造函数
     */
    public Shop(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws Exception{
        String data = null;
        boolean retValue;
        while (flag){
            data = "面包"+ goods.incrementAndGet();
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"生产"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"未生产"+data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"老板叫停，停止生产");
    }

    public void consume() throws Exception{
        String  result = null;
        while (flag){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"超时未买到，消费退出");
                System.out.println();
                return;
            }
            TimeUnit.SECONDS.sleep(0);
            System.out.println(Thread.currentThread().getName()+"消费"+result+"成功");
        }
    }

    public void stop()throws Exception{
        System.out.println();
        System.out.println("时间到，main叫停，关店");
        this.flag = false;
    }

}

public class ProdConsumerBlockQueueDemo {
    public static void main(String[] args) throws Exception {
        Shop shop = new Shop(new ArrayBlockingQueue<>(1));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                shop.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Producer").start();


        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                shop.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();




        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e ){
            e.printStackTrace();
        }

        shop.stop();
    }

}
