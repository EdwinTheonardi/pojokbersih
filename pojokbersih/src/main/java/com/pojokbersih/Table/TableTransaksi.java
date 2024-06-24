package com.pojokbersih.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Form.EditTransaksi;
import com.pojokbersih.Form.TambahTransaksi;
import com.pojokbersih.Model.Customer;
import com.pojokbersih.Model.Produk;
import com.pojokbersih.Model.Staff;
import com.pojokbersih.Model.Transaksi;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.util.converter.LocalDateStringConverter;

public class TableTransaksi {
    private final BorderPane rootPane;
    private final List<Customer> listCustomer = new ArrayList<Customer>();
    private final List<Staff> listPIC = new ArrayList<Staff>();

    public TableTransaksi() {
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");

        getData();

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
            TableHome homee = new TableHome();
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
        
        // Search Nama Customer & Tanggal
        Label searchNamaCustLabel = new Label("Nama Customer:");
        searchNamaCustLabel.getStyleClass().add("form-label");
        searchNamaCustLabel.setPrefSize(200, 35);
        TextField searchNamaCustField = new TextField();
        searchNamaCustField.setPrefSize(200, 35);
        GridPane.setConstraints(searchNamaCustLabel, 0, 0);
        GridPane.setConstraints(searchNamaCustField, 1, 0);
        formSearch.getChildren().addAll(searchNamaCustLabel, searchNamaCustField);

        Label searchTanggalTransaksiFromLabel = new Label("Tanggal Transaksi Awal");
        searchTanggalTransaksiFromLabel.getStyleClass().add("form-label");
        searchTanggalTransaksiFromLabel.setPrefSize(200, 35);
        DatePicker searchTanggalTransaksiFromField = new DatePicker();
        searchTanggalTransaksiFromField.setPrefSize(200,35);
        GridPane.setConstraints(searchTanggalTransaksiFromLabel, 0, 1);
        GridPane.setConstraints(searchTanggalTransaksiFromField, 1, 1);
        formSearch.getChildren().addAll(searchTanggalTransaksiFromLabel, searchTanggalTransaksiFromField);

        Label searchTanggalTransaksiToLabel = new Label("Tanggal Transaksi Akhir");
        searchTanggalTransaksiToLabel.getStyleClass().add("form-label");
        searchTanggalTransaksiToLabel.setPrefSize(200, 35);
        DatePicker searchTanggalTransaksiToField = new DatePicker();
        searchTanggalTransaksiToField.setPrefSize(200,35);
        GridPane.setConstraints(searchTanggalTransaksiToLabel, 2, 1);
        GridPane.setConstraints(searchTanggalTransaksiToField, 3, 1);
        formSearch.getChildren().addAll(searchTanggalTransaksiToLabel, searchTanggalTransaksiToField);

        Button searchButton = new Button();
        searchButton.setText("Cari");
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(e -> {
            String searchNamaCustQuery = searchNamaCustField.getText().toLowerCase();
            LocalDate searchTanggalTransaksiFromQuery = searchTanggalTransaksiFromField.getValue();
            LocalDate searchTanggalTransaksiToQuery = searchTanggalTransaksiToField.getValue();
            FilteredList<Transaksi> filteredTransaksi = new FilteredList<>(FXCollections.observableArrayList(listTransaksi), p -> true);
            filteredTransaksi.setPredicate(p -> {
                Customer customer = getCustomer(p.getIdCustomer());
                String tanggalTransaksiString = p.getTanggalTransaksi();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate tanggalTransaksi = LocalDate.parse(tanggalTransaksiString, formatter);
                return (searchNamaCustQuery.isEmpty() || customer.getNamaCustomer().toLowerCase().contains(searchNamaCustQuery)) &&
               (searchTanggalTransaksiFromQuery == null || tanggalTransaksi.isEqual(searchTanggalTransaksiFromQuery) || tanggalTransaksi.isAfter(searchTanggalTransaksiFromQuery)) &&
               (searchTanggalTransaksiToQuery == null || tanggalTransaksi.isEqual(searchTanggalTransaksiToQuery) || tanggalTransaksi.isBefore(searchTanggalTransaksiToQuery));
        });

            tb.setItems(filteredTransaksi);
        });
        
        Button viewAllButton = new Button("Lihat Semua");
        viewAllButton.getStyleClass().add("search-button");
        viewAllButton.setOnAction(e -> {
            tb.setItems(FXCollections.observableArrayList(new ArrayList<>(listTransaksi)));
            searchNamaCustField.clear();
            searchTanggalTransaksiFromField.setValue(null);
            searchTanggalTransaksiToField.setValue(null);
        });
        
        Button addButton = new Button();
        addButton.setText("Tambah");
        addButton.getStyleClass().add("add-button");

        addButton.setOnAction(event -> {
            TambahTransaksi formTambah = new TambahTransaksi(this);
            formTambah.getStage().show();
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

        Label labelTabel = new Label("Tabel Transaksi");
        labelTabel.getStyleClass().add("label-tabel-transaksi");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    private List<Transaksi> listTransaksi = new ArrayList<Transaksi>();
    private TableView<Transaksi> tb = new TableView<Transaksi>();

    @SuppressWarnings("unchecked")
    public TableView<Transaksi> createTable() {

        TableColumn<Transaksi, String> col_kodetransaksi = new TableColumn<>("Kode Transaksi");
        TableColumn<Transaksi, String> col_idcustomer = new TableColumn<>("Nama Customer");
        TableColumn<Transaksi, String> col_pic = new TableColumn<>("PIC");
        TableColumn<Transaksi, String> col_tanggaltransaksi = new TableColumn<>("Tanggal Transaksi");
        TableColumn<Transaksi, String> col_tanggalpengerjaan = new TableColumn<>("Tanggal Pengerjaan");
        TableColumn<Transaksi, String> col_biayajasa = new TableColumn<>("Biaya Jasa");
        TableColumn<Transaksi, String> col_status = new TableColumn<>("Status");

        col_kodetransaksi.setCellValueFactory(v -> v.getValue().idTransaksiProperty());
        col_idcustomer.setCellValueFactory(v -> getCustomer(v.getValue().idCustomerProperty().getValue()).namaCustomerProperty());
        col_pic.setCellValueFactory(v -> getStaff(v.getValue().picProperty().getValue()).namaStaffProperty());
        col_tanggaltransaksi.setCellValueFactory(v -> v.getValue().tanggalTransaksiProperty());
        col_tanggalpengerjaan.setCellValueFactory(v -> v.getValue().tanggalPengerjaanProperty());
        col_biayajasa.setCellValueFactory(v -> v.getValue().biayaJasaProperty());
        col_status.setCellValueFactory(v -> v.getValue().statusProperty());

        ArrayList<TableColumn<Transaksi, String>> col = new ArrayList<>();
        col.add(col_kodetransaksi);
        col.add(col_idcustomer);
        col.add(col_pic);
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
        tb.getItems().clear();
        tb.getColumns().clear();

        tb = createTable();

        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        for(int y = 0; y < listTransaksi.size(); y++) {
            tb.getItems().add(listTransaksi.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData() {
        DB db = new DB();
        String query_admin = "SELECT * FROM transaksi";

        List<Object> rs = db.runQuery(query_admin);
        listTransaksi.clear();

        for(int i = 0; i < rs.size(); i++) {
            Transaksi transaksi = new Transaksi(rs.get(i));

            listTransaksi.add(transaksi);
        }
    }

    public HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(30));
        buttonBox.getStyleClass().add("button-box");
    
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("edit-button");

        editButton.setOnAction(e -> {
            Transaksi selectedTransaksi = tb.getSelectionModel().getSelectedItem();

            EditTransaksi formEdit = new EditTransaksi(selectedTransaksi, this);
            formEdit.getStage().show();
        });
    
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Transaksi> tb = (TableView<Transaksi>) tableHBox.getChildren().get(0);
            Transaksi selectedTransaksi = tb.getSelectionModel().getSelectedItem();
            if (selectedTransaksi != null) {
                if (selectedTransaksi.delete()) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Data berhasil dihapus!");
                    a.show();
                    TableTransaksi transaksii = new TableTransaksi();
                    rootPane.getScene().setRoot(transaksii.getRootPane());
                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Data gagal dihapus!");
                    a.show();
                }
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setContentText("Pilih transaksi yang ingin dihapus!");
                a.show();
            }
        });
        buttonBox.getChildren().addAll(editButton, deleteButton);
    
        return buttonBox;
    }

    private Customer getCustomer(String id){
        for(Customer customer : listCustomer){
            if(customer.getIdCustomer().equals(id)){
                return customer;
            }
        }

        return new Customer();
    }

    private Staff getStaff(String id){
        for(Staff staff : listPIC){
            if(staff.getIdStaff().equals(id)){
                return staff;
            }
        }

        return new Staff();
    }

    public void refreshTable(){
        getData();

        rootPane.setCenter(null);
        rootPane.setCenter(getTable());
    }

    public Pane getRootPane() {
        return rootPane;
    }
}