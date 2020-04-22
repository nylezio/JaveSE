package javase.thread.basis;

/**
 * @author: codeJerry
 * @description: 使用Runnable接口实现线程创建
 * 使用同步方法解决Runnable接口的线程安全问题
 * 同步代码块
 * 同步方法
 * @date: 2020/04/04 09:41
 */
class Window implements Runnable{

    private int ticket = 100;


    @Override
    public void run() {
        while (ticket > 0) {
            show();
        }
    }

    /**
     * （同步方法）同步监视器: this
     */
    private synchronized void show() {

            if (ticket > 0){
                System.out.println(Thread.currentThread().getName()+"卖票后:余票"+ticket);
                ticket--;
            }

        }
}

/**
 * @author Jerry
 */
public class RunnableTest {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.start();
        t2.start();
        t3.start();

    }
}
