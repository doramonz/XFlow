package com.nhnacademy.aiot.Node.Fibo;

import java.util.logging.LogManager;

public class Node {
    LogManager logger = LogManager.getLogManager();

    public Node() {
        super();
    }

    public void log(String message) {
        logger.getLogger(message);
    }
}
