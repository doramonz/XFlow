package com.nhnacademy.aiot.Node;


import com.nhnacademy.aiot.exception.AlreadyExistsException;
import com.nhnacademy.aiot.exception.InvalidArgumentException;
import com.nhnacademy.aiot.exception.OutOfBoundsException;
import com.nhnacademy.aiot.message.Message;

public class InputNode extends ActiveNode{
    private final Port [] peerPorts;

    protected InputNode(int count){
        super();
        if(count <= 0){
            throw new InvalidArgumentException();
        }
        this.peerPorts=new Port[count];
    }

    public void connect(int index, Port port){
        if(count<= index){
            throw new OutOfBoundsException();
        }
        if(peerPorts[index]!=null){
            throw new AlreadyExistsException();
        }
        this.peerPorts[index]=port;
    }

    void output(Message message) {
        for(Port port : peerPorts){
            if(port != null){
                port.put(message);
            }
        }
        
    }
    
}
