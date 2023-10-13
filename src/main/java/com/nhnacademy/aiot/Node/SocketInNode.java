package com.nhnacademy.aiot.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import com.nhnacademy.aiot.message.StringMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketInNode extends InputNode {
    Socket socket;
    BufferedWriter writer;
    BufferedReader terminalIn;
    BufferedReader reader;

    public SocketInNode(Socket socket) throws IOException {
        super(2);
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.terminalIn = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    void process() {
        try {
            String line = reader.readLine();
            log.info(line);
            String header = line;
            StringBuilder dataString = new StringBuilder();
            boolean data = false;
            int len = 0;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    break;
                }

            }
            output(new StringMessage(header));
            interrupt();
        } catch (IOException e) {

        }
    }


}
