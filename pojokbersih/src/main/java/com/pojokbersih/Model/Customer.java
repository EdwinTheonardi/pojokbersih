package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.DB;

import javafx.beans.property.*;

public class Customer {
    private static final String List = null;
    private final StringProperty idCustomer = new SimpleStringProperty();
    private final StringProperty kodeCustomer = new SimpleStringProperty();
    private final StringProperty namaCustomer = new SimpleStringProperty();
    private final StringProperty nomorHpCustomer = new SimpleStringProperty();
    private final StringProperty alamatCustomer = new SimpleStringProperty();

    public Customer() {

    }

    public Customer(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idCustomer.set(list.get(0).toString());
        kodeCustomer.set(list.get(1).toString());
        namaCustomer.set(list.get(2).toString());
        nomorHpCustomer.set(list.get(3).toString());
        alamatCustomer.set(list.get(4).toString());
    }

    public String getIdCustomer() {
        return idCustomer.get();
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer.set(idCustomer);
    }

    public StringProperty idCustomerProperty() {
        return idCustomer;
    }

    // Getter and Setter for kodeCustomer
    public String getKodeCustomer() {
        return kodeCustomer.get();
    }

    public void setKodeCustomer(String kodeCustomer) {
        this.kodeCustomer.set(kodeCustomer);
    }

    public StringProperty kodeCustomerProperty() {
        return kodeCustomer;
    }

    // Getter and Setter for namaCustomer
    public String getNamaCustomer() {
        return namaCustomer.get();
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer.set(namaCustomer);
    }

    public StringProperty namaCustomerProperty() {
        return namaCustomer;
    }

    // Getter and Setter for nomorHpCustomer
    public String getNomorHpCustomer() {
        return nomorHpCustomer.get();
    }

    public void setNomorHpCustomer(String nomorHpCustomer) {
        this.nomorHpCustomer.set(nomorHpCustomer);
    }

    public StringProperty nomorHpCustomerProperty() {
        return nomorHpCustomer;
    }

    // Getter and Setter for alamatCustomer
    public String getAlamatCustomer() {
        return alamatCustomer.get();
    }

    public void setAlamatCustomer(String alamatCustomer) {
        this.alamatCustomer.set(alamatCustomer);
    }

    public StringProperty alamatCustomerProperty() {
        return alamatCustomer;
    }
    
    public Boolean create() {
        DB db = new DB();
        String sql = "SELECT MAX(kode_customer) FROM customer";
        ArrayList<Object> result = db.runQuery(sql);
    
        String maxKodeCustomer = null;
        if (!result.isEmpty()) {
            List<Object> row = (List<Object>) result.get(0);
            maxKodeCustomer = (String) row.get(0);
        }
    
        int nextId = maxKodeCustomer == null? 1 : Integer.parseInt(maxKodeCustomer.substring(2)) + 1;
        String kodeCustomer = "CS" + String.format("%05d", nextId);
    
        setKodeCustomer(kodeCustomer);
    
        sql = "INSERT INTO customer (kode_customer, nama_customer, nomor_hp, alamat_customer) VALUES ('" + getKodeCustomer() + "', '" + getNamaCustomer() + "', '" + getNomorHpCustomer() + "', '" + getAlamatCustomer() + "')";
        return db.runSql(sql);
    }

    public Boolean update() {
        String sql = "UPDATE customer SET nama_customer = '" + getNamaCustomer() + "', nomor_hp = '" + getNomorHpCustomer() + "', alamat_customer = '" + getAlamatCustomer() + "' WHERE id_customer = " + getIdCustomer();
        DB db = new DB();
    
        return db.runSql(sql);
    }

    public Boolean delete() {
        String sql = "DELETE FROM customer WHERE id_customer = '" + getIdCustomer() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

}
