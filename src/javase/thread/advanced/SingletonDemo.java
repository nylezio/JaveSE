package javase.thread.advanced;

/**
 * @author: CHNjerry
 * @description: 单例模式
 * 线程安全的单例模式懒汉式
 * 对单例的对象运用volatile关键字
 * @date: 2020/03/25 23:06
 */
public class SingletonDemo {
    /**
     *     volatile禁止指令重排，确保DCL双端检索机制没有失误。
     */
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"SingletonInstanceDemo");
    }

    /**
     * DCl模式（Double Check Lock双端检测机制）
     * @return instance
     */
    public static SingletonDemo getInstance(){
        //先判断对象是否已经实例过，没有实例化过才进⼊加锁代码
        //若不加volatile，仍会出现重排，其他线程已经示例化，但是尚未return
        if (instance == null){
            //类对象加锁
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        int threadNum = 10;
        // 并发多线程，情况变化
        for (int i = 0; i < threadNum; i++){
            new Thread(()->{
                SingletonDemo instance = SingletonDemo.getInstance();
                System.out.println(instance);
            },String.valueOf(i)).start();
        }
    }
}
