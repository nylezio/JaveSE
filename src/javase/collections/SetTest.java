package javase.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/08 23:17
 */
public class SetTest {
    @Test
    public void test1(){
        Set<Object> set = new HashSet<>();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("BB");
        set.add("129");
        //尚未重写equals和hashcode方法
        set.add( new User("小明",5));
        set.add( new User("小明",5));

        System.out.println("**************************");
        System.out.println("foreach*******************");
        for (Object o : set) {
            System.out.println(o);
        }

        System.out.println("iterator******************");
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
