package javase.thread.advanced;


import com.javase.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: CHNjerry
 * @description:
 * 倒计时器
 * @date: 2020/03/26 16:57
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        CountDownLatch countDownLatch1 = new CountDownLatch(5);
        for (int i = 0; i <= 5; i++) {
            new Thread(() ->{
                        countDownLatch1.countDown();
                    },"t1").start();
        }
        countDownLatch1.await();
        System.out.println("完成");
        System.out.println(CountryEnum.FOUR);

        for (int i = 1; i <= 6; i++){

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"国被灭");
                countDownLatch.countDown();
                }, CountryEnum.forEachCountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"六国统一");
        System.out.println("枚举的使用");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
        try {
            TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }


    }
}
