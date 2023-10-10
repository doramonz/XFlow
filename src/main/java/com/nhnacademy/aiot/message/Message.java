package com.nhnacademy.aiot.message;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Message {
    static Integer count =0;
    String id;
    String creationTime;

    Message(){
        this.id=getClass().getSimpleName()+count++;
        this.creationTime=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss`++++").format(new Date());
    }

    public String getId(){
        return this.id;
    }
    public String getCreationTime(){
        return this.creationTime;
    }

    public static int getCount(){
        return count;
    }
}
