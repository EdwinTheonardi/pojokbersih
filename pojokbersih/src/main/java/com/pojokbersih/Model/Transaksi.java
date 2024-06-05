package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaksi {
    private static final String List = null;
    private final StringProperty idTransaksi = new SimpleStringProperty();
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
        idCustomer.set(list.get(1).toString());
        idStaff.set(list.get(2).toString());
        pic.set(list.get(3).toString());
        idProduk.set(list.get(4).toString());
        tanggalTransaksi.set(list.get(5).toString());
        tanggalPengerjaan.set(list.get(6).toString());
        biayaJasa.set(list.get(7).toString());
        status.set(list.get(8).toString());
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

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi.set(tanggalTransaksi);
    }

    public StringProperty tanggalPengerjaanProperty() {
        return tanggalPengerjaan;
    }

    public String getTanggalPengerjaan() {
        return tanggalPengerjaan.get();
    }

    public void setTanggalPengerjaan(String tanggalPengerjaan) {
        this.tanggalPengerjaan.set(tanggalPengerjaan);
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
}
