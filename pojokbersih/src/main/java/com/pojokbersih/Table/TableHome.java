package com.pojokbersih.Table;

import javafx.scene.control.Label;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Form.EditTransaksi;
import com.pojokbersih.Form.TambahTransaksi;
import com.pojokbersih.Model.Customer;
import com.pojokbersih.Model.Produk;
import com.pojokbersih.Model.Staff;
import com.pojokbersih.Model.Transaksi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TableHome {
    private final BorderPane rootPane;
    private final List<Customer> listCustomer = new ArrayList<Customer>();
    private final List<Staff> listPIC = new ArrayList<Staff>();

    public TableHome(){
        rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");

        getData();

        VBox top = new VBox();
        top.getChildren().add(header());
        top.getChildren().add(menu());
        top.getChildren().add(cards());
        top.getChildren().add(labelTabel());
  
        VBox center = new VBox();
        center.getChildren().add(getTable());

        rootPane.setTop(top);
        rootPane.setCenter(center);

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
    
    public HBox cards() {
        HBox cards = new HBox(50);
        cards.getStyleClass().add("cards");

        // Total Deal
        StackPane deal = new StackPane();
        deal.getStyleClass().add("card");

        Rectangle dealBackground = new Rectangle(320, 100, Color.WHITE);

        VBox dealBox = new VBox();
        dealBox.getStyleClass().add("total-box");
        Text dealText = new Text("Total Transaksi Deal");
        Text dealValue = new Text("10");
        dealText.getStyleClass().add("deal-text");
        dealValue.getStyleClass().add("card-value");

        dealBox.getChildren().addAll(dealText, dealValue);
        deal.getChildren().addAll(dealBackground, dealBox);

        // Total On Going
        StackPane ongoing = new StackPane();
        ongoing.getStyleClass().add("card");

        Rectangle ongoingBackground = new Rectangle(320, 100, Color.WHITE);

        VBox ongoingBox = new VBox();
        ongoingBox.getStyleClass().add("total-box");
        Text ongoingText = new Text("Total Transaksi On Going");
        Text ongoingValue = new Text("5");
        ongoingText.getStyleClass().add("ongoing-text");
        ongoingValue.getStyleClass().add("card-value");

        ongoingBox.getChildren().addAll(ongoingText, ongoingValue);
        ongoing.getChildren().addAll(ongoingBackground, ongoingBox);

        // Total Done
        StackPane done = new StackPane();
        done.getStyleClass().add("card");

        Rectangle doneBackground = new Rectangle(320, 100, Color.WHITE);

        VBox doneBox = new VBox();
        doneBox.getStyleClass().add("total-box");
        Text doneText = new Text("Total Transaksi Done");
        Text doneValue = new Text("50");
        doneText.getStyleClass().add("done-text");
        doneValue.getStyleClass().add("card-value");

        doneBox.getChildren().addAll(doneText, doneValue);
        done.getChildren().addAll(doneBackground, doneBox);

        // Total Pendapatan
        StackPane pendapatan = new StackPane();
        pendapatan.getStyleClass().add("card");

        Rectangle pendapatanBackground = new Rectangle(320, 100, Color.WHITE);

        VBox pendapatanBox = new VBox();
        pendapatanBox.getStyleClass().add("total-box");
        Text pendapatanText = new Text("Total Pendapatan");
        Text pendapatanValue = new Text("10.000.000");
        pendapatanText.getStyleClass().add("pendapatan-text");
        pendapatanValue.getStyleClass().add("card-value");

        pendapatanBox.getChildren().addAll(pendapatanText, pendapatanValue);
        pendapatan.getChildren().addAll(pendapatanBackground, pendapatanBox);

        cards.getChildren().add(deal);
        cards.getChildren().add(ongoing);
        cards.getChildren().add(done);
        cards.getChildren().add(pendapatan);

        return cards;
    }

    public HBox labelTabel() {
        HBox tabel = new HBox();
        tabel.getStyleClass().add("tabel");

        Label labelTabel = new Label("Transaksi Terbaru");
        labelTabel.getStyleClass().add("label-tabel");
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
        String query_admin = "SELECT * FROM transaksi WHERE status IN ('deal', 'on going')";

        List<Object> rs = db.runQuery(query_admin);
        listTransaksi.clear();

        for(int i = 0; i < rs.size(); i++) {
            Transaksi transaksi = new Transaksi(rs.get(i));

            listTransaksi.add(transaksi);
        }
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
