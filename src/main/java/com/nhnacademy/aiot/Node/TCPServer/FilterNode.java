package com.nhnacademy.aiot.Node.TCPServer;

public class FilterNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;

    public FilterNode(int inputCount, int outputCount) {
        super();
        inputConnectors = new NodeConnector[inputCount];
        outputConnectors = new NodeConnector[outputCount];
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
