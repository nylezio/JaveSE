package javase.classes.override;

/**
 * @author: codeJerry
 * @description: 测试
 *
 * @date: 2020/04/01 13:47
 */
public class PersonTest {
    public static void main(String[] args) {
        Student p = new Student("计算机");
        p.eat();
        System.out.println(p.getMajor());

        //多态
        //调用的方法是要重写的
        Person p1 = new Student("数学");
        Person p2 = new Student("数学");
        p1.setAge(25);
        p1.setName("亮亮");
        p2.setAge(25);
        System.out.println(p1.getAge());
        System.out.println(p1.getName());
        System.out.println(Math.random());

        System.out.println(p1.equals(p2));
    }


}
