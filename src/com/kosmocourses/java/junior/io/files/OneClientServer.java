package com.kosmocourses.java.junior.io.files;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class OneClientServer {
    public static void main(String[] args) {
        try (
                ServerSocket server = new ServerSocket(10000)
        ) {
            System.out.println("Server was running");

            try {

                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                String msg;
                while (!client.isClosed()) {
                    msg = in.readLine();
                    if (msg.equalsIgnoreCase("EXIT")) break;
                    System.out.println("Server got message: " + msg);
                    out.write("Hi! This is Java Server. I'am got your message: " + msg + System.lineSeparator());
                    //out.newLine();
                    out.flush();
                }
            } finally {
                System.out.println("Client close connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server was stopped");
        }
    }
}

