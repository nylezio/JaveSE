package javase.others;

import java.util.Date;

/**
 * @author: CHNjerry
 * @date: 2020/03/25 15:32
 * 这是⼀个简单的Runnable类，需要⼤约5秒钟来执⾏其任务。
 */
public class MyRunnable implements Runnable{
    private String command;

    public MyRunnable(String s){
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = "
                + new Date());
        System.out.println("执行命令"+ command);
    }

    private void processCommand(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable("HH")).start();

    }
}
