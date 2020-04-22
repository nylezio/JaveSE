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
class HoldLockThread implements Runnable{
    private String a;
    private String b;

    HoldLockThread(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
     synchronized (a){
         System.out.println(Thread.currentThread().getName()+"持有"+ a +"尝试获得"+ b);
         try {
             TimeUnit.SECONDS.sleep(2);
             synchronized (b){
                 System.out.println(Thread.currentThread().getName()+"持有"+ b +"尝试获得"+ a);
             }
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
     }
    }
}

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
        new Thread(new HoldLockThread(lockA,lockB),"AAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBB").start();
    }
}
