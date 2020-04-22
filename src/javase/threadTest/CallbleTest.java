package javase.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/22 17:44
 */
class CallT implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Integer a = 125;
        return a;
    }
}
public class CallbleTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new CallT());

        new Thread(futureTask,"A").start();

        int b = 3;

        System.out.println(futureTask.get() + b);
    }
}
