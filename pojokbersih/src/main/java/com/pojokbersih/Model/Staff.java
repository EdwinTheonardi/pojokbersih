package com.pojokbersih.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.DB;

import javafx.beans.property.*;

public class Staff {
    private static final String List = null;
    private final StringProperty idStaff = new SimpleStringProperty();
    private final StringProperty kodeStaff = new SimpleStringProperty();
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
        kodeStaff.set(list.get(1).toString());
        namaStaff.set(list.get(2).toString());
        jenisKelaminStaff.set(list.get(3).toString());
        tempatLahirStaff.set(list.get(4).toString());
        tanggalLahirStaff.set(list.get(5).toString());
        alamatStaff.set(list.get(6).toString());
        nomorHpStaff.set(list.get(7).toString());
        emailStaff.set(list.get(8).toString());
        spesialisasiStaff.set(list.get(9).toString());

    }

    public String getIdStaff() {
        return idStaff.get();
    }
    
    public void setIdStaff(String idStaff) {
        this.idStaff.set(idStaff);
    }

    public StringProperty idStaffProperty() {
        return idStaff;
    }

    // Getter and Setter for namaStaff
    public String getKodeStaff() {
        return kodeStaff.get();
    }

    public void setKodeStaff(String kodeStaff) {
        this.kodeStaff.set(kodeStaff);
    }

    public StringProperty kodeStaffProperty() {
        return kodeStaff;
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
    public String getTanggalLahir() {
        return tanggalLahirStaff.get();
    }

    public void setTanggalLahirStaff(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        this.tanggalLahirStaff.set(formattedDate);
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

    public Boolean create() {
        DB db = new DB();
        String sql = "SELECT MAX(kode_staff) FROM staff";
        ArrayList<Object> result = db.runQuery(sql);

        String maxKodeStaff = null;
        if (!result.isEmpty()) {
            List<Object> row = (List<Object>) result.get(0);
            maxKodeStaff = (String) row.get(0);

            int nextId = maxKodeStaff == null? 1 : Integer.parseInt(maxKodeStaff.substring(2)) + 1;
            String kodeStaff = "ST" + String.format("%05d", nextId);

            setKodeStaff(kodeStaff);

            sql = "INSERT INTO staff (kode_staff, nama_staff, jk_staff, tempat_lahir, tgl_lahir, alamat_staff, nomor_hp, email_staff, spesialisasi_staff) VALUES ('" + kodeStaff + "','"  + getNamaStaff() + "', '" + getJenisKelaminStaffString() + "','" + getTempatLahirStaffString() + "','" + getTanggalLahir() + "','" + getAlamatStaffString() + "','" + getNomorHpStaff() + "','" + getEmailStaff() + "','" + getSpesialisasiStaff() + "')";
        }

        return db.runSql(sql);
    }

    public Boolean update() {
        String sql = "UPDATE staff SET nama_staff = '" + getNamaStaff() + "', jk_staff = '" + getJenisKelaminStaffString() + "', tempat_lahir = '" + getTempatLahirStaffString() + "', tgl_lahir = '" + getTanggalLahir() + "', alamat_staff = '" + getAlamatStaffString() + "', nomor_hp = '" + getNomorHpStaff() + "', email_staff = '" + getEmailStaff() + "', spesialisasi_staff = '" + getEmailStaff() + "' WHERE id_staff = " + getIdStaff();
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete() {
        String sql = "DELETE FROM staff WHERE id_staff = '" + getIdStaff() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }
}
