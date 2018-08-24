package OnlinemusicstoreClasses;

import java.util.HashMap;

//this map exists to be able to set create indexes on playListTableView in class UserPlaylists
public class PlaylistName {
    private int playlistNumber;
    private HashMap<Integer, Song> playlist;

    public PlaylistName(int playlistNumber) {
        this.playlistNumber = playlistNumber;

    }

    public int getPlaylistNumber() {
        return playlistNumber;
    }

    public void setPlaylistNumber(int playlistNumber) {
        this.playlistNumber = playlistNumber;
    }

    public HashMap<Integer, Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(HashMap<Integer, Song> playlist) {
        this.playlist = playlist;
    }
}
