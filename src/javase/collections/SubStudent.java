package javase.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: codeJerry
 * @description:
 * 子类在继承时指定父类的泛型类型
 * 或者仍然是泛型
 * 泛型方法独立于泛型类，与类是否泛型无关
 * @date: 2020/04/09 22:39
 */
public class SubStudent<T> extends Student<T> {
    public SubStudent(String name, int id, T orderT) {
        super(name, id, orderT);
    }

    /**
     * 泛型方法在调用时指定参数类型，所以可以用static修饰
     * @param <E> arr
     * @return 复制
     */
    public static <E> List<E> copy(E[] arr){
        ArrayList<E> list = new ArrayList<>();

        for (E e: arr
             ) {
            list.add(e);
        }
        return list;
    }
}
