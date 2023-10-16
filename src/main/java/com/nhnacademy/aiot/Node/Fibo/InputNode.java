package com.nhnacademy.aiot.Node.Fibo;

public class InputNode extends ActiveNode {
    private InputPort[] peerPorts;

    public InputNode(int count) {
        super();
        peerPorts = new InputPort[count];
    }

    public void connect(int index, InputPort port) {
        peerPorts[index] = port;
    }

    public void output(int index, Message message) {
        peerPorts[index].put(message);
    }

    @Override
    public void preProcess() {

    }

    @Override
    public void postProcess() {
    }

    @Override
    public void process() {

    }
}
