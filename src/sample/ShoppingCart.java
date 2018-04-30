package sample;

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

    @FXML
    private TableView<?> tableView;


    private static ShoppingCart instance = null;
    private Queue<Song>songLinkedList;
    private Queue<Album>albumLinkedList;

    private ShoppingCart() {
        songLinkedList = new LinkedList<>();
        albumLinkedList = new LinkedList<>();

    }

    public static ShoppingCart getInstance() {
        if (instance == null){
            instance = new ShoppingCart();
        }
        return instance;
    }



    public void writetoTextArea(){



  }


    }



