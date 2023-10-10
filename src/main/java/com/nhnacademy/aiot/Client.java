package com.nhnacademy.aiot;

import java.io.IOException;
import java.net.Socket;
import com.nhnacademy.aiot.Node.SocketInNode;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 1234);) {

            SocketInNode socketInNode = new SocketInNode(socket);
            
                socketInNode.start();
                socketInNode.join();
        } catch (IOException e) {

        }


    }
}
