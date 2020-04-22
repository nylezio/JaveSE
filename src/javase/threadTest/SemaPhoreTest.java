package javase.threadTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: codeJerry
 * @description: 打印数组
 * @date: 2020/04/22 21:44
 */

class Resource {
    public static int[] nums = {1,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
}
public class SemaPhoreTest {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        new Thread(() ->{
            while (Resource.atomicInteger.get() < Resource.nums.length){
                try {
                    semaphore1.acquire();
                    System.out.println(Resource.nums[Resource.atomicInteger.getAndIncrement()]);
                    if (Resource.atomicInteger.get() < Resource.nums.length){
                        semaphore2.release();
                    } else {
                        semaphore1.release();
                        System.exit(0);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() ->{
            while (Resource.atomicInteger.get() < Resource.nums.length){
                try {
                    semaphore2.acquire();
                    System.out.println(Resource.nums[Resource.atomicInteger.getAndIncrement()]);
                    if (Resource.atomicInteger.get() < Resource.nums.length){
                        semaphore3.release();
                    } else {
                        semaphore2.release();
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

        new Thread(() ->{
            while (Resource.atomicInteger.get() < Resource.nums.length){
                try {
                    semaphore3.acquire();
                    System.out.println(Resource.nums[Resource.atomicInteger.getAndIncrement()]);
                    if (Resource.atomicInteger.get() < Resource.nums.length){
                        semaphore1.release();
                    } else {
                        semaphore3.release();
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3").start();

    }
}
