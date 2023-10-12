package com.nhnacademy.aiot.Server;

import java.text.ParseException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        System.out.println(System.currentTimeMillis());

        Date date = new Date(1696772538*1000L);
        System.out.println(date);
    }

}
