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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Customer {
    private final BorderPane rootPane;

    public Customer() {
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");
        VBox center = new VBox();
        center.getChildren().add(header());
        center.getChildren().add(menu());
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

    public HBox labelTabel() {
        HBox tabel = new HBox();
        tabel.getStyleClass().add("tabel");

        Label labelTabel = new Label("Tabel Customer");
        labelTabel.getStyleClass().add("label-tabel");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    @SuppressWarnings("unchecked")
    public TableView table() {
        TableView tabelCustomer = new TableView();
        tabelCustomer.getStyleClass().add("tabel-jadwal");

        TableColumn<Map, String> kodeCustomer = new TableColumn<>("Kode Customer");
        kodeCustomer.setCellValueFactory(new MapValueFactory<>("Kode Customer"));
        kodeCustomer.setPrefWidth(300);

        TableColumn<Map, String> namaCustomer = new TableColumn<>("Nama Customer");
        namaCustomer.setCellValueFactory(new MapValueFactory<>("Nama Customer"));
        namaCustomer.setPrefWidth(300);

        TableColumn<Map, String> noTelepon = new TableColumn<>("Nomor Telepon");
        noTelepon.setCellValueFactory(new MapValueFactory<>("Nomor Telepon"));
        noTelepon.setPrefWidth(200);

        TableColumn<Map, String> alamat = new TableColumn<>("Alamat");
        alamat.setCellValueFactory(new MapValueFactory<>("Alamat"));
        alamat.setPrefWidth(634);
        alamat.getStyleClass().add("wrap-text");

        tabelCustomer.getColumns().add(kodeCustomer);
        tabelCustomer.getColumns().add(namaCustomer);
        tabelCustomer.getColumns().add(noTelepon);
        tabelCustomer.getColumns().add(alamat);

        ObservableList<Map<String, Object>> items = 
        FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Kode Customer", "CS0000000001");
        item1.put("Nama Customer", "Daniel");
        item1.put("Nomor Telepon", "0877239138712");
        item1.put("Alamat", "Jl. Satu");

        items.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Kode Customer", "CS0000000002");
        item2.put("Nama Customer", "Edwin");
        item2.put("Nomor Telepon", "081234567890");
        item2.put("Alamat", "Jl. Dua");

        items.add(item2);

        tabelCustomer.getItems().addAll(items);

        return tabelCustomer;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
