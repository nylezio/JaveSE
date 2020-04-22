package javase.thread.advanced;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{//资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment()throws Exception{
        lock.lock();
                try{
                    //多线程中必须用while不能用if
                    while (number != 0){
                        //等待，不能生产
                        condition.await();
                    }
                    //干活
                    number++;
                    System.out.println(Thread.currentThread().getName()+""+number);
                    //通知唤醒
                    condition.signalAll();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
        //1 判断

    }
    public void decrement()throws Exception{
        lock.lock();
                try{
                    while (number == 0){
                        //等待，不能消费
                        condition.await();
                    }
                    //干活
                    number--;
                    System.out.println(Thread.currentThread().getName()+""+number);
                    //通知唤醒
                    condition.signalAll();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
        //1 判断

    }

}

/**
 * @author: CHNjerry
 * @description: 初始值为0的变量，两个线程一个加1一个减1交替操作，5轮
 * 1.线程  操作  资源类
 * 2.判断  干活  通知
 * 防止虚假唤醒机制
 * @date: 2020/03/26 21:08
 */
public class ProdConsumerTraditionalDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
