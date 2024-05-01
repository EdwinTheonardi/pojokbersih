package com.pojokbersih;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Staff {
        private final BorderPane rootPane;

    public Staff() {
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

            addButton.setOnAction(event -> {
                Stage halamanTambah = new Stage();
                halamanTambah.initModality(Modality.APPLICATION_MODAL);
                halamanTambah.setTitle("Tambah Staff");
                halamanTambah.setWidth(1280);
                halamanTambah.setHeight(720);

                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formTambah = new VBox();
                Label formTitle = new Label("Tambah Staff");
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
                Label namaStaffLabel = new Label("Nama Staff:");
                namaStaffLabel.getStyleClass().add("form-label");
                namaStaffLabel.setPrefSize(200, 20);
                TextField namaStaffField = new TextField();
                namaStaffField.setPrefSize(200, 20);
                GridPane.setConstraints(namaStaffLabel, 0, 0);
                GridPane.setConstraints(namaStaffField, 1, 0);
                row1.getChildren().addAll(namaStaffLabel, namaStaffField);
                formTambah.getChildren().add(row1);

                // row 2
                GridPane row2 = new GridPane();
                row2.getStyleClass().add("form-grid");
                row2.setHgap(10);
                row2.setVgap(5);
                Label jenisKelamin = new Label("Jenis Kelamin:");
                jenisKelamin.getStyleClass().add("form-label");
                jenisKelamin.setPrefSize(200, 20);
                ComboBox<String> jenisKelaminBox = new ComboBox<>();
                jenisKelaminBox.getItems().addAll("Laki-laki", "Perempuan");
                jenisKelaminBox.setPrefSize(200, 20);
                GridPane.setConstraints(jenisKelamin, 0, 0);
                GridPane.setConstraints(jenisKelaminBox, 1, 0);
                row2.getChildren().addAll(jenisKelamin, jenisKelaminBox);
                formTambah.getChildren().add(row2);

                // row 3
                GridPane row3 = new GridPane();
                row3.getStyleClass().add("form-grid");
                row3.setHgap(10);
                row3.setVgap(5);
                Label tempatLahirLabel = new Label("Tempat Lahir:");
                tempatLahirLabel.getStyleClass().add("form-label");
                tempatLahirLabel.setPrefSize(200, 20);
                TextField tempatLahirField = new TextField();
                tempatLahirField.setPrefSize(200, 20);
                GridPane.setConstraints(tempatLahirLabel, 0, 0);
                GridPane.setConstraints(tempatLahirField, 1, 0);
                row3.getChildren().addAll(tempatLahirLabel, tempatLahirField);
                formTambah.getChildren().add(row3);

                // row 4
                GridPane row4 = new GridPane();
                row4.getStyleClass().add("form-grid");
                row4.setHgap(10);
                row4.setVgap(5);
                Label tanggalLahirLabel = new Label("Tanggal Lahir:");
                tanggalLahirLabel.getStyleClass().add("form-label");
                tanggalLahirLabel.setPrefSize(200, 20);
                DatePicker tanggalLahirField = new DatePicker();
                tanggalLahirField.setPrefSize(200, 20);
                GridPane.setConstraints(tanggalLahirLabel, 0, 0);
                GridPane.setConstraints(tanggalLahirField, 1, 0);
                row4.getChildren().addAll(tanggalLahirLabel, tanggalLahirField);
                formTambah.getChildren().add(row4);

                // row 5
                GridPane row5 = new GridPane();
                row5.getStyleClass().add("form-grid");
                row5.setHgap(10);
                row5.setVgap(5);
                Label alamatLabel = new Label("Alamat:");
                alamatLabel.getStyleClass().add("form-label");
                alamatLabel.setPrefSize(200, 20);
                TextArea alamatField = new TextArea();
                alamatField.setPrefSize(200, 80);
                GridPane.setConstraints(alamatLabel, 0, 0);
                GridPane.setConstraints(alamatField, 1, 0);
                row5.getChildren().addAll(alamatLabel, alamatField);
                formTambah.getChildren().add(row5);

                // row 6
                GridPane row6 = new GridPane();
                row6.getStyleClass().add("form-grid");
                row6.setHgap(10);
                row6.setVgap(5);
                Label nomorTelfonLabel = new Label("Nomor Telepon:");
                nomorTelfonLabel.getStyleClass().add("form-label");
                nomorTelfonLabel.setPrefSize(200, 20);
                TextField nomorTelfonField = new TextField();
                nomorTelfonField.setPrefSize(200, 20);
                GridPane.setConstraints(nomorTelfonLabel, 0, 0);
                GridPane.setConstraints(nomorTelfonField, 1, 0);
                row6.getChildren().addAll(nomorTelfonLabel, nomorTelfonField);
                formTambah.getChildren().add(row6);

                // row 7
                GridPane row7 = new GridPane();
                row7.getStyleClass().add("form-grid");
                row7.setHgap(10);
                row7.setVgap(5);
                Label emailLabel = new Label("Email:");
                emailLabel.getStyleClass().add("form-label");
                emailLabel.setPrefSize(200, 20);
                TextField emailField = new TextField();
                emailField.setPrefSize(200, 20);
                GridPane.setConstraints(emailLabel, 0, 0);
                GridPane.setConstraints(emailField, 1, 0);
                row7.getChildren().addAll(emailLabel, emailField);
                formTambah.getChildren().add(row7);

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

        Label labelTabel = new Label("Tabel Staff");
        labelTabel.getStyleClass().add("label-tabel-staff");
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

        TableColumn<Map, String> tempatLahir = new TableColumn<>("Tempat Lahir");
        tempatLahir.setCellValueFactory(new MapValueFactory<>("Tempat Lahir"));
        tempatLahir.setPrefWidth(140);

        TableColumn<Map, String> tanggalLahir = new TableColumn<>("Tanggal Lahir");
        tanggalLahir.setCellValueFactory(new MapValueFactory<>("Tanggal Lahir"));
        tanggalLahir.setPrefWidth(150);

        TableColumn<Map, String> alamat = new TableColumn<>("Alamat");
        alamat.setCellValueFactory(new MapValueFactory<>("Alamat"));
        alamat.setPrefWidth(314);
        alamat.getStyleClass().add("wrap-text");

        TableColumn<Map, String> noTelepon = new TableColumn<>("Nomor Telepon");
        noTelepon.setCellValueFactory(new MapValueFactory<>("Nomor Telepon"));
        noTelepon.setPrefWidth(160);

        TableColumn<Map, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(new MapValueFactory<>("Email"));
        email.setPrefWidth(220);

        tabelStaff.getColumns().add(kodeStaff);
        tabelStaff.getColumns().add(namaStaff);
        tabelStaff.getColumns().add(jenisKelamin);
        tabelStaff.getColumns().add(tempatLahir);
        tabelStaff.getColumns().add(tanggalLahir);
        tabelStaff.getColumns().add(alamat);
        tabelStaff.getColumns().add(noTelepon);
        tabelStaff.getColumns().add(email);

        ObservableList<Map<String, Object>> items = 
        FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Kode Staff", "ST0000000001");
        item1.put("Nama Staff", "Vinsen");
        item1.put("Jenis Kelamin", "Laki-laki");
        item1.put("Tempat Lahir", "Pontianak");
        item1.put("Tanggal Lahir", "02/06/1999");
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
