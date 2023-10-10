package com.nhnacademy.aiot.message;

import lombok.Getter;

public class StringMessage extends Message{
    @Getter
    String payLoad;

    public StringMessage(String payLoad){
        this.payLoad=payLoad;
    }
    
}
