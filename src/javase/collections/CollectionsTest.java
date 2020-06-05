package javase.collections;


import java.util.*;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/08 18:09
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<Object> collection = new ArrayList<>();
        List<Integer> collection1 = new ArrayList<>();
        collection1.add(123);
        collection.add(456);
        collection.add(123);
        collection.add("abc");
        collection.add("Tom");
        System.out.println(collection);
        System.out.println(collection.hashCode());
        System.out.println(collection.containsAll(collection1));

        System.out.println("**************");
        List<String> list = Arrays.asList("AA", "BB", "CC");
        System.out.println(list);

        //包装类的size就是个数
        List<int[]> ints = Arrays.asList(new int[]{123, 456, 789});
        System.out.println("size:"+ ints.size());

        List<Integer> list1 = Arrays.asList(123, 456, 789);
        System.out.println("size:"+list1.size());

        //迭代器遍历容器
        Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //foreach
        for (Object o : collection) {
            System.out.println(o);
        }



    }
}
