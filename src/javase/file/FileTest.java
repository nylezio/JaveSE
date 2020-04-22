package javase.file;

import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/10 10:49
 */
public class FileTest {
    @Test
    public void test1() {
        File file1 = new File("hello.txt");
        File file2 = new File("C:\\Users\\76582\\Documents\\IDEA Project\\JavaStudy\\file\\src\\hello.txt");
        System.out.println(file1);
        System.out.println(file2);
        File file3 = new File("C:\\Users\\76582\\Documents", "IDEA Project");
        System.out.println(file3);
        File file4 = new File(file3, "hi.ext");
        System.out.println(file4);
    }

    @Test
    public void test2() {
        File file = new File("hello.txt");
        File file1 = new File("C:\\Users\\76582\\Documents\\IDEA Project\\JavaStudy\\file\\hello.txt");
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));
    }

    @Test
    public void test3(){
        File file = new File("C:\\Users\\76582\\Documents\\IDEA Project\\JavaStudy");
        String[] list = file.list();
        for (String s: list) {
            System.out.println(s);
        }
    }
}
