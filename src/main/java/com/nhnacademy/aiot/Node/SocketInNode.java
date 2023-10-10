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

    public SocketInNode(Socket socket) {
        super(1);
        this.socket = socket;
    }

    @Override
    void process() {
        try (BufferedReader reader =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer=
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));) {
            String line = "";
            while ((line = terminalIn.readLine()) != null) {
                writer.write(line);
                writer.flush();
                output(new StringMessage(line));
            }
        } catch (IOException e) {

        }
    }


}
