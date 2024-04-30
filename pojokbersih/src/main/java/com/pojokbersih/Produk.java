package com.pojokbersih;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Produk {
        private final BorderPane rootPane;

    public Produk() {
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");
        VBox center = new VBox();
        center.getChildren().add(header());
        center.getChildren().add(menu());
        center.getChildren().add(labelTabel());
        center.getChildren().add(tool());
        center.getChildren().add(table());
        rootPane.setCenter(center);
    }

    public HBox header() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        Label logo = new Label("PojokBersih");
        logo.getStyleClass().add("logo");

        header.getChildren().add(logo);

        return header;
    }

    public HBox menu() {
        HBox menu = new HBox();
        menu.getStyleClass().add("menu");

        Button home = new Button("Home");
        home.getStyleClass().add("btn");

        home.setOnAction(e -> {
            Home homee = new Home();
            rootPane.getScene().setRoot(homee.getRootPane());
        });

        Button transaksi = new Button("Transaksi");
        transaksi.getStyleClass().add("btn");

        transaksi.setOnAction(e -> {
            Transaksi transaksii = new Transaksi();
            rootPane.getScene().setRoot(transaksii.getRootPane());
        });

        Button customer = new Button("Customer");
        customer.getStyleClass().add("btn");

        customer.setOnAction(e -> {
            Customer customerr = new Customer();
            rootPane.getScene().setRoot(customerr.getRootPane());
        });

        Button staff = new Button("Staff");
        staff.getStyleClass().add("btn");

        staff.setOnAction(e -> {
            Staff stafff = new Staff();
            rootPane.getScene().setRoot(stafff.getRootPane());
        });

        Button produk = new Button("Produk");
        produk.getStyleClass().add("btn");

        produk.setOnAction(e -> {
            Produk produkk = new Produk();
            rootPane.getScene().setRoot(produkk.getRootPane());
        });

        menu.getChildren().add(home);
        menu.getChildren().add(transaksi);
        menu.getChildren().add(customer);
        menu.getChildren().add(staff);
        menu.getChildren().add(produk);

        return menu;
    }

    public HBox tool() {
        HBox tool = new HBox(20);
        tool.getStyleClass().add("tool-box");

            Button filterButton = new Button();
            filterButton.setText("Filter");
            filterButton.getStyleClass().add("filter-button");

            TextField searchField = new TextField();
            searchField.getStyleClass().add("search-field");
            searchField.setPromptText("Cari");
            
            Button addButton = new Button();
            addButton.setText("Tambah");
            addButton.getStyleClass().add("add-button");
        
            tool.getChildren().addAll(filterButton, searchField, addButton);
        return tool;
    }

    public HBox labelTabel() {
        HBox tabel = new HBox();
        tabel.getStyleClass().add("tabel");

        Label labelTabel = new Label("Tabel Produk");
        labelTabel.getStyleClass().add("label-tabel-produk");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    @SuppressWarnings("unchecked")
    public TableView table() {
        TableView tabelProduk = new TableView();
        tabelProduk.getStyleClass().add("tabel-jadwal");

        TableColumn<Map, String> kodeProduk = new TableColumn<>("Kode Produk");
        kodeProduk.setCellValueFactory(new MapValueFactory<>("Kode Produk"));
        kodeProduk.setPrefWidth(717);

        TableColumn<Map, String> namaProduk = new TableColumn<>("Nama Produk");
        namaProduk.setCellValueFactory(new MapValueFactory<>("Nama Produk"));
        namaProduk.setPrefWidth(717);

        tabelProduk.getColumns().add(kodeProduk);
        tabelProduk.getColumns().add(namaProduk);

        ObservableList<Map<String, Object>> items = 
        FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Kode Produk", "PD0000000001");
        item1.put("Nama Produk", "Regular Cleaning");

        items.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Kode Produk", "PD0000000002");
        item2.put("Nama Produk", "Deep Cleaning");

        items.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Kode Produk", "PD0000000003");
        item3.put("Nama Produk", "Inspection");

        items.add(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("Kode Produk", "PD0000000004");
        item4.put("Nama Produk", "Gardening");

        items.add(item4);

        tabelProduk.getItems().addAll(items);

        return tabelProduk;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
