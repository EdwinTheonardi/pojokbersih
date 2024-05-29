package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;

public class Staff {
    private static final String List = null;
    private final StringProperty idStaff = new SimpleStringProperty();
    private final StringProperty namaStaff = new SimpleStringProperty();
    private final StringProperty jenisKelaminStaff = new SimpleStringProperty();
    private final StringProperty tempatLahirStaff = new SimpleStringProperty();
    private final StringProperty tanggalLahirStaff = new SimpleStringProperty();
    private final StringProperty alamatStaff = new SimpleStringProperty();
    private final StringProperty nomorHpStaff = new SimpleStringProperty();
    private final StringProperty emailStaff = new SimpleStringProperty();
    private final StringProperty spesialisasiStaff = new SimpleStringProperty();

    public Staff() {

    }

    public Staff(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idStaff.set(list.get(0).toString());
        namaStaff.set(list.get(1).toString());
        jenisKelaminStaff.set(list.get(2).toString());
        tempatLahirStaff.set(list.get(3).toString());
        tanggalLahirStaff.set(list.get(4).toString());
        alamatStaff.set(list.get(5).toString());
        nomorHpStaff.set(list.get(6).toString());
        emailStaff.set(list.get(7).toString());
        spesialisasiStaff.set(list.get(8).toString());

    }

    public String getStaff() {
        return idStaff.get();
    }
    
    public void setIdStaff(String idStaff) {
        this.idStaff.set(idStaff);
    }

    public StringProperty idStaffProperty() {
        return idStaff;
    }

    // Getter and Setter for namaStaff
    public String getNamaStaff() {
        return namaStaff.get();
    }

    public void setNamaStaff(String namaStaff) {
        this.namaStaff.set(namaStaff);
    }

    public StringProperty namaStaffProperty() {
        return namaStaff;
    }

    // Getter and Setter for jenisKelaminStaff
    public String getJenisKelaminStaffString() {
        return jenisKelaminStaff.get();
    }

    public void setJenisKelaminStaff(String jenisKelaminStaff) {
        this.jenisKelaminStaff.set(jenisKelaminStaff);
    }

    public StringProperty jenisKelaminStaffProperty() {
        return jenisKelaminStaff;
    }

    // Getter and Setter for tempatLahirStaff
    public String getTempatLahirStaffString() {
        return tempatLahirStaff.get();
    }

    public void setTempatLahirStaff(String tempatLahirStaff) {
        this.tempatLahirStaff.set(tempatLahirStaff);
    }

    public StringProperty tempatLahirStaffProperty() {
        return tempatLahirStaff;
    }

    // Getter and Setter for tanggalLahirStaff
    public String getTanggalLahirStaffString() {
        return tanggalLahirStaff.get();
    }

    public void setTanggalLahirStaff(String tanggalLahirStaff) {
        this.tanggalLahirStaff.set(tanggalLahirStaff);
    }

    public StringProperty tanggalLahirStaffProperty() {
        return tanggalLahirStaff;
    }

    // Getter and Setter for alamatStaff
    public String getAlamatStaffString() {
        return alamatStaff.get();
    }

    public void setAlamatStaff(String alamatStaff) {
        this.alamatStaff.set(alamatStaff);
    }

    public StringProperty alamatStaffProperty() {
        return alamatStaff;
    }

    // Getter and Setter for nomorHpStaff
    public String getNomorHpStaff() {
        return nomorHpStaff.get();
    }

    public void setNomorHpStaff(String nomorHpStaff) {
        this.nomorHpStaff.set(nomorHpStaff);
    }

    public StringProperty nomorHpStaffProperty() {
        return nomorHpStaff;
    }

    // Getter and Setter for emailStaff
    public String getEmailStaff() {
        return emailStaff.get();
    }

    public void setEmailStaff(String emailStaff) {
        this.emailStaff.set(emailStaff);
    }

    public StringProperty emailStaffProperty() {
        return emailStaff;
    }

    // Getter and Setter for spesialisasiStaff
    public String getSpesialisasiStaff() {
        return spesialisasiStaff.get();
    }

    public void setSpesialisasiStaff(String spesialisasiStaff) {
        this.spesialisasiStaff.set(spesialisasiStaff);
    }

    public StringProperty spesialisasiStaffProperty() {
        return spesialisasiStaff;
    }
}
