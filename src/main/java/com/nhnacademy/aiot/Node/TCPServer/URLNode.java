package com.nhnacademy.aiot.Node.TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class URLNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;

    public URLNode(int inputCount, int outputCount) {
        super();
        inputConnectors = new NodeConnector[inputCount];
        outputConnectors = new NodeConnector[outputCount];
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

    public String[] urlParse(String url) {
        if (url.contains("?")) {
            String[] result = new String[5];
            result[0] = url.split(" ")[0];
            System.out.println(url.split("//")[1].split("/")[1]);
            result[1] = url.split("//")[1].split("/")[1].split("\\?format")[0];
            result[2] = url.split("startDt=")[1].split("&")[0];
            result[3] = url.split("endDt=")[1].split("&")[0];
            result[4] = url.split("unit=")[1];
            return result;
        } else if (url.contains("/")) {
            String[] result = new String[2];
            result[0] = url.split(" ")[0];
            result[1] = url.split("//")[1].split("/")[1];
            return result;
        }
        return null;
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
                    BufferedReader reader = new BufferedReader(new StringReader((String) message.getData()));
                    String[] url = urlParse(reader.readLine());
                    if (url == null) {
                        continue;
                    }
                    if (url.length == 2) {
                        message.setMethod(url[0]);
                        message.setGetType(url[1]);
                    } else if (url.length == 5) {
                        message.setMethod(url[0]);
                        message.setGetType(url[1]);
                        message.setStartTime(url[2]);
                        message.setEndTime(url[3]);
                        message.setTimeType(url[4]);
                    } else {
                        continue;
                    }
                    for (NodeConnector outputPort : outputConnectors) {
                        if (outputPort != null) {
                            outputPort.push(message);
                        }
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                } catch (IOException e) {
                    log(e.getMessage());
                }
            }
        }
    }

}
