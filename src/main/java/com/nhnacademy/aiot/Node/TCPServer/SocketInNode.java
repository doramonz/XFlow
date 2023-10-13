package com.nhnacademy.aiot.Node.TCPServer;

public class SocketInNode extends ActiveNode {
    private static NodeConnector[] inputConnectors;;
    private NodeConnector[] outputConnectors;

    public SocketInNode(int inputCount, int outputCount) {
        super();
        inputConnectors = new NodeConnector[inputCount];
        outputConnectors = new NodeConnector[outputCount];
    }

    public static void connectInput(NodeConnector inputConnector) {
        for (int i = 0; i < inputConnectors.length; i++) {
            if (inputConnectors[i] == null) {
                inputConnectors[i] = inputConnector;
                return;
            }
        }
    }

    public static void disconnectInput(NodeConnector nodeConnector) {
        for (NodeConnector inputConnector : inputConnectors) {
            if (inputConnector == nodeConnector) {
                inputConnector = null;
            }
        }
    }

    public void connectOutput(int index, NodeConnector port) {
        if (index > outputConnectors.length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        if (outputConnectors[index] != null) {
            throw new IllegalArgumentException("Already connected");
        }
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
        for (NodeConnector inpuConnector : inputConnectors) {
            if (inpuConnector != null) {
                try {
                    message = inpuConnector.pop();
                    if (message != null) {
                        for (NodeConnector outputConnector : outputConnectors) {
                            if (outputConnector != null) {
                                outputConnector.push(message);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }

            }
        }
    }

}
