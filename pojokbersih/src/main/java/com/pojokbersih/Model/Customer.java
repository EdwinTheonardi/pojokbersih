package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;

public class Customer {
    private static final String List = null;
    private final StringProperty idCustomer = new SimpleStringProperty();
    private final StringProperty namaCustomer = new SimpleStringProperty();
    private final StringProperty nomorHpCustomer = new SimpleStringProperty();
    private final StringProperty alamatCustomer = new SimpleStringProperty();

    public Customer() {

    }

    public Customer(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idCustomer.set(list.get(0).toString());
        namaCustomer.set(list.get(1).toString());
        nomorHpCustomer.set(list.get(2).toString());
        alamatCustomer.set(list.get(3).toString());
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
    

}