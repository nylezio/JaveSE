package javase.classes.override;

/**
 * @author: codeJerry
 * @description: 人
 * @date: 2020/04/01 13:47
 */
public class Person {
    private String name;
    private int age;


    public Person() {
    }



    public void eat(){
        System.out.println("eating");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
