package sample;

public class Song {
    private String artistName;
    private String songName;
    private int[] length = new int[2];
    private String albumName;

    public Song(String artistName,  String albumName, String songName, int[] length) {
        this.artistName = artistName;
        this.songName = songName;
        this.length = length;
        this.albumName = albumName;
    }

}