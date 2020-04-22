package javase.classes;

/**
 * @author: codeJerry
 * @description: 接口
 *  1.接口中的属性和方法自动定义成public static final
 *  2.接口中的方法不能有body
 *  3.接口中不能定义构造器，意味着接口不能实例化
 *  4.开发中让类实现接口
 *  实现类 需要覆盖接口中所有的方法才能造instance，否则此类仍然为一个抽象类
 *
 *  5.弥补了单继承的局限性
 *
 *  6.接口和接口之间可以继承，且多继承
 *  7.接口的具体使用：
 *      体现多态性
 *      接口实际上是一种规范
 *      面向接口编程！！！
 *  8.JDK8中还可以定义静态方法、默认方法
 *      :接口中的静态方法只能接口调用
 *      :通过实现类的对象，可以调用接口中的默认方法
 *      :如果实现类重写了接口中的默认方法，则调用重写后的
 *      :如果子类继承了实现类并且声明了同名同参的方法，那么子类对象调用的是实现类的方法--->类优先原则
 *      :如果实现类实现了多个接口，且接口中默认方法同名同参，在实现类没有重写此方法的情况下，报错--->接口冲突
 *          此时需要在实现类重写此方法
 * @date: 2020/04/02 14:33
 */
public class InterfaceTest {
    public static void main(String[] args) {
        Ship ship = new Ship();

        //ship中传入的plane是一种实现了飞行的类
        //1、创建了接口的非匿名实现类的非匿名对象
        Plane plane = new Plane();
        ship.fly(plane);

        //2、创建了接口的非匿名实现类的匿名对象
        ship.fly(new Plane());

        //3、创建了接口的匿名实现类的非匿名对象
        Flyable flyable = new Flyable() {
            @Override
            public void up() {
                System.out.println("1: speed up to " + MAX_SPEED);
            }

            @Override
            public void stop() {
                System.out.println("1: stop to "+ MIN_SPEED);
            }
        };
        ship.fly(flyable);


        //3、创建了接口的匿名实现类的非匿名对象
        ship.fly(new Flyable() {
            @Override
            public void up() {
                System.out.println("2: speed up to " + MAX_SPEED);
            }

            @Override
            public void stop() {
                System.out.println("2: stop to " + MIN_SPEED);
            }
        });
    }
}

class Ship {
    public void fly(Flyable fly){
        fly.up();
        fly.stop();
    }
}

interface Flyable{
    /**
     * 全局常量
     * 第一宇宙速度
     */
    public static final int MAX_SPEED = 7900;
    int MIN_SPEED = 0;

    /**
     * 加速
     */
    void up();

    /**
     * 减速
     */
    void stop();
}

class Plane implements Flyable,CC{


    @Override
    public void up() {
        System.out.println("0: speed up to "+ MAX_SPEED);
    }

    @Override
    public void stop() {
    System.out.println("0: stop to " + MIN_SPEED);
    }


    /**
     * 需要实现CC中AA、BB的方法
     */
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }
}

interface AA{
    void method1();
}

interface BB{
    void method2();
}

interface CC extends AA, BB{

}