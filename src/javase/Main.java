package javase;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/09/29 19:47
 */
public class Main {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("Java").toString();
        System.out.println(str1.intern() == str1);

        System.out.println(Math.round(-11.5) & Math.round(11.5));
    }
}
