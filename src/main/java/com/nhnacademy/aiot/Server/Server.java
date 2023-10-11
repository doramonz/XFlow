package com.nhnacademy.aiot.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.nhnacademy.aiot.Node.RNGNode;
import com.nhnacademy.aiot.Node.SocketInNode;
import com.nhnacademy.aiot.Node.SocketOutNode;
import com.nhnacademy.aiot.message.LongMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    static String newLine ="\n";
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        int count = 0;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket client;

            while ((client = serverSocket.accept()) != null) {
                log.info("{} | PORT : {}", client.getInetAddress().getHostAddress(),
                        client.getPort());

                SocketOutNode socketOutNode = new SocketOutNode(client);
                SocketInNode socketInNode = new SocketInNode(client);
                // JSONObject object = new JSONObject();
                // object.put("hello", "world!");
                // BufferedWriter writer =
                //         new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                // writer.write("HTTP/1.1 " + "200 OK" + newLine);
                // writer.write("Server: Hyeonseon9's" + newLine);
                // writer.write("Date: " + new Date() + newLine);
                // writer.write("Content-type: text/html; charset=UTF-8" + newLine);
                // writer.write("Content-Length: " + object.toString().length() + newLine + newLine);
                // writer.write(object + newLine);
                // writer.flush();
                RNGNode rngNode = new RNGNode(3);
                rngNode.getPort(0).put(new LongMessage(count++));
                rngNode.connect(0, socketOutNode.getPort(0));
                rngNode.start();
                socketOutNode.start();
                socketInNode.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
