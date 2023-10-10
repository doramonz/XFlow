package com.nhnacademy.aiot.Node;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Node {
    
    String id;
    @Getter
    static Integer count=0;

    Node() {
        this.id=String.format("%s=%02d", getClass().getSimpleName(),count++);
        log.trace("create node : {}",id);
    }
    public String getId() {
        return this.id;
    }
    public abstract String getName();
    public abstract void setName(String name);

    

}
