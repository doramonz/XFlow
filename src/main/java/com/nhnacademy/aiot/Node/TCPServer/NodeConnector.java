package com.nhnacademy.aiot.Node.TCPServer;

import java.util.LinkedList;
import java.util.Queue;

public class NodeConnector {
    Queue<Message> MessageQuque = new LinkedList<>();

    public void push(Message data) {
        MessageQuque.add(data);
    }

    public synchronized Message pop() throws InterruptedException {
        return MessageQuque.poll();
    }
}
