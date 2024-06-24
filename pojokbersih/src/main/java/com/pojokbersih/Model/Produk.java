package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;
import javafx.scene.Parent;

public class Produk {
    private static final String List = null;
    private final StringProperty idProduk= new SimpleStringProperty();
    private final StringProperty namaProduk = new SimpleStringProperty();

    public Produk() {

    }

    public Produk(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idProduk.set(list.get(0).toString());
        namaProduk.set(list.get(1).toString());

    }

    public String getProduk() {
        return idProduk.get();
    }
    
    public void setIdProduk(String idProduk) {
        this.idProduk.set(idProduk);
    }

    public StringProperty idProdukProperty() {
        return idProduk;
    }

    // Getter and Setter for namaProduk
    public String getNamaProduk() {
        return namaProduk.get();
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk.set(namaProduk);
    }

    public StringProperty namaProdukProperty() {
        return namaProduk;
    }

    public Parent getRootPane() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRootPane'");
    }

}
