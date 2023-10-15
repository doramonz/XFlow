package com.nhnacademy.aiot.Node.TCPServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTPRequestNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public HTTPRequestNode(int inputCount, int outputCount) {
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

    private void request(Message message) throws IOException {
        long currentTime = System.currentTimeMillis();
        currentTime /= 1000;
        long startTime = currentTime - 600L;
        currentTime -= 500;
        if (message.hasGetType()) {
            if (message.getGetType().equals("/temperature")) {
                writer.write("GET /ep/temperature/24e124126c457594?count=1&st=" + currentTime + "&et=" + currentTime
                        + " HTTP/1.1\n\n");
                writer.flush();
            } else if (message.getGetType().equals("/humidity")) {
                writer.write("GET /ep/humidity/24e124126c457594?count=1&st=" + currentTime + "&et=" + currentTime
                        + " HTTP/1.1\n\n");
                writer.flush();
            }
        }
    }

    private Message receive(Message message) {
        Message result = new Message();
        String line;
        StringBuilder dataString = new StringBuilder();
        int len = 0;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains("Content-Length:")) {
                    len = Integer.parseInt(line.split(" ")[1]);
                }
                if (line.equals("")) {
                    break;
                }

            }
            if (len != 0) {
                char[] dataChar = new char[len];
                reader.read(dataChar);
                dataString.append(new String(dataChar));
            }
            if (!dataString.toString().isEmpty()) {
                String jsonString = dataString.toString().replaceAll("[\\[\\]]", "");
                result.setData(jsonString);
                result.setIpPort(message.getipPort());
            }
        } catch (IOException e) {
            log(e.getMessage());
        }
        return result;
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
                    socket = new Socket("ems.nhnacademy.com", 1880);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    request(message);
                    Message result = receive(message);
                    socket.close();
                    for (NodeConnector output : outputConnectors) {
                        if (output != null) {
                            output.push(result);
                        }
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                } catch (UnknownHostException e) {
                    log(e.getMessage());
                } catch (IOException e) {
                    log(e.getMessage());
                }

            }
        }
    }

}

// if (path.equals("/humidity")) {
// writer.write("GET /ep/humidity/24e124126c457594?count=1&st=" + currentTime
// + "&et=" + currentTime + " HTTP/1.1\n\n");
// writer.flush();
// sensor="humidity";

// } else if (path.equals("/temperature")) {
// writer.write("GET /ep/temperature/24e124126c457594?count=1&st=" + currentTime
// + "&et=" + currentTime + " HTTP/1.1\n\n");
// writer.flush();
// sensor="temperature";
// }

// String line;
// StringBuilder dataString = new StringBuilder();
// int len = 0;

// while ((line = reader.readLine()) != null) {
// if (line.contains("Content-Length:")) {
// len = Integer.parseInt(line.split(" ")[1]);
// }
// if (line.equals("")) {
// break;
// }

// }
// if (len != 0) {
// char[] dataChar = new char[len];
// reader.read(dataChar);
// dataString.append(new String(dataChar));
// }
// if (!dataString.toString().isEmpty()) {
// String jsonString = dataString.toString().replaceAll("[\\[\\]]", "");
// JSONObject json = new JSONObject(jsonString);
// json.put("sensor",sensor);
// output(new JsonMessage(json));
// }
// interrupt();