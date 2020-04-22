package javase.thread.basis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: codeJerry
 * @description: 读取配置文件
 * @date: 2020/04/05 15:43
 */
public class ClassLoaderTest {


    public static void main(String[] args) throws IOException {
        Properties pros = new Properties();

//        方式一 在目录下读取
        FileInputStream fis;
        fis = new FileInputStream("jdbc.properties");
        pros.load(fis);

//        //方式二 位置读取不到
//        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
//        pros.load(is);



        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user:" + user + "\t password:" + password);
    }
}
