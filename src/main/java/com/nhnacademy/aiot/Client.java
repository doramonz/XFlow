package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.nhnacademy.aiot.Node.SocketInNode;
import com.nhnacademy.aiot.Node.SocketOutNode;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("ems.nhnacademy.com", 1880);) {

            SocketInNode socketInNode = new SocketInNode(socket);
            SocketOutNode socketOutNode = new SocketOutNode(socket);
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer.write("GET /dev HTTP/1.1\n\n");
                    // writer.write("Host: ems.nhnacademy.com:1880\n\n");
                    writer.flush();
            String line="";
            byte[] bytes = new byte[10000];
            int byteRead;
            StringBuilder sb = new StringBuilder();
            boolean data=false;
            while((line=reader.readLine())!=null){
                // System.out.println(line);
                if(line.equals("")){
                    data=true;
                    
                }
                if(data){
                    sb.append(line);
                }
            }
            System.out.println(sb.toString());
            JSONParser parse = new JSONParser();
            Object object = parse.parse(sb.toString());
            org.json.simple.JSONArray json = (org.json.simple.JSONArray)object;
            
            System.out.println(json);
            // System.out.println(socket.getInetAddress());
            // socketOutNode.start();
            // socketInNode.start();
            // socketOutNode.join();
        } catch (IOException e) {

        }catch(ParseException e){
            
        }


    }
}
