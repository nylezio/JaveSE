package javase.thread.advanced;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: CodeJerry
 * @description:
 * 集合类不安全的问题
 * ArrayList
 * HashSet
 * HashMap
 *
 * 1 故障对象
 *  java.util.ConcurrentModificationException
 * 2导致原因
 *  并发争强导致的一个正在写入，另一个线程抢夺
 *  导致数据不一致异常，并发修改异常
 * 3解决方案
 *  3.1 new Vector();/new Hashtable();
 *  3.2 Collections.synchronizedList(new ArrayList());
 *  3.3 new CopyOnWriteArrayList<>()
 *  Map中是ConcurrentHashMap<>()
 * 4优化建议
 * @date: 2020/03/26 13:34
 * */

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();
        //java.util.ConcurrentModificationException
        //并发修改异常

    }

    private static void mapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++){
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },"t1").start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"t1").start();
        }
    }
}

