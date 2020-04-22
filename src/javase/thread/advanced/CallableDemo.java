package javase.thread.advanced;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: codeJerry
 * @description:
 * 多线程中，第3种获得多线程的方式
 * 分支合作
 *  * 带返回值的获得多线程的方式
 *  * 创建一个实现Callable的实现类
 * @date: 2020/03/28 15:10
 */
class CallT implements Callable<Integer>{

    /**
     * 实现call()方法
     * @return 返回值
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable线程call");
        int num = 5;
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return num;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //创建接口实现类的对象
        // 将实现类的对象传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask<Integer> futureTask = new FutureTask<>(new CallT());

        //将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，调用start();
        Thread t1 = new Thread(futureTask, "AAA");

        t1.start();

        System.out.println("****************");
        int result01 = 100;

        while (!futureTask.isDone()){
        }
        //建议放在最后，获得Callable的计算结果，没有完成就要强求，直到计算完成
        int result02 = futureTask.get();
        System.out.println(result01+result02);
    }
}
