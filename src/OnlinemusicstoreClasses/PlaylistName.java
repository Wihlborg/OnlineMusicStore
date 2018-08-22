package OnlinemusicstoreClasses;

import java.util.HashMap;

public class PlaylistName {
    private String playlistNumber;
    private HashMap<Integer, Song> playlist;

    public PlaylistName(int playlistNumber) {
        this.playlistNumber = "Playlist "+Integer.toString(playlistNumber);

    }

    public String getPlaylistNumber() {
        return playlistNumber;
    }

    public void setPlaylistNumber(String playlistNumber) {
        this.playlistNumber = playlistNumber;
    }

    public HashMap<Integer, Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(HashMap<Integer, Song> playlist) {
        this.playlist = playlist;
    }
}
