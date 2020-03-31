package com.islam;

import java.util.ArrayList;
import java.util.*;
public class Main {

    static ArrayList<MusicCollection> pop = new ArrayList<>();
    static ArrayList<MusicCollection> jazz = new ArrayList<>();
    static ArrayList<MusicCollection> rock = new ArrayList<>();
    static ArrayList<MusicCollection> country = new ArrayList<>();
    static ArrayList<MusicCollection> list;
    public static void main(String[] args) {
        String state;
        int index;
        do {
            System.out.println("what kind of clloection do you want:\n" +
                                "1)jazz\n2)pop\n" +
                                "3)rock\n4)country\n5)exit");
            index = new Scanner(System.in).nextInt();
            switch (index){
                case 1:
                    list= jazz;
                    break;
                case 2:
                    list= pop;
                    break;
                case 3:
                    list= rock;
                    break;
                case 4:
                    list= country;
                    break;
                default:break;
            }
            do {
                System.out.println("for add type:add\nfor remove type:remove\n" +
                        "for print the list type:print\nfor search type:search\n" +
                        "for exit type:exit");
                state = new Scanner(System.in).nextLine();
                switch (state){
                    case "add":
                        //System.out.println("add\n");
                        MusicCollection.addFile(list);
                        break;
                    case "remove":
                        //System.out.println("remove\n");
                        int index_new = new Scanner(System.in).nextInt();
                        MusicCollection.removeFile(index_new,list);
                        break;
                    case "print":
                        //System.out.println("print\n");
                        MusicCollection.print_list(list.iterator());
                        break;
                    case "search":
                        String str = new Scanner(System.in).nextLine();
                        MusicCollection.search(list,str);
                        break;
                    default:
                        //System.out.println("exit\n");
                        break;
                }
            }while (!state.equals("exit"));
        }while (index!=4);
    }
}

