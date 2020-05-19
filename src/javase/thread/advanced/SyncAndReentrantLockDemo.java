package javase.thread.advanced;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: CHNjerry
 * @description: 同步于可重入锁
 * (要求精确唤醒)
 * 题目:A->B->C线程的启动
 * AA打印5次，BB打印10次，CC打印15次
 * 重复10轮
 *
 * @date: 2020/03/26 23:14
 */
class ShareResource{
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //判断
            while (number != 1){
                condition1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+""+i);
            }
            //通知
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print10(){
        lock.lock();
        try{
            //判断
            while (number != 2){
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+""+i);
            }
            //通知
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print15(){
        lock.lock();
        try{
            //判断
            while (number != 3){
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+""+i);
            }
            //通知
            number = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"AA").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"BB").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"CC").start();
    }
}


