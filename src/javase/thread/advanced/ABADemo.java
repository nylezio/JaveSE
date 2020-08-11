package javase.thread.advanced;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: CodeJerry
 * @description: ABA问题的解决
 * 时间戳的原子引用AtomicStampedReference
 * @date: 2020/03/26 13:04
 */
public class ABADemo {
    public static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    public static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        System.out.println("以下是ABA问题的产生");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            //暂停一秒保证线程t1 完成一次ABA
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
        },"t2").start();


        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        System.out.println("以下是ABA问题的解决");
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t初始版本号:"+stamp);
            //暂停一秒t3线程等t4线程拿到一样的版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t一次更新版本号:"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t二次更新版本号:"+atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t初始版本号:"+stamp);
            //暂停3秒t4线程，保证t3线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100,2019, stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() +"修改成功否"+result);
            System.out.println(Thread.currentThread().getName() +"当前实际引用值"+atomicStampedReference.getReference());

        },"t4").start();
    }
}
