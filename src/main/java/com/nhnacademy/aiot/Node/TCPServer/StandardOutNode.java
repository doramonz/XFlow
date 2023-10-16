package com.nhnacademy.aiot.Node.TCPServer;

public class StandardOutNode extends ActiveNode {
    private NodeConnector[] inputConnectors;

    public StandardOutNode(int count) {
        super();
        inputConnectors = new NodeConnector[count];
    }

    public void connectInput(int index, NodeConnector port) {
        inputConnectors[index] = port;
    }

    public void disconnectInput(int index) {
        inputConnectors[index] = null;
    }

    @Override
    public void preProcess() {

    }

    @Override
    public void postProcess() {

    }

    @Override
    public void process() {
        for (NodeConnector port : inputConnectors) {
            if (port != null) {
                Message message;
                try {
                    message = port.pop();
                    if (message == null) {
                        continue;
                    }
                    log((String) (message.getData()));
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }
            }
        }
    }

}
