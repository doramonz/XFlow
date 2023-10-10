package com.nhnacademy.aiot.Node;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketOutNode extends OutputNode {
    Socket socket;

    public SocketOutNode(Socket socket) {
        super(1);
        this.socket = socket;
    }

    @Override
    void process() {
        try (InputStream in = socket.getInputStream()) {

            String line = "";

            byte[] bytes = new byte[1024];
            int readByteCount = in.read(bytes);

            if (readByteCount > 0) {
                System.out.println(new String(bytes));
            }
        } catch (IOException e) {

        }
    }

}
