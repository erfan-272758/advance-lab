package com.islam;

import java.io.*;
import java.lang.reflect.Executable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2727);
            ExecutorService execute = Executors.newCachedThreadPool();
            for (int i = 0; i < 3; i++) {
                Socket client = server.accept();
                System.out.println("client accepted!");
                execute.execute(new doAct("client" + i,client));
            }
            execute.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class doAct implements Runnable {
        String name;
        Socket client;

        doAct(String name, Socket client) {
            this.name = name;
            this.client = client;
        }

        @Override
        public void run() {
            int index = 0;
            while (index != 3) {
                System.out.println("connected");
                try {
                    InputStream in = client.getInputStream();
                    OutputStream out = client.getOutputStream();
                    StringBuilder builder = new StringBuilder();
                    byte[] b = new byte[1024];
                    for (int i = 0; i < 3; i++) {
                        in.read(b);
                        out.write("continue".getBytes());
                        builder.append(new String(b)).append(" ");
                    }
                    System.out.println(builder.toString());
                    out.write("over".getBytes());
                    in.read(b);
                    out.write(builder.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index ++;
            }
        }
    }
}
