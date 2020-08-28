package javase.bio;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/08/11 13:02
 */

public class HelloServer {

    public  void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);){
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                try ( ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
                    Message message = (Message) objectInputStream.readObject();
                    System.out.println("server receive message:" + message.getContent());
                    message.setContent("new content");
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloServer helloServer = new HelloServer();
        helloServer.start(6666);
    }
}
