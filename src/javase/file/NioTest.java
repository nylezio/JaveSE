package javase.file;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/06/01 23:43
 */
public class NioTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile rw = new RandomAccessFile("hello1.txt", "rw");
        FileChannel channel = rw.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int read = channel.read(buffer);
        while (read != -1) {
            System.out.println("read" + read);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println(buffer.get());
            }

            buffer.clear();
            read = channel.read(buffer);
        }
        rw.close();


    }

}