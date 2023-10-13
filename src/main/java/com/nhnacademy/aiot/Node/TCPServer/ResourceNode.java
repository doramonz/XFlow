package com.nhnacademy.aiot.Node.TCPServer;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;

public class ResourceNode extends ActiveNode {
    private NodeConnector[] inputConnectors;
    private NodeConnector[] outputConnectors;

    public ResourceNode(int inputCount, int outputCount) {
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

    public String[] getCPUProcess() {
        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String cpuUsage = String.format("%.2f", osbean.getSystemLoadAverage() * 100);
        String[] list = new String[2];
        list[0] = cpuUsage;
        list[1] = "100";
        return list;
    }

    public String[] getMemory() {
        MemoryMXBean membean = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = membean.getHeapMemoryUsage();
        MemoryUsage nonheap = membean.getNonHeapMemoryUsage();
        // long heapInit = heap.getInit();
        long heapUsed = heap.getUsed();
        // long heapCommit = heap.getCommitted();
        // long heapMax = heap.getMax();

        long nonheapUsed = nonheap.getUsed();
        String[] list = new String[2];
        // list[0] = String.valueOf(heapInit);
        list[0] = String.valueOf(heapUsed);
        list[1] = String.valueOf(nonheapUsed);
        // list[2] = String.valueOf(heapCommit);
        // list[3] = String.valueOf(heapMax);
        return list;
    }

    public String[] getDiskSpace() {
        File root = null;
        try {
            root = new File("/");
            String[] list = new String[2];
            list[0] = toMB(root.getTotalSpace());
            list[1] = toMB(root.getUsableSpace());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toMB(long size) {
        return String.valueOf((int) (size / (1024 * 1024)));
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
                    for (NodeConnector outputPort : outputConnectors) {
                        Message newMessage = new Message();
                        String data = "";
                        for (String str : getCPUProcess()) {
                            data += str + "/";
                        }
                        data += "\n";
                        for (String str : getMemory()) {
                            data += str + "/";
                        }
                        data += "\n";
                        for (String str : getDiskSpace()) {
                            data += str + "/";
                        }
                        data += "\n";
                        newMessage.setData(data);
                        if (outputPort != null) {
                            outputPort.push(newMessage);
                        }
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }
            }
        }

    }

    public static void main(String[] args) {
        ResourceNode node = new ResourceNode(0, 0);
        Message newMessage = new Message();
        String[] str;
        String data = "";
        data += "CPU Usage : ";
        str = node.getCPUProcess();
        data += str[0] + "/" + str[1] + "\n";
        data += "Memory Usage : ";
        str = node.getMemory();
        data += str[0] + "/" + str[1] + "\n";
        data += "Disk Usage : ";
        str = node.getDiskSpace();
        data += str[0] + "/" + str[1] + "\n";
        newMessage.setData(data);
        System.out.println(newMessage.getData());
    }
}
