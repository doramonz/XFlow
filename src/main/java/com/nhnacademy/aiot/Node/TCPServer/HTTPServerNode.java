package com.nhnacademy.aiot.Node.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;

public class HTTPServerNode extends ActiveNode {
    public HTTPServerNode(int port) {
        super();
        try {
            ClientConnectNode connector = new ClientConnectNode(new ServerSocket(port));
            connector.start();
            connectPort();
        } catch (IOException e) {
            log(e.getMessage());
        }
    }

    void connectPort() {
        StandardInNode standardInNode = new StandardInNode(10);
        StandardOutNode standardOutNode = new StandardOutNode(10);
        SocketInNode socketInNode = new SocketInNode(10,10);
        SocketOutNode socketOutNode = new SocketOutNode(10);
        NodeConnector standardInNodeToSocketOutNode = new NodeConnector();
        NodeConnector socketInNodeToStandardOutNode = new NodeConnector();
        standardInNode.connectOutput(0, standardInNodeToSocketOutNode);
        socketOutNode.connectInput(0, standardInNodeToSocketOutNode);
        socketInNode.connectOutput(0, socketInNodeToStandardOutNode);
        standardOutNode.connectInput(0, socketInNodeToStandardOutNode);
        NodeConnector socketInNodetoSocketOutNode = new NodeConnector();
        socketInNode.connectOutput(1, socketInNodetoSocketOutNode);
        socketOutNode.connectInput(1, socketInNodetoSocketOutNode);
        URLNode urlNode = new URLNode(1, 1);
        NodeConnector socketInNodetourlNode = new NodeConnector();
        NodeConnector urlNodetoSocketOut = new NodeConnector();
        urlNode.connectInput(0, socketInNodetourlNode);
        urlNode.connectOutput(0, urlNodetoSocketOut);
        socketOutNode.connectInput(2, urlNodetoSocketOut);
        socketInNode.connectOutput(2, socketInNodetourlNode);
        urlNode.start();

        standardInNode.start();
        standardOutNode.start();
        socketOutNode.start();
        socketInNode.start();
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

    public static void main(String[] args) {
        HTTPServerNode tcpServer = new HTTPServerNode(8080);
        tcpServer.start();
    }
}
