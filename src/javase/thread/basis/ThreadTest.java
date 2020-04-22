package javase.thread.basis;

/**
 * @author: codeJerry
 * @description: 1、创建线程的第一个方式
 * 创建一个继承于Thread的子类
 * 重写run()方法--->执行的操作声明在run()中
 * 创建MyThread的 instance
 * 通过instance start()
 * @date: 2020/04/03 20:31
 */
class MyThread extends Thread{
    private int n;

    public MyThread() {
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            if (i%2==0){
                System.out.println(Thread.currentThread().getName()+":\t"+ i);
            }
        }
    }
}


/**
 * @author CodeJerry
 */
public class ThreadTest extends Thread{
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("线程1");
        t1.setN(40);
        t1.start();
        System.out.println("hello");
        //已经启动无法在启动
//        t1.start();
    }
}
