package com.nhnacademy.aiot.Node;

import com.nhnacademy.aiot.message.LongMessage;
import com.nhnacademy.aiot.message.Message;
import com.nhnacademy.aiot.message.StringMessage;

public class StandardOutNode extends OutputNode {

    public StandardOutNode() {
        this(1);
    }

    public StandardOutNode(int count) {
        super(count);
    }

    @Override
    void preprocess() {
        setInterval(100);
    }

    @Override
    public void process() {
        for (int i = 0; i < getPortCount(); i++) {
            Port port = getPort(i);
            if (port.hasMessage()) {
                Message message = port.get();
                if (message instanceof StringMessage) {
                    System.out.println("Output >>>> " + ((StringMessage) message).getPayLoad());
                }else if(message instanceof LongMessage){
                    System.out.println("Output >>>> "+((LongMessage)message).getPayLoad());
                }
            }
        }
    }
}
