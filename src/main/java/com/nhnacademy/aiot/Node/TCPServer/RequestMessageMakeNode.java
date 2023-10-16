package com.nhnacademy.aiot.Node.TCPServer;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class RequestMessageMakeNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;

    public RequestMessageMakeNode(int inputCount, int outputCount) {
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

    @Override
    public void preProcess() {

    }

    @Override
    public void postProcess() {

    }

    @Override
    public void process() {
        for (NodeConnector nodeConnector : inputConnectors) {
            if (nodeConnector != null) {
                Message message;
                try {
                    message = nodeConnector.pop();
                    if (message != null) {
                        JSONObject data = new JSONObject(message.getData());
                        String date = data.getString("time");
                        date = date.replaceAll("[a-zA-Z]", " ");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String jsonDate = sdf.format(sdf.parse(date));
                        String sensor = data.getString("sensor");
                        Double value = data.getDouble("value");
                        JSONObject outputData = new JSONObject();
                        outputData.put("dateTime", jsonDate);
                        outputData.put(sensor, value);
                        for (NodeConnector outputPort : outputConnectors) {
                            if (outputPort != null) {
                                outputPort.push(
                                        new Message().setData(outputData.toString()).setIpPort(message.getipPort()));
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                } catch (java.text.ParseException e) {
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