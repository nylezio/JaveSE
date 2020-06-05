package javase.threadtest;


import java.util.concurrent.*;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/06/01 19:30
 */
class CallableThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        int u;
        for (int i = 0; i < 20; i++) {
            sum += i;
            System.out.println(Thread.currentThread().getName() + ":" + sum);
        }
        return sum;
    }
}
/**
 * @author CodeJerry
 */
public class SubmitTest {
    public static void main(String[] args) {
        CallableThread callableThread = new CallableThread();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                5,
                2000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 5; i++) {
            Future future = threadPool.submit(callableThread);
            Integer sum = null;
            try {
                sum = (Integer) future.get();
                System.out.println(sum);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
