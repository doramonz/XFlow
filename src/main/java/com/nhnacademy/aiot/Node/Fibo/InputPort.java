package com.nhnacademy.aiot.Node.Fibo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class InputPort {
    Queue<Message> MessageQuque = new LinkedList<Message>();
    Lock lock;

    public void put(Message data) {
        MessageQuque.add(data);
    }

    public Message get() {
        return MessageQuque.poll();
    }

    public int size() {
        return MessageQuque.size();
    }

    public boolean hasMessage() {
        return !MessageQuque.isEmpty();
    }

    public void remove() {
        MessageQuque.remove();
    }
}
