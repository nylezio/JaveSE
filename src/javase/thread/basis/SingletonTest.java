package javase.thread.basis;

/**
 * @author: codeJerry
 * @description: 单例模式懒汉式改写为线程安全的
 * 运用synchronized的线程安全的单例模式懒汉式
 * @date: 2020/04/04 16:44
 */
class Single {
    private Single(){}

    private volatile static Single instance = null;

    public static Single getInstance() {
        //通过唯一的对象:Bank这个类
        if (instance == null) {
            synchronized (Single.class) {
                if (instance == null) {
                    instance = new Single();
                }
            }
        }
        return instance;
    }
}


public class SingletonTest {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++){
            new Thread(() -> {
                Single instance = Single.getInstance();
                System.out.println(Thread.currentThread().getName()+""+instance);
            },"线程"+ i).start();
        }
    }

}
