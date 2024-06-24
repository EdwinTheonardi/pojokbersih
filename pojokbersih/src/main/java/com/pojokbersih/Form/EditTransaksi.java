package com.pojokbersih.Form;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Model.Customer;
import com.pojokbersih.Model.DetailTransaksi;
import com.pojokbersih.Model.Produk;
import com.pojokbersih.Model.Staff;
import com.pojokbersih.Model.Transaksi;
import com.pojokbersih.Table.TableTransaksi;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditTransaksi {
    private Stage halamanEdit;
    private final BorderPane rootPane;

    private final List<Customer> listCustomer = new ArrayList<Customer>();
    private final List<Staff> listPIC = new ArrayList<Staff>();
    private final List<Produk> listProduk = new ArrayList<Produk>();

    private List<DetailTransaksi> tempListDetailTransaksi = new ArrayList<DetailTransaksi>();
    private List<DetailTransaksi> tempListDetailTransaksiAdded = new ArrayList<DetailTransaksi>();
    private List<DetailTransaksi> tempListDetailTransaksiDeleted = new ArrayList<DetailTransaksi>();
    private double tempTotalHarga;

    private Label totalBiayaLabel = new Label();
    private Transaksi t;

    // Input transaksi //
    private DatePicker tanggalTransaksiField = new DatePicker();
    private DatePicker tanggalPengerjaanField = new DatePicker();
    private ComboBox<String> customerBox = new ComboBox<>();
    private TextField noTelpField = new TextField();
    private TextArea alamatField = new TextArea();
    private ComboBox<String> picBox = new ComboBox<>();
    private ComboBox<String> statusBox = new ComboBox<>();
    private TextField noTransaksiField = new TextField();

    private TableView<DetailTransaksi> tb = new TableView<DetailTransaksi>();
    private int dt_selected;

    private TableTransaksi tbTransaksi;

    public EditTransaksi(Transaksi transaksi, TableTransaksi tabelTransaksi){
        tbTransaksi = tabelTransaksi;
        fetchData();

        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");

        halamanEdit = new Stage();
        halamanEdit.initModality(Modality.APPLICATION_MODAL);
        halamanEdit.setTitle("Edit Transaksi");
        halamanEdit.setWidth(1280);
        halamanEdit.setHeight(720);

        rootPane.setTop(formTitle());
        rootPane.setCenter(formTransaksi());
        rootPane.setBottom(formDetailTransaksi());

        Scene scene = new Scene(rootPane, 1280, 720);
        scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
        halamanEdit.setTitle("Pojok Bersih");
        halamanEdit.setScene(scene);

        rootPane.getStyleClass().add("halaman-baru");
        halamanEdit.setScene(scene);

        // ========== Fetch ========== //

        tanggalTransaksiField.setValue(LocalDate.parse(transaksi.getTanggalTransaksi()));
        tanggalPengerjaanField.setValue(LocalDate.parse(transaksi.getTanggalPengerjaan()));

        for(Customer customer : listCustomer){
            if(transaksi.getIdCustomer().equals(customer.getIdCustomer())){
                customerBox.setValue(customer.getNamaCustomer() + " - " + customer.getIdCustomer());
                noTelpField.textProperty().setValue(customer.getNomorHpCustomer());
                alamatField.textProperty().setValue(customer.getAlamatCustomer());
            }
        }

        for(Staff pic : listPIC){
            if(transaksi.getPic().equals(pic.getStaff())){
                picBox.setValue(pic.getNamaStaff() + " - " + pic.getStaff());
            }
        }

        statusBox.setValue(transaksi.getStatus());
        noTransaksiField.textProperty().setValue("T-" + transaksi.getIdTransaksi());
        t = transaksi;

        DB db = new DB();
        String sqlDetailTransaksi = "SELECT * FROM detailtransaksi WHERE id_transaksi = " + transaksi.getIdTransaksi();
        ArrayList<Object> resultDetailTransaksi = db.runQuery(sqlDetailTransaksi);
        for (Object row : resultDetailTransaksi) {
            DetailTransaksi dt = new DetailTransaksi(row);
            tempListDetailTransaksi.add(dt);
        }

        refreshTable();

        // =========================== //
    }

    // Fetch data untuk ComboBox
    public void fetchData(){
        DB db = new DB();
        String sqlCustomer = "SELECT * FROM customer";
        ArrayList<Object> resultCustomer = db.runQuery(sqlCustomer);
        for (Object row : resultCustomer) {
            Customer customer = new Customer(row);
            listCustomer.add(customer);
        }
        
        String sqlStaff = "SELECT * FROM staff";
        ArrayList<Object> resultStaff = db.runQuery(sqlStaff);
        for (Object row : resultStaff) {
            Staff staff = new Staff(row);
            listPIC.add(staff);
        }

        String sqlProduk = "SELECT * FROM produk";
        ArrayList<Object> resultProduk = db.runQuery(sqlProduk);
        for (Object row : resultProduk) {
            Produk produk = new Produk(row);
            listProduk.add(produk);
        }
    }

    // Judul table
    public HBox formTitle(){
        HBox formTambah = new HBox();
        Label formTitle = new Label("Edit Transaksi");
        formTitle.getStyleClass().add("form-title");
        formTambah.getChildren().add(formTitle);

        HBox.setHgrow(formTambah, Priority.ALWAYS);
        formTambah.setAlignment(Pos.CENTER);

        return formTambah;
    }

    // Bagian form transaksi
    public GridPane formTransaksi(){
        GridPane formWrapper = new GridPane();
        formWrapper.setPadding(new Insets(20, 50, 20, 50));
        formWrapper.setHgap(30);
        
        // ========== Input ========== //

        // Nomor Transaksi
        Label noTransaksiLabel = new Label("No Transaksi : ");
        noTransaksiLabel.getStyleClass().add("form-label");
        noTransaksiLabel.setPrefSize(200, 20);
        noTransaksiField.setDisable(true);
        noTransaksiField.setPrefSize(200, 20);

        // Tanggal Transaksi
        Label tanggalTransaksiLabel = new Label("Tgl Transaksi : ");
        tanggalTransaksiLabel.getStyleClass().add("form-label");
        tanggalTransaksiLabel.setPrefSize(200, 20);
        tanggalTransaksiField.setPrefSize(200, 20);

        // Tanggal Pengerjaan
        Label tanggalPengerjaanLabel = new Label("Tgl Pengerjaan : ");
        tanggalPengerjaanLabel.getStyleClass().add("form-label");
        tanggalPengerjaanLabel.setPrefSize(200, 20);
        tanggalPengerjaanField.setPrefSize(200, 20);

        // Customer
        Label customerLabel = new Label("Nama Customer : ");
        customerLabel.getStyleClass().add("form-label");
        customerLabel.setPrefSize(200, 20);
        customerBox.setPrefSize(200, 20);

        // Nomor Telepon
        Label noTelpLabel = new Label("Nomor Telepon : ");
        noTelpLabel.getStyleClass().add("form-label");
        noTelpLabel.setPrefSize(200, 20);
        noTelpField.setDisable(true);
        noTelpField.setPrefSize(200, 20);

        // Alamat Customer
        Label alamatLabel = new Label("Alamat Customer : ");
        alamatLabel.getStyleClass().add("form-label");
        alamatLabel.setPrefSize(200, 20);
        alamatField.setDisable(true);
        alamatField.setPrefSize(200, 80);

        // PIC
        Label picLabel = new Label("Nama PIC : ");
        picLabel.getStyleClass().add("form-label");
        picLabel.setPrefSize(200, 20);
        picBox.setPrefSize(200, 20);

        // Status
        Label statusLabel = new Label("Status : ");
        statusLabel.getStyleClass().add("form-label");
        statusLabel.setPrefSize(200, 20);
        statusBox.setPrefSize(200, 20);

        // =========================== //

        // ========== Content ========== //

        statusBox.getItems().addAll("Not Contacted", "Deal", "On Going", "Done");

        for (Customer customer : listCustomer) {
            customerBox.getItems().add(customer.getNamaCustomer() + " - " + customer.getIdCustomer());
        }

        for (Staff staff : listPIC) {
            picBox.getItems().add(staff.getNamaStaff() + " - " + staff.getStaff());
        }

        // ============================= //

        // ========== Action ========== //

        customerBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
            String data[] = newVal.split(" - ");

            for(Customer customer : listCustomer){
                if(data[1].equals(customer.getIdCustomer())){
                    noTelpField.textProperty().setValue(customer.getNomorHpCustomer());
                    alamatField.textProperty().setValue(customer.getAlamatCustomer());
                }
            }
        });

        // ============================ //

        // ========== Alignment ========== //

        GridPane formKiri = new GridPane();
        GridPane formTengah = new GridPane();
        GridPane formKanan = new GridPane();
        formKiri.setVgap(10);
        formTengah.setVgap(10);
        formKanan.setVgap(10);

        formKiri.add(noTransaksiLabel, 0, 0);
        formKiri.add(noTransaksiField, 1, 0);

        formKiri.add(tanggalTransaksiLabel, 0, 1);
        formKiri.add(tanggalTransaksiField, 1, 1);

        formKiri.add(tanggalPengerjaanLabel, 0, 2);
        formKiri.add(tanggalPengerjaanField, 1, 2);

        formTengah.add(customerLabel, 0, 0);
        formTengah.add(customerBox, 1, 0);

        formTengah.add(picLabel, 0, 1);
        formTengah.add(picBox, 1, 1);

        formTengah.add(statusLabel, 0, 2);
        formTengah.add(statusBox, 1, 2);

        formKanan.add(noTelpLabel, 0, 0);
        formKanan.add(noTelpField, 1, 0);
        
        formKanan.add(alamatLabel, 0, 1);
        formKanan.add(alamatField, 1, 1);

        formWrapper.add(formKiri, 0, 0);
        formWrapper.add(formTengah, 1, 0);
        formWrapper.add(formKanan, 2, 0);

        return formWrapper;
    }
    
    // Bagian form detail transaksi
    public VBox formDetailTransaksi(){
        VBox formWrapper = new VBox();
        formWrapper.setPadding(new Insets(20, 50, 20, 50));
        HBox.setHgrow(formWrapper, Priority.ALWAYS);
        VBox.setVgrow(formWrapper, Priority.ALWAYS);

        // ========== Input ========== //

        // Produk
        Label produkLabel = new Label("Nama Produk : ");
        produkLabel.getStyleClass().add("form-label");
        produkLabel.setPrefSize(200, 20);
        ComboBox<String> produkBox = new ComboBox<>();
        produkBox.setPrefSize(200, 20);

        // Harga jasa
        Label hargaJasaLabel = new Label("Harga Jasa : ");
        hargaJasaLabel.getStyleClass().add("form-label");
        hargaJasaLabel.setPrefSize(200, 20);
        TextField hargaJasaField = new TextField();
        hargaJasaField.setPrefSize(200, 20);

        // Staff
        Label staffLabel = new Label("Nama Staff : ");
        staffLabel.getStyleClass().add("form-label");
        staffLabel.setPrefSize(200, 20);
        ComboBox<String> staffBox = new ComboBox<>();
        staffBox.setPrefSize(200, 20);

        // Total Biaya
        Label totalLabel = new Label("Total Biaya : ");
        totalLabel.getStyleClass().add("form-label");
        totalLabel.setPrefSize(200, 20);

        totalBiayaLabel.getStyleClass().add("form-label");
        totalBiayaLabel.setPrefSize(200, 20);
        totalBiayaLabel.textProperty().setValue(currency());

        // Button Simpan, Hapus
        Button btnSimpanProduk = new Button("Tambah ke transaksi");
        Button btnHapusProduk = new Button("Hapus");
        Button btnSimpanTransaksi = new Button("Update Transaksi");

        btnSimpanProduk.getStyleClass().add("add-button");
        btnHapusProduk.getStyleClass().add("add-button");
        btnSimpanTransaksi.getStyleClass().add("add-button");

        // =========================== //

        // ========== Content ========== //

        staffBox.setValue("Pilih staff");
        produkBox.setValue("Pilih produk");

        for (Produk produk : listProduk) {
            produkBox.getItems().add(produk.getNamaProduk() + " - " + produk.getProduk());
        }

        for (Staff staff : listPIC) {
            staffBox.getItems().add(staff.getNamaStaff() + " - " + staff.getStaff());
        }

        // ============================= //

        // ========== Action ========== //

        btnSimpanProduk.setOnAction(e -> {
            DetailTransaksi dt = new DetailTransaksi();
            String idProduk = produkBox.getValue().split(" - ")[1];
            String idStaff = staffBox.getValue().split(" - ")[1];

            dt.setIdTransaksi(t.getIdTransaksi());
            dt.setIdProduk(idProduk);
            dt.setIdStaff(idStaff);
            dt.setHarga(hargaJasaField.textProperty().getValue());
            dt.crudState = "ADD";

            tempListDetailTransaksi.add(dt);
            tempListDetailTransaksiAdded.add(dt);

            refreshTable();
        });

        btnHapusProduk.setOnAction(e -> {
            DetailTransaksi dt = tb.getSelectionModel().getSelectedItem();

            tempListDetailTransaksi.remove(dt_selected);
            if(dt.crudState == "ADD"){
                tempListDetailTransaksiAdded.remove(dt);
            }
            else{
                tempListDetailTransaksiDeleted.add(dt);
            }

            refreshTable();
        });

        btnSimpanTransaksi.setOnAction(e -> {
            for(DetailTransaksi dt : tempListDetailTransaksiAdded){
                dt.create();
            }

            for(DetailTransaksi dt : tempListDetailTransaksiDeleted){
                dt.delete();
            }

            t.setTanggalTransaksi(tanggalTransaksiField.getValue());
            t.setTanggalPengerjaan(tanggalPengerjaanField.getValue());
            t.setIdCustomer(customerBox.getValue().split(" - ")[1]);
            t.setPic(picBox.getValue().split(" - ")[1]);
            t.setStatus(statusBox.getValue());
            t.setBiayaJasa(String.valueOf(tempTotalHarga));

            t.update();
            tbTransaksi.refreshTable();

            halamanEdit.close();
        });

        // ============================ //

        // ========== Alignment ========== //

        HBox tabTombol = new HBox();
        HBox inputWrapper = new HBox();
        GridPane formKiri = new GridPane();
        GridPane formKanan = new GridPane();
        HBox formBawah = new HBox();
        HBox totalBiayaWrapper = new HBox();
        Region spacer = new Region();

        formKanan.setPadding(new Insets(0, 0, 0, 20));
        formBawah.setAlignment(Pos.CENTER);
        formBawah.setPadding(new Insets(20, 0, 0, 0));
        HBox.setHgrow(spacer, Priority.ALWAYS);
        inputWrapper.setPadding(new Insets(0, 0, 20, 0));
        tabTombol.setSpacing(10);
        totalBiayaWrapper.setSpacing(10);

        tabTombol.getChildren().addAll(btnSimpanProduk, btnHapusProduk);

        formKiri.setVgap(10);
        formKanan.setVgap(10);

        formKiri.add(produkLabel, 0, 0);
        formKiri.add(produkBox, 1, 0);
        
        formKiri.add(staffLabel, 0, 1);
        formKiri.add(staffBox, 1, 1);

        formKanan.add(hargaJasaLabel, 0, 0);
        formKanan.add(hargaJasaField, 1, 0);

        formKanan.add(tabTombol, 0, 1);

        totalBiayaWrapper.getChildren().addAll(totalLabel, totalBiayaLabel);
        formBawah.getChildren().addAll(btnSimpanTransaksi, spacer, totalBiayaWrapper);

        tb = createTable();

        inputWrapper.getChildren().addAll(formKiri, formKanan);
        formWrapper.getChildren().addAll(inputWrapper, tb, formBawah);

        return formWrapper;
    }

    public void refreshTable() {
        tempTotalHarga = 0;
        tb.getItems().clear();

        for(DetailTransaksi dt : tempListDetailTransaksi){
            tempTotalHarga += Double.parseDouble(dt.getHarga());
            tb.getItems().add(dt);
        }

        totalBiayaLabel.textProperty().setValue(currency());
    }

    // Table produk dalam transaksi ini
    public TableView<DetailTransaksi> createTable() {
        TableColumn<DetailTransaksi, Number> col_no = new TableColumn<>("No.");
        TableColumn<DetailTransaksi, String> col_idproduk = new TableColumn<>("Nama Produk");
        TableColumn<DetailTransaksi, String> col_harga = new TableColumn<>("Biaya Jasa");
        TableColumn<DetailTransaksi, String> col_idstaff = new TableColumn<>("Nama Staff");

        col_no.setCellValueFactory(v -> new ReadOnlyObjectWrapper<Number>(tb.getItems().indexOf(v.getValue()) + 1));
        col_idproduk.setCellValueFactory(v -> getProduk(v.getValue().idProdukProperty().getValue()).namaProdukProperty());
        col_harga.setCellValueFactory(v -> v.getValue().hargaProperty());
        col_idstaff.setCellValueFactory(v -> getStaff(v.getValue().idStaffProperty().getValue()).namaStaffProperty());

        ArrayList<TableColumn<DetailTransaksi, ?>> col = new ArrayList<>();
        col.add(col_no);
        col.add(col_idproduk);
        col.add(col_harga);
        col.add(col_idstaff);
        
        for(int i = 0; i< col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<DetailTransaksi> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    dt_selected = row.getIndex();
                }
            });

            return row;
        });

        return tb;
    }

    private Produk getProduk(String id){
        for(Produk produk : listProduk){
            if(produk.getProduk().equals(id)){
                return produk;
            }
        }

        return new Produk();
    }

    private Staff getStaff(String id){
        for(Staff staff : listPIC){
            if(staff.getStaff().equals(id)){
                return staff;
            }
        }

        return new Staff();
    }

    public String currency() {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(tempTotalHarga);
    }

    public Stage getStage(){
        return halamanEdit;
    }
}
