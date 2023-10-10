package com.nhnacademy.aiot.Node;


import com.nhnacademy.aiot.exception.InvalidArgumentException;
import com.nhnacademy.aiot.exception.OutOfBoundsException;

public class OutputNode extends ActiveNode{
    protected Port [] ports;

    protected OutputNode(int count) throws InvalidArgumentException {
        super();
        if(count <= 0){
            throw new InvalidArgumentException();
        }
        this.ports=new Port[count];
        for(int i=0;i<count;i++){
            ports[i]=new Port();
        }
    }

    public int getPortCount() {
        return this.ports.length;
    }

    public Port getPort(int index) {
        if(index <0 || ports.length<= index){
            throw new OutOfBoundsException();
        }
        return this.ports[index];
    }
    
}
