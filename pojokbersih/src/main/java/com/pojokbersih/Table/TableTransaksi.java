package com.pojokbersih.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Home;
import com.pojokbersih.Model.Customer;
import com.pojokbersih.Model.Produk;
import com.pojokbersih.Model.Staff;
import com.pojokbersih.Model.Transaksi;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
    private final List<Staff> listStaff = new ArrayList<Staff>();
    private final List<Produk> listProduk = new ArrayList<Produk>();
    

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

<<<<<<< Updated upstream
            Button filterButton = new Button();
            filterButton.setText("Filter");
            filterButton.getStyleClass().add("filter-button");
=======
            ComboBox<String> filter = new ComboBox<>();
            filter.getItems().addAll("Tanggal Terbaru", "Tanggal Terlama", "Not Contacted", "Deal", "On Going", "Done");
            filter.setPromptText("Filter");
            filter.getStyleClass().add("filter-button");            
>>>>>>> Stashed changes

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


                ComboBox<String> customerBox = new ComboBox<>();
                ComboBox<String> staffBox = new ComboBox<>();
                ComboBox<String> picBox = new ComboBox<>();
                TextField nomorTelfonField = new TextField();
                TextArea alamatCustomerField = new TextArea();

                customerBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
                    String data[] = newVal.split(" - ");

                    for(Customer customer : listCustomer){
                        if(data[1].equals(customer.getIdCustomer())){
                            nomorTelfonField.textProperty().setValue(customer.getNomorHpCustomer());
                            alamatCustomerField.textProperty().setValue(customer.getAlamatCustomer());
                        }
                    }
                });

                DB db = new DB();
                String sqlCustomer = "SELECT * FROM customer";
                ArrayList<Object> resultCustomer = db.runQuery(sqlCustomer);
                
                String sqlStaff = "SELECT * FROM staff";
                ArrayList<Object> resultStaff = db.runQuery(sqlStaff);

                String sqlProduk = "SELECT * FROM produk";
                ArrayList<Object> resultProduk = db.runQuery(sqlProduk);
                
                System.out.println("Result size: " + resultCustomer.size());
                
                for (Object row : resultCustomer) {
                    Customer customer = new Customer(row);
                    customerBox.getItems().add(customer.getNamaCustomer() + " - " + customer.getIdCustomer());

                    listCustomer.add(customer);
                }
                
                for (Object row : resultStaff) {
                    Staff staff = new Staff(row);
                    picBox.getItems().add(staff.getNamaStaff() + " - " + staff.getStaff());
                    staffBox.getItems().add(staff.getNamaStaff() + " - " + staff.getStaff());
                    listStaff.add(staff);
                }

                for (Object row : resultProduk) {
                    Produk produk = new Produk(row);
                    listProduk.add(produk);
                }

                System.out.println("ComboBox is visible: " + customerBox.isVisible());
                
                customerBox.setPrefSize(200, 20);

                GridPane.setConstraints(namaCustomerLabel, 0, 0);
                GridPane.setConstraints(customerBox, 1, 0);
                row3.getChildren().addAll(namaCustomerLabel, customerBox);
                formTambah.getChildren().add(row3);

                // row 4
                GridPane row4 = new GridPane();
                row4.getStyleClass().add("form-grid");
                row4.setHgap(10);
                row4.setVgap(5);
                Label nomorTelfonLabel = new Label("Nomor Telepon:");
                nomorTelfonLabel.getStyleClass().add("form-label");
                nomorTelfonLabel.setPrefSize(200, 20);
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
                
                
                System.out.println("ComboBox is visible: " + picBox.isVisible());
                picBox.setPrefSize(200, 20);

                GridPane.setConstraints(picLabel, 0, 0);
                GridPane.setConstraints(picBox, 1, 0);
                row6.getChildren().addAll(picLabel, picBox);
                formTambah.getChildren().add(row6);

                // row 7
                GridPane row7 = new GridPane();
                row7.getStyleClass().add("form-grid");
                row7.setHgap(10);
                row7.setVgap(5);
                Label staffLabel = new Label("Staff:");
                staffLabel.getStyleClass().add("form-label");
                staffLabel.setPrefSize(200, 20);

                
                
                System.out.println("Result size: " + resultStaff.size());
                                    
                
                System.out.println("ComboBox is visible: " + staffBox.isVisible());
                staffBox.setPrefSize(200, 20);

                GridPane.setConstraints(namaCustomerLabel, 0, 0);
                GridPane.setConstraints(staffBox, 1, 0);
                row7.getChildren().addAll(staffLabel, staffBox);
                formTambah.getChildren().add(row7);

                // row 8
                GridPane row8 = new GridPane();
                row8.getStyleClass().add("form-grid");
                row8.setHgap(10);
                row8.setVgap(5);
                Label produkLabel = new Label("Produk:");
                produkLabel.getStyleClass().add("form-label");
                produkLabel.setPrefSize(200, 20);

                VBox produkBox = new VBox();
                produkBox.setSpacing(5);

                final ObservableList<Produk> produkList = FXCollections.observableArrayList(listProduk);

                produkList.addListener(new ListChangeListener<Produk>() {
                    @Override
                    public void onChanged(Change<? extends Produk> c) {
                        produkBox.getChildren().clear();
                        for (Produk produk : produkList) {
                            CheckBox checkBox = new CheckBox(produk.getNamaProduk() + " - " + produk.getProduk());
                            checkBox.setPrefSize(200, 20);
                            produkBox.getChildren().add(checkBox);
                        }
                    }
                });

                for (Produk produk : produkList) {
                    CheckBox checkBox = new CheckBox(produk.getNamaProduk() + " - " + produk.getProduk());
                    checkBox.setPrefSize(200, 20);
                    produkBox.getChildren().add(checkBox);
                }

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
                row10.setVgap(5);
                Label statusLabel = new Label("Status:");
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
                    String dataCustomer[] = customerBox.getValue().split(" - ");
                    String dataStaff[] = staffBox.getValue().split(" - ");
                    String dataPic[] = picBox.getValue().split(" - ");
                    String selectedProduk = "";
                
                    for (javafx.scene.Node node : produkBox.getChildren()) {
                        if (node instanceof CheckBox) {
                            CheckBox checkBox = (CheckBox) node;
                            if (checkBox.isSelected()) {
                                selectedProduk += checkBox.getText() + ", ";
                            }
                        }
                    }
                    if (!selectedProduk.isEmpty()) {
                       selectedProduk = selectedProduk.substring(0, selectedProduk.length() - 2);
                    }
                
                    List<String> produkIds = new ArrayList<>();

                    for (String produk : selectedProduk.split(", ")) {
                        String produkParts[] = produk.split(" - ");
                        String produkId = produkParts[1];
                        produkIds.add(produkId);
                    }
                    
                    Transaksi transaksi = new Transaksi();
                    transaksi.setIdCustomer(dataCustomer[1]);
                    transaksi.setIdStaff(dataStaff[1]);
                    transaksi.setPic(dataPic[1]);
                    transaksi.setTanggalTransaksi(tanggalTransaksiField.getValue());
                    transaksi.setTanggalPengerjaan(tanggalPengerjaanField.getValue());
                    transaksi.setBiayaJasa(biayaJasaField.getText());
                    transaksi.setStatus(statusBox.getValue());
                    
                    if(transaksi.create(produkIds)) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil disimpan!");
                        a.show();
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Data gagal disimpan!");
                        a.show();
                    }
                    halamanTambah.close();
                    TableTransaksi transaksii = new TableTransaksi();
                    rootPane.getScene().setRoot(transaksii.getRootPane());
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
<<<<<<< Updated upstream
        TableColumn<Transaksi, String> col_idtransaksi = new TableColumn<>("Kode Transaksi");
        TableColumn<Transaksi, String> col_idcustomer = new TableColumn<>("Kode Customer");
        TableColumn<Transaksi, String> col_idstaff = new TableColumn<>("Kode Staff");
=======
        TableColumn<Transaksi, String> col_kodetransaksi = new TableColumn<>("Kode Transaksi");
        TableColumn<Transaksi, String> col_idcustomer = new TableColumn<>("Nama Customer");
        TableColumn<Transaksi, String> col_idstaff = new TableColumn<>("Nama Staff");
>>>>>>> Stashed changes
        TableColumn<Transaksi, String> col_pic = new TableColumn<>("PIC");
        TableColumn<Transaksi, String> col_idproduk = new TableColumn<>("Kode Produk");
        TableColumn<Transaksi, String> col_tanggaltransaksi = new TableColumn<>("Tanggal Transaksi");
        TableColumn<Transaksi, String> col_tanggalpengerjaan = new TableColumn<>("Tanggal Pengerjaan");
        TableColumn<Transaksi, String> col_biayajasa = new TableColumn<>("Biaya Jasa");
        TableColumn<Transaksi, String> col_status = new TableColumn<>("Status");

        col_kodetransaksi.setCellValueFactory(v -> v.getValue().kodeTransaksiProperty());
        col_idcustomer.setCellValueFactory(v -> v.getValue().idCustomerProperty());
        col_idstaff.setCellValueFactory(v -> v.getValue().idStaffProperty());
        col_pic.setCellValueFactory(v -> v.getValue().picProperty());
        col_idproduk.setCellValueFactory(v -> v.getValue().idProdukProperty());
        col_tanggaltransaksi.setCellValueFactory(v -> v.getValue().tanggalTransaksiProperty());
        col_tanggalpengerjaan.setCellValueFactory(v -> v.getValue().tanggalPengerjaanProperty());
        col_biayajasa.setCellValueFactory(v -> v.getValue().biayaJasaProperty());
        col_status.setCellValueFactory(v -> v.getValue().statusProperty());

        ArrayList<TableColumn<Transaksi, String>> col = new ArrayList<>();
        col.add(col_kodetransaksi);
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

<<<<<<< Updated upstream
=======
    public HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.getStyleClass().add("button-box");
    
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("edit-button");

        editButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Transaksi> tb = (TableView<Transaksi>) tableHBox.getChildren().get(0);
            Transaksi selectedTransaksi = tb.getSelectionModel().getSelectedItem();
            if (selectedTransaksi != null) {
                Stage halamanEdit = new Stage();
                halamanEdit.initModality(Modality.APPLICATION_MODAL);
                halamanEdit.setTitle("Edit Transaksi");
                halamanEdit.setWidth(1280);
                halamanEdit.setHeight(720);

                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formEdit = new VBox();
                Label formTitle = new Label("Edit Transaksi");
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
                Label tanggalTransaksiLabel = new Label("Tanggal Transaksi:");
                tanggalTransaksiLabel.getStyleClass().add("form-label");
                tanggalTransaksiLabel.setPrefSize(200, 20);
                DatePicker tanggalTransaksiField = new DatePicker();
                tanggalTransaksiField.setPrefSize(200, 20);
                tanggalTransaksiField.setValue(LocalDate.parse(selectedTransaksi.getTanggalTransaksi()));
                GridPane.setConstraints(tanggalTransaksiLabel, 0, 0);
                GridPane.setConstraints(tanggalTransaksiField, 1, 0);
                row1.getChildren().addAll(tanggalTransaksiLabel, tanggalTransaksiField);
                formEdit.getChildren().add(row1);

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
                tanggalPengerjaanField.setValue(LocalDate.parse(selectedTransaksi.getTanggalPengerjaan()));;
                GridPane.setConstraints(tanggalPengerjaanLabel, 0, 0);
                GridPane.setConstraints(tanggalPengerjaanField, 1, 0);
                row2.getChildren().addAll(tanggalPengerjaanLabel, tanggalPengerjaanField);
                formEdit.getChildren().add(row2);

                // row 3
                GridPane row3 = new GridPane();
                row3.getStyleClass().add("form-grid");
                row3.setHgap(10);
                row3.setVgap(5);
                Label namaCustomerLabel = new Label("Nama Customer:");
                namaCustomerLabel.getStyleClass().add("form-label");
                namaCustomerLabel.setPrefSize(200, 20);


                ComboBox<String> customerBox = new ComboBox<>();
                ComboBox<String> staffBox = new ComboBox<>();
                ComboBox<String> picBox = new ComboBox<>();
                ComboBox<String> produkBox = new ComboBox<>();
                TextField nomorTelfonField = new TextField();
                TextArea alamatCustomerField = new TextArea();

                customerBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
                    String data[] = newVal.split(" - ");

                    for(Customer customer : listCustomer){
                        if(data[1].equals(customer.getIdCustomer())){
                            nomorTelfonField.textProperty().setValue(customer.getNomorHpCustomer());
                            alamatCustomerField.textProperty().setValue(customer.getAlamatCustomer());
                        }
                    }
                });

                DB db = new DB();
                String sqlCustomer = "SELECT * FROM customer";
                ArrayList<Object> resultCustomer = db.runQuery(sqlCustomer);
                
                String sqlStaff = "SELECT * FROM staff";
                ArrayList<Object> resultStaff = db.runQuery(sqlStaff);

                String sqlProduk = "SELECT * FROM produk";
                ArrayList<Object> resultProduk = db.runQuery(sqlProduk);
                
                System.out.println("Result size: " + resultCustomer.size());
                
                for (Object row : resultCustomer) {
                    Customer customer = new Customer(row);
                    customerBox.getItems().add(customer.getNamaCustomer() + " - " + customer.getIdCustomer());

                    listCustomer.add(customer);
                }
                
                for (Object row : resultStaff) {
                    Staff staff = new Staff(row);
                    picBox.getItems().add(staff.getNamaStaff() + " - " + staff.getStaff());
                    staffBox.getItems().add(staff.getNamaStaff() + " - " + staff.getStaff());
                    listStaff.add(staff);
                }

                for (Object row : resultProduk) {
                    Produk produk = new Produk(row);
                    produkBox.getItems().add(produk.getNamaProduk() + " - " + produk.getProduk());
                    listProduk.add(produk);
                }

                System.out.println("ComboBox is visible: " + customerBox.isVisible());
                
                customerBox.setPrefSize(200, 20);
                customerBox.setValue(selectedTransaksi.getIdCustomer());
                GridPane.setConstraints(namaCustomerLabel, 0, 0);
                GridPane.setConstraints(customerBox, 1, 0);
                row3.getChildren().addAll(namaCustomerLabel, customerBox);
                formEdit.getChildren().add(row3);

                // row 4
                GridPane row4 = new GridPane();
                row4.getStyleClass().add("form-grid");
                row4.setHgap(10);
                row4.setVgap(5);
                Label nomorTelfonLabel = new Label("Nomor Telepon:");
                nomorTelfonLabel.getStyleClass().add("form-label");
                nomorTelfonLabel.setPrefSize(200, 20);
                nomorTelfonField.setPrefSize(200, 20);
                nomorTelfonField.setDisable(true);
                GridPane.setConstraints(nomorTelfonLabel, 0, 0);
                GridPane.setConstraints(nomorTelfonField, 1, 0);
                row4.getChildren().addAll(nomorTelfonLabel, nomorTelfonField);
                formEdit.getChildren().add(row4);

                // row 5
                GridPane row5 = new GridPane();
                row5.getStyleClass().add("form-grid");
                row5.setHgap(10);
                row5.setVgap(5);
                Label alamatCustomerLabel = new Label("Alamat Customer:");
                alamatCustomerLabel.getStyleClass().add("form-label");
                alamatCustomerLabel.setPrefSize(200, 20);
                alamatCustomerField.setPrefSize(200, 80);
                alamatCustomerField.setDisable(true);
                GridPane.setConstraints(alamatCustomerLabel, 0, 0);
                GridPane.setConstraints(alamatCustomerField, 1, 0);
                row5.getChildren().addAll(alamatCustomerLabel, alamatCustomerField);
                formEdit.getChildren().add(row5);

                // row 6
                GridPane row6 = new GridPane();
                row6.getStyleClass().add("form-grid");
                row6.setHgap(10);
                row6.setVgap(5);
                Label picLabel = new Label("PIC:");
                picLabel.getStyleClass().add("form-label");
                picLabel.setPrefSize(200, 20);
                
                
                System.out.println("ComboBox is visible: " + picBox.isVisible());
                picBox.setPrefSize(200, 20);
                picBox.setValue(selectedTransaksi.getIdStaff());
                GridPane.setConstraints(picLabel, 0, 0);
                GridPane.setConstraints(picBox, 1, 0);
                row6.getChildren().addAll(picLabel, picBox);
                formEdit.getChildren().add(row6);

                // row 7
                GridPane row7 = new GridPane();
                row7.getStyleClass().add("form-grid");
                row7.setHgap(10);
                row7.setVgap(5);
                Label staffLabel = new Label("Staff:");
                staffLabel.getStyleClass().add("form-label");
                staffLabel.setPrefSize(200, 20);

                
                
                System.out.println("Result size: " + resultStaff.size());
                                    
                
                System.out.println("ComboBox is visible: " + staffBox.isVisible());
                staffBox.setPrefSize(200, 20);
                staffBox.setValue(selectedTransaksi.getIdStaff());
                GridPane.setConstraints(namaCustomerLabel, 0, 0);
                GridPane.setConstraints(staffBox, 1, 0);
                row7.getChildren().addAll(staffLabel, staffBox);
                formEdit.getChildren().add(row7);

                // row 8
                GridPane row8 = new GridPane();
                row8.getStyleClass().add("form-grid");
                row8.setHgap(10);
                row8.setVgap(5);
                Label produkLabel = new Label("Produk:");
                produkLabel.getStyleClass().add("form-label");
                produkLabel.setPrefSize(200, 20);                
                produkBox.setPrefSize(200, 20);
                produkBox.setValue(selectedTransaksi.getIdProduk());
                GridPane.setConstraints(produkLabel, 0, 0);
                GridPane.setConstraints(produkBox, 1, 0);
                row8.getChildren().addAll(produkLabel, produkBox);
                formEdit.getChildren().add(row8);

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
                biayaJasaField.setText(selectedTransaksi.getBiayaJasa());
                GridPane.setConstraints(biayaJasaLabel, 0, 0);
                GridPane.setConstraints(biayaJasaField, 1, 0);
                row9.getChildren().addAll(biayaJasaLabel, biayaJasaField);
                formEdit.getChildren().add(row9);

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
                statusBox.setValue(selectedTransaksi.getStatus());
                GridPane.setConstraints(statusLabel, 0, 0);
                GridPane.setConstraints(statusBox, 1, 0);
                row10.getChildren().addAll(statusLabel, statusBox);
                formEdit.getChildren().add(row10);

                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(event -> {
                    String dataCustomer[] = customerBox.getValue().split(" - ");
                    String dataStaff[] = staffBox.getValue().split(" - ");
                    String dataPic[] = picBox.getValue().split(" - ");
                    String dataProduk[] = produkBox.getValue().split(" - ");

                    Transaksi transaksi = new Transaksi();
                    transaksi.setIdCustomer(dataCustomer[1]);
                    transaksi.setIdStaff(dataStaff[1]);
                    transaksi.setPic(dataPic[1]);
                    transaksi.setIdProduk(dataProduk[1]);
                    transaksi.setTanggalTransaksi(tanggalTransaksiField.getValue());
                    transaksi.setTanggalPengerjaan(tanggalPengerjaanField.getValue());
                    transaksi.setBiayaJasa(biayaJasaField.getText());
                    transaksi.setStatus(statusBox.getValue());
                    if(selectedTransaksi != null) {
                        if(selectedTransaksi.update()) {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setContentText("Data berhasil disimpan!");
                            a.show();
                        } else {
                            Alert a = new Alert(AlertType.ERROR);
                            a.setContentText("Data gagal disimpan!");
                            a.show();
                        }
                        halamanEdit.close();
                        TableTransaksi transaksii = new TableTransaksi();
                        rootPane.getScene().setRoot(transaksii.getRootPane());
                    }});


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
                a.setContentText("Pilih transaksi yang ingin diedit!");
                a.show();
            }
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

>>>>>>> Stashed changes
    public Pane getRootPane() {
        return rootPane;
    }
}