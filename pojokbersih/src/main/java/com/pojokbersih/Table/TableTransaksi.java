package com.pojokbersih.Table;

import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Home;
import com.pojokbersih.Model.Transaksi;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableTransaksi {
    private final BorderPane rootPane;

    public TableTransaksi() {
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

    private List<Transaksi> listTransaksi;
    private TableView<Transaksi> tb = new TableView<Transaksi>();

    @SuppressWarnings("unchecked")
    public TableView<Transaksi> createTable() {
        TableColumn<Transaksi, String> col_idtransaksi = new TableColumn<>("Kode Transaksi");
        TableColumn<Transaksi, String> col_idcustomer = new TableColumn<>("Kode Customer");
        TableColumn<Transaksi, String> col_idstaff = new TableColumn<>("Kode Staff");
        TableColumn<Transaksi, String> col_pic = new TableColumn<>("PIC");
        TableColumn<Transaksi, String> col_idproduk = new TableColumn<>("Kode Produk");
        TableColumn<Transaksi, String> col_tanggaltransaksi = new TableColumn<>("Tanggal Transaksi");
        TableColumn<Transaksi, String> col_tanggalpengerjaan = new TableColumn<>("Tanggal Pengerjaan");
        TableColumn<Transaksi, String> col_biayajasa = new TableColumn<>("Biaya Jasa");
        TableColumn<Transaksi, String> col_status = new TableColumn<>("Status");

        col_idtransaksi.setCellValueFactory(v -> v.getValue().idTransaksiProperty());
        col_idcustomer.setCellValueFactory(v -> v.getValue().idCustomerProperty());
        col_idstaff.setCellValueFactory(v -> v.getValue().idStaffProperty());
        col_pic.setCellValueFactory(v -> v.getValue().picProperty());
        col_idproduk.setCellValueFactory(v -> v.getValue().idProdukProperty());
        col_tanggaltransaksi.setCellValueFactory(v -> v.getValue().tanggalTransaksiProperty());
        col_tanggalpengerjaan.setCellValueFactory(v -> v.getValue().tanggalPengerjaanProperty());
        col_biayajasa.setCellValueFactory(v -> v.getValue().biayaJasaProperty());
        col_status.setCellValueFactory(v -> v.getValue().statusProperty());

        ArrayList<TableColumn<Transaksi, String>> col = new ArrayList<>();
        col.add(col_idtransaksi);
        col.add(col_idcustomer);
        col.add(col_idstaff);
        col.add(col_pic);
        col.add(col_idproduk);
        col.add(col_tanggaltransaksi);
        col.add(col_tanggalpengerjaan);
        col.add(col_biayajasa);
        col.add(col_status);

        for(int i = 0; i< col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        return tb;
    }

    public HBox getTable() {
        HBox table = new HBox();
        TableView<Transaksi> tb = createTable();

        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Transaksi> list_transaksi = getData();

        for(int y = 0; y < list_transaksi.size(); y++) {
            tb.getItems().add(list_transaksi.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Transaksi> getData() {
        DB db = new DB();
        String query_admin = "SELECT id_transaksi, c.nama_customer, s1.nama_staff, s2.nama_staff, p.nama_produk, tgl_transaksi, tgl_pengerjaan, biaya_jasa, status FROM customer AS c INNER JOIN transaksi AS t on c.id_customer = t.id_customer INNER JOIN staff AS s1 on s1.id_staff = t.id_staff INNER JOIN staff AS s2 on s2.id_staff = t.pic INNER JOIN produk AS p on p.id_produk = t.id_produk;";

        List<Object> rs = db.runQuery(query_admin);
        listTransaksi = new ArrayList<Transaksi>();

        for(int i = 0; i < rs.size(); i++) {
            Transaksi transaksi = new Transaksi(rs.get(i));

            listTransaksi.add(transaksi);
        }
        
        return listTransaksi;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
