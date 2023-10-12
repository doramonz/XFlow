package com.nhnacademy.aiot.Node.Fibo;

public class Fibo {
    public static void main(String[] args) {
        InputNode inputNode = new InputNode(2);
        InputOutputNode inputOutputNode1 = new InputOutputNode(1,1);
        InputOutputNode inputOutputNode2 = new InputOutputNode(2,1);
        InputOutputNode inputOutputNode3 = new InputOutputNode(1,2);
        InputPort inputPort1 = new InputPort();
        InputPort inputPort2 = new InputPort();
        InputPort inputPort3 = new InputPort();
        InputPort inputPort4 = new InputPort();
        inputNode.connect(0, inputPort1);
        inputNode.connect(1, inputPort2);
        inputOutputNode1.getInputPort(0,inputPort1);
        inputOutputNode2.getInputPort(0,inputPort2);
        inputOutputNode3.getInputPort(0,inputPort3);
        inputOutputNode3.getInputPort(1,inputPort4);
        inputOutputNode1.connect(0, inputPort3);
        inputOutputNode2.connect(0, inputPort4);
        inputOutputNode2.connect(1, inputPort1);
        inputOutputNode3.connect(0, inputPort2);
        inputOutputNode3.setFFFFFF();
        inputNode.start();
        inputOutputNode1.start();
        inputOutputNode2.start();
        inputOutputNode3.start();
        inputNode.output(0,new Message(0));
        inputNode.output(1,new Message(1));
    }
}
