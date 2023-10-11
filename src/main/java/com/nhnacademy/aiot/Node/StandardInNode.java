package com.nhnacademy.aiot.Node;


import java.util.Scanner;
import com.nhnacademy.aiot.message.StringMessage;

public class StandardInNode extends InputNode{
    Scanner sc;

    public StandardInNode(){
        this(1);
    }

    public StandardInNode(int count){
        super(count);
        
    }

    @Override
    void preprocess(){
        sc = new Scanner(System.in);
        setInterval(1);
    }

    @Override
    void process() {
        System.out.print("Input >>>> ");
        String words = sc.nextLine();
        StringMessage message = new StringMessage(words);
        
        output(message);
    }

    @Override
    void postprocess(){
        sc=null;
    }
}
