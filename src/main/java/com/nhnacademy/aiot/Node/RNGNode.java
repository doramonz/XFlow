package com.nhnacademy.aiot.Node;

import com.nhnacademy.aiot.message.LongMessage;
import com.nhnacademy.aiot.message.Message;

public class RNGNode extends InputOutputNode{
    long num;

    public RNGNode(long num){
        super(1,2);
        this.num=num;
    }

    @Override
    void process() {
        boolean accept = true;
        for (int i = 0; i < getPortCount(); i++) {
            accept &= getPort(i).hasMessage();
        }
        if (accept) {
            for (int i = 0; i < getPortCount(); i++) {
                Message message = getPort(i).get();
                if(message instanceof LongMessage){
                    // System.out.print(((LongMessage)message).getPayLoad()+"  ");
                    num=((LongMessage)message).getPayLoad();
                }
            }
            output(new LongMessage(num));
            
        }
    }
    
}
