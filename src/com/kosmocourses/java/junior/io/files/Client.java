package com.kosmocourses.java.junior.io.files;

import java.io.*;
import java.net.Socket;


public class Client {

    public static void main(String[] args) {
        try {
            try (Socket socket = new Socket("localhost", 10000);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                String msg, serverMsg;
                while (!socket.isOutputShutdown()) {

                    System.out.println("Enter you message:");
                    msg = reader.readLine();
                    out.write(msg + System.lineSeparator());
                    out.flush();
                    if (msg.equalsIgnoreCase("EXIT")) break;
                    serverMsg = in.readLine();
                    System.out.println(serverMsg);

                }
            } finally {
                System.out.println("Close connection...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



