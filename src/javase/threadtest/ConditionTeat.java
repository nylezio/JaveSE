package javase.threadtest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: CodeJerry
 * @description: 同步于可重入锁
 * (要求精确唤醒)
 * 题目:A->B->C线程的启动
 * 线程ABC依次打印一个数组内容
 * 重复10轮
 *
 * @date: 2020/03/26 23:14
 */
class ShareResource{
    /**
     *A:1 B:2 C:3
     */
    private int flag = 1;
    public int[] nums ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    public int index = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void print1 (){
        lock.lock();
        try{
            //判断
            while (flag != 1){
                condition1.await();
            }
            //干活
            if (index < nums.length) {
                System.out.print(Thread.currentThread().getName()+"");
                System.out.println(nums[index++]);
            }
            //通知
            flag = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print2(){
        lock.lock();
        try{
            //判断
            while (flag != 2){
                condition2.await();
            }
            //干活
            if (index < nums.length){

                System.out.print(Thread.currentThread().getName()+"");
                System.out.println(nums[index++]);
            }
            //通知
            flag = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print3(){
        lock.lock();
        try{
            //判断
            while (flag != 3){
                condition3.await();
            }
            //干活

            if (index < nums.length){
                System.out.print(Thread.currentThread().getName()+"");
                System.out.println(nums[index++]);
            }

            //通知
            flag = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}

public class ConditionTeat {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() ->{
            while (shareResource.index < shareResource.nums.length){
                shareResource.print1();
            }
        },"ThreadA：").start();

        new Thread(() ->{
            while (shareResource.index < shareResource.nums.length){
                shareResource.print2();
            }
        },"ThreadB：").start();

        new Thread(() ->{
            while (shareResource.index < shareResource.nums.length){
                shareResource.print3();
            }
        },"ThreadC：").start();
    }
}


