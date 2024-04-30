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

public class Staff {
        private final BorderPane rootPane;

    public Staff() {
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");
        VBox center = new VBox();
        center.getChildren().add(header());
        center.getChildren().add(menu());
        center.getChildren().add(tool());
        center.getChildren().add(labelTabel());
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

        Label labelTabel = new Label("Tabel Staff");
        labelTabel.getStyleClass().add("label-tabel");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    @SuppressWarnings("unchecked")
    public TableView table() {
        TableView tabelStaff = new TableView();
        tabelStaff.getStyleClass().add("tabel-jadwal");

        TableColumn<Map, String> kodeStaff = new TableColumn<>("Kode Staff");
        kodeStaff.setCellValueFactory(new MapValueFactory<>("Kode Staff"));
        kodeStaff.setPrefWidth(150);

        TableColumn<Map, String> namaStaff = new TableColumn<>("Nama Staff");
        namaStaff.setCellValueFactory(new MapValueFactory<>("Nama Staff"));
        namaStaff.setPrefWidth(150);
        
        TableColumn<Map, String> jenisKelamin = new TableColumn<>("Jenis Kelamin");
        jenisKelamin.setCellValueFactory(new MapValueFactory<>("Jenis Kelamin"));
        jenisKelamin.setPrefWidth(150);

        TableColumn<Map, String> tempatTanggalLahir = new TableColumn<>("TTL");
        tempatTanggalLahir.setCellValueFactory(new MapValueFactory<>("TTL"));
        tempatTanggalLahir.setPrefWidth(230);

        TableColumn<Map, String> alamat = new TableColumn<>("Alamat");
        alamat.setCellValueFactory(new MapValueFactory<>("Alamat"));
        alamat.setPrefWidth(334);
        alamat.getStyleClass().add("wrap-text");

        TableColumn<Map, String> noTelepon = new TableColumn<>("Nomor Telepon");
        noTelepon.setCellValueFactory(new MapValueFactory<>("Nomor Telepon"));
        noTelepon.setPrefWidth(200);

        TableColumn<Map, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(new MapValueFactory<>("Email"));
        email.setPrefWidth(220);

        tabelStaff.getColumns().add(kodeStaff);
        tabelStaff.getColumns().add(namaStaff);
        tabelStaff.getColumns().add(jenisKelamin);
        tabelStaff.getColumns().add(tempatTanggalLahir);
        tabelStaff.getColumns().add(alamat);
        tabelStaff.getColumns().add(noTelepon);
        tabelStaff.getColumns().add(email);

        ObservableList<Map<String, Object>> items = 
        FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Kode Staff", "ST0000000001");
        item1.put("Nama Staff", "Vinsen");
        item1.put("Jenis Kelamin", "Laki-laki");
        item1.put("TTL", "Pontianak, 02/06/1999");
        item1.put("Alamat", "Jl. Trunojoyo");
        item1.put("Nomor Telepon", "081234567890");
        item1.put("Email", "pak.vinsen@gmail.com");

        items.add(item1);

        tabelStaff.getItems().addAll(items);

        return tabelStaff;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
