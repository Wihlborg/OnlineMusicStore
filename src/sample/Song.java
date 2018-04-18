package sample;

public class Song {
    private String artistName;
    private String songName;
    private int[] length = new int[2];
    private String genre;

    public Song(String artistName, String songName, int[] length, String genre) {
        this.artistName = artistName;
        this.songName = songName;
        this.length = length;
        this.genre = genre;
    }

}
