package javase.classes;

/**
 * @author: codeJerry
 * @description: 单例模式静态内部类
 * @date: 2020/05/19 16:57
 */

public class SingletonTest3 {
    public static void main(String[] args) {
        Single single = Single.getInstance();
        Single single2 = Single.getInstance();
        System.out.println(single == single2);
    }
}

class Single {
    private Single(){}

    private static class SingleIn{
        private static final Single INSTANCE = new Single();
    }

    public static Single getInstance(){
        return SingleIn.INSTANCE;
    }
}