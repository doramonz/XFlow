package com.nhnacademy.aiot.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.JSONObject;
import com.nhnacademy.aiot.message.JsonMessage;
import com.nhnacademy.aiot.message.Message;

public class SocketOutNode extends InputOutputNode {
    static String newLine = "\n";
    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;

    public SocketOutNode(Socket socket) throws IOException {
        super(1, 1);
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    void process() {
        boolean accept = false;
        while (!accept) {
            for (int i = 0; i < getPortCount(); i++) {
                Port port = getPort(i);
                if (port.hasMessage()) {
                    accept = true;
                }
            }
        }
        for (int i = 0; i < getPortCount(); i++) {
            accept &= getPort(i).hasMessage();
        }
        try {
            if (accept) {
                for (int i = 0; i < getPortCount(); i++) {
                    Message message = getPort(i).get();
                    if (message instanceof JsonMessage) {
                        JSONObject data = ((JsonMessage) message).getPayLoad();
                        writer.write("HTTP/1.1 " + "200 OK" + "\n");
                        writer.write("Access-Control-Allow-Origin: *" + "\n");
                        writer.write("Content-type: text/json; charset=UTF-8" + "\n");
                        writer.write("Content-Length: " + data.toString().length() + "\n" + "\n");
                        writer.flush();
                        writer.write(data.toString() + "\n");
                        writer.flush();
                    }
                    // JSONObject object = new JSONObject();
                    // Long payLoad = ((LongMessage) message).getPayLoad();
                    // object.put("server", payLoad);

                    // writer.write("HTTP/1.1 " + "200 OK" + newLine);
                    // writer.write("Server: Hyeonseon9's" + newLine);
                    // writer.write("Date: " + new Date() + newLine);
                    // writer.write("Content-type: text/html; charset=UTF-8" + newLine);
                    // writer.write(
                    // "Content-Length: " + object.toString().length() + newLine + newLine);
                    // writer.write(object + newLine);
                    // writer.flush();

                    // output(message);
                }
            }
        } catch (Exception e) {

        }
        // catch (IOException e) {

        // }


        // try {
        // String line = "";
        // while ((line = reader.readLine()) != null) {
        // writer.write(line+"\n");
        // writer.flush();
        // }
        // } catch (IOException e) {

        // }
    }

}
