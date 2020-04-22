package javase.file;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: codeJerry
 * @description:
 * 字节流
 * InputStream/OutputStream
 * 字符流
 * Reader/Writer
 * @date: 2020/04/10 12:38
 */
public class FileReaderWriterTest {
    @Test
    public void readTest() {
        FileReader fr = null;
        try {
            File file =new File("hello.txt");
            fr = new FileReader(file);

            //返回读入的一个字符
            int data;
            while ((data = fr.read()) != -1){
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     *     对read()的操作升级
     */
    @Test
    public void readTest1(){
        FileReader fr = null;
        try {
            File file =new File("hello.txt");
            fr = new FileReader(file);

            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                String str = new String(cbuf,0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fr != null;
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void writeTest() {
        FileWriter fw = null;
        try {
            File file = new File("hello1.txt");

            /*
             * true追加
             * 默认false覆盖
             */
            fw = new FileWriter(file,true);

            fw.write("I have a dream!\n");
            fw.write("You need to have a dream too!\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    @Test
    public void readerAndWriterTest(){
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File srcFile = new File("hello1.txt");
            File destFile = new File("hello2.txt");

            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                fw.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
