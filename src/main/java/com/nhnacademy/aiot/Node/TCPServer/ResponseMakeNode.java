package com.nhnacademy.aiot.Node.TCPServer;

import java.util.Calendar;

import org.json.JSONObject;

public class ResponseMakeNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;
    private Calendar calendar = Calendar.getInstance();

    public ResponseMakeNode(int inputCount, int outputCount) {
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
        for (NodeConnector port : inputConnectors) {
            if (port != null) {
                Message message;
                try {
                    message = port.pop();
                    if (message == null) {
                        continue;
                    }
                    if (message.hasMethod()) {
                        if(message.hasStartTime()){
                            
                        }else{
                            JSONObject jsonObject = new JSONObject();
                            String strDate = String.format("%s-%s-%s %s:%s:%s", String.valueOf(calendar.get(Calendar.YEAR)),String.valueOf(calendar.get(Calendar.MONTH)),String.valueOf(calendar.get(Calendar.DATE)),String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),String.valueOf(calendar.get(Calendar.MINUTE)),String.valueOf(calendar.get(Calendar.SECOND)));
                            jsonObject.put("dateTime", strDate);
                        }
                    } else {
                        log("ERROR : No Method");
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }
            }
        }
    }

}
