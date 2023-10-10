package com.nhnacademy.aiot.Node;


import com.nhnacademy.aiot.message.LongMessage;
import com.nhnacademy.aiot.message.Message;
import lombok.Getter;

@Getter
public class AdditionNode extends InputOutputNode {
    long sum;
    public AdditionNode() {
        this(2, 1);
    }

    public AdditionNode(int inputCount, int outputCount) {
        super(inputCount, outputCount);
    }


    @Override
    void process() {
        boolean accept = true;
        for (int i = 0; i < getPortCount(); i++) {
            accept &= getPort(i).hasMessage();
        }
        if (accept) {
            sum=0;
            for (int i = 0; i < getPortCount(); i++) {
                Message message = getPort(i).get();
                if(message instanceof LongMessage){
                    // System.out.print(((LongMessage)message).getPayLoad()+"  ");
                    sum+=((LongMessage)message).getPayLoad();
                }
            }
            output(new LongMessage(sum));
            
        }
    }

}
