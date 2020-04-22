package javase.threadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: CodeJerry
 * @description: ABA问题的解决
 * 时间戳的原子引用AtomicStampedReference
 * @date: 2020/03/26 13:04
 */
public class ABATest {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,0);
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(1);

    public static void main(String[] args) {
        System.out.println("ABA问题");
        new Thread(()->{
            atomicReference.compareAndSet(1,2);
            atomicReference.compareAndSet(2,1);
        },"t1").start();

        new Thread(()->{
            //暂停一秒保证线程t1 完成一次ABA
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(1, 2020)+"\t"+atomicReference.get());
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        System.out.println("Stamp版本号解决");
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t初始版本号:"+stamp);
            //暂停一秒t3线程等t4线程拿到一样的版本号
            try {
                TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            atomicStampedReference.compareAndSet(1,2,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t一次更新版本号:"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(2,1,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t二次更新版本号:"+atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t初始版本号:"+stamp);
            //暂停3秒t4线程，保证t3线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(1,2020, stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() +"修改是否成功："+result);
            System.out.println(Thread.currentThread().getName() +"当前实际引用值"+atomicStampedReference.getReference());

        },"t4").start();
    }
}
