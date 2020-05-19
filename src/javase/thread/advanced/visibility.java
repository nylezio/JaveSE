package javase.thread.advanced;

import java.util.concurrent.TimeUnit;

/**
 * @author: codeJerry
 * @description: 可见性验证
 * 此时程序无法正常退出
 * @date: 2020/03/30 23:27
 */

class Num{
//    volatile int number=10;
    int number=10;
    void addNumber(){
        this.number+=100;
    }
}

public class visibility {
    public static void main(String[] args) {
        Num num = new Num();
        System.out.println(num.number);

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            num.addNumber();
            System.out.println(Thread.currentThread().getName()+"修改值为"+num.number);
        },"AAA").start();

        while (num.number == 10){

        }
        System.out.println(num.number);
    }

}


