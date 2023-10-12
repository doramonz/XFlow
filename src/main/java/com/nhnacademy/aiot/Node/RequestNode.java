package com.nhnacademy.aiot.Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.JSONObject;
import com.nhnacademy.aiot.message.Message;
import com.nhnacademy.aiot.message.StringMessage;

public class RequestNode extends InputOutputNode {
    String[] request;
    Socket socket;

    public RequestNode(Socket socket) {
        super(1, 1);
        this.socket = socket;
    }

    @Override
    void process() {
        boolean accept = false;

        for (int i = 0; i < getPortCount(); i++) {
            Port port = getPort(i);
            if (port.hasMessage()) {
                Message message = port.get();
                request = ((StringMessage) message).getPayLoad().split(" ");
                String method = request[0];
                String path = request[1];
                String version = request[2];

                try {
                    BufferedWriter writer =
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    JSONObject json = new JSONObject();


                    json.put("dateTime", "2023-10-05 15:41:13");



                    System.out.println(json);
                    if (path.contains("temperature")) {
                        json.put("temperature", 39);
                        System.out.println(json);
                    } else {
                        json.put("humidity", 39);
                        System.out.println(json);
                    }
                    writer.write("HTTP/1.1 " + "200 OK" + "\n");
                    writer.write("Access-Control-Allow-Origin: *" + "\n");
                    writer.write("Content-type: text/json; charset=UTF-8" + "\n");
                    writer.write("Content-Length: " + json.toString().length() + "\n" + "\n");
                    writer.flush();
                    writer.write(json.toString() + "\n");
                    writer.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }


}
