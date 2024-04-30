package com.pojokbersih;

import javafx.scene.control.Label;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class Home {
    private final BorderPane rootPane;

    public Home(){
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");
        VBox center = new VBox();
        center.getChildren().add(header());
        center.getChildren().add(menu());
        center.getChildren().add(cards());
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

        Button transaksi = new Button("Transaksi");
        transaksi.getStyleClass().add("btn");

        Button customer = new Button("Customer");
        customer.getStyleClass().add("btn");

        Button staff = new Button("Staff");
        staff.getStyleClass().add("btn");

        Button produk = new Button("Produk");
        produk.getStyleClass().add("btn");

        menu.getChildren().add(home);
        menu.getChildren().add(transaksi);
        menu.getChildren().add(customer);
        menu.getChildren().add(staff);
        menu.getChildren().add(produk);

        return menu;
    }
    
    public HBox cards() {
        HBox cards = new HBox(50);
        cards.getStyleClass().add("cards");

        // Total Deal
        StackPane deal = new StackPane();
        deal.getStyleClass().add("card");

        Rectangle dealBackground = new Rectangle(320, 100, Color.WHITE);

        VBox dealBox = new VBox();
        dealBox.getStyleClass().add("total-box");
        Text dealText = new Text("Total Transaksi Deal");
        Text dealValue = new Text("10");
        dealText.getStyleClass().add("deal-text");
        dealValue.getStyleClass().add("card-value");

        dealBox.getChildren().addAll(dealText, dealValue);
        deal.getChildren().addAll(dealBackground, dealBox);

        // Total On Going
        StackPane ongoing = new StackPane();
        ongoing.getStyleClass().add("card");

        Rectangle ongoingBackground = new Rectangle(320, 100, Color.WHITE);

        VBox ongoingBox = new VBox();
        ongoingBox.getStyleClass().add("total-box");
        Text ongoingText = new Text("Total Transaksi On Going");
        Text ongoingValue = new Text("5");
        ongoingText.getStyleClass().add("ongoing-text");
        ongoingValue.getStyleClass().add("card-value");

        ongoingBox.getChildren().addAll(ongoingText, ongoingValue);
        ongoing.getChildren().addAll(ongoingBackground, ongoingBox);

        // Total Done
        StackPane done = new StackPane();
        done.getStyleClass().add("card");

        Rectangle doneBackground = new Rectangle(320, 100, Color.WHITE);

        VBox doneBox = new VBox();
        doneBox.getStyleClass().add("total-box");
        Text doneText = new Text("Total Transaksi Done");
        Text doneValue = new Text("50");
        doneText.getStyleClass().add("done-text");
        doneValue.getStyleClass().add("card-value");

        doneBox.getChildren().addAll(doneText, doneValue);
        done.getChildren().addAll(doneBackground, doneBox);

        // Total Pendapatan
        StackPane pendapatan = new StackPane();
        pendapatan.getStyleClass().add("card");

        Rectangle pendapatanBackground = new Rectangle(320, 100, Color.WHITE);

        VBox pendapatanBox = new VBox();
        pendapatanBox.getStyleClass().add("total-box");
        Text pendapatanText = new Text("Total Pendapatan");
        Text pendapatanValue = new Text("10.000.000");
        pendapatanText.getStyleClass().add("pendapatan-text");
        pendapatanValue.getStyleClass().add("card-value");

        pendapatanBox.getChildren().addAll(pendapatanText, pendapatanValue);
        pendapatan.getChildren().addAll(pendapatanBackground, pendapatanBox);

        cards.getChildren().add(deal);
        cards.getChildren().add(ongoing);
        cards.getChildren().add(done);
        cards.getChildren().add(pendapatan);

        return cards;
    }

    public HBox labelTabel() {
        HBox tabel = new HBox();
        tabel.getStyleClass().add("tabel");

        Label labelTabel = new Label("Jadwal Layanan");
        labelTabel.getStyleClass().add("label-tabel");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    @SuppressWarnings("unchecked")
    public TableView table() {
        TableView tabelJadwal = new TableView();
        tabelJadwal.getStyleClass().add("tabel-jadwal");

        TableColumn<Map, String> kodeTransaksi = new TableColumn<>("Kode Transaksi");
        kodeTransaksi.setCellValueFactory(new MapValueFactory<>("Kode Transaksi"));
        kodeTransaksi.setPrefWidth(200);

        TableColumn<Map, String> tanggalPengerjaan = new TableColumn<>("Tanggal Pengerjaan");
        tanggalPengerjaan.setCellValueFactory(new MapValueFactory<>("Tanggal Pengerjaan"));
        tanggalPengerjaan.setPrefWidth(200);
        
        TableColumn<Map, String> namaCustomer = new TableColumn<>("Nama Customer");
        namaCustomer.setCellValueFactory(new MapValueFactory<>("Nama Customer"));
        namaCustomer.setPrefWidth(200);

        TableColumn<Map, String> noTelepon = new TableColumn<>("Nomor Telepon");
        noTelepon.setCellValueFactory(new MapValueFactory<>("Nomor Telepon"));
        noTelepon.setPrefWidth(180);

        TableColumn<Map, String> alamat = new TableColumn<>("Alamat");
        alamat.setCellValueFactory(new MapValueFactory<>("Alamat"));
        alamat.setPrefWidth(354);
        alamat.getStyleClass().add("wrap-text");

        TableColumn<Map, String> pic = new TableColumn<>("PIC");
        pic.setCellValueFactory(new MapValueFactory<>("PIC"));
        pic.setPrefWidth(150);

        TableColumn<Map, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(new MapValueFactory<>("Status"));
        status.setPrefWidth(150);

        tabelJadwal.getColumns().add(kodeTransaksi);
        tabelJadwal.getColumns().add(tanggalPengerjaan);
        tabelJadwal.getColumns().add(namaCustomer);
        tabelJadwal.getColumns().add(noTelepon);
        tabelJadwal.getColumns().add(alamat);
        tabelJadwal.getColumns().add(pic);
        tabelJadwal.getColumns().add(status);

        ObservableList<Map<String, Object>> items = 
        FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Kode Transaksi", "PB0000000001");
        item1.put("Tanggal Pengerjaan", "20/05/2024");
        item1.put("Nama Customer", "Edwin");
        item1.put("Nomor Telepon", "081234567812");
        item1.put("Alamat", "Jl. Trunojoyo");
        item1.put("PIC", "Daniel");
        item1.put("Status", "On Going");

        items.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Kode Transaksi", "PB0000000002");
        item2.put("Tanggal Pengerjaan", "21/05/2024");
        item2.put("Nama Customer", "Daniel");
        item2.put("Nomor Telepon", "081234567853");
        item2.put("Alamat", "Jl. Trunojoyo");
        item2.put("PIC", "Edwin");
        item2.put("Status", "Deal");

        items.add(item2);

        tabelJadwal.getItems().addAll(items);

        return tabelJadwal;
    }

    
    public Pane getRootPane() {
        return rootPane;
    }
}
