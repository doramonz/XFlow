package com.nhnacademy.aiot.Node.TCPServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends ActiveNode {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private String ip;
    private String port;
    private String enterTime;
    private NodeConnector outputConnector = new NodeConnector();

    public Client(Socket socket) {
        super();
        this.socket = socket;
        ip = socket.getInetAddress().toString();
        port = String.valueOf(socket.getPort());
        enterTime = String.valueOf(System.currentTimeMillis());
        // outputConnector = SocketInNode.getInputConnector();
        SocketInNode.connectInput(outputConnector);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
        }
        start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void send(String message) {
        try {
            writer.write(message);
        } catch (IOException e) {
        }
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
        }
    }

    // writer.write("HTTP/1.1 " + "200 OK" + "\n");
    // writer.write("Access-Control-Allow-Origin: *" + "\n");
    // writer.write("Content-type: text/json; charset=UTF-8" + "\n");
    // writer.write("Content-Length: " + data.toString().length() + "\n" + "\n");
    // writer.flush();
    // writer.write(data.toString() + "\n");
    // writer.flush();

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
        // SocketInNode.disconnectInput(outputConnector);
    }

    @Override
    public void process() {
        try {
            String line = reader.readLine();
            log("receive: " + line);
            String header = line;
            StringBuilder dataString = new StringBuilder();
            int len = 0;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    break;
                }
            }
            getOutputConnector().push(new Message().setData(header).setIpPort(getDestination()));
            SocketInNode.disconnectInput(outputConnector);
            stop();
        } catch (IOException e) {
            log("Client disconnected");
            log(e.getMessage());
            SocketInNode.disconnectInput(outputConnector);
            Thread.currentThread().interrupt();
        }
    }
}

// String line = reader.readLine();
// log.info(line);
// String header = line;
// StringBuilder dataString = new StringBuilder();
// boolean data = false;
// int len = 0;
// while ((line = reader.readLine()) != null) {
// if (line.equals("")) {
// break;
// }

// }
// output(new StringMessage(header));