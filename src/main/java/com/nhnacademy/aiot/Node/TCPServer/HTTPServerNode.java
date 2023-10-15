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
        StandardInNode standardInNode = new StandardInNode(100);
        StandardOutNode standardOutNode = new StandardOutNode(100);
        SocketInNode socketInNode = new SocketInNode(100,100);
        SocketOutNode socketOutNode = new SocketOutNode(100);
        URLNode urlNode = new URLNode(100,100);
        HTTPRequestNode httpRequestNode = new HTTPRequestNode(100,100);
        NodeConnector standardInNodeToSocketOutNode = new NodeConnector();
        NodeConnector socketInNodeToStandardOutNode = new NodeConnector();
        NodeConnector socketInNodeToURLNode = new NodeConnector();
        NodeConnector urlNodeToHTTPRequestNode = new NodeConnector();
        NodeConnector httpRequestNodeToSocketOutNode = new NodeConnector();
        standardInNode.connectOutput(0, standardInNodeToSocketOutNode);
        socketInNode.connectOutput(0, socketInNodeToStandardOutNode);
        socketInNode.connectOutput(1, socketInNodeToURLNode);
        urlNode.connectOutput(0, urlNodeToHTTPRequestNode);
        httpRequestNode.connectOutput(0, httpRequestNodeToSocketOutNode);
        socketOutNode.connectInput(0, standardInNodeToSocketOutNode);
        standardOutNode.connectInput(0, socketInNodeToStandardOutNode);
        socketOutNode.connectInput(1, httpRequestNodeToSocketOutNode);
        urlNode.connectInput(0, socketInNodeToURLNode);
        httpRequestNode.connectInput(0, urlNodeToHTTPRequestNode);

        standardInNode.start();
        standardOutNode.start();
        socketInNode.start();
        socketOutNode.start();
        urlNode.start();
        httpRequestNode.start();
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
