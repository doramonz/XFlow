package com.nhnacademy.aiot.Node.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectNode extends ActiveNode {
    ServerSocket serverSocket;

    public ClientConnectNode(ServerSocket serverSocket) {
        super();
        this.serverSocket = serverSocket;
    }

    @Override
    public void preProcess() {
    }

    @Override
    public void postProcess() {
    }

    @Override
    public void process() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                ClientManager.getInstance().addClient(new Client(socket));
                log("Client connected"+socket.getInetAddress().toString()+":"+socket.getPort());
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

}
