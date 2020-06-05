package javase.threadtest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/22 18:24
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,() -> {
            System.out.println("通关");
        });
        for (int i = 0; i < 5; i++) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                    cyclicBarrier.await();
                }catch (InterruptedException | BrokenBarrierException e){
                    e.printStackTrace();
                }
        }).start();
    }
    }
}
