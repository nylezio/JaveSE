package javase.collections;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/09 13:25
 */
public class TreeSetTest {

    @Test
    public void test1() {
        //按照年龄从小到大排列
        Comparator<User> com = (u1, u2) -> {
            if (u1.getName().equals(u2.getName())) {
                return 0;
            }
            return -1;
        };



        //有comparator定制排序覆盖了类中的comparable
        TreeSet<User> treeSet = new TreeSet<>();
//        treeSet.add(123);
//        treeSet.add(9);
//        treeSet.add(7);
//        treeSet.add(5);
//        treeSet.add(1);


        treeSet.add(new User("Tom",5));
        treeSet.add(new User("Jerry",7));
        treeSet.add(new User("Jack",7));
        treeSet.add(new User("Jack",15));

        for (Object user: treeSet
             ) {
            System.out.println(user);
        }

        Comparator<Integer> comparator = (o1, o2) -> {
            return Integer.compare(o2, o1);
        };

        TreeSet<Integer> treeSet1 = new TreeSet<>(comparator);
        treeSet1.add(3);
        treeSet1.add(5);
        treeSet1.add(7);
        treeSet1.add(1);


        //增强for循环两种写法
        for (Object in: treeSet1
             ) {
            System.out.println(in);
        }

        treeSet1.forEach(System.out::println);

    }

}
