package com.pojokbersih.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Home;
import com.pojokbersih.Model.Customer;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
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

        Label labelTabel = new Label("Tabel Customer");
        labelTabel.getStyleClass().add("label-tabel-customer");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    private List<Customer> listCust;
    private TableView<Customer> tb = new TableView<Customer>();

    @SuppressWarnings("unchecked")
    public TableView<Customer> createTable() {
        TableColumn<Customer, String> col_id = new TableColumn<>("Kode Customer");
        TableColumn<Customer, String> col_nama = new TableColumn<>("Nama Customer");
        TableColumn<Customer, String> col_nomorhp = new TableColumn<>("Nomor Telepon");
        TableColumn<Customer, String> col_alamat = new TableColumn<>("Alamat Customer");

        col_id.setCellValueFactory(v -> v.getValue().idCustomerProperty());
        col_nama.setCellValueFactory(v -> v.getValue().namaCustomerProperty());
        col_nomorhp.setCellValueFactory(v -> v.getValue().nomorHpCustomerProperty());
        col_alamat.setCellValueFactory(v -> v.getValue().alamatCustomerProperty());

        ArrayList<TableColumn<Customer, String>> col = new ArrayList<>();
        col.add(col_id);
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

    public Pane getRootPane() {
        return rootPane;
    }
}
