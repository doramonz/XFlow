package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import com.nhnacademy.aiot.Node.SocketInNode;
import com.nhnacademy.aiot.Node.SocketOutNode;
import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("ems.nhnacademy.com", 1880);) {

            SocketInNode socketInNode = new SocketInNode(socket);
            SocketOutNode socketOutNode = new SocketOutNode(socket);
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer.write("GET /ep/temperature/24e124126c457594?count=40&st=1696772438&et=1696772438 HTTP/1.1\n\n");
                    // writer.write("Host: ems.nhnacademy.com:1880\n\n");
                    writer.flush();
            String line="";
            byte[] bytes = new byte[10000];
            int byteRead;
            StringBuilder sb = new StringBuilder();
            boolean data=false;
            int len=0;
            while((line=reader.readLine())!=null){
                if(line.contains("Content-Length:")){
                    len=Integer.parseInt(line.split(" ")[1]);
                }
                if(line.equals("")){
                    break;
                }
            }
            char[] datas = new char[len];
            reader.read(datas);
            System.out.println(new String(datas));
            
            // System.out.println(sb.toString());
            // JSONParser parse = new JSONParser();
            // Object object = parse.parse(sb.toString());
            // org.json.simple.JSONArray json = (org.json.simple.JSONArray)object;
            
            // System.out.println();
            // System.out.println(socket.getInetAddress());
            // socketOutNode.start();
            // socketInNode.start();
            // socketOutNode.join();
        } catch (IOException e) {

        }


    }
}
