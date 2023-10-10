package com.nhnacademy.aiot.Node;


import java.util.Scanner;
import com.nhnacademy.aiot.message.StringMessage;

public class TerminalInNode extends InputNode{
    Scanner sc;

    public TerminalInNode(){
        this(1);
    }

    public TerminalInNode(int count){
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
