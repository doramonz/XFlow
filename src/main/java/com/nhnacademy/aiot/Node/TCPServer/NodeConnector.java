package com.nhnacademy.aiot.Node.TCPServer;

public class NodeConnector {
    MyMessageQueue MessageQuque = new MyMessageQueue();

    public void push(Message data) {
        MessageQuque.push(data);
    }

    public synchronized Message pop() throws InterruptedException {
        return MessageQuque.pop();
    }
}
