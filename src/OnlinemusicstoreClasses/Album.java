package OnlinemusicstoreClasses;

import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songs;
    private String albumName;
    private int albumId;

    public Album(String albumName, int albumId) {
        this.albumName = albumName;
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    @Override
    public String toString() {
        return albumName;
    }
}
