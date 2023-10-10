package com.nhnacademy.aiot.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.nhnacademy.aiot.Node.SocketOutNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    public static void main(String[] args) {
        int port = 1234;
        int count=0;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket client;

            while ((client = serverSocket.accept()) != null) {
                log.info("{} | PORT : {}",client.getInetAddress().getHostAddress(),client.getPort());

                SocketOutNode socketOutNode = new SocketOutNode(client);
                socketOutNode.start();
                
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
