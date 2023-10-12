package com.nhnacademy.aiot.Node.TCPServer;

import java.util.Scanner;

public class StandardInNode extends ActiveNode {
    private NodeConnector[] outputConnectors;
    private Scanner scanner;

    public StandardInNode(int count) {
        super();
        outputConnectors = new NodeConnector[count];
        scanner = new Scanner(System.in);
    }

    public void connectOutput(int index, NodeConnector port) {
        outputConnectors[index] = port;
    }

    public void disconnectOutput(int index) {
        outputConnectors[index] = null;
    }

    @Override
    public void preProcess() {
    }

    @Override
    public void postProcess() {
    }

    @Override
    public void process() {
        String message = scanner.nextLine();
        for (NodeConnector port : outputConnectors) {
            if (port != null)
                port.push(new Message(message));
        }
    }
}
