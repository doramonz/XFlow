package com.nhnacademy.aiot.Node;


import java.util.LinkedList;
import java.util.Queue;
import com.nhnacademy.aiot.message.Message;

public class Port {
    Queue<Message> messageQueue;

    public Port(){
        this.messageQueue=new LinkedList<>();
    }

    public void put(Message message){
        messageQueue.add(message);
    }

    public boolean hasMessage(){
        return !messageQueue.isEmpty();
    }
    public Message get() {
        return messageQueue.poll();
    }
    
}
