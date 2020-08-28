package javase.bio;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/08/11 13:17
 */
public class HelloClient {

    public Object send(Message message, String host, int port) {
        //1. 创建Socket对象并且指定服务器的地址和端口号
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //2.通过输出流向服务器端发送请求信息
            objectOutputStream.writeObject(message);
            //3.通过输入流获取服务器响应的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("occur exception:");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        HelloClient helloClient = new HelloClient();
        Object contentFromClient = helloClient.send(new Message("content from client"), "127.0.0.1", 6666);
        Message message = null;
        if (contentFromClient != null) {
            message = (Message) contentFromClient;
        }
        System.out.println("client receive message:" + message.getContent());

    }
}
