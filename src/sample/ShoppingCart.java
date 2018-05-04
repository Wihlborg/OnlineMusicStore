package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ShoppingCart {


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








