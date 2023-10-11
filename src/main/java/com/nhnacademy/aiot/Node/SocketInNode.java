package com.nhnacademy.aiot.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import com.nhnacademy.aiot.message.StringMessage;

public class SocketInNode extends InputNode {
    Socket socket;
    BufferedWriter writer;
    BufferedReader terminalIn;
    BufferedReader reader;

    public SocketInNode(Socket socket) throws IOException {
        super(1);
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.terminalIn = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    void process() {
        try {
            String line = "";
            while ((line = reader.readLine()) != null) {
                
                System.out.println(line);
                output(new StringMessage(line));
            }
        } catch (IOException e) {

        }
    }


}
