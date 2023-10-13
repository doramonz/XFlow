package com.nhnacademy.aiot.Node.TCPServer;

import java.util.Calendar;

public class Haha {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        String strDate = String.format("%s-%s-%s %s:%s:%s", String.valueOf(calendar.get(Calendar.YEAR)),
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), String.valueOf(calendar.get(Calendar.DATE)),
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), String.valueOf(calendar.get(Calendar.MINUTE)),
                String.valueOf(calendar.get(Calendar.SECOND)));
        System.out.println(strDate);
    }
}
