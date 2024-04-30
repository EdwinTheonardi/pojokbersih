package com.pojokbersih;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Coba {
        private final BorderPane rootPane;

    public Coba(){
        rootPane = new BorderPane();
        rootPane.setTop(hbox1());
    }
    
    public HBox hbox1() {
        HBox tampilan = new HBox();
        Label text1 = new Label("halaman 2");

        tampilan.getChildren().add(text1);

        return tampilan;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
