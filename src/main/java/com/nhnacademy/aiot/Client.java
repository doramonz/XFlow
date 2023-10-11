package com.nhnacademy.aiot;

import java.io.IOException;
import java.net.Socket;
import com.nhnacademy.aiot.Node.SocketInNode;
import com.nhnacademy.aiot.Node.SocketOutNode;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 8080);) {

            SocketInNode socketInNode = new SocketInNode(socket);
            SocketOutNode socketOutNode = new SocketOutNode(socket);
            socketOutNode.start();
            socketInNode.start();
            socketOutNode.join();
        } catch (IOException e) {

        }


    }
}
