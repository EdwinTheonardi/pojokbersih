package com.pojokbersih.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pojokbersih.App;
import com.pojokbersih.DB;
import com.pojokbersih.Model.Produk;
import com.pojokbersih.Model.Staff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableProduk {
        private final BorderPane rootPane;

    public TableProduk() {
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
        
        // Search Nama Produk
        Label searchNamaProdukLabel = new Label("Nama Produk:");
        searchNamaProdukLabel.getStyleClass().add("form-label");
        searchNamaProdukLabel.setPrefSize(200, 35);
        TextField searchNamaProdukField = new TextField();
        searchNamaProdukField.setPrefSize(200, 35);
        GridPane.setConstraints(searchNamaProdukLabel, 0, 0);
        GridPane.setConstraints(searchNamaProdukField, 1, 0);
        formSearch.getChildren().addAll(searchNamaProdukLabel, searchNamaProdukField);
 
        Button searchButton = new Button();
        searchButton.setText("Cari");
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(e -> {
            FilteredList<Produk> flProduk = new FilteredList<>(FXCollections.observableArrayList(getData()), p -> true);
        
            flProduk.setPredicate(p -> {
                return (searchNamaProdukField.getText() == null || searchNamaProdukField.getText().isEmpty() || p.getNamaProduk().toLowerCase().contains(searchNamaProdukField.getText().toLowerCase()));
            });
        
            tb.setItems(flProduk);
        });
        
        Button viewAllButton = new Button("Lihat Semua");
        viewAllButton.getStyleClass().add("search-button");
        viewAllButton.setOnAction(e -> {
            tb.setItems(FXCollections.observableArrayList(getData()));
            searchNamaProdukField.clear();
        });
           
            
            Button addButton = new Button();
            addButton.setText("Tambah");
            addButton.getStyleClass().add("add-button");

            addButton.setOnAction(event -> {
                Stage halamanTambah = new Stage();
                halamanTambah.initModality(Modality.APPLICATION_MODAL);
                halamanTambah.setTitle("Tambah Produk");
                halamanTambah.setWidth(1280);
                halamanTambah.setHeight(720);
                
                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formTambah = new VBox();
                Label formTitle = new Label("Tambah Produk");
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
                Label namaProdukLabel = new Label("Nama Produk:");
                namaProdukLabel.getStyleClass().add("form-label");
                namaProdukLabel.setPrefSize(200, 20);
                TextField namaProdukField = new TextField();
                namaProdukField.setPrefSize(300, 20);
                GridPane.setConstraints(namaProdukLabel, 0, 0);
                GridPane.setConstraints(namaProdukField, 1, 0);
                row1.getChildren().addAll(namaProdukLabel, namaProdukField);
                formTambah.getChildren().add(row1);

                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(e -> {
                    Produk produk = new Produk();
                    produk.setNamaProduk(namaProdukField.getText());

                    if(produk.create()) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil disimpan!");
                        a.show();
                    }
                    halamanTambah.close();
                    TableProduk produkk = new TableProduk();
                    rootPane.getScene().setRoot(produkk.getRootPane());
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

        Label labelTabel = new Label("Tabel Produk");
        labelTabel.getStyleClass().add("label-tabel-produk");
        tabel.getChildren().add(labelTabel);

        return tabel;
    }

    private List<Produk> listProduk;
    private TableView<Produk> tb = new TableView<Produk>();

    @SuppressWarnings("unchecked")
    public TableView<Produk> createTable() {
        TableColumn<Produk, String> col_kode = new TableColumn<>("Kode Produk");
        TableColumn<Produk, String> col_nama = new TableColumn<>("Nama Produk");

        col_kode.setCellValueFactory(v-> v.getValue().kodeProdukProperty());
        col_nama.setCellValueFactory(v-> v.getValue().namaProdukProperty());

        ArrayList<TableColumn<Produk, String>> col = new ArrayList<>();
        col.add(col_kode);
        col.add(col_nama);

        for (int i = 0; i< col.size(); i++) {
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        return tb;
    }

    public HBox getTable() {
        HBox table = new HBox();
        TableView<Produk> tb = createTable();

        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Produk> list_produk = getData();

        for(int y = 0; y < list_produk.size(); y++) {
            tb.getItems().add(list_produk.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Produk> getData() {
        DB db = new DB();
        String query_admin = "SELECT * FROM produk";

        List<Object> rs = db.runQuery(query_admin);
        listProduk = new ArrayList<Produk>();

        for(int i = 0; i < rs.size(); i++) {
            Produk produk = new Produk(rs.get(i));

            listProduk.add(produk);
        }
        
        return listProduk;
    }

    public HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.getStyleClass().add("button-box");
    
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("edit-button");


        editButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Produk> tb = (TableView<Produk>) tableHBox.getChildren().get(0);
            Produk selectedProduk = tb.getSelectionModel().getSelectedItem();
            if (selectedProduk != null) {
                Stage halamanEdit = new Stage();
                halamanEdit.initModality(Modality.APPLICATION_MODAL);
                halamanEdit.setTitle("Edit Produk");
                halamanEdit.setWidth(1280);
                halamanEdit.setHeight(720);

                StackPane halamanBaru = new StackPane();
                halamanBaru.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                halamanBaru.getStyleClass().add("halaman-baru");

                VBox formEdit = new VBox();
                Label formTitle = new Label("Edit Produk");
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
                Label namaProdukLabel = new Label("Nama Produk:");
                namaProdukLabel.getStyleClass().add("form-label");
                namaProdukLabel.setPrefSize(200, 20);
                TextField namaProdukField = new TextField();
                namaProdukField.setPrefSize(300, 20);
                namaProdukField.setText(selectedProduk.getNamaProduk());
                GridPane.setConstraints(namaProdukLabel, 0, 0);
                GridPane.setConstraints(namaProdukField, 1, 0);
                row1.getChildren().addAll(namaProdukLabel, namaProdukField);
                formEdit.getChildren().add(row1);

                Button saveButton = new Button();
                saveButton.setText("Simpan");
                saveButton.getStyleClass().add("save-button");
                saveButton.setOnAction(event -> {
                    selectedProduk.setNamaProduk(namaProdukField.getText());
                    
                    if (selectedProduk.update()) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("Data berhasil diupdate");
                        a.show();
                        System.out.println(selectedProduk.getNamaProduk());
                    } else {
                        Alert a = new  Alert(AlertType.ERROR);
                        a.setContentText("Data gagal diupdate!");
                        a.show();
                    }
                    halamanEdit.close();
                    TableProduk produkk = new TableProduk();
                    rootPane.getScene().setRoot(produkk.getRootPane());
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
                a.setContentText("Pilih produk yang ingin diedit!");
                a.show();
            }
        });
    
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setOnAction(e -> {
            HBox tableHBox = getTable();
            TableView<Produk> tb = (TableView<Produk>) tableHBox.getChildren().get(0);
            Produk selectedProduk = tb.getSelectionModel().getSelectedItem();
            if (selectedProduk != null) {
                if (selectedProduk.delete()) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Data berhasil dihapus!");
                    a.show();
                    TableProduk produkk = new TableProduk();
                    rootPane.getScene().setRoot(produkk.getRootPane());
                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Data gagal dihapus!");
                    a.show();
                }
            } else {
                Alert a = new Alert(AlertType.WARNING);
                a.setContentText("Pilih produk yang ingin dihapus!");
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
