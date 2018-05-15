package OnlinemusicstoreClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShoppingCart  {


    private static ShoppingCart instance = null;
    private ObservableList<OnlinemusicstoreClasses.Song> songLinkedList;
    private ObservableList<Album>albumLinkedList;
    private ObservableList<OnlinemusicstoreClasses.Song> boughtSong;




    private ShoppingCart() {
        songLinkedList =  FXCollections.observableArrayList();
        albumLinkedList = FXCollections.observableArrayList();
        boughtSong=FXCollections.observableArrayList();

    }


    public static ShoppingCart getInstance() {
        if (instance == null){
            instance = new ShoppingCart();

        }
        return instance;
    }

    public void addSong(Song song){
    songLinkedList.add(song);
}

    public ObservableList<Song> getSongLinkedList() {
        return songLinkedList;
    }

    public void addBoughtSong(Song song){
        boughtSong.add(song);
    }

    public ObservableList<Song> getBoughtSong() {
        return boughtSong;
    }

    public void clear(){
        this.songLinkedList.clear();
        this.albumLinkedList.clear();
    }
}









