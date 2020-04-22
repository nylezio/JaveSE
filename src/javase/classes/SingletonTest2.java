package javase.classes;

/**
 * @author: codeJerry
 * @description: 单例模式的懒汉式实现
 * @date: 2020/04/01 22:07
 */
public class SingletonTest2 {
    public static void main(String[] args) {
        Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();
        System.out.println(order1 == order2);
    }
}

class Order{
    /**
     * 1.私有化类的构造器
     */
    private Order(){}


    /**
     * 2.声明类对象，没有初始化
     */
    private static Order instance = null;

    /**
     *
     * 声明一个public、static的返回当前类对象的方法
     */
    public static Order getInstance(){
        if (instance == null){
            instance = new Order();
        }
        return instance;
    }
}