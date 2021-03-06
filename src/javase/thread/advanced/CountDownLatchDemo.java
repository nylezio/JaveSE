package javase.thread.advanced;

import javase.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: CodeJerry
 * @description:
 * 倒计时器
 * @date: 2020/03/26 16:57
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++){

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                System.out.println(Thread.currentThread().getName()+"国被灭");
                countDownLatch.countDown();
                }, CountryEnum.forEachCountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"秦统一六国");
        System.out.println("——枚举的使用——");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.FOUR);
        System.out.print(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
        try {
            TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }


    }
}
