package javase.thread.advanced;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author: codeJerry
 * @description: 线程池
 * ①基础Thread类
 * ②实现Runnable接口 无返回值
 * ③实现Callable接口 有返回值
 * ④第4种获得多线程的方式:线程池
 *
 * 线程池构造器 threadPoolExecutor
 * 不建议采用执行类 Executors（s辅助工具类）//Collections
 * * 手写线程池
 *          * maximumPoolSize 设置考虑：
 *          * CPU密集型：CPU核数+1
 *          * IO密集型：CPU核数*2
 * @date: 2020/03/28 16:14
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                11,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolWork(threadPool);

        threadPoolInit();

    }

    private static void threadPoolWork(ExecutorService threadPool) {
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //线程池关闭
            threadPool.shutdown();
        }
    }

    /**
     * 但是生产中不用Executors创建线程池，需要规避资源耗尽的风险,因为无法设定Blocking的长度。
     */
    @Test
    private static void threadPoolInit() {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);//一池5个处理线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//一池1个处理线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();//一池N个处理线程,根据业务自动扩线程
        //模拟10个用户办理业务，每个用户就是一个请求线程
        threadPoolWork(threadPool1);

        threadPoolWork(threadPool2);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool3.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
                try {
                    TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool3.shutdown();
        }
    }
}
