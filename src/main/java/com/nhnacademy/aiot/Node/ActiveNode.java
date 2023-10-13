package com.nhnacademy.aiot.Node;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ActiveNode extends Node implements Runnable {
    Thread thread;
    @Setter
    long interval = 1000;
    String name;

    ActiveNode() {
        super();
        this.thread = new Thread(this, getId());
    }

    ActiveNode(String name) {
        this();
        setName(name);
    }

    public void start() {
        thread.start();
    }

    public synchronized void stop() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public void join() throws InterruptedException {
        thread.join();
    }

    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    public synchronized boolean isAlive() {
        return (thread != null) && thread.isAlive();
    }

    @Override
    public void run() {
        preprocess();

        long startTime = System.currentTimeMillis();
        long previousTime = startTime;

        while (!Thread.currentThread().isInterrupted()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;
            if (elapsedTime < interval) {
                try {
                    process();
                    Thread.sleep(interval - elapsedTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            previousTime =
                    startTime + (System.currentTimeMillis() - startTime) / interval * interval;
        }
        postprocess();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    void preprocess() {}

    synchronized void postprocess() {
        thread=null;
    }

    void terminated() {}

    void process() {

    }
}
