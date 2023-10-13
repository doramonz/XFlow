package com.nhnacademy.aiot.Node.TCPServer;

public abstract class ActiveNode extends Node implements Runnable {
    private Thread thread;
    private long startTime;
    private long interval = 0;

    public ActiveNode() {
        super();
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void checkNodeConnect(NodeConnector[] nodeConnector, int index, NodeConnector port) throws IndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
        if (index < 0 || index >= nodeConnector.length) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        if (nodeConnector[index] != null) {
            throw new IllegalArgumentException("Already connected");
        }
        if (port == null) {
            throw new NullPointerException("port is null");
        }
    }
    
    public void checkNodedisconnect(NodeConnector[] nodeConnector, int index) throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index >= nodeConnector.length) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        if(nodeConnector[index] == null) {
            throw new NullPointerException("port is null");
        }
    }

    public abstract void preProcess();

    public abstract void postProcess();

    public abstract void process();

    @Override
    public void run() {
        preProcess();
        while (!Thread.interrupted()) {
            startTime = System.currentTimeMillis();
            process();
            long tempTime = System.currentTimeMillis() - startTime;
            if (tempTime < interval) {
                try {
                    Thread.sleep(1000 - tempTime);
                } catch (InterruptedException e) {
                    log(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        postProcess();
    }

}
