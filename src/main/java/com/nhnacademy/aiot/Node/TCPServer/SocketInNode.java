package com.nhnacademy.aiot.Node.TCPServer;

public class SocketInNode extends ActiveNode {

    private static NodeConnector inputConnectors = new NodeConnector();
    private NodeConnector[] outputConnectors;

    public SocketInNode(int outputCount) {
        super();
        outputConnectors = new NodeConnector[outputCount];
    }

    public static NodeConnector getInputConnector() {
        return inputConnectors;
    }

    public void connectOutput(int index, NodeConnector port) {
        outputConnectors[index] = port;
    }

    @Override
    public void preProcess() {
    }

    @Override
    public void postProcess() {
    }

    @Override
    public void process() {
        Message message;
        try {
            message = inputConnectors.pop();
            if (message == null) {
                return;
            }
            for (NodeConnector port : outputConnectors) {
                if (port != null) {
                    port.push(message);
                }
            }
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }

}
