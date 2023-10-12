package com.nhnacademy.aiot.Node.TCPServer;

import java.util.List;

public class Message {
    enum DataType {
        INT, STRING, CHAR, BOOL, LIST, INT_ARRAY
    }

    DataType dataType;
    int intData;
    String stringData;
    char charData;
    boolean boolData;
    List<Object> listData;
    int[] intArrayData;

    public Message(int data) {
        super();
        this.intData = data;
        dataType = DataType.INT;
    }

    public Message(String data) {
        super();
        this.stringData = data;
        dataType = DataType.STRING;
    }

    public Message(char data) {
        super();
        this.charData = data;
        dataType = DataType.CHAR;
    }

    public Message(boolean data) {
        super();
        this.boolData = data;
        dataType = DataType.BOOL;
    }

    public Message(List<Object> data) {
        super();
        this.listData = data;
        dataType = DataType.LIST;
    }

    public Message(int[] data) {
        super();
        this.intArrayData = data;
        dataType = DataType.INT_ARRAY;
    }

    public Object getData() {
        if (dataType == DataType.INT) {
            return intData;
        } else if (dataType == DataType.STRING) {
            return stringData;
        } else if (dataType == DataType.LIST) {
            return listData;
        } else if (dataType == DataType.INT_ARRAY) {
            return intArrayData;
        } else if (dataType == DataType.CHAR) {
            return charData;
        } else if (dataType == DataType.BOOL) {
            return boolData;
        } 

        return null;
    }

    public static void main(String[] args) {
        Message msg = new Message(1);
        System.out.println(msg.getData());
        msg = new Message("Hello");
        System.out.println(msg.getData());
    }
}
