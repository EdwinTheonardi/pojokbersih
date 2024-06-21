package com.pojokbersih.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Home;
import com.pojokbersih.Model.Customer;
import com.pojokbersih.Model.Staff;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

        VBox top = new VBox();
        top.getChildren().add(header());
        top.getChildren().add(menu());
        top.getChildren().add(labelTabel());
        top.getChildren().add(tool());

        VBox center = new VBox();
        center.getChildren().add(getTable());

        VBox bottom = new VBox();
        bottom.getChildren().add(createButtonBox());

        rootPane.setTop(top);
        rootPane.setCenter(center);
        rootPane.setBottom(bottom);
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
            TableTransaksi transaksii = new TableTransaksi();
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

// Form Search
        GridPane formSearch = new GridPane();
        formSearch.getStyleClass().add("form-search");
        formSearch.setHgap(20);
        formSearch.setVgap(5);
        
        // Search Nama Staff
        Label searchNamaStaffLabel = new Label("Nama Customer:");
        searchNamaStaffLabel.getStyleClass().add("form-label");
        searchNamaStaffLabel.setPrefSize(200, 35);
        TextField searchNamaStaffField = new TextField();
        searchNamaStaffField.setPrefSize(200, 35);
        GridPane.setConstraints(searchNamaStaffLabel, 0, 0);
        GridPane.setConstraints(searchNamaStaffField, 1, 0);
        formSearch.getChildren().addAll(searchNamaStaffLabel, searchNamaStaffField);
 
        Button searchButton = new Button();
        searchButton.setText("Cari");
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(e -> {
            FilteredList<Staff> flStaff = new FilteredList<>(FXCollections.observableArrayList(getData()), p -> true);
        
            flStaff.setPredicate(p -> {
                return (searchNamaStaffField.getText() == null || searchNamaStaffField.getText().isEmpty() || p.getNamaStaff().toLowerCase().contains(searchNamaStaffField.getText().toLowerCase()));
            });
        
            tb.setItems(flStaff);
        });
        
        Button viewAllButton = new Button("Lihat Semua");
        viewAllButton.getStyleClass().add("search-button");
        viewAllButton.setOnAction(e -> {
            tb.setItems(FXCollections.observableArrayList(getData()));
            searchNamaStaffField.clear();
        });
            
            Button addButton = new Button();
            addButton.setText("Tambah");
            addButton.getStyleClass().add("add-button");
            GridPane.setColumnSpan(addButton, 2);
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
                jenisKelaminBox.getItems().addAll("L", "P");
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

                // row 8
                GridPane row8 = new GridPane();
                row8.getStyleClass().add("form-grid");
                row8.setHgap(10);
                row8.setVgap(5);
                Label spesialisasiLabel = new Label("Spesialisasi Staff:");
                spesialisasiLabel.getStyleClass().add("form-label");
                spesialisasiLabel.setPrefSize(200, 20);
                TextField spesialisasiField = new TextField();
                spesialisasiField.setPrefSize(200, 20);
                GridPane.setConstraints(spesialisasiLabel, 0, 0);
                GridPane.setConstraints(spesialisasiField, 1, 0);
                row8.getChildren().addAll(spesialisasiLabel, spesialisasiField);
                formTambah.getChildren().add(row8);

                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(e -> {
                    Staff staff = new Staff();
                    staff.setNamaStaff(namaStaffField.getText());
                    staff.setJenisKelaminStaff(jenisKelaminBox.getValue());
                    staff.setTempatLahirStaff(tempatLahirField.getText());
                    staff.setTanggalLahirStaff(tanggalLahirField.getValue());
                    staff.setAlamatStaff(alamatField.getText());
                    staff.setNomorHpStaff(nomorTelfonField.getText());
                    staff.setEmailStaff(emailField.getText());
                    staff.setSpesialisasiStaff(spesialisasiField.getText());

                    if(staff.create()) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil disimpan!");
                        a.show();
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Data gagal disimpan!");
                        a.show();
                    }
                    halamanTambah.close();
                    TableStaff stafff = new TableStaff();
                    rootPane.getScene().setRoot(stafff.getRootPane());
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
        
            HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(searchButton, viewAllButton, addButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        
        tool.getChildren().add(formSearch);
        tool.getChildren().add(buttonBox);
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
        TableColumn<Staff, String> col_kodestaff = new TableColumn<>("Kode Staff");
        TableColumn<Staff, String> col_nama = new TableColumn<>(" Nama Staff");
        TableColumn<Staff, String> col_jeniskelamin = new TableColumn<>("Jenis Kelamin");
        TableColumn<Staff, String> col_tempatlahir = new TableColumn<>("Tempat Lahir");
        TableColumn<Staff, String> col_tanggallahir = new TableColumn<>("Tanggal Lahir");
        TableColumn<Staff, String> col_alamat = new TableColumn<>("Alamat Staff");
        TableColumn<Staff, String> col_nomorhp = new TableColumn<>("Nomor Telepon");
        TableColumn<Staff, String> col_email = new TableColumn<>("Email Staff");
        TableColumn<Staff, String> col_spesialisasi = new TableColumn<>("Spesialisasi Staff");

        col_kodestaff.setCellValueFactory(v-> v.getValue().kodeStaffProperty());
        col_nama.setCellValueFactory(v-> v.getValue().namaStaffProperty());
        col_jeniskelamin.setCellValueFactory(v-> v.getValue().jenisKelaminStaffProperty());
        col_tempatlahir.setCellValueFactory(v-> v.getValue().tempatLahirStaffProperty());
        col_tanggallahir.setCellValueFactory(v-> v.getValue().tanggalLahirStaffProperty());
        col_alamat.setCellValueFactory(v-> v.getValue().alamatStaffProperty());
        col_nomorhp.setCellValueFactory(v-> v.getValue().nomorHpStaffProperty());
        col_email.setCellValueFactory(v-> v.getValue().emailStaffProperty());
        col_spesialisasi.setCellValueFactory(v-> v.getValue().spesialisasiStaffProperty());

        ArrayList<TableColumn<Staff, String>> col = new ArrayList<>();
        col.add(col_kodestaff);
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

    public HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.getStyleClass().add("button-box");
    
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("edit-button");
    
        editButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Staff> tb = (TableView<Staff>) tableHBox.getChildren().get(0);
            Staff selectedStaff = tb.getSelectionModel().getSelectedItem();
            if (selectedStaff != null) {
                Stage halamanEdit = new Stage();
                halamanEdit.initModality(Modality.APPLICATION_MODAL);
                halamanEdit.setTitle("Edit Staff");
                halamanEdit.setWidth(1280);
                halamanEdit.setHeight(720);

                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formEdit = new VBox();
                Label formTitle = new Label("Edit Staff");
                formTitle.getStyleClass().add("form-title");
                formEdit.getChildren().add(formTitle);

                StackPane formTitleWrapper = new StackPane();
                formTitleWrapper.getChildren().add(formTitle);
                formTitleWrapper.setPrefWidth(200);
                StackPane.setAlignment(formTitle, Pos.CENTER);
                formEdit.getChildren().add(formTitleWrapper);

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
                namaStaffField.setText(selectedStaff.getNamaStaff());
                GridPane.setConstraints(namaStaffLabel, 0, 0);
                GridPane.setConstraints(namaStaffField, 1, 0);
                row1.getChildren().addAll(namaStaffLabel, namaStaffField);
                formEdit.getChildren().add(row1);

                // row 2
                GridPane row2 = new GridPane();
                row2.getStyleClass().add("form-grid");
                row2.setHgap(10);
                row2.setVgap(5);
                Label jenisKelamin = new Label("Jenis Kelamin:");
                jenisKelamin.getStyleClass().add("form-label");
                jenisKelamin.setPrefSize(200, 20);
                ComboBox<String> jenisKelaminBox = new ComboBox<>();
                jenisKelaminBox.getItems().addAll("L", "P");
                jenisKelaminBox.setPrefSize(200, 20);
                jenisKelaminBox.setValue(selectedStaff.getJenisKelaminStaffString());
                GridPane.setConstraints(jenisKelamin, 0, 0);
                GridPane.setConstraints(jenisKelaminBox, 1, 0);
                row2.getChildren().addAll(jenisKelamin, jenisKelaminBox);
                formEdit.getChildren().add(row2);

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
                tempatLahirField.setText(selectedStaff.getTempatLahirStaffString());
                GridPane.setConstraints(tempatLahirLabel, 0, 0);
                GridPane.setConstraints(tempatLahirField, 1, 0);
                row3.getChildren().addAll(tempatLahirLabel, tempatLahirField);
                formEdit.getChildren().add(row3);

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
                tanggalLahirField.setValue(LocalDate.parse(selectedStaff.getTanggalLahir()));
                GridPane.setConstraints(tanggalLahirLabel, 0, 0);
                GridPane.setConstraints(tanggalLahirField, 1, 0);
                row4.getChildren().addAll(tanggalLahirLabel, tanggalLahirField);
                formEdit.getChildren().add(row4);

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
                alamatField.setText(selectedStaff.getAlamatStaffString());
                GridPane.setConstraints(alamatLabel, 0, 0);
                GridPane.setConstraints(alamatField, 1, 0);
                row5.getChildren().addAll(alamatLabel, alamatField);
                formEdit.getChildren().add(row5);

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
                nomorTelfonField.setText(selectedStaff.getNomorHpStaff());
                GridPane.setConstraints(nomorTelfonLabel, 0, 0);
                GridPane.setConstraints(nomorTelfonField, 1, 0);
                row6.getChildren().addAll(nomorTelfonLabel, nomorTelfonField);
                formEdit.getChildren().add(row6);

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
                emailField.setText(selectedStaff.getEmailStaff());
                GridPane.setConstraints(emailLabel, 0, 0);
                GridPane.setConstraints(emailField, 1, 0);
                row7.getChildren().addAll(emailLabel, emailField);
                formEdit.getChildren().add(row7);

                // row 8
                GridPane row8 = new GridPane();
                row8.getStyleClass().add("form-grid");
                row8.setHgap(10);
                row8.setVgap(5);
                Label spesialisasiLabel = new Label("Spesialisasi Staff:");
                spesialisasiLabel.getStyleClass().add("form-label");
                spesialisasiLabel.setPrefSize(200, 20);
                TextField spesialisasiField = new TextField();
                spesialisasiField.setPrefSize(200, 20);
                spesialisasiField.setText(selectedStaff.getSpesialisasiStaff());
                GridPane.setConstraints(spesialisasiLabel, 0, 0);
                GridPane.setConstraints(spesialisasiField, 1, 0);
                row8.getChildren().addAll(spesialisasiLabel, spesialisasiField);
                formEdit.getChildren().add(row8);

                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(event -> {
                    selectedStaff.setNamaStaff(namaStaffField.getText());
                    selectedStaff.setJenisKelaminStaff(jenisKelaminBox.getValue());
                    selectedStaff.setTempatLahirStaff(tempatLahirField.getText());
                    selectedStaff.setTanggalLahirStaff(tanggalLahirField.getValue());
                    selectedStaff.setAlamatStaff(alamatField.getText());
                    selectedStaff.setNomorHpStaff(nomorTelfonField.getText());
                    selectedStaff.setEmailStaff(emailField.getText());
                    selectedStaff.setSpesialisasiStaff(spesialisasiField.getText());

                    if (selectedStaff.update()) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil diupdate!");
                        a.show();
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Data gagal diupdate!");
                        a.show();
                    }
                    halamanEdit.close();
                    TableStaff stafff = new TableStaff();
                    rootPane.getScene().setRoot(stafff.getRootPane());
                });

                HBox buttonWrapper = new HBox();
                buttonWrapper.getStyleClass().add("button-wrap");
                buttonWrapper.setAlignment(Pos.CENTER);
                buttonWrapper.getChildren().add(saveButton);

                formEdit.getChildren().add(buttonWrapper);

                halamanBaru.getChildren().add(formEdit);

                Scene scene = new Scene(halamanBaru);
                halamanEdit.setScene(scene);
                halamanEdit.show();
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setContentText("Pilih staff yang ingin diedit!");
                a.show();
            }
        }); 

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Staff> tb = (TableView<Staff>) tableHBox.getChildren().get(0);
            Staff selectedStaff = tb.getSelectionModel().getSelectedItem();
            if (selectedStaff != null) {
                if (selectedStaff.delete()) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Data berhasil dihapus!");
                    a.show();
                    TableStaff stafff = new TableStaff();
                    rootPane.getScene().setRoot(stafff.getRootPane());
                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Data gagal dihapus!");
                    a.show();
                }
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setContentText("Pilih staff yang ingin dihapus!");
                a.show();
            }
        });


        buttonBox.getChildren().addAll(editButton, deleteButton);
    
        return buttonBox;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}