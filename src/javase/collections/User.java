package javase.collections;

import java.util.Objects;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/09 12:28
 */
public class User implements Comparable<User>{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User equals method");
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) { return false;}

        User user = (User) o;

        if (age != user.age) {
            return false;
        }
        return Objects.equals(name, user.name);
    }

//    @Override
//    public int hashCode() {
//        System.out.println("User not override Hashcode method");
//        return super.hashCode();
//    }

    @Override
    public int hashCode() {
        System.out.println("User override hashcode method");
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    /**
     * 自然排序
     *  按照姓名从小到大
     * @return 大小
     */
    //使用泛型之前
//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof User){
//            User user = (User) o;
//            int compare = this.name.compareTo(user.name);
//            if (compare != 0){
//                return compare;
//            } else {
//                return Integer.compare(this.age, user.age);
//            }
//
//        } else {
//            throw new RuntimeException("输入的类型不匹配");
//        }
//    }

    @Override
    public int compareTo(User o) {
        int compare = this.name.compareTo(o.name);
        if (compare != 0){
            return compare;
        } else {
            return Integer.compare(this.age, o.age);
        }
    }
}
