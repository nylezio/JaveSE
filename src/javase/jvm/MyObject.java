package javase.jvm;

/**
 * @author: codeJerry
 * @description: jvm
 * 1.jvm系统架构图
 * 2.类加载器：
 *  2.1类型
 *      BootStrap
 *      Extension
 *      AppClassLoader
 *  2.2双亲委派！
 *  2.3沙箱安全机制！
 * 3.Native
 *      native关键字
 *      声明有，实现无
 * 4.PC寄存器
 *  4.1是一个行号指示器（指针）指向方法区中的方法字节码行号
 * @date: 2020/03/28 22:14
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        MyObject myObject = new MyObject();
        System.out.println(object.getClass().getClassLoader());
        System.out.println(myObject.getClass().getClassLoader().getParent());
    }
}
