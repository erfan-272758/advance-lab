package com.islam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A class to hold details of audio files.
 *
 * @author David J. Barnes and Michael K�lling
 * @version 2011.07.31
 */
public class MusicCollection
{
    // An ArrayList for storing the file names of music files.
    // A player for the music files.
    private MusicPlayer player =new MusicPlayer();
    private String player_Name;
    private String player_adress;
    private int player_Date;
    /**
     * Create a MusicCollection
     */
    public MusicCollection(String player_Name,String player_adress,int player_Date)
    {

        this.player_adress=player_adress;
        this.player_Name=player_Name;
        this.player_Date=player_Date;
    }
    public static void print_list(Iterator<MusicCollection> list){
        int counter=1;
        while (list.hasNext()) {
            MusicCollection music = list.next();
            System.out.println(counter+") " + music.player_Name
                    + " | " + music.player_adress
                    + " | " + music.player_Date
                    + "\n");
            counter++;

        }
    }

    public static void addFile(ArrayList<MusicCollection> list )
    {

        Scanner scan=new Scanner(System.in);
        String name = scan.nextLine();
        String adress = scan.nextLine();
        int date = scan.nextInt();
        MusicCollection new_Music = new MusicCollection(name,adress,date);
        list.add(new_Music);
    }
    public static void search (ArrayList<MusicCollection> list,String str){
        ArrayList<MusicCollection> new_list = new ArrayList<>();
        boolean state=false;
        for (MusicCollection element : list) {
            if (element.player_Name.contains(str)){
                new_list.add(element);
                state=true;
            }else if (element.player_adress.contains(str)){
                new_list.add(element);
                state=true;
            }
            if (state)
                print_list(new_list.iterator());
        }
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles(ArrayList<MusicCollection> list)
    {
        return list.size();

    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {

    }

    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFiles()
    {

    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public static void removeFile(int index ,ArrayList<MusicCollection> list)
    {

        list.remove(index);
        print_list(list.iterator());
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index , ArrayList<MusicCollection> list)
    {

        player.startPlaying(list.get(index).player_Name);
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {

        player.stop();
    }


    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index , ArrayList<MusicCollection> list)
    {
        // The return value.
        // Set according to whether the index is valid or not.
     return list.size() >= index;
    }
}
