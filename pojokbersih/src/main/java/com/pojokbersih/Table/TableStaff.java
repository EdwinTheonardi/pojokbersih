package com.pojokbersih.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Home;
import com.pojokbersih.Model.Staff;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableStaff {
        private final BorderPane rootPane;

    public TableStaff() {
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");
        VBox center = new VBox();
        center.getChildren().add(header());
        center.getChildren().add(menu());
        center.getChildren().add(labelTabel());
        center.getChildren().add(tool());
        center.getChildren().add(getTable());
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
            TableStaff stafff = new TableStaff();
            rootPane.getScene().setRoot(stafff.getRootPane());
        });

        Button produk = new Button("Produk");
        produk.getStyleClass().add("btn");

        produk.setOnAction(e -> {
            TableProduk produkk = new TableProduk();
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

    private List<Staff> listStaff;
    private TableView<Staff> tb = new TableView<Staff>();

    @SuppressWarnings("unchecked")
    public TableView<Staff> createTable() {
        TableColumn<Staff, String> col_id = new TableColumn<>("Kode Staff");
        TableColumn<Staff, String> col_nama = new TableColumn<>(" Nama Staff");
        TableColumn<Staff, String> col_jeniskelamin = new TableColumn<>("Jenis Kelamin");
        TableColumn<Staff, String> col_tempatlahir = new TableColumn<>("Tempat Lahir");
        TableColumn<Staff, String> col_tanggallahir = new TableColumn<>("Tanggal Lahir");
        TableColumn<Staff, String> col_alamat = new TableColumn<>("Alamat Staff");
        TableColumn<Staff, String> col_nomorhp = new TableColumn<>("Nomor Telepon");
        TableColumn<Staff, String> col_email = new TableColumn<>("Email Staff");
        TableColumn<Staff, String> col_spesialisasi = new TableColumn<>("Spesialisasi Staff");

        col_id.setCellValueFactory(v-> v.getValue().idStaffProperty());
        col_nama.setCellValueFactory(v-> v.getValue().namaStaffProperty());
        col_jeniskelamin.setCellValueFactory(v-> v.getValue().jenisKelaminStaffProperty());
        col_tempatlahir.setCellValueFactory(v-> v.getValue().tempatLahirStaffProperty());
        col_tanggallahir.setCellValueFactory(v-> v.getValue().tanggalLahirStaffProperty());
        col_alamat.setCellValueFactory(v-> v.getValue().alamatStaffProperty());
        col_nomorhp.setCellValueFactory(v-> v.getValue().nomorHpStaffProperty());
        col_email.setCellValueFactory(v-> v.getValue().emailStaffProperty());
        col_spesialisasi.setCellValueFactory(v-> v.getValue().spesialisasiStaffProperty());

        ArrayList<TableColumn<Staff, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_nama);
        col.add(col_jeniskelamin);
        col.add(col_tempatlahir);
        col.add(col_tanggallahir);
        col.add(col_alamat);
        col.add(col_nomorhp);
        col.add(col_email);
        col.add(col_spesialisasi);

        for (int i = 0; i< col.size(); i++) {
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        return tb;
    }

    public HBox getTable() {
        HBox table = new HBox();
        TableView<Staff> tb = createTable();

        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Staff> list_cust = getData();

        for(int y = 0; y < list_cust.size(); y++) {
            tb.getItems().add(list_cust.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Staff> getData() {
        DB db = new DB();
        String query_admin = "SELECT * FROM staff";

        List<Object> rs = db.runQuery(query_admin);
        listStaff = new ArrayList<Staff>();

        for(int i = 0; i < rs.size(); i++) {
            Staff staff = new Staff(rs.get(i));

            listStaff.add(staff);
        }
        
        return listStaff;
    }


    public Pane getRootPane() {
        return rootPane;
    }
}
