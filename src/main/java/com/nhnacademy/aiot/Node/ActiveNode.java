package com.nhnacademy.aiot.Node;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ActiveNode extends Node implements Runnable {
    Thread thread;
    @Setter
    long interval = 1000;

    boolean running;
    String name;

    ActiveNode() {
        super();
        this.thread = new Thread(this, getId());
        running = false;
    }

    ActiveNode(String name) {
        this();
        setName(name);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        running = false;
        thread.interrupt();
    }

    public void join() throws InterruptedException {
        thread.join();
    }

    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        preprocess();
        running = true;
        long startTime = System.currentTimeMillis();
        long previousTime = startTime;
        while (!Thread.currentThread().isInterrupted()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;
            if (elapsedTime < interval) {
                try {
                    log.info(thread.getName());
                    process();
                    Thread.sleep(interval - elapsedTime);
                } catch (InterruptedException e) {
                    // System.out.println("?");
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

    void postprocess() {}

    void terminated() {}

    void process() {
        System.out.println(thread.currentThread().getName());
    }
}
