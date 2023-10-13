package com.nhnacademy.aiot.Node.TCPServer;

import java.util.logging.Logger;

import org.json.JSONObject;

public class Node {
    private Logger logger;
    private JSONObject json;

    public Node() {
        super();
        logger = Logger.getLogger("Node");
        json = new JSONObject();
    }

    public JSONObject getJson() {
        return json;
    }

    public void log(String message) {
        logger.info(message);
    }
}
