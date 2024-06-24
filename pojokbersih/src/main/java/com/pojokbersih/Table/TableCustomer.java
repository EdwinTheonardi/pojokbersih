package com.pojokbersih.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Model.Customer;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class TableCustomer {
    private final BorderPane rootPane;

    public TableCustomer() {
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
        
        // Search Nama Customer
        Label searchNamaCustLabel = new Label("Nama Customer:");
        searchNamaCustLabel.getStyleClass().add("form-label");
        searchNamaCustLabel.setPrefSize(200, 35);
        TextField searchNamaCustField = new TextField();
        searchNamaCustField.setPrefSize(200, 35);
        GridPane.setConstraints(searchNamaCustLabel, 0, 0);
        GridPane.setConstraints(searchNamaCustField, 1, 0);
        formSearch.getChildren().addAll(searchNamaCustLabel, searchNamaCustField);

        // Search Alamat Customer
        Label searchAlamatCustLabel = new Label("Alamat Customer:");
        searchAlamatCustLabel.getStyleClass().add("form-label");
        searchAlamatCustLabel.setPrefSize(200, 35);
        TextField searchAlamatCustField = new TextField();
        searchAlamatCustField.setPrefSize(200, 35);
        GridPane.setConstraints(searchAlamatCustLabel, 2, 0);
        GridPane.setConstraints(searchAlamatCustField, 3, 0);
        formSearch.getChildren().addAll(searchAlamatCustLabel, searchAlamatCustField);
 
        Button searchButton = new Button();
        searchButton.setText("Cari");
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(e -> {
            FilteredList<Customer> flCustomer = new FilteredList<>(FXCollections.observableArrayList(getData()), p -> true);
        
            flCustomer.setPredicate(p -> {
                return (searchNamaCustField.getText() == null || searchNamaCustField.getText().isEmpty() || p.getNamaCustomer().toLowerCase().contains(searchNamaCustField.getText().toLowerCase()))
                        && (searchAlamatCustField.getText() == null || searchAlamatCustField.getText().isEmpty() || p.getAlamatCustomer().toLowerCase().contains(searchAlamatCustField.getText().toLowerCase()));
            });
        
            tb.setItems(flCustomer);
        });
        
        Button viewAllButton = new Button("Lihat Semua");
        viewAllButton.getStyleClass().add("search-button");
        viewAllButton.setOnAction(e -> {
            tb.setItems(FXCollections.observableArrayList(getData()));
            searchNamaCustField.clear();
            searchAlamatCustField.clear();
        });

        Button addButton = new Button();
        addButton.setText("Tambah");
        addButton.getStyleClass().add("add-button");
        GridPane.setColumnSpan(addButton, 2);
        addButton.setOnAction(event -> {
            Stage halamanTambah = new Stage();
            halamanTambah.initModality(Modality.APPLICATION_MODAL);
            halamanTambah.setTitle("Tambah Customer");
            halamanTambah.setWidth(1280);
            halamanTambah.setHeight(720);
            
            StackPane halamanBaru = new StackPane();
            halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
            halamanBaru.getStyleClass().add("halaman-baru");
    
            VBox formTambah = new VBox();
            Label formTitle = new Label("Tambah Customer");
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
            Label namaCustomerLabel = new Label("Nama Customer:");
            namaCustomerLabel.getStyleClass().add("form-label");
            namaCustomerLabel.setPrefSize(200, 20);
            TextField namaCustomerField = new TextField();
            namaCustomerField.setPrefSize(200, 20);
            GridPane.setConstraints(namaCustomerLabel, 0, 0);
            GridPane.setConstraints(namaCustomerField, 1, 0);
            row1.getChildren().addAll(namaCustomerLabel, namaCustomerField);
            formTambah.getChildren().add(row1);
    
            // row 2
            GridPane row2 = new GridPane();
            row2.getStyleClass().add("form-grid");
            row2.setHgap(10);
            row2.setVgap(5);
            Label nomorTelfonLabel = new Label("Nomor Telepon:");
            nomorTelfonLabel.getStyleClass().add("form-label");
            nomorTelfonLabel.setPrefSize(200, 20);
            TextField nomorTelfonField = new TextField();
            nomorTelfonField.setPrefSize(200, 20);
            GridPane.setConstraints(nomorTelfonLabel, 0, 0);
            GridPane.setConstraints(nomorTelfonField, 1, 0);
            row2.getChildren().addAll(nomorTelfonLabel, nomorTelfonField);
            formTambah.getChildren().add(row2);
    
            // row 3
            GridPane row3 = new GridPane();
            row3.getStyleClass().add("form-grid");
            row3.setHgap(10);
            row3.setVgap(5);
            Label alamatCustomerLabel = new Label("Alamat Customer:");
            alamatCustomerLabel.getStyleClass().add("form-label");
            alamatCustomerLabel.setPrefSize(200, 20);
            TextArea alamatCustomerField = new TextArea();
            alamatCustomerField.setPrefSize(200, 80);
            GridPane.setConstraints(alamatCustomerLabel, 0, 0);
            GridPane.setConstraints(alamatCustomerField, 1, 0);
            row3.getChildren().addAll(alamatCustomerLabel, alamatCustomerField);
            formTambah.getChildren().add(row3);
    
            Button saveButton = new Button();
            saveButton.setText("Simpan");
            saveButton.getStyleClass().add("save-button");
            saveButton.setOnAction(e -> {
                Customer customer = new Customer();
                customer.setNamaCustomer(namaCustomerField.getText());
                customer.setNomorHpCustomer(nomorTelfonField.getText());
                customer.setAlamatCustomer(alamatCustomerField.getText());
    
                if(customer.create()) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Data berhasil disimpan!");
                    a.show();
                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Data gagal disimpan!");
                    a.show();
                }
                halamanTambah.close();
                TableCustomer customerr = new TableCustomer();
                rootPane.getScene().setRoot(customerr.getRootPane());
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

        Label labelTabel = new Label("Tabel Customer");
        labelTabel.getStyleClass().add("label-tabel-customer");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    private List<Customer> listCust;
    private TableView<Customer> tb = new TableView<Customer>();

    @SuppressWarnings("unchecked")
    public TableView<Customer> createTable() {
        TableColumn<Customer, String> col_kode = new TableColumn<>("Kode Customer");
        TableColumn<Customer, String> col_nama = new TableColumn<>("Nama Customer");
        TableColumn<Customer, String> col_nomorhp = new TableColumn<>("Nomor Telepon");
        TableColumn<Customer, String> col_alamat = new TableColumn<>("Alamat Customer");

        col_kode.setCellValueFactory(v -> v.getValue().kodeCustomerProperty());
        col_nama.setCellValueFactory(v -> v.getValue().namaCustomerProperty());
        col_nomorhp.setCellValueFactory(v -> v.getValue().nomorHpCustomerProperty());
        col_alamat.setCellValueFactory(v -> v.getValue().alamatCustomerProperty());

        ArrayList<TableColumn<Customer, String>> col = new ArrayList<>();
        col.add(col_kode);
        col.add(col_nama);
        col.add(col_nomorhp);
        col.add(col_alamat);

        for(int i = 0; i< col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        return tb;
    }

    public HBox getTable() {
        HBox table = new HBox();
        TableView<Customer> tb = createTable();

        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Customer> list_cust = getData();

        for(int y = 0; y < list_cust.size(); y++) {
            tb.getItems().add(list_cust.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Customer> getData() {
        DB db = new DB();
        String query_admin = "SELECT * FROM customer";

        List<Object> rs = db.runQuery(query_admin);
        listCust = new ArrayList<Customer>();

        for(int i = 0; i < rs.size(); i++) {
            Customer cust = new Customer(rs.get(i));

            listCust.add(cust);
        }
        
        return listCust;
    }

    public HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.getStyleClass().add("button-box");
    
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("edit-button");

        editButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Customer> tb = (TableView<Customer>) tableHBox.getChildren().get(0);
            Customer selectedCustomer = tb.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                Stage halamanEdit = new Stage();
                halamanEdit.initModality(Modality.APPLICATION_MODAL);
                halamanEdit.setTitle("Edit Customer");
                halamanEdit.setWidth(1280);
                halamanEdit.setHeight(720);

                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formEdit = new VBox();
                Label formTitle = new Label("Edit Customer");
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
                Label namaCustomerLabel = new Label("Nama Customer:");
                namaCustomerLabel.getStyleClass().add("form-label");
                namaCustomerLabel.setPrefSize(200, 20);
                TextField namaCustomerField = new TextField();
                namaCustomerField.setPrefSize(200, 20);
                namaCustomerField.setText(selectedCustomer.getNamaCustomer());
                GridPane.setConstraints(namaCustomerLabel, 0, 0);
                GridPane.setConstraints(namaCustomerField, 1, 0);
                row1.getChildren().addAll(namaCustomerLabel, namaCustomerField);
                formEdit.getChildren().add(row1);

                // row 2
                GridPane row2 = new GridPane();
                row2.getStyleClass().add("form-grid");
                row2.setHgap(10);
                row2.setVgap(5);
                Label nomorTelfonLabel = new Label("Nomor Telepon:");
                nomorTelfonLabel.getStyleClass().add("form-label");
                nomorTelfonLabel.setPrefSize(200, 20);
                TextField nomorTelfonField = new TextField();
                nomorTelfonField.setPrefSize(200, 20);
                nomorTelfonField.setText(selectedCustomer.getNomorHpCustomer());
                GridPane.setConstraints(nomorTelfonLabel, 0, 0);
                GridPane.setConstraints(nomorTelfonField, 1, 0);
                row2.getChildren().addAll(nomorTelfonLabel, nomorTelfonField);
                formEdit.getChildren().add(row2);

                // row 3
                GridPane row3 = new GridPane();
                row3.getStyleClass().add("form-grid");
                row3.setHgap(10);
                row3.setVgap(5);
                Label alamatCustomerLabel = new Label("Alamat Customer:");
                alamatCustomerLabel.getStyleClass().add("form-label");
                alamatCustomerLabel.setPrefSize(200, 20);
                TextArea alamatCustomerField = new TextArea();
                alamatCustomerField.setPrefSize(200, 80);
                alamatCustomerField.setText(selectedCustomer.getAlamatCustomer());
                GridPane.setConstraints(alamatCustomerLabel, 0, 0);
                GridPane.setConstraints(alamatCustomerField, 1, 0);
                row3.getChildren().addAll(alamatCustomerLabel, alamatCustomerField);
                formEdit.getChildren().add(row3);

                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(event -> {
                    selectedCustomer.setNamaCustomer(namaCustomerField.getText());
                    selectedCustomer.setNomorHpCustomer(nomorTelfonField.getText());
                    selectedCustomer.setAlamatCustomer(alamatCustomerField.getText());

                    if (selectedCustomer.update()) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil diupdate!");
                        a.show();
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Data gagal diupdate!");
                        a.show();
                    }
                    halamanEdit.close();
                    TableCustomer customerr = new TableCustomer();
                    rootPane.getScene().setRoot(customerr.getRootPane());
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
                a.setContentText("Pilih customer yang ingin diedit!");
                a.show();
            }
        });
    
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Customer> tb = (TableView<Customer>) tableHBox.getChildren().get(0);
            Customer selectedCustomer = tb.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Hapus Customer");
                alert.setHeaderText("Apakah anda yakin ingin menghapus customer?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (selectedCustomer.delete()) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil dihapus!");
                        a.show();
                        TableCustomer customerr = new TableCustomer();
                        rootPane.getScene().setRoot(customerr.getRootPane());
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Data gagal dihapus!");
                        a.show();
                    }
                }
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setContentText("Pilih customer yang ingin dihapus!");
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