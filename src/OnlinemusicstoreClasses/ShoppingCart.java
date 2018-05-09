package OnlinemusicstoreClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShoppingCart  {


    private static ShoppingCart instance = null;
    private ObservableList<Song> songLinkedList;
    private ObservableList<Album>albumLinkedList;




    private ShoppingCart() {
        songLinkedList =  FXCollections.observableArrayList();
        albumLinkedList = FXCollections.observableArrayList();

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

}









