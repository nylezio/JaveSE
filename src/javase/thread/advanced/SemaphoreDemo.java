package javase.thread.advanced;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: CodeJerry
 * @description: 信号灯 抢车位
 * 设置Seamphore(车位个数)
 * 车来seamphore.acquire; 车走seamphore.release();
 * @date: 2020/03/26 20:06
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(6);
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"停3秒后车已走");
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
