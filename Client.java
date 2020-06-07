package com.islam;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Client {

    public static void main(String[] args) {
        while (true){
            try {
                Socket client = new Socket("127.0.0.1",2727);
                Scanner scan = new Scanner(System.in);
                String line = scan.nextLine();
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
                DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                System.out.println(line);
                while (!readData(in).equals("over")){
                    System.out.println(line);
                    out.write(line.getBytes());
                }
                System.out.println(line + "out");
                System.out.println(readData(in));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static String readData(DataInputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        byte[] b = new byte[1024];
        while (in.read(b) > 0){
            builder.append(new String(b));
        }
        return builder.toString();
    }
}
