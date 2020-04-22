package javase.thread.advanced;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    /**
     * 资源类 机场大屏幕
     * volatile保证可见性，第一时间能让大家看到
     */
    private volatile Map<String, Object> map= new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    public void put(String key, Object value){
        rwlock.writeLock().lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"开始写入"+key);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    map.put(key,value);
                    System.out.println(Thread.currentThread().getName()+"写入完成");

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rwlock.writeLock().unlock();
                }
        }
        public void get(String key){
            rwlock.readLock().lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"开始读取");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Object result = map.get(key);
                    System.out.println(Thread.currentThread().getName()+"读取完成:"+result);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rwlock.readLock().unlock();
                }
        }

    /**
     *  清楚缓存
     */
    public void clearMap(){
        map.clear();
        }

}

/**
 * @author: CHNjerry
 * @description: 读写锁
 *      满足并发量，读读能共存，写不能与读写共存
 *      写操作：原子+独占,整个过程必须是完整的统一体，不许被打断
 *      写操作被严格控制，没有加塞，既保证一致性，又保证并发性，读写分离。
 * @date: 2020/03/26 16:13
 */
public class ReadAndWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"", tempInt+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
