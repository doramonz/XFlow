package com.nhnacademy.aiot.message;

import org.json.JSONObject;
import lombok.Getter;

public class JsonMessage extends Message {
    @Getter
    JSONObject payLoad;

    public JsonMessage(JSONObject payLoad){
        this.payLoad=payLoad;
    }

}
