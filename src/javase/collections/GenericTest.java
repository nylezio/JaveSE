package javase.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/09 21:56
 */
public class GenericTest {

    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(78);
        list.add(94);
        list.add(67);
        list.add(88);

        for (Integer obj : list){
            int stuScore = obj;
            System.out.println(stuScore);
        }
    }

    /**
     * 自定义泛型的测试
     */
    @Test
    public void test1(){
        Student<String> student = new Student<>("Tom",13,"你好");
        student.orderT = "Hello泛型";
        System.out.println(student);

        SubStudent<Integer> subStudent = new SubStudent<>("Tom",14,5);



        Integer[] arr = new Integer[]{1,2,3,4};
        List<Integer> copy = SubStudent.copy(arr);
        System.out.println(copy);

        Double[] arr1 = new Double[]{1.1,2.3,3.4};
        List<Double> copy1 = SubStudent.copy(arr1);
        System.out.println(copy1);
    }
}
