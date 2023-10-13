package com.nhnacademy.aiot.Node.TCPServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends ActiveNode {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String ip;
    private String port;
    private String enterTime;
    private NodeConnector outputConnector;

    public Client(Socket socket) {
        super();
        this.socket = socket;
        ip = socket.getInetAddress().toString();
        port = String.valueOf(socket.getPort());
        enterTime = String.valueOf(System.currentTimeMillis());
        outputConnector = new NodeConnector();
        SocketInNode.connectInput(outputConnector);
        try {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (IOException e) {
        }
        start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void send(String message) {
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
        }
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public String getDestination() {
        return ip + ":" + port;
    }

    public NodeConnector getOutputConnector() {
        return outputConnector;
    }

    @Override
    public void preProcess() {

    }

    @Override
    public void postProcess() {

    }

    @Override
    public void process() {
        try {
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            if (length == -1) {
                throw new IOException("Connection closed");
            }
            String message = new String(buffer, 0, length);
            getOutputConnector().push(new Message().setData(message).setIpPort(getDestination()));
        } catch (IOException e) {
            log(e.getMessage());
            if (ClientManager.getInstance().contains(this))
                ClientManager.getInstance().removeClient(this);
            SocketInNode.disconnectInput(outputConnector);
            Thread.currentThread().interrupt();
        }
    }
}
