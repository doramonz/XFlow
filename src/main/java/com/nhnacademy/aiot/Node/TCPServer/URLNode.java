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

    public Message urlParse(Message message) {
        String[] request = message.getData().split(" ");
        String method = request[0];
        String path = request[1];
        String version = request[2];
        String sensor = "";
        message.setMethod(method);
        message.setGetType(path);
        return message;
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
                    message = urlParse(message);
                    for (NodeConnector outputPort : outputConnectors) {
                        if (outputPort != null) {
                            outputPort.push(message);
                        }
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }
            }
        }
    }

}

// JSONObject data = ((JsonMessage) message).getPayLoad();
// String date = data.getString("time");
// date = date.replaceAll("[a-zA-Z]", " ");
// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// Date currentDate= sdf.parse(date);
// String jsonDate = sdf.format(currentDate);
// String sensor = data.getString("sensor");
// int value = data.getInt("value");

// JSONObject outputData = new JSONObject();
// outputData.put("dateTime", jsonDate);
// outputData.put(sensor,value);
// output(new JsonMessage(outputData));