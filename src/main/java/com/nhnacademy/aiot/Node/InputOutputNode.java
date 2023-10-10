package com.nhnacademy.aiot.Node;


import com.nhnacademy.aiot.exception.InvalidArgumentException;
import com.nhnacademy.aiot.exception.OutOfBoundsException;
import com.nhnacademy.aiot.message.Message;

public class InputOutputNode extends ActiveNode {
    private final Port[] ports;
    private final Port[] peerPorts;



    protected InputOutputNode(int inputCount, int outputCount) {
        super();
        if (inputCount <= 0 || outputCount <= 0) {
            throw new InvalidArgumentException();
        }
        this.ports = new Port[inputCount];
        for(int i=0;i<ports.length;i++){
            ports[i]=new Port();
        }
        this.peerPorts = new Port[outputCount];
    }

    public void connect(int index, Port port) {
        if (index < 0 || peerPorts.length <= index) {
            throw new OutOfBoundsException();
        }
        this.peerPorts[index] = port;
    }

    public int getPortCount() {
        return this.ports.length;

    }

    public Port getPort(int index) {
        if (index < 0 || ports.length <= index) {
            throw new OutOfBoundsException();
        }
        return this.ports[index];

    }

    void output(Message message) {
        for (Port port : peerPorts) {
            if (port != null) {
                port.put(message);
            }
        }
    }

}
