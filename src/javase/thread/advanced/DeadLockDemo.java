package javase.thread.advanced;

import java.util.concurrent.TimeUnit;

/**
 * @author: codeJerry
 * @description: 死锁
 * 多个线程（两个以上）争夺资源互相等待的现象
 * 必须具备四个条件：
 * 互斥
 * 请求与保持
 * 资源不可剥夺
 * 循环等待条件
 * @date: 2020/03/28 20:00
 */
class DeadLockThread implements Runnable{
    private final String A;
    private final String B;

    DeadLockThread(String a, String b) {
        this.A = a;
        this.B = b;
    }

    @Override
    public void run() {
     synchronized (A){
         System.out.println(Thread.currentThread().getName()+"持有"+ A +"尝试获得"+ B);
         try {
             TimeUnit.SECONDS.sleep(2);
             synchronized (B){
                 System.out.println(Thread.currentThread().getName()+"持有"+ B +"尝试获得"+ A);
             }
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
     }
    }
}

/**
 * @author Code
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        /*
          资源就是这边的String
         */
        String lockA = "lockA";
        String lockB = "lockB";

        /*
         * 线程AAA获得了lockA，马上要用lockB
         * 线程BBB获得了lockB，马上要用lockA
         * 但是都被占用了
         */
        new Thread(new DeadLockThread(lockA,lockB),"AAA").start();
        new Thread(new DeadLockThread(lockB,lockA),"BBB").start();
    }
}
