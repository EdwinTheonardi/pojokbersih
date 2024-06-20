package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.DB;

import javafx.beans.property.*;

public class Produk {
    private static final String List = null;
    private final StringProperty idProduk= new SimpleStringProperty();
    private final StringProperty kodeProduk= new SimpleStringProperty();
    private final StringProperty namaProduk = new SimpleStringProperty();

    public Produk() {

    }

    public Produk(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idProduk.set(list.get(0).toString());
        kodeProduk.set(list.get(1).toString());
        namaProduk.set(list.get(2).toString());

    }

    public String getIdProduk() {
        return idProduk.get();
    }
    
    public void setIdProduk(String idProduk) {
        this.idProduk.set(idProduk);
    }

    public StringProperty idProdukProperty() {
        return idProduk;
    }

    // Getter and Setter for namaProduk
    public String getKodeProduk() {
        return kodeProduk.get();
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk.set(kodeProduk);
    }

    public StringProperty kodeProdukProperty() {
        return kodeProduk;
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

    public Boolean create() {
        DB db = new DB();
        String sql = "SELECT MAX(kode_produk) FROM produk";
        ArrayList<Object> result = db.runQuery(sql);

        String maxKodeProduk = null;
        if (!result.isEmpty()) {
            List<Object> row = (List<Object>) result.get(0);
            maxKodeProduk = (String) row.get(0);

            int nextId = maxKodeProduk == null? 1 : Integer.parseInt(maxKodeProduk.substring(2)) + 1;
            String kodeProduk = "PR" + String.format("%05d", nextId);

            setKodeProduk(kodeProduk);

            sql = "INSERT INTO produk (kode_produk, nama_produk) VALUES ('" + getKodeProduk() + "', '" + getNamaProduk() + "' )";
        }

        return db.runSql(sql);
    }

    public Boolean update() {
        String sql = "UPDATE produk SET nama_produk = '" + getNamaProduk() + "' WHERE id_produk = " + getIdProduk();
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete() {
        String sql = "DELETE FROM produk WHERE id_produk = '" + getIdProduk() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

}
