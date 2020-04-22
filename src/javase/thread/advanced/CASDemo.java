package javase.thread.advanced;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: CHNjerry
 * @description:
 * CAS是什么？
 *  比较并交换===>compareAndSet
 * @date: 2020/03/25 23:34
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //main do thing...
        //如果是5，表示main线程创建的5没被修改过，才更新为2019
        System.out.println(atomicInteger.compareAndSet(5, 2019)+" current Data:"+atomicInteger.get());
        //但上面已经修改为2019，则不会被更新为1024
        System.out.println(atomicInteger.compareAndSet(5, 1024)+" current Data:"+atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement()+" value:"+ atomicInteger.get());
    }
}
