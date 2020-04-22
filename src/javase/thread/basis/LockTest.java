package javase.thread.basis;

import java.util.concurrent.locks.ReentrantLock;

class Window1 implements Runnable {

    private int ticket = 100;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();

                if (ticket > 0) {
                    Thread.sleep(100);
                    System.out.println(ticket);
                    ticket--;
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally { lock.unlock(); }

        }
    }
}

/**
 * @author: codeJerry
 * @description: Reentrantlock
 * @date: 2020/04/04 17:17
 */
public class LockTest {
    public static void main(String[] args) {
        Window1 window1 = new Window1();
        Thread thread = new Thread(window1);
        Thread thread1 = new Thread(window1);
        Thread thread2 = new Thread(window1);
        thread.setName("窗口1");
        thread2.setName("窗口2");
        thread1.setName("窗口3");
        thread.start();
        thread1.start();
        thread2.start();
    }
}

