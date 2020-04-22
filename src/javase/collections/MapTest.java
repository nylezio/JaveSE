package javase.collections;

import org.junit.Test;

import java.util.*;

/**
 * @author: codeJerry
 * @description:
 * HashMap:线程不安全，效率高,可以存储null的key和value，一个键值对是一个Node对象.
 *  key是使用Set保存，不能重复（所以Key所在的类要重写equals()和hashcode()），Value是用Collection保存
 *  jdk7 数组+链表,实例化后创建长度为16的一维数组Entry[] table.
 *      map.put(key,value);
 *      扩容时扩为原来的2倍，并将原有的数据全部复制过来.
 *  jdk8 数据+链表+红黑树
 *      new HashMap():没有创建数组
 *      首次map.put()时创建一个长度为16的数组存放键值对Node对象
 *      当一个索引位置上的元素以链表形式存在数个数大于8，且当前数组的长度>64时，当前索引的数据改为用红黑树存储。
 * LinkedHashMap:在遍历时候因为在HashMap底层基础上，添加了一堆指针，可以按照添加顺序实现遍历
 * TreeMap:保证按照添加的key-value对进行排序，按照key排序遍历，底层使用红黑树,要求是同类的对象存储key
 * Hashtable:古老的类，线程安全的，效率低，加了synchronized同步锁
 * @date: 2020/04/09 15:40
 */
public class MapTest {
    @Test
    public void test(){
        Map<Object,Object> map = new HashMap<>();
        map.put(null,null);
        map.put(null,3);
        map.put(2,4);
        map.put(1,4);
        map.put("hhh",4);

        System.out.println(map);
        boolean b = map.containsKey(2);
        if (b){
            System.out.println(map.get(2));
        }


        Set<Object> set = map.keySet();
        System.out.println(set);

        Collection<Object> values = map.values();
        System.out.println(values);

        Set<Map.Entry<Object,Object>> entrySet = map.entrySet();
        System.out.println(entrySet);

        Iterator<Map.Entry<Object, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }


        map.clear();
        System.out.println(map);

        //线程安全的
        Map<Object, Object> map1 = Collections.synchronizedMap(map);
        System.out.println(map1);
    }

    @Test
    public void test1(){
        Map<Object,Object> map = new HashMap<>();
        map.put(null,null);
        map.put(null,3);
        map.put(2,4);
        map.put(1,4);
        map.put("hhh",4);

        System.out.println(map);
        boolean b = map.containsKey(2);
        if (b){
            System.out.println(map.get(2));
        }

        //Map的两种遍历
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }


        Iterator<Map.Entry<Object, Object>> iterator1 = entries.iterator();
        while (iterator1.hasNext()){
            Map.Entry<Object, Object> next = iterator1.next();
            System.out.println("key:"+next.getKey()+",value:"+next.getValue());
        }

    }

    @Test
    public void test2(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        map.put(1,2);



        System.out.println(map.get(0));
        System.out.println(map.get(1));
        System.out.println(map.get(3));

    }
}
