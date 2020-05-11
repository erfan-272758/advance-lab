package com.islam;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtils {

    private static final String NOTES_PATH = "./notes/";
    private static final String BIN_NOTES_PATH = "./bin_notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
//        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        boolean isSuccessfulB = new File(BIN_NOTES_PATH).mkdirs();
//        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
        System.out.println("Creating " + BIN_NOTES_PATH + " directory is successful: " + isSuccessfulB);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }

    public static File[] getBinsInDirectory() {
        return new File(BIN_NOTES_PATH).listFiles();
    }
    public static Note binReader(File file){
        try(FileInputStream in = new FileInputStream(file)){
            ObjectInputStream oi = new ObjectInputStream(in);
            return (Note) oi.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void binWriter(Note note){
        try(FileOutputStream out = new FileOutputStream(BIN_NOTES_PATH + getProperFileName(note.getContent())+".bin")){
            ObjectOutputStream oi = new ObjectOutputStream(out);
            oi.writeObject(note);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String fileReader(File file) {
        //TODO: Phase1: read content from file
        StringBuilder sb = new StringBuilder("");
        try {
            FileInputStream fi = new FileInputStream(file);
            byte[] b = new byte[1024];

            while (fi.read(b) != -1){
                sb.append(new String(b));
            }
            fi.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line = "";
//            while ((line = br.readLine()) != null){
//                sb.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            System.out.println("The file not found");
//            e.printStackTrace();
//        }

        return sb.toString();
    }

    public static void fileWriter(String content) {
        //TODO: write content on file

        String fileName = getProperFileName(content);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./notes/"+fileName+".txt"));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            FileOutputStream fo = new FileOutputStream("./notes/"+fileName+".txt");
//            fo.write(content.getBytes());
//            fo.close();
//        } catch (IOException e) {
//            System.out.println("The file  could'nt write");
//            e.printStackTrace();
//        }


    }


    public static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }

        return System.currentTimeMillis() + "_new file.txt";
    }

    public static Note changeFileToNote(File file){
        try(FileInputStream fs = new FileInputStream(file)){
            ObjectInputStream os = new ObjectInputStream(fs);
            return (Note) os.readObject();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
