package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;

public class Admin {
    private final StringProperty idAdmin = new SimpleStringProperty();
    private final StringProperty namaAdmin = new SimpleStringProperty();
    private final StringProperty usernameAdmin = new SimpleStringProperty();
    private final StringProperty passwordAdmin = new SimpleStringProperty();

    public Admin() {

    }

    public Admin(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idAdmin.set(list.get(0).toString());
        namaAdmin.set(list.get(1).toString());
        usernameAdmin.set(list.get(2).toString());
        passwordAdmin.set(list.get(3).toString());
    }

    // Getter and Setter for idAdmin
    public String getIdAdmin() {
        return idAdmin.get();
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin.set(idAdmin);
    }

    public StringProperty idAdminProperty() {
        return idAdmin;
    }

    // Getter and Setter for namaAdmin
    public String getNamaAdmin() {
        return namaAdmin.get();
    }

    public void setNamaAdmin(String namaAdmin) {
        this.namaAdmin.set(namaAdmin);
    }

    public StringProperty namaAdminProperty() {
        return namaAdmin;
    }

    // Getter and Setter for usernameAdmin
    public String getUsernameAdmin() {
        return usernameAdmin.get();
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin.set(usernameAdmin);
    }

    public StringProperty usernameAdminProperty() {
        return usernameAdmin;
    }

    // Getter and Setter for passwordAdmin
    public String getPasswordAdmin() {
        return passwordAdmin.get();
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin.set(passwordAdmin);
    }

    public StringProperty passwordAdminProperty() {
        return passwordAdmin;
    }
}
