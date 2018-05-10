package OnlinemusicstoreClasses;

import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songs;
    private String albumName;


    public Album(String albumName) {
        this.albumName = albumName;
        this.songs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return albumName;
    }
}
