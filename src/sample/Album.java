package sample;

import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songs;
    private String albumName;


    public Album(String albumName, ArrayList<Song> songs) {
        this.albumName = albumName;
        this.songs = songs;
    }
}
