package javase.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/08 22:08
 */
public class ArrayListTest {


    @Test
    public void test1 (){
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(123);
        arrayList.add("A");
        arrayList.add(456);
        System.out.println(arrayList);

        arrayList.add(1,"BB");
        System.out.println(arrayList);
        List<Integer> list = Arrays.asList(1,2,3);
        arrayList.addAll(list);
        System.out.println(arrayList.size());

    }



    @Test
    public void test2 (){
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(123);
        arrayList.add("A");
        arrayList.add(456);
        arrayList.add(1,"BB");
        System.out.println(arrayList);
        int i = arrayList.indexOf(45);
        System.out.println(i);

        List<Object> list = arrayList.subList(1, 3);
        System.out.println("list:"+ list);
        System.out.println(list.size());
        //移除位置，移除指定值
        try {
            arrayList.remove(1);
            arrayList.remove((Integer) 456);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(arrayList);
        System.out.println(arrayList.size());

        System.out.println("****************");
        //Iterator遍历器
        Iterator<Object> iterator = arrayList.iterator();


        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //增强for循环
        for (Object obj: arrayList
             ) {
            System.out.println(obj);
        }
        
        //普通循环
        for (int j = 0; j < arrayList.size(); j++) {
            System.out.println(arrayList.get(j));
        }
    }


}
