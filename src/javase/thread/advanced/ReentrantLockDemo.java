package javase.thread.advanced;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author: CodeJerry
 * @description:
 * 可重入锁
 * @date: 2020/03/26 15:05
 */
class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+" sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+" sendEmail()");
    }
    Lock lock = new ReentrantLock();


    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try {
            //可重入锁线程可以进入任何一个他已经拥有的锁
            // 避免了死锁
            System.out.println(Thread.currentThread().getName()+" get()");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" set()");
        }finally {
            lock.unlock();
        }
    }
    ////////////////////
}



public class ReentrantLockDemo {
    public static void main(String[] args) throws Exception{
        Phone phone= new Phone();
        new Thread(()->{
            try{
                phone.sendSMS();
            } catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();


        new Thread(()->{
            try{
                phone.sendSMS();
            } catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();

    }
}

