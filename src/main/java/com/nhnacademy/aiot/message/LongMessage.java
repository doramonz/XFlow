package com.nhnacademy.aiot.message;

import lombok.Getter;

public class LongMessage extends Message{
    @Getter
    long payLoad;

    public LongMessage(long payLoad){
        this.payLoad=payLoad;
    }
    
}
