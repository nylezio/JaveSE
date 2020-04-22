package javase.thread.advanced;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class myData {
    //Mydata.java => Mydata.class===>JVM字节码
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }
    //此时number加了volatile但是不保证原子性。
    public void addPlusPlus(){
        number++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        //构造默认初始值0
        atomicInteger.getAndIncrement();
    }
}

/**
 * @author: CHNjerry
 * @description: Demo
 * 1.volatile 的可见性
 *  1.1 加入 int number = 0; number变量无volatile关键字修饰，没有可见性。
 *  1.2 添加了volatile，可以解决可见性问题。
 * 2.验证volatile不保证原子性
 *  2.1原子性是什么意思：
 *          不可分割，完整性，线程正在做某个具体业务，中间不可以被加塞或者被分割。需要整体完整性
 *          要么同时成功，要么同时失败。
 *  2.2volatile 不保证原子性演示
 *  2.3why //底层分为三条指令相加
 *  2.4如何解决原子性
 *      *加synchronized（重量级锁）（不提倡）
 *      *加atomic（如AtomicInteger、AtomicReference）********
 * @date: 2020/03/25 19:53
 */

public class VolatileDemo {
    public static void main(String[] args) {
        myData myData = new myData();
        for (int i = 0; i < 20; i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        //等待20个线程全部计算完后，再用main线程取得最终的结果
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type ,finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type ,finally number value:" + myData.atomicInteger);
    }

    //volatile 可以保证可见性，及时通知其他线程，主内存变量值已经变换。
    private static void seeOkByvolatile() {
        myData myDate =new myData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myDate.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t  update number value:"+myDate.number);
            },"AAA").start();

        while (myDate.number==0){
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get MyDate value number:"+ myDate.number);
    }
}
