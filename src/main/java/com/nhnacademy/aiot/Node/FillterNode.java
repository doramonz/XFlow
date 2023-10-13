package com.nhnacademy.aiot.Node;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
import com.nhnacademy.aiot.message.JsonMessage;
import com.nhnacademy.aiot.message.Message;

public class FillterNode extends InputOutputNode {
    public FillterNode() {
        super(1, 1);
    }

    void process() {

        try {
            for (int i = 0; i < getPortCount(); i++) {
                Port port = getPort(i);
                if (port.hasMessage()) {
                    Message message = getPort(i).get();
                    if (message instanceof JsonMessage) {
                        JSONObject data = ((JsonMessage) message).getPayLoad();

                        String date = data.getString("time");
                        date = date.replaceAll("[a-zA-Z]", " ");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date currentDate= sdf.parse(date);
                        String jsonDate = sdf.format(currentDate);
                        String sensor = data.getString("sensor");
                        int value = data.getInt("value");

                        JSONObject outputData = new JSONObject();
                        outputData.put("dateTime", jsonDate);
                        outputData.put(sensor,value);
                        output(new JsonMessage(outputData));
                    }
                }
            }
        } catch (ParseException e) {

        }
    }
}
