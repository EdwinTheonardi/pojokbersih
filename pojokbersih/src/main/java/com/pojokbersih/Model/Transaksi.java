package com.pojokbersih.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaksi {
    private static final String List = null;
    private final StringProperty idTransaksi = new SimpleStringProperty();
    private final StringProperty kodeTransaksi = new SimpleStringProperty();
    private final StringProperty idCustomer = new SimpleStringProperty();
    private final StringProperty idStaff = new SimpleStringProperty();
    private final StringProperty pic = new SimpleStringProperty();
    private final StringProperty idProduk = new SimpleStringProperty();
    private final StringProperty tanggalTransaksi = new SimpleStringProperty();
    private final StringProperty tanggalPengerjaan = new SimpleStringProperty();
    private final StringProperty biayaJasa = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public Transaksi() {

    }

    public Transaksi(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idTransaksi.set(list.get(0).toString());
        kodeTransaksi.set(list.get(1).toString());
        idCustomer.set(list.get(2).toString());
        idStaff.set(list.get(3).toString());
        pic.set(list.get(4).toString());
        idProduk.set(list.get(5).toString());
        tanggalTransaksi.set(list.get(6).toString());
        tanggalPengerjaan.set(list.get(7).toString());
        biayaJasa.set(list.get(8).toString());
        status.set(list.get(9).toString());
    }

    public StringProperty idTransaksiProperty() {
        return idTransaksi;
    }

    public String getIdTransaksi() {
        return idTransaksi.get();
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi.set(idTransaksi);
    }

    public StringProperty kodeTransaksiProperty() {
        return kodeTransaksi;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi.get();
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi.set(kodeTransaksi);
    }

    public StringProperty idCustomerProperty() {
        return idCustomer;
    }

    public String getIdCustomer() {
        return idCustomer.get();
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer.set(idCustomer);
    }

    public StringProperty idStaffProperty() {
        return idStaff;
    }

    public String getIdStaff() {
        return idStaff.get();
    }

    public void setIdStaff(String idStaff) {
        this.idStaff.set(idStaff);
    }

    public StringProperty picProperty() {
        return pic;
    }

    public String getPic() {
        return pic.get();
    }

    public void setPic(String pic) {
        this.pic.set(pic);
    }

    public StringProperty idProdukProperty() {
        return idProduk;
    }

    public String getIdProduk() {
        return idProduk.get();
    }

    public void setIdProduk(String idProduk) {
        this.idProduk.set(idProduk);
    }

    public StringProperty tanggalTransaksiProperty() {
        return tanggalTransaksi;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi.get();
    }

    public void setTanggalTransaksi(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        this.tanggalTransaksi.set(formattedDate);
    }

    public StringProperty tanggalPengerjaanProperty() {
        return tanggalPengerjaan;
    }

    public String getTanggalPengerjaan() {
        return tanggalPengerjaan.get();
    }

    public void setTanggalPengerjaan(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        this.tanggalPengerjaan.set(formattedDate);
    }

    public StringProperty biayaJasaProperty() {
        return biayaJasa;
    }

    public String getBiayaJasa() {
        return biayaJasa.get();
    }

    public void setBiayaJasa(String biayaJasa) {
        this.biayaJasa.set(biayaJasa);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Boolean create(List<String> produkIds) {
        DB db = new DB();
        String sql = "SELECT MAX(kode_transaksi) FROM transaksi";
        ArrayList<Object> result = db.runQuery(sql);

        String maxKodeTransaksi = null;
        if (!result.isEmpty()) {
            List<Object> row = (List<Object>) result.get(0);
            maxKodeTransaksi = (String) row.get(0);
        }

        int nextId = maxKodeTransaksi == null? 1 : Integer.parseInt(maxKodeTransaksi.substring(2)) + 1;
        String kodeTransaksi = "TR" + String.format("%05d", nextId);

        setKodeTransaksi(kodeTransaksi);

        // for (String produkId : produkIds) {
        // sql = "INSERT INTO transaksi (kode_transaksi, id_customer, id_staff, pic, id_produk, tgl_transaksi, tgl_pengerjaan, biaya_jasa, status) VALUES ('" + getKodeTransaksi() + "', '" + getIdCustomer() + "', '" + getIdStaff() + "', '" + getPic() + "', '" + produkId + "', '" + getTanggalTransaksi() + "', '" + getTanggalPengerjaan() + "', '" + getBiayaJasa() + "', '" + getStatus() + "')";
    
        //     if (!db.runSql(sql)) {
        //         return false;
        //     }
        // }
        sql = "INSERT INTO transaksi (kode_transaksi, id_customer, id_staff, pic, id_produk, tgl_transaksi, tgl_pengerjaan, biaya_jasa, status) VALUES ('" + getKodeTransaksi() + "', '" + getIdCustomer() + "', '" + getIdStaff() + "', '" + getPic() + "', '" + produkIds + "', '" + getTanggalTransaksi() + "', '" + getTanggalPengerjaan() + "', '" + getBiayaJasa() + "', '" + getStatus() + "')";
        return db.runSql(sql);
    }

    public Boolean update() {
        String sql = "UPDATE transaksi SET id_customer = '" + getIdCustomer() + "', id_staff = '" + getIdStaff() + "', pic = '" + getPic() + "', id_produk = '" + getIdProduk() + "', tgl_transaksi = '" + getTanggalTransaksi() + "', tgl_pengerjaan = '" + getTanggalPengerjaan() + "', biaya_jasa = '" + getBiayaJasa() + "', status = '" + getStatus() + "' WHERE id_transaksi = " + getIdTransaksi();
        DB db = new DB();
    
        return db.runSql(sql);
    }

    public Boolean delete() {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = '" + getIdTransaksi() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }
}