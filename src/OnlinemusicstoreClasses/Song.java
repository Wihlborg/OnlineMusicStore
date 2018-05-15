package OnlinemusicstoreClasses;

import java.util.Arrays;

public class Song {
    private String artistName;
    private String songName;
    private int songId;
    private String albumName;
    private double songPrice;

    public Song(String artistName, String songName,  String albumName, int songId, double songPrice) {
        this.artistName = artistName;
        this.songName = songName;
        this.songId = songId;
        this.albumName = albumName;
        this.songPrice = songPrice;
    }

    public double getSongPrice() {
        return songPrice;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongName() {
        return songName;
    }

    public int getSongId(){return songId;}


    public String getAlbumName() {
        return albumName;
    }

    @Override
    public String toString() {
        return "Song{" +
                "artistName='" + artistName + '\'' +
                ", songName='" + songName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", songPrice=" + songPrice +
                '}';
    }
}
