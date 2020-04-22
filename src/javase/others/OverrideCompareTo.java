package javase.others;

import java.util.Set;
import java.util.TreeMap;

/**
 * Author: CHNjerry
 * Date: 2020/03/20 22:57
 * @author 76582
 */
public class OverrideCompareTo {
    public static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age){
            super();
            this.name = name;
            this.age =age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Person o){
            if (this.age > o.getAge()){
                return 1;
            } else if (this.age < o.getAge()){
                return -1;
            }
            return age;
        }

    }
    public static void main(String[] args) {
        TreeMap<Person, String> pdata = new TreeMap<Person, String>();
        pdata.put(new Person("张三", 30), "zhangsan");
        pdata.put(new Person("李四", 20), "lisi");
        pdata.put(new Person("王五", 10), "wangwu");
        pdata.put(new Person("⼩红", 5), "xiaohong");
// 得到key的值的同时得到key所对应的值
        Set<Person> keys = pdata.keySet();
        for (Person key : keys) {
            System.out.println(key.getAge() + "-" + key.getName());
        }
    }
}
