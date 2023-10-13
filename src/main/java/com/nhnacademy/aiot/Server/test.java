package com.nhnacademy.aiot.Server;

import java.text.ParseException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        // String data = "2023-10-13T05:47:31.354Z";
        // data=data.replaceAll("[a-zA-Z]", " ");
        // System.out.println(data);
        // SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // Date datas = sdf.parse(data);
        // String dattt = sdf.format(datas);
        // System.out.println(dattt);
        // System.out.println(datas.getTime()/1000);

        // Date newd= new Date(datas.getTime());
        // String form = sdf.format(newd);
        // System.out.println(newd);
        // System.out.println(form);

        long cur = System.currentTimeMillis();
        cur/=1000;
        long start =cur-500;
        System.out.println(cur);
        Date date = new Date(start*1000L);
        System.out.println(date);
        // System.out.println(System.currentTimeMillis());

        // Date date = new Date(1696772538*1000L);
        // System.out.println(date);
    }

}
