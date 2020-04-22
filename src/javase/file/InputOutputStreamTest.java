package javase.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: codeJerry
 * @description: 字节流处理
 * @date: 2020/04/10 13:41
 */
public class InputOutputStreamTest {

    @Test
    public void test1() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File fInput = new File("eyes.jpg");
            File fOutput = new File("eye1.jpg");

            inputStream = new FileInputStream(fInput);
            outputStream = new FileOutputStream(fOutput);

            byte[] bytes = new byte[5];
            int len;
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
