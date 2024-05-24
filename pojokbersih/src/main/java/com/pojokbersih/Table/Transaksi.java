package com.pojokbersih.Table;

import java.util.HashMap;
import java.util.Map;

import com.pojokbersih.App;
import com.pojokbersih.Home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            TableCustomer customerr = new TableCustomer();
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

            addButton.setOnAction(event -> {
                Stage halamanTambah = new Stage();
                halamanTambah.initModality(Modality.APPLICATION_MODAL);
                halamanTambah.setTitle("Tambah Transaksi");
                halamanTambah.setWidth(1280);
                halamanTambah.setHeight(720);
                
                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formTambah = new VBox();
                Label formTitle = new Label("Tambah Transaksi");
                formTitle.getStyleClass().add("form-title");
                formTambah.getChildren().add(formTitle);

                StackPane formTitleWrapper = new StackPane();
                formTitleWrapper.getChildren().add(formTitle);
                formTitleWrapper.setPrefWidth(200);
                StackPane.setAlignment(formTitle, Pos.CENTER);
                formTambah.getChildren().add(formTitleWrapper);

                // row 1
                GridPane row1 = new GridPane();
                row1.getStyleClass().add("form-grid");
                row1.setHgap(10);
                row1.setVgap(5);
                Label tanggalTransaksiLabel = new Label("Tanggal Transaksi:");
                tanggalTransaksiLabel.getStyleClass().add("form-label");
                tanggalTransaksiLabel.setPrefSize(200, 20);
                DatePicker tanggalTransaksiField = new DatePicker();
                tanggalTransaksiField.setPrefSize(200, 20);
                GridPane.setConstraints(tanggalTransaksiLabel, 0, 0);
                GridPane.setConstraints(tanggalTransaksiField, 1, 0);
                row1.getChildren().addAll(tanggalTransaksiLabel, tanggalTransaksiField);
                formTambah.getChildren().add(row1);

                // row 2
                GridPane row2 = new GridPane();
                row2.getStyleClass().add("form-grid");
                row2.setHgap(10);
                row2.setVgap(5);
                Label tanggalPengerjaanLabel = new Label("Tanggal Pengerjaan:");
                tanggalPengerjaanLabel.getStyleClass().add("form-label");
                tanggalPengerjaanLabel.setPrefSize(200, 20);
                DatePicker tanggalPengerjaanField = new DatePicker();
                tanggalPengerjaanField.setPrefSize(200, 20);
                GridPane.setConstraints(tanggalPengerjaanLabel, 0, 0);
                GridPane.setConstraints(tanggalPengerjaanField, 1, 0);
                row2.getChildren().addAll(tanggalPengerjaanLabel, tanggalPengerjaanField);
                formTambah.getChildren().add(row2);

                // row 3
                GridPane row3 = new GridPane();
                row3.getStyleClass().add("form-grid");
                row3.setHgap(10);
                row3.setVgap(5);
                Label namaCustomerLabel = new Label("Nama Customer:");
                namaCustomerLabel.getStyleClass().add("form-label");
                namaCustomerLabel.setPrefSize(200, 20);
                TextField namaCustomerField = new TextField();
                namaCustomerField.setPrefSize(140, 20);
                namaCustomerField.setDisable(true);
                Button insert = new Button("+");
                insert.getStyleClass().add("form-button");
                insert.setPrefSize(50, 20);
                GridPane.setConstraints(namaCustomerLabel, 0, 0);
                GridPane.setConstraints(namaCustomerField, 1, 0);
                GridPane.setConstraints(insert, 2, 0);
                row3.getChildren().addAll(namaCustomerLabel, namaCustomerField, insert);
                formTambah.getChildren().add(row3);

                // row 4
                GridPane row4 = new GridPane();
                row4.getStyleClass().add("form-grid");
                row4.setHgap(10);
                row4.setVgap(5);
                Label nomorTelfonLabel = new Label("Nomor Telepon:");
                nomorTelfonLabel.getStyleClass().add("form-label");
                nomorTelfonLabel.setPrefSize(200, 20);
                TextField nomorTelfonField = new TextField();
                nomorTelfonField.setPrefSize(200, 20);
                nomorTelfonField.setDisable(true);
                GridPane.setConstraints(nomorTelfonLabel, 0, 0);
                GridPane.setConstraints(nomorTelfonField, 1, 0);
                row4.getChildren().addAll(nomorTelfonLabel, nomorTelfonField);
                formTambah.getChildren().add(row4);

                // row 5
                GridPane row5 = new GridPane();
                row5.getStyleClass().add("form-grid");
                row5.setHgap(10);
                row5.setVgap(5);
                Label alamatCustomerLabel = new Label("Alamat Customer:");
                alamatCustomerLabel.getStyleClass().add("form-label");
                alamatCustomerLabel.setPrefSize(200, 20);
                TextArea alamatCustomerField = new TextArea();
                alamatCustomerField.setPrefSize(200, 80);
                alamatCustomerField.setDisable(true);
                GridPane.setConstraints(alamatCustomerLabel, 0, 0);
                GridPane.setConstraints(alamatCustomerField, 1, 0);
                row5.getChildren().addAll(alamatCustomerLabel, alamatCustomerField);
                formTambah.getChildren().add(row5);

                // row 6
                GridPane row6 = new GridPane();
                row6.getStyleClass().add("form-grid");
                row6.setHgap(10);
                row6.setVgap(5);
                Label picLabel = new Label("PIC:");
                picLabel.getStyleClass().add("form-label");
                picLabel.setPrefSize(200, 20);
                TextField picField = new TextField();
                picField.setPrefSize(140, 20);
                picField.setDisable(true);
                Button insertPic = new Button("+");
                insertPic.getStyleClass().add("form-button");
                insertPic.setPrefSize(50, 20);
                GridPane.setConstraints(picLabel, 0, 0);
                GridPane.setConstraints(picField, 1, 0);
                GridPane.setConstraints(insertPic, 2, 0);
                row6.getChildren().addAll(picLabel, picField, insertPic);
                formTambah.getChildren().add(row6);

                // row 7
                GridPane row7 = new GridPane();
                row7.getStyleClass().add("form-grid");
                row7.setHgap(10);
                row7.setVgap(5);
                Label staffLabel = new Label("Staff:");
                staffLabel.getStyleClass().add("form-label");
                staffLabel.setPrefSize(200, 20);
                TextField staffField = new TextField();
                staffField.setPrefSize(140, 20);
                staffField.setDisable(true);
                Button insertStaff = new Button("+");
                insertStaff.getStyleClass().add("form-button");
                insertStaff.setPrefSize(50, 20);
                GridPane.setConstraints(staffLabel, 0, 0);
                GridPane.setConstraints(staffField, 1, 0);
                GridPane.setConstraints(insertStaff, 2, 0);
                row7.getChildren().addAll(staffLabel, staffField, insertStaff);
                formTambah.getChildren().add(row7);

                // row 8
                GridPane row8 = new GridPane();
                row8.getStyleClass().add("form-grid");
                row8.setHgap(10);
                row8.setVgap(5);
                Label produkLabel = new Label("Produk:");
                produkLabel.getStyleClass().add("form-label");
                produkLabel.setPrefSize(200, 20);
                ComboBox<String> produkBox = new ComboBox<>();
                produkBox.getItems().addAll("Regular Cleaning", "Deep Cleaning", "Inspection", "Gardening");
                produkBox.setPrefSize(200, 20);
                GridPane.setConstraints(produkLabel, 0, 0);
                GridPane.setConstraints(produkBox, 1, 0);
                row8.getChildren().addAll(produkLabel, produkBox);
                formTambah.getChildren().add(row8);

                // row 9
                GridPane row9 = new GridPane();
                row9.getStyleClass().add("form-grid");
                row9.setHgap(10);
                row9.setVgap(5);
                Label biayaJasaLabel = new Label("Biaya Jasa:");
                biayaJasaLabel.getStyleClass().add("form-label");
                biayaJasaLabel.setPrefSize(200, 20);
                TextField biayaJasaField = new TextField();
                biayaJasaField.setPrefSize(200, 20);
                GridPane.setConstraints(biayaJasaLabel, 0, 0);
                GridPane.setConstraints(biayaJasaField, 1, 0);
                row9.getChildren().addAll(biayaJasaLabel, biayaJasaField);
                formTambah.getChildren().add(row9);

                // row 10
                GridPane row10 = new GridPane();
                row10.getStyleClass().add("form-grid");
                row10.setHgap(10);
                row10.setVgap(5);Label statusLabel = new Label("Status:");
                statusLabel.getStyleClass().add("form-label");
                statusLabel.setPrefSize(200, 20);
                ComboBox<String> statusBox = new ComboBox<>();
                statusBox.getItems().addAll("Not Contacted", "Deal", "On Going", "Done");
                statusBox.setPrefSize(200, 20);
                GridPane.setConstraints(statusLabel, 0, 0);
                GridPane.setConstraints(statusBox, 1, 0);
                row10.getChildren().addAll(statusLabel, statusBox);
                formTambah.getChildren().add(row10);


                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(e -> {
                    halamanTambah.close();
                });

                HBox buttonWrapper = new HBox();
                buttonWrapper.getStyleClass().add("button-wrap");
                buttonWrapper.setAlignment(Pos.CENTER);
                buttonWrapper.getChildren().add(saveButton);
            
                formTambah.getChildren().add(buttonWrapper);

                halamanBaru.getChildren().add(formTambah);

                Scene scene = new Scene(halamanBaru);
                halamanTambah.setScene(scene);
                halamanTambah.show();
            });

        
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
