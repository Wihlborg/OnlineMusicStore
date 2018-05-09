package OnlinemusicstoreClasses;

import java.util.Arrays;

public class Song {
    private String artistName;
    private String songName;
    private int[] length = new int[2];
    private String albumName;
    private double songPrice;

    public Song(String artistName, String songName,  String albumName,int[] length, double songPrice) {
        this.artistName = artistName;
        this.songName = songName;
        this.length = length;
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

    public int[] getLength() {
        return length;
    }

    public String getAlbumName() {
        return albumName;
    }
    @Override
    public String toString(){
        return "Song{" +
                "artistName='" + artistName + '\'' +
                ", songName='" + songName + '\'' +
                ", length=" + Arrays.toString(length) +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
