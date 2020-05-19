package javase.thread.advanced;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author: CodeJerry
 * @description: 初始值为0的变量，两个线程一个加1一个减1交替操作，5轮
 * 1.线程  操作  资源类
 * 2.判断  干活  通知
 * 防止虚假唤醒机制
 * @date: 2020/03/26 21:08
 */
class Market {//资源类
    private int flag = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public void increment()throws Exception{
        lock.lock();
        try{
            //多线程中必须用while不能用if
            while (flag != 0){
                //等待，不能生产
                condition.await();
            }
            //干活
            flag++;
            System.out.println(Thread.currentThread().getName()+""+ flag);
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
            while (flag == 0){
                //等待，不能消费
                condition.await();
            }
            //干活
            flag--;
            System.out.println(Thread.currentThread().getName()+""+ flag);
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


public class ProdConsumerTraditionalDemo {
    public static void main(String[] args) {
        Market market = new Market();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    market.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    market.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    market.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    market.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
