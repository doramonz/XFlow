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
        StandardInNode standardInNode = new StandardInNode(1);
        StandardOutNode standardOutNode = new StandardOutNode(1);
        SocketInNode socketInNode = new SocketInNode(1);
        SocketOutNode socketOutNode = new SocketOutNode(1);
        NodeConnector standardInNodeToSocketOutNode = new NodeConnector();
        NodeConnector socketInNodeToStandardOutNode = new NodeConnector();
        standardInNode.connectOutput(0, standardInNodeToSocketOutNode);
        socketOutNode.connectInput(0, standardInNodeToSocketOutNode);
        socketInNode.connectOutput(0, socketInNodeToStandardOutNode);
        standardOutNode.connectInput(0, socketInNodeToStandardOutNode);

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
