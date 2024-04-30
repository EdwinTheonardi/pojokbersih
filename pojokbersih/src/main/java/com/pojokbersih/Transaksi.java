package com.pojokbersih;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Transaksi {
    private final BorderPane rootPane;

    public Transaksi() {
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

        Label labelTabel = new Label("Tabel Transaksi");
        labelTabel.getStyleClass().add("label-tabel-transaksi");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    @SuppressWarnings("unchecked")
    public TableView table() {
        TableView tabelTransaksi = new TableView();
        tabelTransaksi.getStyleClass().add("tabel-jadwal");

        TableColumn<Map, String> kodeTransaksi = new TableColumn<>("Kode Transaksi");
        kodeTransaksi.setCellValueFactory(new MapValueFactory<>("Kode Transaksi"));
        kodeTransaksi.setPrefWidth(300);

        TableColumn<Map, String> tanggalTransaksi = new TableColumn<>("Tanggal Transaksi");
        tanggalTransaksi.setCellValueFactory(new MapValueFactory<>("Tanggal Transaksi"));
        tanggalTransaksi.setPrefWidth(200);

        TableColumn<Map, String> namaCustomer = new TableColumn<>("Nama Customer");
        namaCustomer.setCellValueFactory(new MapValueFactory<>("Nama Customer"));
        namaCustomer.setPrefWidth(300);

        TableColumn<Map, String> pic = new TableColumn<>("PIC");
        pic.setCellValueFactory(new MapValueFactory<>("PIC"));
        pic.setPrefWidth(244);

        TableColumn<Map, String> biayaJasa = new TableColumn<>("Biaya Jasa");
        biayaJasa.setCellValueFactory(new MapValueFactory<>("Biaya Jasa"));
        biayaJasa.setPrefWidth(200);
        biayaJasa.getStyleClass().add("wrap-text");

        TableColumn<Map, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(new MapValueFactory<>("Status"));
        status.setPrefWidth(190);
        status.getStyleClass().add("wrap-text");

        tabelTransaksi.getColumns().add(kodeTransaksi);
        tabelTransaksi.getColumns().add(tanggalTransaksi);
        tabelTransaksi.getColumns().add(namaCustomer);
        tabelTransaksi.getColumns().add(pic);
        tabelTransaksi.getColumns().add(biayaJasa);
        tabelTransaksi.getColumns().add(status);

        ObservableList<Map<String, Object>> items = 
        FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Kode Transaksi", "PB0000000001");
        item1.put("Tanggal Transaksi", "03/04/2024");
        item1.put("Nama Customer", "Daniel");
        item1.put("PIC", "Edwin");
        item1.put("Biaya Jasa", "300000");
        item1.put("Status", "Deal");

        items.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Kode Transaksi", "PB0000000002");
        item2.put("Tanggal Transaksi", "04/04/2024");
        item2.put("Nama Customer", "Edwin");
        item2.put("PIC", "Daniel");
        item2.put("Biaya Jasa", "300000");
        item2.put("Status", "On Going");

        items.add(item2);

        tabelTransaksi.getItems().addAll(items);

        return tabelTransaksi;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
