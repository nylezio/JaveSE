package javase.thread.advanced;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: CHNjerry
 * @description: 生产消费者模式阻塞队列
 * volatile/CAS/AtomicInteger/BlockQueue/线程交互/AtomicReference
 * @date: 2020/03/26 23:29
 */

class MyResource {
    /**
     * flag 默认开启，进行生产+消费
     */
    private volatile boolean flag = true;
    /**
     * 商品
     */
    private AtomicInteger goods = new AtomicInteger();


    BlockingQueue<String> blockingQueue = null;
    /**
     * 构造函数
     */

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProduct() throws Exception{
        String data = null;
        boolean retValue;
        while (flag){
            data = goods.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"插入"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"插入"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"老板叫停，别生产了");
    }

    public void myConsumer() throws Exception{
        String  result = null;
        while (flag){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"超时未取到，消费也退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费"+result+"成功");

        }
    }

    public void stop()throws Exception{
        this.flag = false;
    }

}

public class ProdConsumerBlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                myResource.myProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Product").start();


        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e ){
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("时间到，老板叫main停止，活动结束");
        myResource.stop();
    }

}
