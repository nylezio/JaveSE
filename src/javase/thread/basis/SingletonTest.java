package javase.thread.basis;

/**
 * @author: codeJerry
 * @description: 单例模式懒汉式改写为线程安全的
 * 运用synchronized的线程安全的单例模式懒汉式
 * @date: 2020/04/04 16:44
 */
class Bank {
    private Bank(){}

    private static Bank instance = null;

    public static Bank getInstance() {
        //通过唯一的对象:Bank这个类
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}

/**
 * @author 76582
 */
public class SingletonTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                Bank bank = Bank.getInstance();
                System.out.println(Thread.currentThread().getName()+""+bank);
            },String.valueOf(i)).start();
        }
    }

}
