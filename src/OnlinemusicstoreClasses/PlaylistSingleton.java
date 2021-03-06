package OnlinemusicstoreClasses;


import javafx.collections.ObservableList;


import java.util.HashMap;

//This class exists just to send a playlistobject from one scene to another

public class PlaylistSingleton {
    //private ObservableList<Song> songs = FXCollections.observableArrayList();
    private  HashMap<Integer,ObservableList<Song>> playlist = new HashMap<>();
    private static int playlistNum = 0;

    private static PlaylistSingleton ourInstance = new PlaylistSingleton();

    public static PlaylistSingleton getInstance() {
        return ourInstance;
    }

    private PlaylistSingleton() {
    }

    public HashMap<Integer,ObservableList<Song>> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ObservableList<Song> songs) {
         playlist.put(playlistNum, songs);
         playlistNum++;
        }


    }

