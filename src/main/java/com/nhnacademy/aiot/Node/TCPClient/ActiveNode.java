package com.nhnacademy.aiot.Node.TCPClient;

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
