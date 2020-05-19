package javase.classes.override;

/**
 * @author: codeJerry
 * @description: 学生
 * @date: 2020/04/01 13:47
 */
public class Student extends Person {
    private final String major;

    public Student(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getMajor() {
        return major;
    }

    @Override
    public void eat() {
        System.out.println("吃点好的");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
