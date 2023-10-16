package com.nhnacademy.aiot.Node.TCPServer;

public class FilterNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;
    private String key;
    private String value;

    public FilterNode(int inputCount, int outputCount) {
        super();
        inputConnectors = new NodeConnector[inputCount];
        outputConnectors = new NodeConnector[outputCount];
    }

    public void setFilter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void connectInput(int index, NodeConnector port) {
        try {
            checkNodeConnect(inputConnectors, index, port);
            inputConnectors[index] = port;
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    public void disconnectInput(int index) {
        try {
            checkNodedisconnect(inputConnectors, index);
            inputConnectors[index] = null;
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    public void connectOutput(int index, NodeConnector port) {
        try {
            checkNodeConnect(outputConnectors, index, port);
            outputConnectors[index] = port;
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    public void disconnectOutput(int index) {
        try {
            checkNodedisconnect(outputConnectors, index);
            outputConnectors[index] = null;
        } catch (Exception e) {
            log(e.getMessage());
        }
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
                    if (message.json.has(key)) {
                        if (message.json.get(key).equals(message.json.get(value))) {
                            for (NodeConnector outputPort : outputConnectors) {
                                if (outputPort != null) {
                                    outputPort.push(message);
                                }
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
