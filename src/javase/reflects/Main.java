package javase.reflects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/09/19 21:55
 */
public class Main {
    public static void main(String[] args) throws Exception {
        /*
         * 获取代理类，再获取对象
         * */
        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        Collection proxy1 = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        System.out.println(proxy1);
        System.out.println(proxy1.toString());
        proxy1.clear();

        /**
         * 直接创建对象
         */
        Collection list = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {

                    List target = new ArrayList<>();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long start = System.currentTimeMillis();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        Object retVal = method.invoke(target, args);
                        long end = System.currentTimeMillis();
                        System.out.println(method.getName() + " " + (end - start));
                        return retVal;
                    }
                });

        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.toString());
        System.out.println(list.size());
    }
}
