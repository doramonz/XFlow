package com.nhnacademy.aiot.Node.TCPServer;

import org.json.JSONObject;

public class Message {
    JSONObject json;

    public Message() {
        super();
        json = new JSONObject();
    }

    public Message setData(String data) {
        json.put("data", data);
        return this;
    }

    public boolean hasData() {
        return json.has("data");
    }

    public String getData() {
        if (json.has("data")) {
            return json.get("data").toString();
        }
        return null;
    }
    //ip:port
    public Message setIpPort(String ipPort) {
        json.put("ip-port", ipPort);
        return this;
    }

    public boolean hasipPort() {
        return json.has("ip-port");
    }

    public String getipPort() {
        if (json.has("ip-port")) {
            return json.get("ip-port").toString();
        }
        return null;
    }

    public Message setMethod(String method) {
        json.put("method", method);
        return this;
    }

    public Message setGetType(String getType) {
        json.put("get-type", getType);
        return this;
    }

    public Message setStartTime(String startTime) {
        json.put("start-time", startTime);
        return this;
    }

    public Message setEndTime(String endTime) {
        json.put("end-time", endTime);
        return this;
    }

    public Message setTimeType(String timeType) {
        json.put("time-type", timeType);
        return this;
    }

    public boolean hasMethod() {
        return json.has("method");
    }

    public String getMethod() {
        if (json.has("method")) {
            return json.get("method").toString();
        }
        return null;
    }
    
    public boolean hasGetType() {
        return json.has("get-type");
    }

    public String getGetType() {
        if (json.has("get-type")) {
            return json.get("get-type").toString();
        }
        return null;
    }

    public boolean hasStartTime() {
        return json.has("start-time");
    }

    public String getStartTime() {
        if (json.has("start-time")) {
            return json.get("start-time").toString();
        }
        return null;
    }

    public boolean hasEndTime() {
        return json.has("end-time");
    }

    public String getEndTime() {
        if (json.has("end-time")) {
            return json.get("end-time").toString();
        }
        return null;
    }

    public boolean hasTimeType() {
        return json.has("time-type");
    }

    public String getTimeType() {
        if (json.has("time-type")) {
            return json.get("time-type").toString();
        }
        return null;
    }

    public static void main(String[] args) {
        Message message = new Message().setData("haha");
        System.out.println(message.json.toString());
    }
}
