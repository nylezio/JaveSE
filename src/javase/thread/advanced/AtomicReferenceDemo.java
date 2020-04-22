package javase.thread.advanced;


import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: CodeJerry
 * @description:
 * 原子引用AtomicReference
 * @date: 2020/03/26 12:50
 */

class User{
    String userName;
    int age;

    public User(String userName,int age){
        this.age = age;
        this.userName = userName;
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 22);
        User l4 = new User("l4", 25);
        //封装成原子引用
        AtomicReference<User> atomicReference =new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, l4)+""+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, l4)+""+atomicReference.get().toString());

    }
}
