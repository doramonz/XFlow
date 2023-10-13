package com.nhnacademy.aiot.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import org.json.JSONObject;
import com.nhnacademy.aiot.message.JsonMessage;
import com.nhnacademy.aiot.message.Message;
import com.nhnacademy.aiot.message.StringMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataServerNode extends InputOutputNode {
    static String newLine = "\n";
    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;

    public DataServerNode(Socket socket) throws IOException {
        super(1, 1);
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    void process() {
        try {
            for (int i = 0; i < getPortCount(); i++) {
                Port port = getPort(i);
                if (port.hasMessage()) {
                    Message message = getPort(i).get();
                    String[] request = ((StringMessage) message).getPayLoad().split(" ");
                    String method = request[0];
                    String path = request[1];
                    String version = request[2];
                    String sensor="";
                    // http://ems.nhnacademy.com:1880/ep/temperature/24e124126c457594?count=40&st=1696772438&et=1696772438
                    // GET /ep/temperature/24e124126c457594?count=40&st=1696772438&et=1696772438
                    // HTTP/1.1
                    long currentTime = System.currentTimeMillis();
                    currentTime /= 1000;
                    long startTime = currentTime - 600L;
                    currentTime -= 500;
                    Date date = new Date(currentTime*1000L);
                    if (path.equals("/humidity")) {
                        writer.write("GET /ep/humidity/24e124126c457594?count=1&st=" + currentTime
                                + "&et=" + currentTime + " HTTP/1.1\n\n");
                        writer.flush();
                        sensor="humidity";

                    } else if (path.equals("/temperature")) {
                        writer.write("GET /ep/temperature/24e124126c457594?count=1&st=" + currentTime
                                + "&et=" + currentTime + " HTTP/1.1\n\n");
                        writer.flush();
                        sensor="temperature";
                    }

                    String line;
                    StringBuilder dataString = new StringBuilder();
                    int len = 0;

                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Content-Length:")) {
                            len = Integer.parseInt(line.split(" ")[1]);
                        }
                        if (line.equals("")) {
                            break;
                        }

                    }
                    if (len != 0) {
                        char[] dataChar = new char[len];
                        reader.read(dataChar);
                        dataString.append(new String(dataChar));
                    }
                    if (!dataString.toString().isEmpty()) {
                        String jsonString = dataString.toString().replaceAll("[\\[\\]]", "");
                        JSONObject json = new JSONObject(jsonString);
                        json.put("sensor",sensor);
                        output(new JsonMessage(json));
                    }
                    interrupt();
                }

            }
        } catch (Exception e) {

        }

    }
}
